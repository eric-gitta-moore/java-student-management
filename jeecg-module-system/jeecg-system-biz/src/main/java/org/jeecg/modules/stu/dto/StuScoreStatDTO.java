package org.jeecg.modules.stu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author w
 */
@Data
@ApiModel(value = "stu_StuScoreStatDTO对象", description = "学生成绩统计数据")
public class StuScoreStatDTO {
    @ApiModelProperty("平均成绩")
    private Double average;

    @ApiModelProperty("总分")
    private Double total;

    @ApiModelProperty("课程总数")
    private Long courseTotalSize;
}
