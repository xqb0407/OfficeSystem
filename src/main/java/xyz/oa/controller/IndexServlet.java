package xyz.oa.controller;

import xyz.oa.entity.Department;
import xyz.oa.entity.Employee;
import xyz.oa.entity.Node;
import xyz.oa.entity.User;
import xyz.oa.service.RbacService;
import xyz.oa.service.UserService;
import xyz.oa.service.impl.RbacServiceImpl;
import xyz.oa.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    //注入业务逻辑层
    private RbacService rbacService =new RbacServiceImpl();
    private UserService userService = new UserServiceImpl();

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =(User) request.getSession().getAttribute("Login_user");
        List<Node> nodeList = rbacService.SelectNodeByUserId(user.getUserId()); //查询用户角色拥有的菜单
        Employee employee = userService.InfoByEmployee(user.getEmployeeId()); //查询用户登录信息
        Long userNum = userService.countUser(); //查询总用户数量
        Department department = userService.SelectBydeparTmentId(employee.getDepartmentId());//查询用户所在的部门

        //查询用户总数量
        request.getSession().setAttribute("node_list",nodeList); //菜单放入到请求当中去
        request.getSession().setAttribute("current_User",employee);
        request.getSession().setAttribute("department",department);
        request.getSession().setAttribute("userNum",userNum);
        // request.getSession().setAttribute("url","/pageServlet?page=Console");
        request.getRequestDispatcher("index.ftl").forward(request,response);
    }


}
