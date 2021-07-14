package test;

import org.junit.Test;
import pojo.Book;
import service.impl.BookService;
import service.impl.BookServiceImpl;
import web.BookServlet;

import java.util.List;

/**
 * @Description
 * @ClassName UserServletTest
 * @PackageNmae test
 * @Author Yanhao
 * @Date 2021/3/3 18:42
 * @Version 1.0
 */
public class UserServletTest {
    BookService bs = new BookServiceImpl();
    @Test
    public void test(){
        List<Book> bookList = bs.queryBooks();
        System.out.println(bookList);
    }
}
