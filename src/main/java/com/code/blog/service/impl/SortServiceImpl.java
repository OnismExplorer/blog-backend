package com.code.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.code.blog.entity.Label;
import com.code.blog.entity.Sort;
import com.code.blog.entity.vo.LabelVO;
import com.code.blog.entity.vo.SortVO;
import com.code.blog.service.LabelService;
import com.code.blog.service.SortService;
import com.code.blog.mapper.SortMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author HeXin
* @description 针对表【sort(分类)】的数据库操作Service实现
* @createDate 2024-03-09 10:39:33
*/
@Service
public class SortServiceImpl extends ServiceImpl<SortMapper, Sort>
    implements SortService{

    @Autowired
    private LabelService labelService;

    @Override
    public SortVO getLabels(Long id) {
        Sort sort = getById(id);
        SortVO vo = new SortVO();
        BeanUtils.copyProperties(sort,vo);
        // 获取标签集合
        List<Label> list = labelService.lambdaQuery()
                .select(Label::getId,Label::getLabelName,Label::getLabelDescription)
                .eq(Label::getSortId, id).list();
        setLabels(vo, list);
        return vo;
    }

    @Override
    public List<SortVO> listFront(Integer isShow) {
        List<Sort> sortList = lambdaQuery()
                .select(Sort::getId, Sort::getSortDescription, Sort::getSortName,Sort::getPriority,Sort::getSortType)
                .orderByAsc(Sort::getPriority)
                .list();

        Map<Long, List<Label>> labelMap = null;
        if(isShow.equals(1)) {
            // 获取排序id
            List<Long> idList = sortList.stream().map(Sort::getId).toList();
            // 获取标签集合(按sort_id分组)
            labelMap =  labelService.lambdaQuery()
                    .select(Label::getId, Label::getLabelName, Label::getLabelDescription, Label::getSortId)
                    .in(Label::getSortId, idList).list().stream().collect(Collectors.groupingBy(Label::getSortId));

        }
        List<SortVO> voList = new ArrayList<>();
        for (Sort sort : sortList) {
            SortVO vo = new SortVO();
            BeanUtils.copyProperties(sort,vo);
            if(isShow.equals(1) && (labelMap != null)) {
                    List<Label> labelList = labelMap.get(vo.getId());
                    setLabels(vo, labelList);
            }
            voList.add(vo);
        }

        return voList;
    }

    /**
     * 设置标签
     *
     * @param vo        VO型
     * @param labelList 标签列表
     */
    private void setLabels(SortVO vo, List<Label> labelList) {
        List<LabelVO> labelVOList = new ArrayList<>();
        for (Label label : labelList) {
            LabelVO labelVO = new LabelVO();
            BeanUtils.copyProperties(label,labelVO);
            labelVOList.add(labelVO);
        }
        vo.setLabels(labelVOList);
    }
}




