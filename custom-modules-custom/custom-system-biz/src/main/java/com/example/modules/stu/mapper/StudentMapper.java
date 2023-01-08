package com.example.modules.stu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.core.base.mapper.BaseMapper;
import com.example.modules.stu.bo.SysUserBO;

/**
 * @author w
 */
public interface StudentMapper extends BaseMapper<SysUserBO> {

    List<SysUserBO> queryPageDTO(@Param("limitBegin") Long limitBegin, @Param("limitEnd") Long limitEnd,
        @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    Long countPageDTO(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
