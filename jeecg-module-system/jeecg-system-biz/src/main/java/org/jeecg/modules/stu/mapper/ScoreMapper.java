package org.jeecg.modules.stu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.stu.dto.ScoreStatDTO;
import org.jeecg.modules.stu.entity.Score;

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
    public List<ScoreStatDTO> getStuScoreStatAll(@Param("userIds") List<String> userIds);

}
