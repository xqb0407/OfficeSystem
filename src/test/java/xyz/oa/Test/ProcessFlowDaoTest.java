package xyz.oa.Test;

import org.junit.Test;
import xyz.oa.dao.ProcessFlowDao;
import xyz.oa.dao.impl.ProcessFlowDaoImpl;
import xyz.oa.entity.ProcessFlow;
import xyz.oa.utils.MybatisUtils;

import java.util.Date;
import java.util.List;

public class ProcessFlowDaoTest {
    private ProcessFlowDao processFlowDao = new ProcessFlowDaoImpl();
    @Test
    public void insert() {
        MybatisUtils.executeUpdate(sqlSession -> {
            ProcessFlow processFlow =new ProcessFlow();
            processFlow.setFormId(4L);
            processFlow.setOperatopId(4L);
            processFlow.setAction("apply");
            processFlow.setResult(null);
            processFlow.setReason(null);
            processFlow.setCreateTime(new Date());
            processFlow.setAuditTime(new Date());
            processFlow.setOrderNo(1);
            processFlow.setState("complete");
            processFlow.setIsLast(0);
            sqlSession.getMapper(ProcessFlowDao.class).Insert(processFlow);
            return null;
        });
    }
    @Test
    public void SelectByFromId(){
        List<ProcessFlow> processFlows = processFlowDao.selectByFromId(13l);
        System.out.println(processFlows.get(0).getFormId());
    }
}