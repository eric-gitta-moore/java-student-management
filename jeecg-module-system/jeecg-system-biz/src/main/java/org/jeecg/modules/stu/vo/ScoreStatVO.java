package org.jeecg.modules.stu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.modules.system.entity.SysUser;

/**
 * @author w
 */
@Data
@ApiModel(value = "stu_ScoreStatVO对象", description = "分数统计对象")
public class ScoreStatVO {

    @ApiModelProperty("学生信息")
    private SysUser user;
    @ApiModelProperty("平均成绩")
    private Double average;

    @ApiModelProperty("总分")
    private Double total;

    @ApiModelProperty("课程总数")
    private Long courseTotalSize;
}
