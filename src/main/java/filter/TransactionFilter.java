package filter;

import utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description
 * @ClassName TransactionFilter
 * @PackageNmae filter
 * @Author Yanhao
 * @Date 2021/3/12 20:29
 * @Version 1.0
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtils.rollbackAndClose();
            throw new RuntimeException(e); //把异常抛给tomcat服务器
        }
    }

    @Override
    public void destroy() {

    }
}
