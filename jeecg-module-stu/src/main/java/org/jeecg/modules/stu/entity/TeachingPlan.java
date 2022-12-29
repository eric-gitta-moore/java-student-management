package org.jeecg.modules.stu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 教学计划
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@ApiModel(value = "stu_teaching_plan对象", description = "教学计划")
@Data
@TableName("stu_teaching_plan")
public class TeachingPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "课程编号")
    private java.lang.String id;
    /**
     * 课程名称
     */
    @Excel(name = "课程名称", width = 15)
    @ApiModelProperty(value = "课程名称")
    private java.lang.String name;
    /**
     * 科目编号
     */
    @Excel(name = "科目编号", width = 15)
    @ApiModelProperty(value = "科目编号")
    @Dict(dictTable = "stu_subject", dicText = "name", dicCode = "id")
    private java.lang.String subjectId;
    /**
     * 开课单位
     */
    @Excel(name = "开课单位", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "开课单位")
    private java.lang.String courseOpeningDepart;
    /**
     * 开课学期
     */
    @Excel(name = "开课学期", width = 15, dicCode = "academic_year_term")
    @Dict(dicCode = "academic_year_term")
    @ApiModelProperty(value = "开课学期")
    private java.lang.String offerTerm;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
}
