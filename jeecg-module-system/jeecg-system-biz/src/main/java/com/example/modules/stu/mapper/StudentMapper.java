package com.example.modules.stu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.modules.stu.dto.resp.StudentDTO;
import org.apache.ibatis.annotations.Param;
import com.example.core.base.mapper.BaseMapper;
import org.jeecg.modules.system.entity.SysUser;

import java.util.List;

/**
 * @author w
 */
public interface StudentMapper extends BaseMapper<SysUser> {

    List<StudentDTO> queryPageDTO(@Param("limitBegin") Long limitBegin, @Param("limitEnd") Long limitEnd,
        @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    Long countPageDTO(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
