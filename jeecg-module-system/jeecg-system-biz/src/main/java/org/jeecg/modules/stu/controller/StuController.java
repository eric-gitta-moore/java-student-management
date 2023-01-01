package org.jeecg.modules.stu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.stu.vo.StuUserPage;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.entity.SysUserDepart;
import org.jeecg.modules.system.service.ISysDepartService;
import org.jeecg.modules.system.service.ISysUserDepartService;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "学生管理")
@RestController
@RequestMapping("/stu/stu")
@Slf4j
public class StuController {

    @Autowired
    private ISysDepartService sysDepartService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysUserDepartService sysUserDepartService;

    @GetMapping("/list")
    @ApiOperation(value = "学生列表-分页列表查询", notes = "学生列表-分页列表查询")
    public Result<IPage<StuUserPage>> queryStuPageList(SysUser user,
        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize, HttpServletRequest req) {
        QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(user, req.getParameterMap());
        //部门ID
        String departId = req.getParameter("departId");
        if (oConvertUtils.isNotEmpty(departId)) {
            LambdaQueryWrapper<SysUserDepart> query = new LambdaQueryWrapper<>();
            query.eq(SysUserDepart::getDepId, departId);
            List<SysUserDepart> list = sysUserDepartService.list(query);
            List<String> userIds = list.stream().map(SysUserDepart::getUserId).collect(Collectors.toList());
            if (oConvertUtils.listIsNotEmpty(userIds)) {
                queryWrapper.in("id", userIds);
            } else {
                return Result.OK();
            }
        }
        //用户ID
        String code = req.getParameter("code");
        if (oConvertUtils.isNotEmpty(code)) {
            queryWrapper.in("id", Arrays.asList(code.split(",")));
            pageSize = code.split(",").length;
        }
        String status = req.getParameter("status");
        if (oConvertUtils.isNotEmpty(status)) {
            queryWrapper.eq("status", Integer.parseInt(status));
        }
        queryWrapper.ne("username", "_reserve_user_external");
        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
        IPage<SysUser> pageList = sysUserService.page(page, queryWrapper);

        //批量查询用户的所属部门
        //step.1 先拿到全部的 useids
        //step.2 通过 useids，一次性查询用户的所属部门名字
        List<String> userIds = pageList.getRecords().stream().map(SysUser::getId).collect(Collectors.toList());
        if (userIds != null && userIds.size() > 0) {
            Map<String, String> useDepNames = sysUserService.getDepNamesByUserIds(userIds);
            pageList.getRecords().forEach(item -> {
                item.setOrgCodeTxt(useDepNames.get(item.getId()));
            });
        }

        // 拿到学生的上机部门名称 -> 学院名称
        LambdaQueryWrapper<SysUserDepart> sysUserDepartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserDepartLambdaQueryWrapper.in(SysUserDepart::getUserId, userIds);
        List<SysUserDepart> userDepartList = sysUserDepartService.list(sysUserDepartLambdaQueryWrapper);
        Map<String, SysDepart> parentDepartMap = sysDepartService.queryParentDepartsByDepartIds(
            userDepartList.stream().map(SysUserDepart::getDepId).distinct().collect(
                Collectors.toList()));
        log.debug("父级部门->>>>> " + parentDepartMap);
        IPage<StuUserPage> stuUserPage = pageList.convert(currentUserItem -> {
            StuUserPage tempUserPage = new StuUserPage();
            BeanUtils.copyProperties(currentUserItem, tempUserPage);
            List<SysUserDepart> userDeparts = userDepartList.stream()
                .filter(e -> e.getUserId().equals(currentUserItem.getId())).collect(Collectors.toList());
            List<String> userDepartIds = userDeparts.stream().map(SysUserDepart::getDepId).collect(Collectors.toList());
            Set<String> userParentDepartIds = new HashSet<>();
            for (String tempDepartId : userDepartIds) {
                if (!parentDepartMap.containsKey(tempDepartId)) {
                    continue;
                }
                userParentDepartIds.add(parentDepartMap.get(tempDepartId).getId());
            }
            tempUserPage.setParentDepartId(String.join(",", userParentDepartIds));
            return tempUserPage;
        });
        log.debug("学生列表-分页列表查询->>>>>>>>>>>>" + stuUserPage.getRecords());
        return Result.ok(stuUserPage);
    }
}
