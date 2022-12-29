package org.jeecg.modules.stu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 教学计划
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Data
@ApiModel(value = "stu_teaching_planPage对象", description = "教学计划")
public class TeachingPlanPage {

    /**
     * 主键
     */
    @ApiModelProperty(value = "课程编号")
    @Excel(name = "课程编号")
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
    @Excel(name = "科目", width = 15, dictTable = "stu_subject", dicText = "name", dicCode = "id")
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
    @Excel(name = "创建人", dictTable = "sys_user", dicText = "realname", dicCode = "username")
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
    @Excel(name = "更新人", dictTable = "sys_user", dicText = "realname", dicCode = "username")
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
    @Excel(name = "所属部门", dictTable = "sys_depart", dicText = "depart_name", dicCode = "org_code")
    private java.lang.String sysOrgCode;


}
