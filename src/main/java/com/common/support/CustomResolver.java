package com.common.support;

import com.common.annotations.XSS;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * @author super
 * Create time 2017/12/4 22:11
 */
@Component
public class CustomResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(XSS.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String parameterType = methodParameter.getParameterType().getTypeName();
        String name = methodParameter.getParameterName();
        String value = nativeWebRequest.getParameter(name);
        value = value + "customer resolver";
        return value;
    }
}
