package com.example.modules.stu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.core.base.service.IBaseService;
import com.example.modules.stu.bo.SysUserBO;
import com.example.modules.stu.query.StudentQuery;

/**
 * @author w
 */
public interface IStudentService extends IBaseService<SysUserBO> {

//    /**
//     * 添加学生
//     *
//     * @param sysUser
//     * @param selectedRoles
//     * @param selectedDeparts
//     * @param classIds
//     * @return SysUser
//     */
//    SysUserBO saveStudent(SysUserBO sysUser, String selectedRoles, String selectedDeparts, String classIds);

    /**
     * 手动分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<SysUserBO> queryPage(IPage<StudentQuery> page, QueryWrapper<SysUserBO> wrapper);

    IPage<SysUserBO> queryPage(IPage<StudentQuery> page, QueryWrapper<SysUserBO> wrapper, StudentQuery query);
}
