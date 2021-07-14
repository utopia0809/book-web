package service.impl;

import dao.impl.UserDaoImpl;
import pojo.User;

/**
 * @Description
 * @ClassName UserServiceImpl
 * @PackageNmae service.impl
 * @Author Yanhao
 * @Date 2021/2/26 18:26
 * @Version 1.0
 */
public class UserServiceImpl implements UserService{
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public int registUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(String username , String password) {
        return userDao.queryUserByUsernameAndPassword(username , password);
    }

    @Override
    public boolean existsUsername(String username) {
        return userDao.queryUserByUsername(username) != null;
    }
}
