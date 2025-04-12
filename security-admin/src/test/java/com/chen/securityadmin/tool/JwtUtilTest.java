package com.chen.securityadmin.tool;

import com.chen.securityadmin.service.DUsersService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.tool
* @className com.chen.securityadmin.tool.JwtUtilTest

* @author chenyingtao
* @date 2025/4/11 11:41
* @version 1.0
* @description @todo 
*/
@SpringBootTest
public class JwtUtilTest {

    private DUsersService dUsersService;
    private JwtUtil2 jwtUtil;

    @Test
    public void createJWTTest() {
        Map<String, Object> claims = new HashMap<>();



        jwtUtil.createJWT(null, 0) ;
    }
}
