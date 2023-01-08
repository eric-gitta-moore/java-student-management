package com.example.modules.stu.service.impl;

import java.util.List;

import org.jeecg.common.util.oConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.core.base.service.impl.BaseServiceimpl;
import com.example.modules.stu.bo.SysUserBO;
import com.example.modules.stu.entity.StudentClass;
import com.example.modules.stu.manager.SysUserManager;
import com.example.modules.stu.mapper.StudentClassMapper;
import com.example.modules.stu.mapper.StudentMapper;
import com.example.modules.stu.query.StudentQuery;
import com.example.modules.stu.service.IStudentService;

/**
 * @author w
 */
@Service
public class StudentServiceImpl extends BaseServiceimpl<StudentMapper, SysUserBO> implements IStudentService {

    @Autowired
    private SysUserManager sysUserManager;

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private StudentMapper studentMapper;

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public SysUserBO saveStudent(SysUserBO sysUser, String selectedRoles, String selectedDeparts, String classIds) {
//        // 保存用户
//        SysUserBO addedUser = sysUserService.saveUser(sysUser, selectedRoles, selectedDeparts);
//        if (oConvertUtils.isNotEmpty(classIds)) {
//            String[] arr = classIds.split(",");
//            for (String classId : arr) {
//                StudentClass studentClass = new StudentClass(addedUser.getId(), classId);
//                studentClassMapper.insert(studentClass);
//            }
//        }
//        return addedUser;
//    }

    /**
     * 手动分页查询
     *
     * @param page
     * @param wrapper
     * @return
     */
    @Override
    public IPage<SysUserBO> queryPage(IPage<StudentQuery> page, QueryWrapper<SysUserBO> wrapper) {
        return queryPage(page, wrapper, null);
    }

    @Override
    public IPage<SysUserBO> queryPage(IPage<StudentQuery> page, QueryWrapper<SysUserBO> wrapper, StudentQuery query) {
        Long limitBegin = page.offset();
        Long limitEnd = limitBegin + page.getSize();
        QueryWrapper<StudentQuery> studentDTOQueryWrapper = new QueryWrapper<>();
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
        List<SysUserBO> pageDTO = studentMapper.queryPageDTO(limitBegin, limitEnd, studentDTOQueryWrapper);
        Page<SysUserBO> currentPage = new Page<>();
        currentPage.setRecords(pageDTO);
        currentPage.setTotal(studentMapper.countPageDTO(studentDTOQueryWrapper));
        currentPage.setSize(page.getSize());
        currentPage.setCurrent(page.getCurrent());
        currentPage.addOrder(OrderItem.desc("total"));
        return currentPage;
    }

}
