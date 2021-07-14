package service.impl;

import dao.impl.BookDaoImpl;
import dao.impl.bookDao;
import pojo.Book;
import pojo.Page;

import java.util.List;

/**
 * @Description
 * @ClassName BookServiceImpl
 * @PackageNmae service.impl
 * @Author Yanhao
 * @Date 2021/3/3 17:29
 * @Version 1.0
 */
public class BookServiceImpl implements BookService {
    private bookDao bookDao= new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(Integer pageNo, Integer pageSize) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        page.setPageNo(pageNo);

        Long pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Long pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //页面边界检查
        if(pageNo < 0){
            page.setPageNo(1);
        }
        if(pageNo > pageTotal){
            page.setPageNo(pageTotal.intValue());
        }

        List<Book> items = bookDao.queryForPageItems((page.getPageNo()-1)*pageSize , pageSize);
        //设置当前页数据
        page.setItems(items);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int minPrice, int maxPrice) {
        Page<Book> page = new Page<>();

        page.setPageSize(pageSize);
        page.setPageNo(pageNo);

        Long pageTotalCount = bookDao.queryForPageTotalCountByPrice(minPrice,maxPrice);
        page.setPageTotalCount(pageTotalCount);

        Long pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //页面边界检查
        if(pageNo < 0){
            page.setPageNo(1);
        }
        if(pageNo > pageTotal){
            page.setPageNo(pageTotal.intValue());
        }

        List<Book> items = bookDao.queryForPageItemByPrice((page.getPageNo()-1)*pageSize , pageSize,minPrice,maxPrice);
        //设置当前页数据
        page.setItems(items);
        return page;
    }
}
