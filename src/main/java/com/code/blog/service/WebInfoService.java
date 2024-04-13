package com.code.blog.service;

import com.code.blog.entity.WebInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.blog.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
* @author HeXin
* @description 针对表【web_info(网站信息表)】的数据库操作Service
* @createDate 2024-03-09 10:39:33
*/
public interface WebInfoService extends IService<WebInfo> {
}
