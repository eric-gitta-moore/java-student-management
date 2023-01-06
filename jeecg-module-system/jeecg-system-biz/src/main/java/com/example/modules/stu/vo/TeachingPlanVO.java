package com.example.modules.stu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.example.modules.stu.entity.TeachingPlan;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 教学计划
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "stu_teaching_planPage对象", description = "教学计划")
public class TeachingPlanVO extends TeachingPlan {
    /**
     * 修读人数
     */
    @Excel(name = "修读人数")
    @ApiModelProperty(value = "修读人数")
    private Long studentCount;

    /**
     * 及格人数
     */
    @Excel(name = "及格人数")
    @ApiModelProperty(value = "及格人数")
    private Long passCount;

    /**
     * 所有修读人数平均分
     */
    @Excel(name = "平均分")
    @ApiModelProperty(value = "平均分")
    private String scoreAverage;

    /**
     * 及格率
     */
    @Excel(name = "及格率")
    @ApiModelProperty(value = "及格率")
    private String passRatio;
}
