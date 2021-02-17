package xyz.oa.utils;

import xyz.oa.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局过滤器
 */
public class FilterUtils implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request =(HttpServletRequest) req;
        HttpServletResponse response =(HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        User login_user = (User)request.getSession().getAttribute("Login_user");
        String spath = request.getServletPath();
        String[] urls = {"/login.html","/json",".woff",".ttf",".eot",".woff2",".js",".css",".ico",".jpg",".png","/verifyServlet","/UserServlet"};
        boolean flag = true;
        for (String str : urls) {
            if (spath.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }
        if (flag) {
            if (login_user == null) {
                System.out.println(login_user);
                response.sendRedirect("/login.html");
            }else {
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
