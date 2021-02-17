package xyz.oa.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/verifyServlet", loadOnStartup = 1)
public class VerifyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        //生成验证码
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 36, 4, 4);
        ServletOutputStream outputStream = resp.getOutputStream();
        captcha.write(outputStream);
        System.out.println("验证码："+captcha.getCode());
        //应该把正确验证码存到上下文
        req.getServletContext().setAttribute("vcode",captcha.getCode());
        outputStream.close();
    }
}
