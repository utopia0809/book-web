package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.impl.BookService;
import service.impl.BookServiceImpl;

/**
 * @Description
 * @ClassName BookServiceTest
 * @PackageNmae test
 * @Author Yanhao
 * @Date 2021/3/4 17:26
 * @Version 1.0
 */
public class BookServiceTest {
    BookService bs = new BookServiceImpl();

    @Test
    public void test(){
        Page<Book> page = bs.page(2, 4);
        System.out.println(page);
    }

    @Test
    public void test1(){
        Page<Book> bookPage = bs.pageByPrice(1, 4, 0, 100);
        //bookPage.getItems().forEach(System.out::println);
    }

}
