package org.jeecg.modules.stu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.stu.dto.resp.StuClassDTO;
import org.jeecg.modules.stu.entity.StuClassInfo;
import org.jeecg.modules.stu.mapper.StuClassMapper;
import org.jeecg.modules.stu.service.IStuClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
@Service
public class StuClassServiceImpl extends ServiceImpl<StuClassMapper, StuClassInfo> implements IStuClassService {

    @Autowired
    private StuClassMapper stuClassMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveMain(StuClassInfo stuClassInfo) {
        stuClassMapper.insert(stuClassInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMain(StuClassInfo stuClassInfo) {
        stuClassMapper.updateById(stuClassInfo);

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

    @Override
    public Map<String, StuClassDTO> queryStuClasses(List<String> studentIds) {
        List<StuClassDTO> stuClassList = stuClassMapper.queryStuClasses(studentIds);
        return stuClassList.stream()
            .collect(Collectors.toMap(StuClassDTO::getStudentId, e -> e));
    }

}
