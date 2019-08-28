package com.linkpets.configuration;

import com.linkpets.annotation.ResponseResult;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    public static final String RESPONSE_RESULT = "RESPONSE-RESULT";


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        //响应结果控制拦截
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if (handler instanceof HandlerMethod) {
                    final HandlerMethod handlerMethod = (HandlerMethod) handler;
                    final Class<?> clazz = handlerMethod.getBeanType();
                    final Method method = handlerMethod.getMethod();
                    if (clazz.isAnnotationPresent(ResponseResult.class)) {
                        request.setAttribute(RESPONSE_RESULT, clazz.getAnnotation(ResponseResult.class));
                    } else if (method.isAnnotationPresent(ResponseResult.class)) {
                        request.setAttribute(RESPONSE_RESULT, method.getAnnotation(ResponseResult.class));
                    }
                }

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

            }
        }).addPathPatterns(apiUri);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*");

    }

}