package com.example.modules.stu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.modules.stu.dto.resp.StuClassDTO;
import com.example.modules.stu.entity.StuClassInfo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
public interface IStuClassService extends IService<StuClassInfo> {

    /**
     * 添加一对多
     *
     * @param stuClassInfo
     */
    public void saveMain(StuClassInfo stuClassInfo);

    /**
     * 修改一对多
     *
     * @param stuClassInfo
     */
    public void updateMain(StuClassInfo stuClassInfo);

    /**
     * 删除一对多
     *
     * @param id
     */
    public void delMain(String id);

    /**
     * 批量删除一对多
     *
     * @param idList
     */
    public void delBatchMain(Collection<? extends Serializable> idList);

    /**
     * 批量查询班级信息
     *
     * @param studentIds
     * @return
     */
    Map<String, StuClassDTO> queryStuClasses(List<String> studentIds);

}
