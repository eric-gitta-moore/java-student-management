package com.example.modules.stu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import com.example.core.base.entity.BaseEntity;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("stu_score")
@ApiModel(value = "stu_score对象", description = "学生成绩")
public class Score extends BaseEntity<String> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;

    /**
     * 学生
     */
    @ApiModelProperty(value = "学生")
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    @Excel(name = "学生", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "id")
    private java.lang.String studentId;

    /**
     * 课程
     */
    @ApiModelProperty(value = "课程")
    @Dict(dictTable = "stu_teaching_plan", dicText = "name", dicCode = "id")
    @Excel(name = "课程", width = 15, dictTable = "stu_teaching_plan", dicText = "name", dicCode = "id")
    private java.lang.String courseId;

    /**
     * 成绩
     */
    @Excel(name = "成绩", width = 15)
    @ApiModelProperty(value = "成绩")
    private java.lang.Double score;

    /**
     * 是否及格
     */
    @Excel(name = "是否及格", dicCode = "yn")
    @ApiModelProperty("是否及格")
    @Dict(dicCode = "yn")
    private Integer isPass;

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
    @Excel(name = "创建日期", format = "yyyy-MM-dd")
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
    @Excel(name = "更新日期", format = "yyyy-MM-dd")
    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    @Excel(name = "所属部门", dictTable = "sys_depart", dicText = "depart_name", dicCode = "org_code")
    private java.lang.String sysOrgCode;
}
