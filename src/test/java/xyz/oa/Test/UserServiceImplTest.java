package xyz.oa.Test;


import org.junit.Test;
import xyz.oa.entity.User;
import xyz.oa.service.UserService;
import xyz.oa.service.impl.UserServiceImpl;


public class UserServiceImplTest {
    private UserService userService =new UserServiceImpl();

    /**
     * 用户名不存在
     */
    @Test
    public void login1() {
        User t1 = userService.Login("T1", "123456","111","111");
        System.out.println(t1);
    }
    /**
     * 密码错误
     */
    @Test
    public void login2() {
        User t1 = userService.Login("T1", "123456","111","111");
        System.out.println(t1);
    }
    /**
     * 用户名密码正确的
     */
    @Test
    public void login3() {
        User t1 = userService.Login("T1", "123456","111","111");
        System.out.println(t1.getUsername());
    }
}
