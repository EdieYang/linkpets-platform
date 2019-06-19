package com.linkpets.handler;

import com.linkpets.configuration.InterceptorConfig;
import com.linkpets.annotation.ResponseResult;
import com.linkpets.result.DefaultErrorResult;
import com.linkpets.result.PlatformResult;
import com.linkpets.result.Result;
import com.linkpets.util.RequestContextHolderUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @desc 接口响应体处理器
 *
 * @author zhumaer
 * @since 4/1/2018 3:00 PM
 */
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest().getAttribute(InterceptorConfig.RESPONSE_RESULT);
        return responseResultAnn == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResultAnn = (ResponseResult) RequestContextHolderUtil.getRequest().getAttribute(InterceptorConfig.RESPONSE_RESULT);

        Class<? extends Result> resultClazz = responseResultAnn.value();

        if (resultClazz.isAssignableFrom(PlatformResult.class)) {
            if (body instanceof DefaultErrorResult) {
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult) body;
                PlatformResult result = new PlatformResult();
                result.setCode(Integer.valueOf(defaultErrorResult.getCode()));
                result.setMsg(defaultErrorResult.getMessage());
                result.setData(defaultErrorResult.getErrors());
                result.setSuccess(false);
                return result;
            }

            if(body instanceof  PlatformResult){
                return body;
            }

            return PlatformResult.success(body);
        }

        return body;
    }

}