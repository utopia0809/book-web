package test;

import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.List;

import static org.junit.Assert.*;

public class bookDaoTest {
    private BookDaoImpl bdi = new BookDaoImpl();
    @Test
    public void addBook() {
        int i = bdi.addBook(new Book(null, "数据结构", "12345", new BigDecimal(999), 1120, 0, null));
        System.out.println(i);
    }

    @Test
    public void deleteBookById() {
        int i = bdi.deleteBookById(20);
        System.out.println(i);
    }

    @Test
    public void updateBook() {
        int i = bdi.updateBook(new Book(21, "计算机组成原理", "12345", new BigDecimal(999), 1120, 0, null));
        System.out.println(i);
    }

    @Test
    public void queryBookById() {
        Book book = bdi.queryBookById(21);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bdi.queryBooks();
        bookList.forEach(System.out::println);
    }

    @Test
    public void queryForPageTotalCount(){
        Long integer = bdi.queryForPageTotalCount();
        System.out.println(integer);
    }

    @Test
    public void queryForPageItems(){
        List<Book> bookList = bdi.queryForPageItems(2, 4);
        bookList.forEach(System.out::println);
    }
}