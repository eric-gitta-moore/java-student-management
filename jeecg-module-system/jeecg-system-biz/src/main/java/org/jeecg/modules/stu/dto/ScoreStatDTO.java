package org.jeecg.modules.stu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author w
 */
@Data
@ApiModel(value = "stu_ScoreStatDTO对象", description = "学生成绩统计数据")
public class ScoreStatDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("学生ID")
    private String userId;

    @ApiModelProperty("课程平均分")
    private Double average;

    @ApiModelProperty("课程总分")
    private Double total;

    @ApiModelProperty("课程总数")
    private Long courseTotalSize;

    @ApiModelProperty("及格课程数")
    private Long coursePassTotalSize;

}
