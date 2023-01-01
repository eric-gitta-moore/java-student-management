package org.jeecg.modules.stu.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.stu.dto.StuScoreStatDTO;
import org.jeecg.modules.stu.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date:   2022-12-29
 * @Version: V1.0
 */
public interface ScoreMapper extends BaseMapper<Score> {

    /**
     * 获取学生所有课程的平均成绩和总成绩
     * @param userId
     * @return
     */
    public StuScoreStatDTO getStuScoreStat(@Param("userId") String userId);

}
