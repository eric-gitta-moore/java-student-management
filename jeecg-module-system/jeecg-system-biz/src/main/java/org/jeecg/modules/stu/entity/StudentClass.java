package org.jeecg.modules.stu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author w
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;


    /**
     * 学生用户id
     */
    private String studentId;


    /**
     * 班级id
     */
    private String classId;

    public StudentClass() {

    }

    public StudentClass(String studentId, String classId) {
        this.studentId = studentId;
        this.classId = classId;
    }


}
