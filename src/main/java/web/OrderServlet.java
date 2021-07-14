package web;

import pojo.Cart;
import pojo.User;
import service.impl.OrderService;
import service.impl.OrderServiceImpl;
import utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @ClassName OrderServlet
 * @PackageNmae web
 * @Author Yanhao
 * @Date 2021/3/9 18:08
 * @Version 1.0
 */
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        User loginUser = (User)req.getSession().getAttribute("user");
        //若还没有登录，则跳到登陆页面
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            //执行if内语句后if外的语句不需要再次执行
            return;
        }

        Integer userId = loginUser.getId();

        //调用orderservice.createOrder(Cart,Userid)生成订单
        String orderId = orderService.createOrder(cart,userId);

        //req.setAttribute("orderId",orderId);
        //req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
