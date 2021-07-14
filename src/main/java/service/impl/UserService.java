package service.impl;

import pojo.User;

/**
 * @Description
 * @ClassName UserService
 * @PackageNmae service.impl
 * @Author Yanhao
 * @Date 2021/2/26 18:22
 * @Version 1.0
 */
public interface UserService {
    /**
    *@Description  注册用户
    *@Author Yanhao
    */
    public int registUser(User user);

    /**
    *@Description  登录
    *@Author Yanhao
     * @return
    */
    public User login(String username , String password);

    /**
    *@Description 检查用户名是否可用
    *@Author Yanhao
    */
    public boolean existsUsername(String username);
}
