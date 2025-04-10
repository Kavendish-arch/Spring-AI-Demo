package com.chen.securityadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chen.securityadmin.entiry.dto.LoginUser;
import com.chen.securityadmin.entiry.po.DRoles;
import com.chen.securityadmin.entiry.po.DUserRoles;
import com.chen.securityadmin.entiry.po.DUsers;
import com.chen.securityadmin.mapper.DRolesMapper;
import com.chen.securityadmin.mapper.DUserRolesMapper;
import com.chen.securityadmin.mapper.DUsersMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.securityadmin.service.impl
 * @className com.chen.securityadmin.service.impl.UserdetailsServiceImpl
 * @date 2025/4/10 20:00
 * @description @todo
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final DUsersMapper dUsersMapper;
    private final DUserRolesMapper dUserRolesMapper;
    private final DRolesMapper dRolesMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        LambdaQueryWrapper<DUsers> wrapper = new LambdaQueryWrapper<>();

        LambdaQueryWrapper<DUserRoles> dUserRolesLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<DRoles> dRolesLambdaQueryWrapper = new LambdaQueryWrapper<>();

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


        Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("role_admin"));

        return new LoginUser(dUsers, authorities);
    }


}
