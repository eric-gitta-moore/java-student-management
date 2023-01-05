package org.jeecg.modules.stu.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "stu_UserScorePage对象", description = "班级成绩-学生用户分页对象")
public class StuUserScoreVO extends SysUser {

    @Excel(name = "父部门", dictTable = "sys_depart", dicCode = "id", dicText = "depart_name")
    @Dict(dictTable = "sys_depart", dicCode = "id", dicText = "depart_name")
    @ApiModelProperty("父级部门ID")
    private String parentDepartId;

    @ApiModelProperty("所有课程平均分")
    private String scoreAverage;

    @ApiModelProperty("所有课程总分")
    private String scoreTotal;

    @ApiModelProperty("课程总数")
    private Long courseCountTotal;

    @ApiModelProperty("及格课程总数")
    private Long coursePassTotalSize;

    @ApiModelProperty("所有课程总及格率")
    private String passRatio;

    @ApiModelProperty("班级排名")
    private Long classRank;

    @ApiModelProperty("班级信息")
    private StuClassVO classInfo;
}
