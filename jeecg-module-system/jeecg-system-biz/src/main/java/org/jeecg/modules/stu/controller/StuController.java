package org.jeecg.modules.stu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.controller.SysUserController;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "学生管理")
@RestController
@RequestMapping("/stu/stu")
@Slf4j
public class StuController extends SysUserController {

    @Autowired
    private ISysDepartService sysDepartService;

    @Override
    public Result<IPage<SysUser>> queryPageList(SysUser user, Integer pageNo, Integer pageSize,
        HttpServletRequest req) {
        Result<IPage<SysUser>> userPage = super.queryPageList(user, pageNo, pageSize, req);
        // 拿到学生的上机部门名称 -> 学院名称
        List<String> departCodeList = userPage.getResult().getRecords().stream().map(SysUser::getOrgCode).collect(
            Collectors.toList());
//        Map<String,String> departPidList = sysDepartService.query
        return null;
    }
}
