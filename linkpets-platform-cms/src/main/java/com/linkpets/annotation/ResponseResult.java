package com.linkpets.annotation;

import com.linkpets.result.PlatformResult;
import com.linkpets.result.Result;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author Edie
 * @desc 接口返回结果增强  会通过拦截器拦截后放入标记，在ResponseResultHandler 进行结果处理
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {

    Class<? extends Result> value() default PlatformResult.class;

}
