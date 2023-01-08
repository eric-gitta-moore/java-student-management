package com.example.modules.stu.dto.resp;

import lombok.Data;

/**
 * 课程统计信息
 *
 * @author w
 */
@Data
public class CourseScoreStatDTO {

    /**
     * 课程ID
     */
    private String id;

    /**
     * 修读人数
     */
    private Long count;

    /**
     * 平均成绩
     */
    private Double average;

    /**
     * 及格人数
     */
    private Long passCount;


}
