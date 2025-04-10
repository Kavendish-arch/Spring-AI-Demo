package com.chen.springai.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 校区表
 * @TableName school
 */
@TableName(value ="school")
@Data
public class School {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 校区名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 校区所在城市
     */
    @TableField(value = "city")
    private String city;
}