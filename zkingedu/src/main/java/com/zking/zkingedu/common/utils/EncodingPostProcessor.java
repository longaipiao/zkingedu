package com.zking.zkingedu.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xingyuzhu on
 * 解决@ResponseBody返回的响应中中文乱码问题.
 */
@Component
public class EncodingPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException{
        if (bean instanceof RequestMappingHandlerAdapter) {
            List < HttpMessageConverter <  ?  >> convs = ((RequestMappingHandlerAdapter)bean).getMessageConverters();
            for (HttpMessageConverter <  ?  > conv : convs) {
                if (conv instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter)conv).setSupportedMediaTypes(
                            Arrays.asList(new MediaType("text", "html",
                                    Charset.forName("UTF-8"))));
                }
            }
        }
        if (bean instanceof RequestResponseBodyMethodProcessor) {
            List < HttpMessageConverter <  ?  >> convs = ((RequestMappingHandlerAdapter)bean).getMessageConverters();
            for (HttpMessageConverter <  ?  > conv : convs) {
                if (conv instanceof StringHttpMessageConverter) {
                    ((StringHttpMessageConverter)conv).setSupportedMediaTypes(
                            Arrays.asList(new MediaType("text", "html",
                                    Charset.forName("UTF-8"))));
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException{
        return bean;
    }
}