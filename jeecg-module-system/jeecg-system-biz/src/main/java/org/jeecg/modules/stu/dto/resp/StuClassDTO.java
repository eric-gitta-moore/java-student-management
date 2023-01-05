package org.jeecg.modules.stu.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @author w
 */
@Data
public class StuClassDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private java.lang.String id;
    /**
     * 学生用户
     */
    private String studentId;
    /**
     * 班级名称
     */
    private java.lang.String name;
    /**
     * 年级
     */
    @Dict(dicCode = "grade")
    private java.lang.String grade;
    /**
     * 班主任
     */
    @Dict(dictTable = "sys_user", dicText = "realname", dicCode = "id")
    private java.lang.String classTeacher;
    /**
     * 所属专业
     */
    @Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    private java.lang.String major;
    /**
     * 所属学院
     */
    @Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    private java.lang.String collegeId;
    /**
     * 创建人
     */
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    private java.lang.String sysOrgCode;
}
