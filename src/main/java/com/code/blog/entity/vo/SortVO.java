package com.code.blog.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分类 vo
 *
 * @author HeXin
 * @date 2024/03/12
 */
@Data
@Accessors(chain = true)
public class SortVO {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String sortName;

    /**
     * 分类描述
     */
    @Schema(description = "分类描述")
    private String sortDescription;

    /**
     * 分类类型[0:导航栏分类，1:普通分类]
     */
    @Schema(description = "分类类型[0:导航栏分类，1:普通分类]")
    private Integer sortType;

    /**
     * 导航栏分类优先级：数字小的在前面
     */
    @Schema(description = "导航栏分类优先级：数字小的在前面")
    private Integer priority;

    @Schema(description = "分类下标签")
    private List<LabelVO> labels;
}
