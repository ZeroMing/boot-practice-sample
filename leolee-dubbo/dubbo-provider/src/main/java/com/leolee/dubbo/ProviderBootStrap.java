package com.leolee.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年11月24 12时26分
 */
public class ProviderBootStrap {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-provider.xml");
        context.start();
        System.out.println("执行...");
        // 按任意键退出
        System.in.read();
    }
}
