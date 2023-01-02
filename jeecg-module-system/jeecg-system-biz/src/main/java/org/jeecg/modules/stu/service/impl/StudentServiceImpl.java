package org.jeecg.modules.stu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.stu.dto.StudentDTO;
import org.jeecg.modules.stu.entity.StudentClass;
import org.jeecg.modules.stu.mapper.StudentClassMapper;
import org.jeecg.modules.stu.mapper.StudentMapper;
import org.jeecg.modules.stu.service.IStudentService;
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
public class StudentServiceImpl implements IStudentService {

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
    public IPage<StudentDTO> queryPage(IPage<StudentDTO> page, QueryWrapper<StudentDTO> wrapper, String classId) {
        Long limitBegin = page.offset();
        Long limitEnd = limitBegin + page.getSize();
        QueryWrapper<StudentDTO> studentDTOQueryWrapper = new QueryWrapper<>();
        studentDTOQueryWrapper.orderByDesc("stat_total");
        if (oConvertUtils.isNotEmpty(classId)) {
            studentDTOQueryWrapper.eq("stu_class.id", classId);
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
