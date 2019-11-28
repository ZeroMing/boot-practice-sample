package com.leolee.dubbo;

import com.leolee.dubbo.api.IHelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年11月24 12时12分
 */
public class ConsumerBootStrap {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-consumer.xml");
        context.start();
        // 获取远程服务代理
        IHelloService demoService = (IHelloService)context.getBean("helloService");
        // 执行远程方法
        String hello = demoService.sayHello("world");
        // 显示调用结果
        System.err.println( hello );
    }

}
