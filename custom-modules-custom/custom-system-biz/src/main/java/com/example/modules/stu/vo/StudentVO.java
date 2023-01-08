package com.example.modules.stu.vo;

import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;

import com.example.modules.stu.bo.SysUserBO;
import com.example.modules.stu.entity.StuClassInfo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 学生用户对象
 * 
 * @author w
 */
@ApiModel(value = "StudentVO对象", description = "学生用户对象")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StudentVO extends SysUserBO {

    // 开始--------------------------------课程分数统计

    /**
     * 课程平均分
     */
    private Double average;

    /**
     * 课程总分
     */
    private Double total;

    /**
     * 课程总数
     */
    private Long courseTotalSize;

    /**
     * 及格课程数
     */
    private Long coursePassTotalSize;

    /**
     * 班级排名
     */
    private Long classRank;

    // 结束--------------------------------课程分数统计

    /**
     * 班级信息
     */
    private StuClassInfo classInfo;

    /**
     * 父级部门
     */
    @Excel(name = "父部门", dictTable = "sys_depart", dicCode = "id", dicText = "depart_name")
    @Dict(dictTable = "sys_depart", dicCode = "id", dicText = "depart_name")
    private String parentDepartId;
}
