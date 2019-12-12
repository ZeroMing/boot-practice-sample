package com.leolee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.context.request.async.WebAsyncUtils;

import java.util.concurrent.*;

/**
 * @author: zeroming@163.com
 * @version:
 * @date: 2019年12月11 21时45分
 */
@RestController
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/index")
    public WebAsyncTask<String> index(String clientId){
        // 1. 将clientId作为key，空字符串或者特殊的字符串作为value，存入redis,保证线程安全。
        // SETNX ,返回0，说明已经存在请求，返回请求失败。如果返回为1，继续
        // 将设置redis的key放在这里,如果失败，直接拒绝请求，可以避免过多的异步线程资源

        // 2. 开启有线程处理任务
        WebAsyncTask<String> asyncTask = new WebAsyncTask<>(10 * 1000L, new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 3. 根据clientId获取通道，下发指令
                System.out.println(Thread.currentThread().getName());
                String msg = "";

                /*// 1. 该循环，用于实时将key设置到redis中，多请求存在竞争
                // 将设置key放在这个位置：优点:避免发生冲突之后，客户端立即返回false;缺点: 不论有没有冲突，每次都会单独开启一个异步线。
                while (true){
                    if("setnx == 1".equals("setnx == 1")){
                        break;
                    }
                }*/

                // 4. 该轮询用于，阻塞获取数据,轮询redis中的key的值，直到获取到的值不为空且不为空字符串
                Integer num = 0;
                while (true){
                    Thread.sleep(1000);
                    System.out.println(clientId + "轮询中");
                    num += 1;
                    if(num > 1){
                        msg = "111";
                        break;
                    }
                }
                return msg;
            }
        });
        asyncTask.onCompletion(() -> {
            // TODO 完成，移除key。出错或者超时，都会执行完成

            logger.info("任务执行完成，移除key");
        });
        asyncTask.onError(() -> {
            // 出错需要删掉redis的key del

            return "出错";
        });
        asyncTask.onTimeout(() -> {
            // 5. 超时需要删掉redis的key del

            return "超时";
        });
        System.err.println("主线程继续干其他！");
        return asyncTask;
    }


}
