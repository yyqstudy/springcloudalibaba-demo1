package com.example.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试授权：通过name确定白名单和黑名单授权
 */
public class RequestOriginParserDefinition implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        if(StringUtils.isEmpty(name)){
            throw new RuntimeException("name is null");
        }
        return name;
    }

}
