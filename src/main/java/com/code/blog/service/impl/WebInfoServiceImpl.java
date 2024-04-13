package com.code.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.entity.WebInfo;
import com.code.blog.service.WebInfoService;
import com.code.blog.mapper.WebInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author HeXin
* @description 针对表【web_info(网站信息表)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class WebInfoServiceImpl extends ServiceImpl<WebInfoMapper, WebInfo>
    implements WebInfoService{

}




