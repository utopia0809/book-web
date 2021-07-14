package dao.impl;

import pojo.User;

/**
 * @Description
 * @ClassName userDao
 * @PackageNmae dao.impl
 * @Author Yanhao
 * @Date 2021/2/26 16:57
 * @Version 1.0
 */
public interface userDao {
    /**
    *@Description 根据用户名查找用户信息
    *@Author Yanhao
    */
    public User queryUserByUsername(String username);

    /**
    *@Description  根据用户名和密码查询用户信息
    *@Author Yanhao
    */
    public User queryUserByUsernameAndPassword(String username , String password);

    /**
    *@Description  保存用户信息
    *@Author Yanhao
    */
    public int saveUser(User user);
}
