package org.jeecg.modules.stu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.stu.entity.Score;
import org.jeecg.modules.stu.mapper.ScoreMapper;
import org.jeecg.modules.stu.service.IScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements IScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(Score score) {
        scoreMapper.insert(score);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(Score score) {
        scoreMapper.updateById(score);

        //1.先删除子表数据

        //2.子表数据重新插入
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        scoreMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            scoreMapper.deleteById(id);
        }
    }

}
