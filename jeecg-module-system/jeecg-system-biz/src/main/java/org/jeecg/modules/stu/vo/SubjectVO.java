package org.jeecg.modules.stu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.modules.stu.entity.Subject;

/**
 * @Description: 科目管理
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "stu_subjectPage对象", description = "科目管理")
public class SubjectVO extends Subject {

}
