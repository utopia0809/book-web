package web;

import com.google.gson.Gson;
import pojo.User;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Description
 * @ClassName UserServlet
 * @PackageNmae web
 * @Author Yanhao
 * @Date 2021/3/2 9:53
 * @Version 1.0
 */
public class UserServlet extends BaseServlet{
    //处理登录
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserServiceImpl service = new UserServiceImpl();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loginUser = service.login(username , password);
        if(loginUser == null){
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req , resp);
        }else{
            //保存用户信息到Session域中
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req , resp);
        }
    }

    //处理注册
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        UserServiceImpl service = new UserServiceImpl();
        //获取Session中的验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = new User();
        WebUtils.copyParamToBean(req,user);
        System.out.println(user);


        if(token != null && token.equalsIgnoreCase(code)){
            if(service.existsUsername(username)){
                System.out.println("用户名已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req , resp);

                //把回显信息保存在request域中
                req.setAttribute("msg" , "用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
            }else{
                service.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req , resp);
            }
        }else{
            //把回显信息保存在request域中
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req , resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.getSession().invalidate();
        //重定向到首页(或登陆页面)
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        UserServiceImpl service = new UserServiceImpl();
        boolean existsUsername = service.existsUsername(username);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }
}
