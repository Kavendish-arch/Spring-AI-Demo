package com.chen.securityadmin.mapper;

import com.chen.securityadmin.entiry.vo.VUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.mapper
* @className com.chen.securityadmin.mapper.DUserMapperTest

* @author chenyingtao
* @date 2025/4/11 13:13
* @version 1.0
* @description @todo 
*/
@SpringBootTest
public class DUserMapperTest {

    @Autowired
    private DUsersMapper dUsersMapper;
    @Test
    public void testSelect() {
        // 1. 初始化 Spring 上下文
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MybatisPlusConfig.class);
//        // 2. 获取 Mapper 对象
//        DUsersMapper mapper = applicationContext.getBean(DUsersMapper.class);
//        // 3. 执行查询操作
//        List<DUsers> list = mapper.selectList(null);
        VUserRole vUserRole = dUsersMapper.getUserRoleByUserId("1");

        System.out.println(vUserRole);
        vUserRole.getRoles().forEach(System.out::println);
    }
}
