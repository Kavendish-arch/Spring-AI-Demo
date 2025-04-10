package com.chen.securityadmin.tool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.tool
* @className com.chen.securityadmin.tool.JwtProperties

* @author chenyingtao
* @date 2025/4/10 21:43
* @version 1.0
* @description @todo 
*/
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {
    private String SecretKey;
    private Long Ttl;
    private String TokenName;
}
