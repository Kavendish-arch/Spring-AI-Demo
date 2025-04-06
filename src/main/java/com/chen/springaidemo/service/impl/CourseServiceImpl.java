package com.chen.springaidemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springaidemo.domain.Course;
import com.chen.springaidemo.service.CourseService;
import com.chen.springaidemo.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author chenyingtao
* @description 针对表【course(学科表)】的数据库操作Service实现
* @createDate 2025-04-05 20:31:44
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




