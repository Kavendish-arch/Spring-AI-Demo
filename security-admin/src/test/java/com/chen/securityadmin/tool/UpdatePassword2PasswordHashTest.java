package com.chen.securityadmin.tool;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.chen.securityadmin.entiry.po.DUsers;
import com.chen.securityadmin.mapper.DUsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.tool
* @className com.chen.securityadmin.tool.UpdatePassword2PasswordHashTest

* @author chenyingtao
* @date 2025/4/10 21:33
* @version 1.0
* @description @todo 
*/
@SpringBootTest
public class UpdatePassword2PasswordHashTest {

    @Autowired
    private DUsersMapper dUsersMapper;

    @Test
    public void testUpdateAllPasswords() {
        // 创建一个BCryptPasswordEncoder实例
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 更新所有用户的密码为加密后的"123456"
        String encodedPassword = encoder.encode("123456");

        // 构造更新条件
        LambdaUpdateWrapper<DUsers> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(DUsers::getPasswordHash, encodedPassword);

        // 执行更新操作
        boolean result = dUsersMapper.update(null, updateWrapper) > 0;

        if (result) {
            System.out.println("所有用户的密码更新成功！");
        } else {
            System.out.println("密码更新失败！");
        }

    }
}
