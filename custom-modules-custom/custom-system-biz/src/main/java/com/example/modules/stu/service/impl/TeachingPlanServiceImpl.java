package com.example.modules.stu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.modules.stu.entity.TeachingPlan;
import com.example.modules.stu.mapper.TeachingPlanMapper;
import com.example.modules.stu.service.ITeachingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 教学计划
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Service
public class TeachingPlanServiceImpl extends ServiceImpl<TeachingPlanMapper, TeachingPlan> implements
    ITeachingPlanService {

    @Autowired
    private TeachingPlanMapper teachingPlanMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(TeachingPlan teachingPlan) {
        teachingPlanMapper.insert(teachingPlan);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(TeachingPlan teachingPlan) {
        teachingPlanMapper.updateById(teachingPlan);

        //1.先删除子表数据

        //2.子表数据重新插入
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        teachingPlanMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            teachingPlanMapper.deleteById(id);
        }
    }

}
