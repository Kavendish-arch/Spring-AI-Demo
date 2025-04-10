package com.chen.springai.tools;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;

import com.chen.springai.domain.Course;
import com.chen.springai.domain.CourseReservation;
import com.chen.springai.domain.School;
import com.chen.springai.entity.query.CourseQuery;
import com.chen.springai.entity.vo.CourseVO;
import com.chen.springai.service.CourseReservationService;
import com.chen.springai.service.CourseService;
import com.chen.springai.service.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenyingtao
 * @version 1.0
 * @projectName SpringAIDemo
 * @package com.chen.springaidemo.tools
 * @className com.chen.springaidemo.tools.CourseTool
 * @date 2025/4/5 20:40
 * @description @todo
 */
@AllArgsConstructor
@Component
public class CourseTool {

    private final CourseService courseService;
    private final SchoolService schoolService;
    private final CourseReservationService courseReservationService;
    /**
     * 根据查询条件查询课程列表
     *
     * @param query 课程查询条件对象，包含以下过滤条件：
     *              - type: 课程类型（精确匹配）
     *              - edu: 学历要求（小于等于匹配）
     *              - sorts: 排序规则列表，可指定多个排序字段及排序方向
     * @return 符合查询条件的课程VO列表，当查询条件为空时返回空列表
     */
    @Tool(description = "查询课程列表")
    public List<CourseVO> queryCourses(@ToolParam(description = "查询条件") CourseQuery query) {
        // 处理空查询条件情况
        if (query == null) {
            return List.of();
        }

        // 构建动态查询条件
        QueryChainWrapper<Course> queryWrapper = courseService.query()
                .eq(query.getType() != null, "type", query.getType())  // 类型条件过滤
                .le(query.getEdu() != null, "edu", query.getEdu());    // 学历条件过滤

        // 动态添加排序规则
        if (query.getSorts() != null && !query.getSorts().isEmpty()) {
            for (CourseQuery.Sort sort : query.getSorts()) {
                queryWrapper.orderBy(true, sort.getAsc(), sort.getField());
            }
        }

        // 执行查询并转换为视图对象
        return queryWrapper.list().stream().map(CourseVO::new).toList();
    }


    /**
     * 查询学校列表
     *
     * @return 包含所有学校信息的列表，列表元素为School对象，
     *         返回数据通过schoolService.list()从服务层获取
     */
    @Tool(description = "查询学校列表")
    public List<School> querySchools() {
        return schoolService.list();
    }



    /**
     * 创建课程预约
     *
     * @param courseId 课程id，标识要预约的具体课程
     * @param schoolId 学校id，标识课程所属的学校
     * @param student_name 学生姓名，预约课程的学生全名
     * @param contact_info 联系方式，用于联系学生的有效信息
     * @param remark 备注，与预约相关的附加信息或特殊要求
     * @return 返回创建成功的课程预约记录ID，若保存失败则返回null
     */
    @Tool(description = "创建课程预约")
    public Integer createCourseReservation(
            @ToolParam(description = "课程id") String courseId,
            @ToolParam(description = "学校id") String schoolId,
            @ToolParam(description = "学生姓名") String student_name,
            @ToolParam(description = "联系方式") String contact_info,
            @ToolParam(description = "备注") String remark) {

        // 构建课程预约对象并设置属性
        CourseReservation courseReservation = new CourseReservation();
        courseReservation.setCourse(courseId);
        courseReservation.setSchool(schoolId);
        courseReservation.setStudent_name(student_name);
        courseReservation.setContact_info(contact_info);
        courseReservation.setRemark(remark);

        // 持久化存储并返回结果
        return courseReservationService.save(courseReservation) ? courseReservation.getId() : null;
    }

}
