package web;

import pojo.Book;
import service.impl.BookService;
import service.impl.BookServiceImpl;
import utils.WebUtils;
import pojo.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description
 * @ClassName BookServlet
 * @PackageNmae web
 * @Author Yanhao
 * @Date 2021/3/3 17:34
 * @Version 1.0
 */
public class BookServlet extends BaseServlet{
    private BookService service = new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo ;

        String no = req.getParameter("pageNo");
        if(no == null){
            pageNo = 1;
        }else{
            //总是将添加后的页面定位到最后一页
            pageNo = Integer.parseInt(no) + 1;
        }

        Book book = new Book();
        WebUtils.copyParamToBean(req,book);
        service.addBook(book);
        System.out.println(book);
        //重定向
        resp.sendRedirect(req.getContextPath()+"/manager/BookServlet?action=page&pageNo=" + pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        //将id的String类型转换为Integer类型
        int bookId = Integer.parseInt(id);
        service.deleteBookById(bookId);

        resp.sendRedirect(req.getContextPath()+"/manager/BookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        WebUtils.copyParamToBean(req,book);
        service.updateBook(book);

        resp.sendRedirect(req.getContextPath()+"/manager/BookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        //将id的String类型转换为Integer类型
        int bookId = Integer.parseInt(id);

        Book book = service.queryBookById(bookId);
        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = service.queryBooks();
        req.setAttribute("books",books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageSize = Page.PAGE_SIZE;
        int pageNo ;

        String no = req.getParameter("pageNo");
        if(no == null){
            pageNo = 1;
        }else{
            pageNo = Integer.parseInt(no);
        }

        Page<Book> page = service.page(pageNo,pageSize);
        page.setUrl("manager/BookServlet?action=page");
        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
