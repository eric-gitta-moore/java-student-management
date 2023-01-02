package org.jeecg.modules.stu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.stu.dto.StudentDTO;
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
