package dao.impl;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import pojo.OrderItem;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Description
 * @ClassName OrderItemDaoImpl
 * @PackageNmae dao.impl
 * @Author Yanhao
 * @Date 2021/3/8 22:23
 * @Version 1.0
 */
public class OrderItemDaoImpl implements OrderItemDao{

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        Connection conn = null;
        int result = 0;
        try {
            String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values (?,?,?,?,?)";
            conn = JDBCUtils.getConnection();

            QueryRunner runner = new QueryRunner();
            result = runner.update(conn, sql, orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
