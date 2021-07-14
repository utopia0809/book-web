package dao.impl;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import pojo.Book;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description
 * @ClassName BookDaoImpl
 * @PackageNmae dao.impl
 * @Author Yanhao
 * @Date 2021/3/3 15:49
 * @Version 1.0
 */
public class BookDaoImpl implements bookDao{
    @Override
    public int addBook(Book book) {
        Connection conn = null;
        int result = 0;
        try {
            QueryRunner runner = new QueryRunner();

            String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
            conn = JDBCUtils.getConnection();

            result = runner.update(conn, sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteBookById(Integer id) {
        Connection conn = null;
        int result = 0;
        try {
            QueryRunner runner = new QueryRunner();

            String sql = "delete from t_book where id = ?";
            conn = JDBCUtils.getConnection();

            result = runner.update(conn, sql , id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int updateBook(Book book) {
        Connection conn = null;
        int result = 0;
        try {
            QueryRunner runner = new QueryRunner();

            String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
            conn = JDBCUtils.getConnection();

            result = runner.update(conn, sql , book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Book queryBookById(Integer id) {
        Connection conn = null;
        Book book = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select * from t_book where id = ?";
            BeanHandler<Book> handler = new BeanHandler<>(Book.class);

            book = runner.query(conn, sql, handler, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        Connection conn = null;
        List<Book> bookList = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select * from t_book";
            BeanListHandler<Book> beanListHandler = new BeanListHandler<>(Book.class);
            bookList = runner.query(conn, sql, beanListHandler);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public Long queryForPageTotalCount() {
        Connection conn = null;
        Long count = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select count(*) from t_book";
            ScalarHandler handler = new ScalarHandler();

            QueryRunner runner = new QueryRunner();
            count = (Long)runner.query(conn, sql, handler);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public List<Book> queryForPageItems(Integer begin, Integer pageSize) {
        Connection conn = null;
        List<Book> pageItems = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book limit ?,?";
            BeanListHandler<Book> beanListHandler = new BeanListHandler<>(Book.class);

            pageItems = runner.query(conn, sql, beanListHandler , begin , pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return pageItems;
    }

    @Override
    public Long queryForPageTotalCountByPrice(int minPrice, int maxPrice) {
        Connection conn = null;
        Long count = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select count(*) from t_book where price between ? and ?";
            ScalarHandler handler = new ScalarHandler();

            QueryRunner runner = new QueryRunner();
            count = (Long)runner.query(conn, sql, handler,minPrice,maxPrice);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return count;
    }

    @Override
    public List<Book> queryForPageItemByPrice(int begin, int pageSize, int minPrice, int maxPrice) {
        Connection conn = null;
        List<Book> pageItems = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection();
            String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` from t_book where price between ? and ? order by price asc limit ?,?";
            BeanListHandler<Book> beanListHandler = new BeanListHandler<>(Book.class);

            pageItems = runner.query(conn , sql , beanListHandler , minPrice , maxPrice , begin , pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return pageItems;
    }
}
