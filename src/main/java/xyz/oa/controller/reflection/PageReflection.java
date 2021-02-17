package xyz.oa.controller.reflection;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 页面操作进入反射
 */
public class PageReflection extends HttpServlet {
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response =(HttpServletResponse)res;
        //获取用户请求的方法名
        String methodName = request.getParameter("page");
        // String methodName=request.getRequestURI();
        Class<?> aClass = this.getClass();
        try {
            //获得方法名 反射调用对应的方法
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
