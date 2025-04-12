package com.chen.securityadmin.entiry.vo;

import com.chen.securityadmin.entiry.po.DRoles;
import com.chen.securityadmin.entiry.po.DUsers;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @projectName SpringAIDemo
* @package com.chen.securityadmin.entiry.vo
* @className com.chen.securityadmin.entiry.vo.VUserRole

* @author chenyingtao
* @date 2025/4/11 12:44
* @version 1.0
* @description @todo 
*/
@Getter
@Setter
public class VUserRole extends DUsers {

    // 用户到角色的多对多查询
    List<DRoles> roles;

}
