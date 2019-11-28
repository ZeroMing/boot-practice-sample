package com.leolee.dubbo.service;

import com.leolee.dubbo.api.IHelloService;

/**
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年11月24 12时11分
 */
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String userName) {
        return "hello！ "+ userName;
    }
}
