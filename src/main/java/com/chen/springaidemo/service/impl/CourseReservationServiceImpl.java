package com.chen.springaidemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springaidemo.domain.CourseReservation;
import com.chen.springaidemo.service.CourseReservationService;
import com.chen.springaidemo.mapper.CourseReservationMapper;
import org.springframework.stereotype.Service;

/**
* @author chenyingtao
* @description 针对表【course_reservation】的数据库操作Service实现
* @createDate 2025-04-05 20:31:59
*/
@Service
public class CourseReservationServiceImpl extends ServiceImpl<CourseReservationMapper, CourseReservation>
    implements CourseReservationService{

}




