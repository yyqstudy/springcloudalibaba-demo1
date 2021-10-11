package com.example.config;
//import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

/**
 * 要让 RequestOriginParserDefinition 生效，需要在配置类中进行配置。
 * pom引入依赖失败
 */

/*@Configuration
public class SentinelConfiguration {
    @PostConstruct
    public void init(){
        WebCallbackManager.setRequestOriginParser(new RequestOriginParserDefinition());
    }
}*/
