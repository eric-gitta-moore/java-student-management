package org.jeecg.modules.stu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.stu.entity.TeachingPlan;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 教学计划
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
public interface ITeachingPlanService extends IService<TeachingPlan> {

    /**
     * 添加一对多
     *
     * @param teachingPlan
     */
    public void saveMain(TeachingPlan teachingPlan);

    /**
     * 修改一对多
     *
     * @param teachingPlan
     */
    public void updateMain(TeachingPlan teachingPlan);

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

}
