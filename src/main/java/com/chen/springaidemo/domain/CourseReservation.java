package com.chen.springaidemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @TableName course_reservation
 */
@TableName(value ="course_reservation")
@Data
@Setter
@Getter
public class CourseReservation {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 预约课程
     */
    @TableField(value = "course")
    private String course;

    /**
     * 学生姓名
     */
    @TableField(value = "student_name")
    private String student_name;

    /**
     * 联系方式
     */
    @TableField(value = "contact_info")
    private String contact_info;

    /**
     * 预约校区
     */
    @TableField(value = "school")
    private String school;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;
}