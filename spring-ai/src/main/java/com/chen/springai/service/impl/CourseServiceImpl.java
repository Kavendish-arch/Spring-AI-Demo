package com.chen.springai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springai.domain.Course;
import com.chen.springai.service.CourseService;
import com.chen.springai.mapper.CourseMapper;
import org.springframework.stereotype.Service;

/**
* @author chenyingtao
* @description 针对表【course(学科表)】的数据库操作Service实现
* @createDate 2025-04-10 18:23:21
*/
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
    implements CourseService{

}




