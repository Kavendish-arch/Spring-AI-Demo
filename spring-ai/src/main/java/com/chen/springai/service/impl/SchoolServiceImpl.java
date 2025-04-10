package com.chen.springai.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.springai.domain.School;
import com.chen.springai.service.SchoolService;
import com.chen.springai.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

/**
* @author chenyingtao
* @description 针对表【school(校区表)】的数据库操作Service实现
* @createDate 2025-04-10 18:23:21
*/
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolService{

}




