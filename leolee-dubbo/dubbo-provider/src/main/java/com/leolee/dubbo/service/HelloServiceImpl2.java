package com.leolee.dubbo.service;

import com.leolee.dubbo.api.IHelloService;

/**
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年11月24 14时46分
 */
public class HelloServiceImpl2 implements IHelloService {
    @Override
    public String sayHello(String userName) {
        return "hello！Version2.0.0"+ userName;
    }
}
