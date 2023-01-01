package org.jeecg.modules.stu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.stu.entity.StuClass;
import org.jeecg.modules.stu.mapper.StuClassMapper;
import org.jeecg.modules.stu.service.IStuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
@Service
public class StuClassServiceImpl extends ServiceImpl<StuClassMapper, StuClass> implements IStuClassService {

    @Autowired
    private StuClassMapper stuClassMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(StuClass stuClass) {
        stuClassMapper.insert(stuClass);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(StuClass stuClass) {
        stuClassMapper.updateById(stuClass);

        //1.先删除子表数据

        //2.子表数据重新插入
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        stuClassMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            stuClassMapper.deleteById(id);
        }
    }

}
