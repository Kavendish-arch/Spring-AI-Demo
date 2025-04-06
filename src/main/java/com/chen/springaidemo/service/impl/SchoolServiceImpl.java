package com.chen.springaidemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springaidemo.domain.School;
import com.chen.springaidemo.service.SchoolService;
import com.chen.springaidemo.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

/**
* @author chenyingtao
* @description 针对表【school(校区表)】的数据库操作Service实现
* @createDate 2025-04-05 20:32:10
*/
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolService{

}




