package com.example.modules.stu.mapper;

import com.example.modules.stu.dto.resp.CourseScoreStatDTO;
import com.example.modules.stu.dto.resp.ScoreStatDTO;
import com.example.modules.stu.entity.Score;
import org.apache.ibatis.annotations.Param;
import com.example.core.base.mapper.BaseMapper;

import java.util.List;

/**
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
public interface ScoreMapper extends BaseMapper<Score> {

    /**
     * 获取学生所有课程的平均成绩和总成绩
     *
     * @param userIds
     * @return
     */
    List<ScoreStatDTO> getStuScoreStatAll(@Param("userIds") List<String> userIds);

    /**
     * 获取课程统计信息
     *
     * @param courseIds
     * @return
     */
    List<CourseScoreStatDTO> getCourseScoreStat(@Param("courseIds") List<String> courseIds);

}
