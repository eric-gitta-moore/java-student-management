package com.example.modules.stu.manager;

import java.util.List;
import java.util.Map;

import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.modules.stu.bo.SysUserBO;

/**
 * @author w
 */
@Component
public class SysUserManager {

    @Autowired
    private ISysBaseAPI sysBaseAPI;

    public SysUserBO getById(String id) {
        LoginUser user = sysBaseAPI.getUserById(id);
        SysUserBO sysUserBO = new SysUserBO();
        BeanUtils.copyProperties(user, sysUserBO);
        return sysUserBO;
    }

    public Map<String, String> getDepNamesByUserIds(List<String> userIds) {
        return null;
    }
}
