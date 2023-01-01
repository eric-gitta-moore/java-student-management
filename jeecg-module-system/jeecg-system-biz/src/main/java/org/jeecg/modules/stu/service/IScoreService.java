package org.jeecg.modules.stu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.stu.dto.StuScoreStatDTO;
import org.jeecg.modules.stu.entity.Score;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
public interface IScoreService extends IService<Score> {

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
     * 获取学生所有课程的平均成绩和总成绩
     * @param userId
     * @return
     */
    public StuScoreStatDTO getStuScoreStat(String userId);

}
