package com.example.modules.stu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.modules.stu.dto.StudentDTO;
import com.example.modules.stu.query.StudentQuery;
import org.jeecg.common.util.oConvertUtils;
import com.example.core.base.service.impl.BaseServiceimpl;
import com.example.modules.stu.entity.StudentClass;
import com.example.modules.stu.mapper.StudentClassMapper;
import com.example.modules.stu.mapper.StudentMapper;
import com.example.modules.stu.service.IStudentService;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author w
 */
@Service
public class StudentServiceImpl extends BaseServiceimpl<StudentMapper, SysUser> implements IStudentService {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser saveStudent(SysUser sysUser, String selectedRoles, String selectedDeparts, String classIds) {
        // 保存用户
        SysUser addedUser = sysUserService.saveUser(sysUser, selectedRoles, selectedDeparts);
        if (oConvertUtils.isNotEmpty(classIds)) {
            String[] arr = classIds.split(",");
            for (String classId : arr) {
                StudentClass studentClass = new StudentClass(addedUser.getId(), classId);
                studentClassMapper.insert(studentClass);
            }
        }
        return addedUser;
    }

    @Override
    public IPage<StudentDTO> queryPage(IPage<StudentDTO> page, QueryWrapper<StudentDTO> wrapper) {
        return queryPage(page, wrapper, null);
    }

    @Override
    public IPage<StudentDTO> queryPage(IPage<StudentDTO> page, QueryWrapper<StudentDTO> wrapper, StudentQuery query) {
        Long limitBegin = page.offset();
        Long limitEnd = limitBegin + page.getSize();
        QueryWrapper<StudentDTO> studentDTOQueryWrapper = new QueryWrapper<>();
        studentDTOQueryWrapper.orderByDesc("stat_total");
        if (oConvertUtils.isNotEmpty(query.getClassId())) {
            studentDTOQueryWrapper.eq("stu_class.id", query.getClassId());
        }
        if (oConvertUtils.isNotEmpty(query.getRealname())) {
            studentDTOQueryWrapper.like("sys_user.realname", query.getRealname());
        }
        if (oConvertUtils.isNotEmpty(query.getWorkNo())) {
            studentDTOQueryWrapper.like("sys_user.work_no", query.getWorkNo());
        }
        List<StudentDTO> pageDTO = studentMapper.queryPageDTO(limitBegin, limitEnd, studentDTOQueryWrapper);
        Page<StudentDTO> currentPage = new Page<>();
        currentPage.setRecords(pageDTO);
        currentPage.setTotal(studentMapper.countPageDTO(studentDTOQueryWrapper));
        currentPage.setSize(page.getSize());
        currentPage.setCurrent(page.getCurrent());
        currentPage.addOrder(OrderItem.desc("total"));
        return currentPage;
    }
}
