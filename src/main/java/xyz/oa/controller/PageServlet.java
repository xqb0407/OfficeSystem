package xyz.oa.controller;

import xyz.oa.controller.reflection.PageReflection;
import xyz.oa.service.RbacService;
import xyz.oa.service.UserService;
import xyz.oa.service.impl.RbacServiceImpl;
import xyz.oa.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 负责页面转发
 */
@WebServlet("/pageServlet")
public class PageServlet extends PageReflection {
    //注入业务逻辑层
    private RbacService rbacService =new RbacServiceImpl();
    private UserService userService = new UserServiceImpl();

    public void Console(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/console.ftl").forward(request,response);
    }
    public void leaveFrom(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/leaveFrom.ftl").forward(request,response);

    }

}
