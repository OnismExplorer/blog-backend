package com.code.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.entity.Label;
import com.code.blog.service.LabelService;
import com.code.blog.mapper.LabelMapper;
import org.springframework.stereotype.Service;

/**
* @author HeXin
* @description 针对表【label(标签)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label>
    implements LabelService{

}




