package xyz.oa.controller;

import com.alibaba.fastjson.JSON;
import xyz.oa.entity.Node;
import xyz.oa.entity.User;
import xyz.oa.service.RbacService;
import xyz.oa.service.impl.RbacServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 渲染菜单栏
 */
@WebServlet("/navs")
public class Navs extends HttpServlet {
    private RbacService rbacService =new RbacServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =(User) request.getSession().getAttribute("Login_user");
        List<Node> nodeList = rbacService.SelectNodeByUserId(user.getUserId());
        response.setContentType("application/json;charset=utf-8");
        String json = JSON.toJSONString(nodeList);
        response.getWriter().println(json);
    }
}
