package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description
 * @ClassName JDBCUtils
 * @PackageNmae utils
 * @Author Yanhao
 * @Date 2021/2/26 15:54
 * @Version 1.0
 */
public class JDBCUtils {
    private static DruidDataSource source = null;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();


    static {
        try {
            Properties pros = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);

            source = (DruidDataSource) DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = source.getConnection();
                conns.set(conn); //保存ThreadLocal对象中，供后面的dbc操作使用
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
            }
        }
        return conn;
    }

    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){ //如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.commit(); //提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DbUtils.closeQuietly(conn); //关闭连接，
            }
        }
        //一定要执行remove操作，否则就会出错(因为tomcat会使用线程池技术)
        conns.remove();
    }


    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn != null){ //如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.rollback(); //回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DbUtils.closeQuietly(conn); //关闭连接，
            }
        }
        //一定要执行remove操作，否则就会出错(因为tomcat会使用线程池技术)
        conns.remove();
    }





}
