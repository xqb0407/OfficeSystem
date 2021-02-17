package xyz.oa.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import xyz.oa.dao.EmployeeDao;
import xyz.oa.dao.LeaveFromDao;
import xyz.oa.dao.ProcessFlowDao;
import xyz.oa.dao.impl.EmployeeDaoImpl;
import xyz.oa.dao.impl.LeaveFromDaoImpl;
import xyz.oa.dao.impl.ProcessFlowDaoImpl;
import xyz.oa.entity.Employee;
import xyz.oa.entity.LeaveFrom;
import xyz.oa.entity.ProcessFlow;
import xyz.oa.service.Exception.BussinessException;
import xyz.oa.service.LeaveFromService;
import xyz.oa.utils.BussinessContentsFinal;
import xyz.oa.utils.MybatisUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 请求业务
 */
public class LeaveFromServiceImpl implements LeaveFromService {
    private EmployeeDao employeeDao= new EmployeeDaoImpl();
    private LeaveFromDao leaveFromDao =new LeaveFromDaoImpl();
    private  ProcessFlowDao processFlowDao = new ProcessFlowDaoImpl();

    /**
     * 创建请假单
     * @param leaveFrom
     * @return
     *  1、持久化leaveFrom表单数据，8级以下员工表单状态为processing，8级（总经理）状态为approved
     *  2、增加一条流程数据，说明表单已经提交，状态为complete
     *  3、分情况创建其余流程数据
     *  3.1、 7级以下员工，生成部门经理审批任务，请假时间大于36小时，还需要总经理审批任务
     *  3.2、 7级员工，生成总经理审批任务
     *  3.3、 8级员工生成总经理审批任务，系统自动通过
     */
    @Override
    public LeaveFrom createLeaveFrom(LeaveFrom leaveFrom) {
        LeaveFrom saveFrom =(LeaveFrom)MybatisUtils.executeUpdate(sqlSession -> {
            //查询用户等级
            Employee employee = employeeDao.SelectEmpById(leaveFrom.getEmployeeId());
            //持久化leaveFrom表单数据，8级以下员工表单状态为processing，8级（总经理）状态为approved
            if(employee.getLevel() < 8 ){
                leaveFrom.setState("processing");
            }else{
                leaveFrom.setState("approved");
            }
            //执行插入LeaveFrom 请假数据
            sqlSession.getMapper(LeaveFromDao.class).Insert(leaveFrom);
            //增加一条流程数据，说明表单已经提交，状态为complete
            ProcessFlow flow1 = new ProcessFlow();
            flow1.setFormId(leaveFrom.getFormId());
            flow1.setOperatopId(employee.getEmployeeId());
            flow1.setAction("apply");
            flow1.setCreateTime(new Date());
            flow1.setOrderNo(1);
            flow1.setState("complete");
            flow1.setIsLast(0);
            ProcessFlowDao processFlowMapper = sqlSession.getMapper(ProcessFlowDao.class);
            processFlowMapper.Insert(flow1);
            //7级以下员工，生成部门经理审批任务，请假时间大于36小时，还需要总经理审批任务
            if (employee.getLevel() < 7){
                Employee dmanager = employeeDao.SelectLeader(employee); //查询当前所在部门的经理
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(leaveFrom.getFormId());
                flow2.setOperatopId(dmanager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setOrderNo(2);
                flow2.setState("process");
                //时间转成毫秒
                Long leaveTime = leaveFrom.getEndTime().getTime() - leaveFrom.getStartTime().getTime();
                //毫秒转成小时
                float hours = leaveTime/(1000*60*60) * 1f;
                //请假时间大于36小时
                if (hours > BussinessContentsFinal.LEAVE_TIME_HOURS){
                    //执行插入
                    flow2.setIsLast(0);
                    processFlowMapper.Insert(flow2);
                    ProcessFlow flow3 = new ProcessFlow();
                    flow3.setFormId(leaveFrom.getFormId());
                    //查询部门经理的上一级
                    Employee manager = employeeDao.SelectLeader(dmanager);
                    flow3.setOperatopId(manager.getEmployeeId());
                    flow3.setAction("audit");
                    flow3.setCreateTime(new Date());
                    flow3.setState("ready");
                    flow3.setOrderNo(3);
                    flow3.setIsLast(1);
                    processFlowMapper.Insert(flow3);
                }else{
                    flow2.setIsLast(1);
                    processFlowMapper.Insert(flow2);
                }

            }else if(employee.getLevel() == 7){
                // 7级员工，生成总经理审批任务
                Employee manager = employeeDao.SelectLeader(employee);
                ProcessFlow flow4 = new ProcessFlow();
                flow4.setFormId(leaveFrom.getFormId());
                flow4.setOperatopId(manager.getEmployeeId());
                flow4.setAction("audit");
                flow4.setCreateTime(new Date());
                flow4.setOrderNo(2);
                flow4.setState("process");
                flow4.setIsLast(1);
                processFlowMapper.Insert(flow4); //数据插入

            }else if (employee.getLevel() == 8){
                // 8级员工生成总经理审批任务，系统自动通过
                ProcessFlow flow5 = new ProcessFlow();
                flow5.setFormId(leaveFrom.getFormId());
                flow5.setOperatopId(employee.getEmployeeId());
                flow5.setAction("audit");
                flow5.setResult("approved");
                flow5.setReason("系统自动同意");
                flow5.setCreateTime(new Date());
                flow5.setAuditTime(new Date());
                flow5.setState("complete");
                flow5.setOrderNo(2);
                flow5.setIsLast(1);
                processFlowMapper.Insert(flow5); //数据插入
            }
            return leaveFrom;
        });
        return saveFrom;
    }

    /**
     * 查询审批列表
     * @param employeeId
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Map> SelectExamineFrom(Long employeeId,Integer page ,Integer limit) {
        PageHelper.startPage(page,limit);
        Page<Map> pagelist = (Page)leaveFromDao.SelectByParems("process", employeeId);
        // System.out.println(pagelist.getTotal());
        return pagelist;
    }
    /**
     * 审批流程
     * 1、无论同意还是驳回/当前任务节点更为complet
     * 2、如果当前任务是最后一个节点，代表流程结束，更新请假表单状态为approved/refused
     * 3、如果当前任务节点不是最后一个节点且审批通过，那下一个节点状态ready，变为process
     * 4、如果当前节点不是最后一个节点且审批驳回,则后续节点变为cancel ，请假表单状态为refused
     */
    @Override
    public void ApproveFrom(Long operatopId, Long formId, String result, String reason) {
        MybatisUtils.executeUpdate(sqlSession -> {
            // 1、无论同意还是驳回当前任务节点更为complet
            List<ProcessFlow> processFlows = processFlowDao.selectByFromId(formId);
            if (processFlows.size() == 0 ){
                throw  new BussinessException("SP001","无效审批");
            }
            //遍历拉姆达表达式
            List<ProcessFlow> processList = processFlows.stream().filter(p -> p.getOperatopId() == operatopId && p.getState().equals("process")).collect(Collectors.toList());
            ProcessFlow processFlow = null;
            if (processList.size()==0){
                throw new BussinessException("SP002","未找到待处理任务");
            }else {
                processFlow = processList.get(0); //遍历后赋值给processFlow对象
                processFlow.setState("complete");
                processFlow.setResult(result);
                processFlow.setReason(reason);
                processFlow.setAuditTime(new Date());
                processFlowDao.updateProcess(processFlow);
            }
            LeaveFrom leaveFrom = leaveFromDao.SelectLeaveFromByFormId(formId); //获取请假表单
            //2、如果当前任务是最后一个节点，代表流程结束，更新请假表单状态为approved/refused
            if(processFlow.getIsLast() == 1){
                leaveFrom.setState(result);
                leaveFromDao.UpdateFromLeave(leaveFrom);
            }else{
                List<ProcessFlow> readyProcessList = processFlows.stream().filter(p -> p.getState().equals("ready")).collect(Collectors.toList());
                // 3、如果当前任务节点不是最后一个节点且审批通过，那下一个节点状态ready，变为process
                if (result.equals("approved")){
                    ProcessFlow readyFlow = readyProcessList.get(0);
                    readyFlow.setState("process");
                    processFlowDao.updateProcess(readyFlow);
                }else if (result.equals("refused")){
                // 4、如果当前节点不是最后一个节点且审批驳回,则后续节点变为cancel ，请假表单状态为refused
                    for (ProcessFlow process:readyProcessList) {
                        process.setState("cancel");
                        processFlowDao.updateProcess(process);
                    }
                    leaveFrom.setState("refused");
                    leaveFromDao.UpdateFromLeave(leaveFrom);
                }
            }
            return null;
        });


    }


}
