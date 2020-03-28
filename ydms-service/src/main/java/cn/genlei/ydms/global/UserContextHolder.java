package cn.genlei.ydms.global;

import cn.genlei.ydms.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
/**
 *  User context holder
 * @author nid
 */
public class UserContextHolder {

    /**
     * The constant USER_IN_SESSION.
     */
    public static final String USER_IN_SESSION="user";

    /**
     * Get request http servlet request.
     *
     * @return the http servlet request
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Sets current.
     *
     * @param current the current
     */
    public static void setCurrent(User current) {
        getRequest().getSession().setAttribute(USER_IN_SESSION,current);
    }

    /**
     * Get current user.
     *
     * @return user
     */
    public static User getCurrent(){
        return (User) getRequest().getSession().getAttribute(USER_IN_SESSION);
    }

    /**
     * Remove current user.
     */
    public static void removeCurrent(){
        getRequest().getSession().removeAttribute(USER_IN_SESSION);
    }

}