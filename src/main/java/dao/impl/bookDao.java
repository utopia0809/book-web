package dao.impl;

import pojo.Book;

import java.util.List;

public interface bookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    public Long queryForPageTotalCount();

    List<Book> queryForPageItems(Integer begin , Integer pageSize);

    Long queryForPageTotalCountByPrice(int minPrice, int maxPrice);

    List<Book> queryForPageItemByPrice(int begin , int pageSize , int min , int max);
}
