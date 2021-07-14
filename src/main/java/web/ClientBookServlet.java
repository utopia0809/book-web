package web;

import pojo.Book;
import pojo.Page;
import service.impl.BookService;
import service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @ClassName ClientBookServlet
 * @PackageNmae web
 * @Author Yanhao
 * @Date 2021/3/5 17:30
 * @Version 1.0
 */
public class ClientBookServlet extends BaseServlet{
    private BookService service = new BookServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = Page.PAGE_SIZE;
        int pageNo;

        String no = req.getParameter("pageNo");
        if(no == null){
            pageNo = 1;
        }else{
            pageNo = Integer.parseInt(no);
        }

        Page<Book> page = service.page(pageNo,pageSize);
        page.setUrl("client/BookServlet?action=page");
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = Page.PAGE_SIZE;
        int pageNo;
        int minPrice;
        int maxPrice;

        String no = req.getParameter("pageNo");
        String min = req.getParameter("min");
        String max = req.getParameter("max");
        if(no == null ){
            pageNo = 1;
        }else{
            pageNo = Integer.parseInt(no);
        }

        if(min == null){
            minPrice = 0;
        }else{
            minPrice = Integer.parseInt(min);
        }

        if(max == null){
            maxPrice = Integer.MAX_VALUE;
        }else{
            maxPrice = Integer.parseInt(max);
        }

        Page<Book> page = service.pageByPrice(pageNo,pageSize,minPrice,maxPrice );

        StringBuilder sb = new StringBuilder("client/BookServlet?action=pageByPrice");
        if(req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
