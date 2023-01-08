package com.example.modules.stu.vo;

import com.example.modules.stu.bo.SysUserBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author w
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "stu_ScoreStatVO对象", description = "分数统计对象")
public class ScoreStatVO {

    @ApiModelProperty("学生信息")
    private SysUserBO user;
    @ApiModelProperty("平均成绩")
    private Double average;

    @ApiModelProperty("总分")
    private Double total;

    @ApiModelProperty("课程总数")
    private Long courseTotalSize;
}
