package test;

import dao.impl.BookDaoImpl;
import dao.impl.bookDao;
import org.junit.Test;
import pojo.Book;

import java.util.List;

/**
 * @Description
 * @ClassName BookDaoTest
 * @PackageNmae test
 * @Author Yanhao
 * @Date 2021/3/6 17:08
 * @Version 1.0
 */
public class BookDaoTest {
    bookDao bookDao= new BookDaoImpl();

    @Test
    public void test(){
        List<Book> bookList = bookDao.queryForPageItemByPrice(2, 4, 10, 100);
        System.out.println(bookList);
    }

    @Test
    public void test1(){
        Long aLong = bookDao.queryForPageTotalCountByPrice(10, 100);
        System.out.println(aLong);

    }
}
