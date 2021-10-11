package com.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    //spring的EL表达式,获取yml端口号
    @Value("${server.port}")
    private String port;

    //访问localhost:8081/index  访问localhost:8082/index 访问localhost:8083/index返回对应端口 8083
    @GetMapping("/index")
    public String index(){
        return this.port;
    }

    @GetMapping("/list")
    public String list(){
        return "list";
    }

    /**
     * 热点
     * 访问：http://localhost:8083/hot  或者 http://localhost:8083/hot?num1=1&num2=2
     */


    @GetMapping("/hot")
    @SentinelResource("hot")
    public String hot(
            @RequestParam(value = "num1",required =false) Integer num1,
            @RequestParam(value = "num2",required =false) Integer num2){
        return num1+"-"+num2;
    }


}
