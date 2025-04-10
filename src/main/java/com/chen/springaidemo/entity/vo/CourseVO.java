package com.chen.springaidemo.entity.vo;

import com.chen.springaidemo.domain.Course;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
* @projectName SpringAIDemo
* @package com.chen.springaidemo.entity.vo
* @className com.chen.springaidemo.entity.vo.CourseVO

* @author chenyingtao
* @date 2025/4/5 20:41
* @version 1.0
* @description @todo 
*/
@Data
@Schema(title = "课程信息")
public class CourseVO extends Course {
    public CourseVO(Course course) {
        this.setId(course.getId());
        this.setName(course.getName());
        this.setEdu(course.getEdu());
        this.setType(course.getType());
        this.setPrice(course.getPrice());
        this.setDuration(course.getDuration());

    }
}
