package org.jeecg.modules.stu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.stu.dto.StudentDTO;
import org.jeecg.modules.stu.query.StudentQuery;
import org.jeecg.modules.system.entity.SysUser;

/**
 * @author w
 */
public interface IStudentService {

    /**
     * 添加学生
     *
     * @param sysUser
     * @param selectedRoles
     * @param selectedDeparts
     * @param classIds
     * @return SysUser
     */
    SysUser saveStudent(SysUser sysUser, String selectedRoles, String selectedDeparts, String classIds);

    /**
     * 手动分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<StudentDTO> queryPage(IPage<StudentDTO> page, QueryWrapper<StudentDTO> wrapper);
    IPage<StudentDTO> queryPage(IPage<StudentDTO> page, QueryWrapper<StudentDTO> wrapper, StudentQuery query);
}
