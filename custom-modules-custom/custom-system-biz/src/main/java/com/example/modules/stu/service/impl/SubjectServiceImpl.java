package com.example.modules.stu.service.impl;

import com.example.modules.stu.entity.Subject;
import com.example.modules.stu.mapper.SubjectMapper;
import com.example.modules.stu.service.ISubjectService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 科目管理
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(Subject subject) {
        subjectMapper.insert(subject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(Subject subject) {
        subjectMapper.updateById(subject);

        //1.先删除子表数据

        //2.子表数据重新插入
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMain(String id) {
        subjectMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBatchMain(Collection<? extends Serializable> idList) {
        for (Serializable id : idList) {
            subjectMapper.deleteById(id);
        }
    }

}
