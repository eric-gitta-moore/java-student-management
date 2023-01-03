package org.jeecg.modules.stu.query;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 学生管理-分页列表查询 sql查询参数
 *
 * @author w
 */
@Data
@Accessors(chain = true)
public class StudentQuery {

    /**
     * 班级id 精确搜索
     */
    private String classId;

    /**
     * 学号 模糊搜索
     */
    private String workNo;

    /**
     * 真实姓名 模糊搜索
     */
    private String realname;
}
