package com.example.modules.stu.service;

import com.example.core.base.service.IBaseService;
import com.example.modules.stu.dto.CourseScoreStatDTO;
import com.example.modules.stu.dto.ScoreStatDTO;
import com.example.modules.stu.entity.Score;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
public interface IScoreService extends IBaseService<Score> {

    /**
     * 添加一对多
     *
     * @param score
     */
    public void saveMain(Score score);

    /**
     * 修改一对多
     *
     * @param score
     */
    public void updateMain(Score score);

    /**
     * 删除一对多
     *
     * @param id
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     *
     * @param idList
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

    /**
     * 获取学生所有课程的统计信息
     *
     * @param userId
     * @return
     */
    public ScoreStatDTO getStuScoreStat(String userId);

    /**
     * 获取学生所有课程的统计信息
     *
     * @param userIds
     * @return
     */
    public Map<String, ScoreStatDTO> getStuScoreStat(List<String> userIds);

    /**
     * 获取课程统计信息
     *
     * @param courseIds
     * @return
     */
    Map<String, CourseScoreStatDTO> getCourseScoreStat(List<String> courseIds);

}
