package com.code.blog.service;

import com.code.blog.entity.Sort;
import com.baomidou.mybatisplus.extension.service.IService;
import com.code.blog.entity.vo.SortVO;

import java.util.List;

/**
* @author HeXin
* @description 针对表【sort(分类)】的数据库操作Service
* @createDate 2024-03-09 10:39:33
*/
public interface SortService extends IService<Sort> {

    /**
     * 获取标签
     *
     * @param id 编号
     * @return {@link SortVO}
     */
    SortVO getLabels(Long id);


    /**
     * 获取分类列表
     *
     * @param isShow 是否显示其标签
     * @return {@link List}<{@link SortVO}>
     */
    List<SortVO> listFront(Integer isShow);
}
