package com.example.modules.stu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.modules.stu.dto.resp.StuClassDTO;
import com.example.modules.stu.entity.StuClassInfo;

import java.util.List;

/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
public interface StuClassMapper extends BaseMapper<StuClassInfo> {

    /**
     * 批量查询班级信息
     *
     * @param studentIds
     * @return
     */
    List<StuClassDTO> queryStuClasses(List<String> studentIds);
}
