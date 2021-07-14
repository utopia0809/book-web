package service.impl;

import dao.impl.*;
import pojo.*;

import java.util.Date;

/**
 * @Description
 * @ClassName OrderServiceImpl
 * @PackageNmae service.impl
 * @Author Yanhao
 * @Date 2021/3/9 17:29
 * @Version 1.0
 */
public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private bookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);

        //遍历购物车中的每一个商品项转换成为订单保存到数据库
        for(CartItem cartItem : cart.getItems().values()){
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();


        return orderId;
    }
}
