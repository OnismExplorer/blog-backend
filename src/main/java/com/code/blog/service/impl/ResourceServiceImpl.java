package com.code.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.entity.Resource;
import com.code.blog.service.ResourceService;
import com.code.blog.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

/**
* @author HeXin
* @description 针对表【resource(资源信息)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
    implements ResourceService{

}




