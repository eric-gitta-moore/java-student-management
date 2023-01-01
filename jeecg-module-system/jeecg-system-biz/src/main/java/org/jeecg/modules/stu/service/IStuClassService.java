package org.jeecg.modules.stu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.stu.entity.StuClass;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
public interface IStuClassService extends IService<StuClass> {

    /**
     * 添加一对多
     *
     * @param stuClass
     */
    public void saveMain(StuClass stuClass);

    /**
     * 修改一对多
     *
     * @param stuClass
     */
    public void updateMain(StuClass stuClass);

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
