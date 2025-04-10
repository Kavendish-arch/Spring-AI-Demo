package com.chen.securityadmin.entiry.dto;

import com.chen.securityadmin.entiry.po.DUsers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.entiry.dto
* @className com.chen.securityadmin.entiry.dto.LoginUser

* @author chenyingtao
* @date 2025/4/10 19:59
* @version 1.0
* @description @todo 
*/
public class LoginUser implements UserDetails {

    private DUsers user;

    public LoginUser(DUsers dUsers, Collection<? extends GrantedAuthority> authorities) {
        this.user = dUsers;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
