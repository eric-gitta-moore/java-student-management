package org.jeecg.modules.stu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.core.base.service.IBaseService;
import org.jeecg.modules.stu.dto.req.StudentDTO;
import org.jeecg.modules.system.entity.SysUser;

/**
 * @author w
 */
public interface IStudentService extends IBaseService<SysUser> {

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
    IPage<org.jeecg.modules.stu.dto.resp.StudentDTO> queryPage(IPage<org.jeecg.modules.stu.dto.resp.StudentDTO> page, QueryWrapper<org.jeecg.modules.stu.dto.resp.StudentDTO> wrapper);

    IPage<org.jeecg.modules.stu.dto.resp.StudentDTO> queryPage(IPage<org.jeecg.modules.stu.dto.resp.StudentDTO> page, QueryWrapper<org.jeecg.modules.stu.dto.resp.StudentDTO> wrapper, StudentDTO query);
}
