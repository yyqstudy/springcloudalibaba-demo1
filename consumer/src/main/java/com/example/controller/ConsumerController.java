package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@Slf4j
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient; //自动装在IOC容器中

    @Autowired
    private RestTemplate restTemplate; //不会自动装在IOC容器中，不能直接用

    //集合的泛型是服务实例
    @GetMapping("/instances")
    public List<ServiceInstance> instances(){
        //this可以删掉 getInstances()参数是serviceId
        //会返回provider对应的三个实例
     List<ServiceInstance> provider = this.discoveryClient.getInstances("provider");

        return provider;
    }


    /**
     *  调用provider中controller的index方法
     *  1、找到provider实例
     *    List<ServiceInstance> provider = this.discoveryClient.getInstances("provider");
     *    provider.get(1) 但是使用随机数更好
     *  2、调用
     */
   /* @GetMapping("/index")
    public String index(){

        //1、找到provider实例
        List<ServiceInstance> list = this.discoveryClient.getInstances("provider");
        int index = ThreadLocalRandom.current().nextInt(list.size());//index随机数不会超过provider.size()
        ServiceInstance serviceInstance = list.get(index);//拿到provider实例
        //String uri = serviceInstance.getUri().toString();//拿到uri：IP+端口，举例localhost:8081
        String url = serviceInstance.getUri()+"/index"; //举例：直接是localhost:8081/index

        //2、调用 使用RestTemplate
        log.info("调用的端口是.{}",serviceInstance.getPort()); //在console打印日志
        return "调用了端口为："+serviceInstance.getPort()+"的服务，返回结果是："+this.restTemplate.getForObject(url,String.class);
        //this可以省略，getForObject(String url, Class<T> responseType),responseType要看provider的controller方法public String index(){}返回的是string
        //访问http://localhost:8180/index时候，结果随机：调用了端口为：8081的服务，返回结果是：8081
        }*/

    /**
     * 使用Ribbon之后，简化上面的代码，实现一样的效果
     */
    @GetMapping("/index")
    public String index(){
        return this.restTemplate.getForObject("http://provider/index",String.class);
        //直接调用provider的index方法获取url，访问http://localhost:8180/index 举例：结果是8082
    }

}
