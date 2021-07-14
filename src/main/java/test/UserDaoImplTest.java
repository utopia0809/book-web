package test;

import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

/**
 * @Description
 * @ClassName UserDaoImplTest
 * @PackageNmae test
 * @Author Yanhao
 * @Date 2021/2/26 17:33
 * @Version 1.0
 */
public class UserDaoImplTest {
    @Test
    public void test(){
        UserDaoImpl userDao = new UserDaoImpl();
        int i = userDao.saveUser(new User(1, "123458", "789356", "123446"));
        System.out.println(i);
    }

    @Test
    public void test1(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsername("123456");
        System.out.println(user);
    }

    @Test
    public void test2(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.queryUserByUsernameAndPassword("123456","789456");
        System.out.println(user);
    }
}
