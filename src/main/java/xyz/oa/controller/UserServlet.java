package xyz.oa.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.oa.controller.reflection.UserReflection;
import xyz.oa.entity.LeaveFrom;
import xyz.oa.entity.User;
import xyz.oa.service.Exception.BussinessException;
import xyz.oa.service.LeaveFromService;
import xyz.oa.service.UserService;
import xyz.oa.service.impl.LeaveFromServiceImpl;
import xyz.oa.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制层模块
 */
@WebServlet("/UserServlet")
public class UserServlet extends UserReflection {

    private UserService userService =new UserServiceImpl(); //注入业务层
    private Logger logger = LoggerFactory.getLogger(UserServlet.class); //日志打印
    private LeaveFromService leaveFromService = new LeaveFromServiceImpl();

    /**
     * 登录
     * @param request  接受用户输入信息
     * @param response 返回结果信息
     * @throws BussinessException  用户信息有误返回异常
     */
    public void Login(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //获取请求参数
        String vercode = request.getParameter("vercode"); //验证码
        String username = request.getParameter("username"); //用户名
        String password =request.getParameter("password"); //密码
        //获取上下文正确的验证码
        String vcode =(String)request.getServletContext().getAttribute("vcode");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //调用业务逻辑
            User user = userService.Login(username,password,vercode,vcode);
            //把查询出来的信息放入到Session会话中
            HttpSession session = request.getSession();
            session.setAttribute("Login_user",user);
            //返回信息
            map.put("code",200);
            map.put("message","success");
            map.put("redirect_url","index");
        }catch (BussinessException e){
            logger.error(e.getMessage(),e);
            map.put("code",e.getCode());
            map.put("message",e.getMsg());
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            map.put("code",e.getClass().getSimpleName());
            map.put("message",e.getMessage());
        }
        //序列化JSON
        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws IOException
     */
    public void logout(HttpServletRequest request , HttpServletResponse response) throws IOException {
        //清除session缓存
        request.getSession().invalidate();
        //重定向到登录页面
        response.sendRedirect("/login.html");
    }
    /**
     * 请假事项
     */
    public void Leave(HttpServletRequest request , HttpServletResponse response) throws IOException, ParseException {
        //设置字符集编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        SimpleDateFormat slf = new SimpleDateFormat("yyyy-MM-dd H:m:s"); //格式化工具类
        Map map = new HashMap(); //map对象
        //获取请求参数
        Integer formType = Integer.parseInt(request.getParameter("formType")); //请假类型
        Long employeeId = Long.valueOf(request.getParameter("employeeId")); //请假用户
        String endTime = request.getParameter("endTime"); //请假结束时间
        String startTime = request.getParameter("startTime"); //请假开始时间
        String reason = request.getParameter("reason"); //请假事由

        //格式化时间
        Date EndTime = slf.parse(endTime);
        Date StartTime = slf.parse(startTime);
        //实例化对象
        LeaveFrom leaveFrom = new LeaveFrom();
        leaveFrom.setEmployeeId(employeeId);
        leaveFrom.setFormType(formType);
        leaveFrom.setEndTime(EndTime);
        leaveFrom.setStartTime(StartTime);
        leaveFrom.setCreateTime(new Date());
        leaveFrom.setReason(reason);
        //执行插入
        try{
            leaveFromService.createLeaveFrom(leaveFrom);
            map.put("code",200);
            map.put("message","提交请假申请成功");
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            map.put("code",500);
            map.put("message",e.getMessage());
        }

        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }
    /**
     * 请假审批表格
     */
    public void ApproveTable(HttpServletRequest request , HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer limit =Integer.parseInt(request.getParameter("limit"));
        User user =(User)request.getSession().getAttribute("Login_user");
        Map map = new HashMap<>();
        PageHelper.startPage(page,limit);
        Page<Map> Pagelist = (Page)leaveFromService.SelectExamineFrom(user.getEmployeeId(), page, limit);
        map.put("code",0);
        map.put("count",Pagelist.getTotal());
        map.put("msg","");
        map.put("data",Pagelist.getResult());
        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }
    //审批流程
    public void audit(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        User user =(User)request.getSession().getAttribute("Login_user");
        Long OperatopId = user.getEmployeeId();
        String formId = request.getParameter("formId");
        String result = request.getParameter("result");
        String reason = request.getParameter("reason");
        Map map = new HashMap<>();
        try{
            leaveFromService.ApproveFrom(OperatopId,Long.valueOf(formId),result,reason);
            map.put("code",200);
            map.put("message","审批成功");

        }catch (BussinessException e){
            logger.error(e.getMessage(),e);
            map.put("code",e.getCode());
            map.put("message",e.getMsg());
        }catch (Exception e){
            logger.error(e.getMessage());
            map.put("code",e.getClass().getSimpleName());
            map.put("message",e.getMessage());
        }
        String json = JSON.toJSONString(map);
        response.getWriter().println(json);
    }

}
