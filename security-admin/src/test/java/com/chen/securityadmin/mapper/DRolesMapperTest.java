package com.chen.securityadmin.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chen.securityadmin.entiry.po.DRoles;
import com.chen.securityadmin.entiry.po.DUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.mapper
 * @className com.chen.securityadmin.mapper.DRolesMapperTest
 * @date 2025/4/10 19:45
 * @description @todo
 */

@SpringBootTest
public class DRolesMapperTest {

    @Autowired
    private DRolesMapper dRolesMapper;

    @Autowired
    private DUsersMapper dUsersMapper;


    @Test
    public void testSelectAll() {
        DRoles dRoles = dRolesMapper.selectById("1");
        System.out.println(dRoles);
//        LambdaQueryWrapper<DRoles> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(DRoles::getRole_id, 1);
//        List<DRoles> dRoles = dRolesMapper.selectList(wrapper);
//        dRoles.forEach(dRoles1 -> {
//            System.out.println(
//                    dRoles1.getRole_id() + " " +
//                            dRoles1.getRole_name() + " " +
//                            dRoles1.getRole_code() + " " +
//                            dRoles1.getDescription() + " " +
//                            dRoles1.getCreate_time());
//        });
    }

    @Test
    public void insert() {
        DRoles dRoles = new DRoles();
        dRoles.setRoleName("role_guess");
        dRoles.setRoleCode("role_guess");
        dRoles.setDescription("guess");
        System.out.println(dRolesMapper.insert(dRoles));

    }

    @Test
    public void queryFromThreeTable() {

        String userId = "1";
        LambdaQueryWrapper<DUsers> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(DUsers::getUserId, userId);

        List<DUsers> dUsers = dUsersMapper.selectList(queryWrapper);
        dUsers.forEach(dUsers1 -> {
            System.out.println(dUsers1.getUsername());
            System.out.println(dUsers1.getRealName());
        });
    }
}
