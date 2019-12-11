package com.leolee.controller;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年12月11 22时13分
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public HttpMessageConverter<String> responseBodyConverter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(60 * 1000L);
        configurer.registerCallableInterceptors(timeoutInterceptor());
        configurer.setTaskExecutor(threadPoolTaskExecutor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        // 核心线程数
        t.setCorePoolSize(10);
        // 最大线程数
        t.setMaxPoolSize(100);
        // 阻塞队列最大值
        t.setQueueCapacity(100);
        t.setThreadNamePrefix("LeoLee-Async-");
        t.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return t;
    }
}

