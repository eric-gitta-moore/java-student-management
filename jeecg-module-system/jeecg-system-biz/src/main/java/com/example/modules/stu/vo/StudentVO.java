package com.example.modules.stu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import com.example.modules.stu.entity.StuClassInfo;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @author w
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class StudentVO extends SysUser {

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
