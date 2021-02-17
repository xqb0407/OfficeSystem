package xyz.oa.utils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class FlowUtils implements ServletRequestListener {



    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //初始化监听器
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String uri = request.getRequestURI();
        String[] urls = {"/login.html","/json",".js",".css",".ico",".jpg",".png","/verifyServlet",".woff2"};
        String spath = request.getServletPath();
        boolean flag = true;
        for (String str : urls) {
            if (spath.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }
        if (!flag){
            return;
        }


    }
}
