package dao.impl;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.User;
import utils.JDBCUtils;

import java.sql.Connection;

/**
 * @Description
 * @ClassName UserDaoImpl
 * @PackageNmae dao.impl
 * @Author Yanhao
 * @Date 2021/2/26 17:04
 * @Version 1.0
 */
public class UserDaoImpl implements userDao {
    @Override
    public User queryUserByUsername(String username) {
        Connection conn = null;
        User user = null;
        try {
            QueryRunner runner = new QueryRunner();

            String sql = "select * from t_user where username = ? ";
            conn = JDBCUtils.getConnection();
            BeanHandler<User> handler = new BeanHandler<>(User.class);

            user = runner.query(conn, sql, handler,username);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        User user = null;
        try {
            QueryRunner runner = new QueryRunner();

            String sql = "select * from t_user where username = ? and password = ?";
            conn = JDBCUtils.getConnection();
            BeanHandler<User> handler = new BeanHandler<>(User.class);

            user = runner.query(conn, sql, handler, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public int saveUser(User user) {
        Connection conn = null;
        int result = 0;
        try {
            QueryRunner runner = new QueryRunner();

            String sql = "insert into t_user(username,password,email) values(?,?,?)";
            conn = JDBCUtils.getConnection();

            result = runner.update(conn,sql,user.getUsername(),user.getPassword(),user.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
