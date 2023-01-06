package com.example.modules.stu.vo;

import com.example.modules.stu.entity.StuClassInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "stu_classPage对象", description = "学生班级")
public class StuClassVO extends StuClassInfo {


}
