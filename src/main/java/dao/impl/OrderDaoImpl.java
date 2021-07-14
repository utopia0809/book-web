package dao.impl;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.Order;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Handler;

/**
 * @Description
 * @ClassName OrderDaoImpl
 * @PackageNmae dao.impl
 * @Author Yanhao
 * @Date 2021/3/8 22:12
 * @Version 1.0
 */
public class OrderDaoImpl implements OrderDao{
    @Override
    public int saveOrder(Order order) {
        Connection conn = null;
        int result = 0;
        try {
            String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values (?,?,?,?,?)";
            conn = JDBCUtils.getConnection();

            QueryRunner runner = new QueryRunner();
            result = runner.update(conn, sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
