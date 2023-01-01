package org.jeecg.modules.stu.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 学生用户对象
 * @author w
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "stu_UserPage对象", description = "学生用户对象")
public class StuUserPage extends SysUser {

    @Excel(name = "父部门", dictTable = "sys_depart", dicCode = "id", dicText = "depart_name")
    @Dict(dictTable = "sys_depart", dicCode = "id", dicText = "depart_name")
    private String parentDepartId;
}
