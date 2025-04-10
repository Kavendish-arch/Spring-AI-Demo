package com.chen.securityadmin.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.securityadmin.entiry.po.DRoles;
import com.chen.securityadmin.entiry.po.DUserRoles;
import com.chen.securityadmin.entiry.po.DUsers;
import com.chen.securityadmin.mapper.DRolesMapper;
import com.chen.securityadmin.mapper.DUserRolesMapper;
import com.chen.securityadmin.mapper.DUsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.service
 * @className com.chen.securityadmin.service.UserDetailsServiceImplTest
 * @date 2025/4/10 20:05
 * @description @todo
 */
@SpringBootTest
public class UserDetailsServiceImplTest {


    @Autowired
    private DUsersMapper dUsersMapper;
    @Autowired
    private DUserRolesMapper dUserRolesMapper;
    @Autowired
    private DRolesMapper dRolesMapper;

    @Test
    public void loadUserByUsername() throws UsernameNotFoundException {
        System.out.println("loadUserByUsername" + "开始测试");
        /**
         * SELECT
         *     u.username,
         *     u.email,
         *     r.rolename,
         *     p.permissionname
         * FROM
         *     dusers u
         * LEFT JOIN
         *     duserroles ur ON u.userid = ur.userid
         * LEFT JOIN
         *     droles r ON ur.roleid = r.roleid
         * LEFT JOIN
         *     drolepermissions rp ON r.roleid = rp.roleid
         * LEFT JOIN
         *     dpermissions p ON rp.permissionid = p.permissionid
         * WHERE
         *     u.username = '指定的用户名';
         */
        LambdaQueryWrapper<DUsers> wrapper = new LambdaQueryWrapper<>();

        LambdaQueryWrapper<DUserRoles> dUserRolesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<DRoles> dRolesLambdaQueryWrapper = new LambdaQueryWrapper<>();

        String username = "user1";
        wrapper.eq(DUsers::getUsername, username);
        DUsers dUsers = dUsersMapper.selectOne(wrapper);
        if (null == dUsers) {
            throw new RuntimeException("用户或密码错误");
        }
        System.out.println("------------------------------------------------");
        System.out.println(dUsers);
        System.out.println("------------------------------------------------");

        dUserRolesLambdaQueryWrapper.eq(DUserRoles::getUserId, dUsers.getUserId());
        // 查询用户角色
        List<DUserRoles> dUserRoles = dUserRolesMapper.selectList(dUserRolesLambdaQueryWrapper);
        // 查询角色
        List<DRoles> userHaveRoles = new ArrayList<>();
        for (DUserRoles dUserRole : dUserRoles) {
            dRolesLambdaQueryWrapper.eq(DRoles::getRoleId, dUserRole.getRoleId());
            DRoles dRoles = dRolesMapper.selectOne(dRolesLambdaQueryWrapper);
            userHaveRoles.add(dRoles);
        }

        System.out.println("------------------------------------------------");
        for (DRoles userHaveRole : userHaveRoles) {
            System.out.println(userHaveRole.getRoleCode());
        }
        System.out.println("------------------------------------------------");

    }
}
