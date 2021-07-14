package web;

import com.google.gson.Gson;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.impl.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName CartServlet
 * @PackageNmae web
 * @Author Yanhao
 * @Date 2021/3/8 12:02
 * @Version 1.0
 */
public class CartServlet extends  BaseServlet{
    private BookService service = new BookServiceImpl();
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数 商品编号
        //调用bookService.queryBookById(id):Book得到的图书信息
        //把图书信息，转换为CartItem商品项
        //调用Cart.addItem(CartItem):添加商品项
       int id;
       String bookId = req.getParameter("id");
       if (bookId == null) {
            id = 0;
       }else{
            id = Integer.parseInt(bookId);
       }

        Book book = service.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);
        //最后一个添加的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        //返回购物车总的商品数和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        String bookId = req.getParameter("id");

        if(bookId == null){
            id = 0;
        }else{
            id = Integer.parseInt(bookId);
        }

        //获取购物车对象
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if(cart != null){
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        int count;
        String bookId = req.getParameter("id");
        String bookCount = req.getParameter("count");

        if(bookId == null){
            id = 0;
        }else{
            id = Integer.parseInt(bookId);
        }

        if(bookCount == null){
            count = 1;
        }else{
            count = Integer.parseInt(bookCount);
        }

        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
            //重定向回原来的页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
