package org.jeecg.modules.stu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.jeecg.core.base.mapper.BaseMapper;
import org.jeecg.modules.stu.dto.resp.StudentDTO;
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
