package utils;

import org.apache.commons.beanutils.BeanUtils;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * @Description
 * @ClassName WebUtils
 * @PackageNmae utils
 * @Author Yanhao
 * @Date 2021/3/2 19:05
 * @Version 1.0
 */
public class WebUtils {
    public static void copyParamToBean(HttpServletRequest req , Object bean){
        try {
            BeanUtils.populate(bean,req.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
