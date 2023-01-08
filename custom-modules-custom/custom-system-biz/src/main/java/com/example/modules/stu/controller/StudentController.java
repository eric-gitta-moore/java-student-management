package com.example.modules.stu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.modules.stu.dto.StudentDTO;
import com.example.modules.stu.query.StudentQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CommonConstant;
import com.example.core.base.controller.BaseRestController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import com.example.core.common.metadata.IPagination;
import org.jeecg.modules.base.service.BaseCommonService;
import com.example.modules.stu.service.IScoreService;
import com.example.modules.stu.service.IStuClassService;
import com.example.modules.stu.service.IStudentService;
import com.example.modules.stu.vo.StuUserVO;
import com.example.modules.stu.vo.StudentVO;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysDepartService;
import org.jeecg.modules.system.service.ISysUserDepartService;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "学生管理")
@RestController
@RequestMapping("/stu/stu")
@Slf4j
public class StudentController extends BaseRestController<SysUser, String> {

    @Autowired
    private ISysDepartService sysDepartService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserDepartService sysUserDepartService;

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private BaseCommonService baseCommonService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IStuClassService stuClassService;

    @GetMapping("/list2")
    @ApiOperation(value = "学生管理-高性能分页", notes = "学生管理-高性能分页")
    public Result<IPagination<StudentVO>> query() {

        return null;
    }


    @GetMapping("/list")
    @ApiOperation(value = "学生管理-分页列表查询", notes = "学生管理-分页列表查询")
    public Result<IPage<StudentVO>> queryStuPageList(StuUserVO user,
        @RequestParam(defaultValue = "1") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        HttpServletRequest req, StudentQuery studentQuery) {
        if (oConvertUtils.isEmpty(studentQuery.getClassId())) {
            return Result.ok(new Page<>());
        }
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(user, studentDTO);
        QueryWrapper<StudentDTO> queryWrapper = QueryGenerator.initQueryWrapper(studentDTO, req.getParameterMap());

        // 账号状态
        String status = req.getParameter("status");
        if (oConvertUtils.isNotEmpty(status)) {
            queryWrapper.eq("sys_user.status", Integer.parseInt(status));
        }
        queryWrapper.ne("sys_user.username", "_reserve_user_external");
        queryWrapper.eq("sys_user.post", "student");
        Page<StudentDTO> page = new Page<>(pageNo, pageSize);
        IPage<StudentDTO> pageList = studentService.queryPage(page, queryWrapper, studentQuery);
        if (pageList.getTotal() == 0) {
            return Result.ok(new Page<>());
        }

        //批量查询用户的所属部门
        //step.1 先拿到全部的 useids
        //step.2 通过 useids，一次性查询用户的所属部门名字
        List<String> userIds = pageList.getRecords().stream().map(StudentDTO::getId).collect(Collectors.toList());
        if (userIds != null && userIds.size() > 0) {
            Map<String, String> useDepNames = sysUserService.getDepNamesByUserIds(userIds);
            pageList.getRecords().forEach(item -> {
                item.setOrgCodeTxt(useDepNames.get(item.getId()));
            });
        }

        // 转换结果输出
        IPage<StudentVO> stuUserPage = pageList.convert(currentUserItem -> {
            StudentVO tempUserPage = new StudentVO();
            BeanUtils.copyProperties(currentUserItem, tempUserPage);
            return tempUserPage;
        });
        return Result.ok(stuUserPage);
    }

    @PostMapping("/add")
    @ApiOperation(value = "学生管理-添加", notes = "学生管理-添加")
    public Result<?> add(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<>();
        String selectedRoles = jsonObject.getString("selectedroles");
        String selectedDeparts = jsonObject.getString("selecteddeparts");
        String selectedClassIds = jsonObject.getString("selectedClasses");
        try {
            SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
            user.setCreateTime(new Date());//设置创建时间
            String salt = oConvertUtils.randomGen(8);
            user.setSalt(salt);
            String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
            user.setPassword(passwordEncode);
            user.setStatus(1);
            user.setDelFlag(CommonConstant.DEL_FLAG_0);
            //用户表字段org_code不能在这里设置他的值
            user.setOrgCode(null);
            // 保存用户走一个service 保证事务
            studentService.saveStudent(user, selectedRoles, selectedDeparts, selectedClassIds);
            baseCommonService.addLog("添加学生用户，username： " + user.getUsername(), CommonConstant.LOG_TYPE_2, 2);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }


    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<SysUser> edit(@RequestBody JSONObject jsonObject) {
        Result<SysUser> result = new Result<SysUser>();
        try {
            SysUser sysUser = sysUserService.getById(jsonObject.getString("id"));
            baseCommonService.addLog("编辑用户，username： " + sysUser.getUsername(), CommonConstant.LOG_TYPE_2, 2);
            if (sysUser == null) {
                result.error500("未找到对应实体");
            } else {
                SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
                user.setUpdateTime(new Date());
                //String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), sysUser.getSalt());
                user.setPassword(sysUser.getPassword());
                String roles = jsonObject.getString("selectedroles");
                String departs = jsonObject.getString("selecteddeparts");
                if (oConvertUtils.isEmpty(departs)) {
                    //vue3.0前端只传递了departIds
                    departs = user.getDepartIds();
                }
                //用户表字段org_code不能在这里设置他的值
                user.setOrgCode(null);
                // 修改用户走一个service 保证事务
                sysUserService.editUser(user, roles, departs);
                result.success("修改成功!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }
}
