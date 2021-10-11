package com.example.test;

import org.springframework.web.client.RestTemplate;

/**
 * 为了测试流控模式中关联（/list出问题，但是是/index背锅）
 * 设置单机阈值是1
 * 多次访问http://localhost:8083/list(是run Testmain()即可，console)会一直跑
 * 并且实时监控http://localhost:8083/index是否报错（在console不断运行期间，网页访问http://localhost:8083/index  出现Blocked by Sentinel (flow limiting)
 * */
public class Test {
    public static void main(String[] args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 测试流控模式的关联
         */
     /*   for (int i = 0; i < 1000; i++) {
            restTemplate.getForObject("http://localhost:8083/list",String.class);
            Thread.sleep(200);//200毫秒，一秒钟来5个,QPS是5，但是我们设置了1
        }
*/
        /**
         *  流控效果的排队等待
         */
           /*   for (int i = 0; i < 1000; i++) {
            restTemplate.getForObject("http://localhost:8083/index",String.class); //测试流控效果：排队等待
            Thread.sleep(200);//200毫秒，一秒钟来5个,QPS是5，但是我们设置了1
        }
*/
        /**
         * 测试熔断规则：RT  RT:0.1  熔断时长是8
         */
        for (int i = 0; i < 100; i++) {
            restTemplate.getForObject("http://localhost:8083/index",String.class);
            Thread.sleep(10);
        }


    }
}
