package cn.genlei.ydms.global;

import cn.genlei.ydms.annotation.YdmsAuth;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.service.TokenService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.utils.SleepUtil;
import cn.genlei.ydms.vo.BaseVO;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author nid
 */
@Slf4j
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    TokenService tokenService;
    /**
     * only true wil continue，return false will cancel current request
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if("OPTIONS".equals(request.getMethod())){
            return true;
        }
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Annotation ana = method.getDeclaringClass().getAnnotation(YdmsAuth.class);
            if(ana==null){
                return true;
            }
        }else {
            return true;
        }

        SleepUtil.sleep();
        String url = request.getRequestURI();
        log.info("preHandle request url:{}",url);

        String token = request.getHeader("X-Token");
        if(StringUtils.isEmpty(token)){
            return loginError(response);
        }
        User user ;
        try{
            user= tokenService.getUserInfo(token);
        }catch (ExpiredJwtException e){
            return loginExpire(response);
        }
        if(user==null){
            // not login
            return loginError(response);
        }
        UserContextHolder.setCurrent(user);
        return true;
    }

    private boolean loginError(HttpServletResponse response) throws IOException {
        BaseVO vo = ReturnUtil.error(StatusCode.ILLEGAL_TOKEN,"illegal token");
        response.getWriter().write(JSON.toJSONString(vo));
        return false;
    }
    private boolean loginExpire(HttpServletResponse response) throws IOException {
        BaseVO vo = ReturnUtil.error(StatusCode.TOKEN_EXPIRED,"token expired");
        response.getWriter().write(JSON.toJSONString(vo));
        return false;
    }

}
