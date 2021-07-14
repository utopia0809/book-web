package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName BaseServlet
 * @PackageNmae web
 * @Author Yanhao
 * @Date 2021/3/2 17:56
 * @Version 1.0
 */
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        try {
            //getDeclaredMethod()需要两个参数，第一个参数需要指明方法名，第二个参数需要指明形参的类型
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println(method);
            //invoke()需要两个参数，第一个参数指明调用该方法的对象实例(如果为静态方法，可以为null,相当于【类.静态方法】进行调用),第二个参数则是方法需要的参数
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛出异常
        }
    }
}
