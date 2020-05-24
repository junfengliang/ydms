package cn.genlei.ydms.global;

import cn.genlei.ydms.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author nid
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${server.servlet.context-path:/}")
    String contextPath;

    @Autowired
    TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(tokenInterceptor);
        registration.addPathPatterns("/**");
        List<String> list = new ArrayList<>();
        list.add("/user/login");
        list.add("/user/checkUsername");
        list.add("/user/sendVerifyCode");
        list.add("/user/resetPassword");
        list.add("/error");
        list.add("/swagger**");
        list.add("/webjars/**");

        registration.excludePathPatterns(list);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");
    }

}
