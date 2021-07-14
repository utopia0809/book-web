package test;

import org.junit.Test;
import pojo.User;
import service.impl.UserServiceImpl;

/**
 * @Description
 * @ClassName UserServiceTest
 * @PackageNmae test
 * @Author Yanhao
 * @Date 2021/2/27 19:54
 * @Version 1.0
 */
public class UserServiceTest {
    UserServiceImpl usi = new UserServiceImpl();

    @Test
    public void test(){
        int user = usi.registUser(new User(null, "147895", "dfgtre", "bgvfder"));
        System.out.println(user);
    }
}
