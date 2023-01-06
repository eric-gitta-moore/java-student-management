package com.example.modules.stu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.modules.stu.entity.StuClassInfo;
import com.example.modules.stu.service.IStuClassService;
import com.example.modules.stu.vo.StuClassVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @Description: 学生班级
 * @Author: jeecg-boot
 * @Date: 2022-12-31
 * @Version: V1.0
 */
@Api(tags = "学生班级")
@RestController
@RequestMapping("/stu/stuClass")
@Slf4j
public class StuClassController {

    @Autowired
    private IStuClassService stuClassService;

    /**
     * 分页列表查询
     *
     * @param stuClassInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "学生班级-分页列表查询")
    @ApiOperation(value = "学生班级-分页列表查询", notes = "学生班级-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<StuClassInfo>> queryPageList(StuClassInfo stuClassInfo,
        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
        HttpServletRequest req) {
        QueryWrapper<StuClassInfo> queryWrapper = QueryGenerator.initQueryWrapper(stuClassInfo, req.getParameterMap());
        Page<StuClassInfo> page = new Page<StuClassInfo>(pageNo, pageSize);
        IPage<StuClassInfo> pageList = stuClassService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param stuClassVO
     * @return
     */
    @AutoLog(value = "学生班级-添加")
    @ApiOperation(value = "学生班级-添加", notes = "学生班级-添加")
    @RequiresPermissions("stu:stu_class:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody StuClassVO stuClassVO) {
        StuClassInfo stuClassInfo = new StuClassInfo();
        BeanUtils.copyProperties(stuClassVO, stuClassInfo);
        stuClassService.saveMain(stuClassInfo);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param stuClassVO
     * @return
     */
    @AutoLog(value = "学生班级-编辑")
    @ApiOperation(value = "学生班级-编辑", notes = "学生班级-编辑")
    @RequiresPermissions("stu:stu_class:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody StuClassVO stuClassVO) {
        StuClassInfo stuClassInfo = new StuClassInfo();
        BeanUtils.copyProperties(stuClassVO, stuClassInfo);
        StuClassInfo stuClassInfoEntity = stuClassService.getById(stuClassInfo.getId());
        if (stuClassInfoEntity == null) {
            return Result.error("未找到对应数据");
        }
        stuClassService.updateMain(stuClassInfo);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "学生班级-通过id删除")
    @ApiOperation(value = "学生班级-通过id删除", notes = "学生班级-通过id删除")
    @RequiresPermissions("stu:stu_class:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        stuClassService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "学生班级-批量删除")
    @ApiOperation(value = "学生班级-批量删除", notes = "学生班级-批量删除")
    @RequiresPermissions("stu:stu_class:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.stuClassService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "学生班级-通过id查询")
    @ApiOperation(value = "学生班级-通过id查询", notes = "学生班级-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<StuClassInfo> queryById(@RequestParam(name = "id", required = true) String id) {
        StuClassInfo stuClassInfo = stuClassService.getById(id);
        if (stuClassInfo == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(stuClassInfo);

    }


    /**
     * 导出excel
     *
     * @param request
     * @param stuClassInfo
     */
    @RequiresPermissions("stu:stu_class:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, StuClassInfo stuClassInfo) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<StuClassInfo> queryWrapper = QueryGenerator.initQueryWrapper(stuClassInfo, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        //Step.2 获取导出数据
        List<StuClassInfo> stuClassInfoList = stuClassService.list(queryWrapper);

        // Step.3 组装pageList
        List<StuClassVO> pageList = new ArrayList<StuClassVO>();
        for (StuClassInfo main : stuClassInfoList) {
            StuClassVO vo = new StuClassVO();
            BeanUtils.copyProperties(main, vo);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "学生班级列表");
        mv.addObject(NormalExcelConstants.CLASS, StuClassVO.class);
        mv.addObject(NormalExcelConstants.PARAMS,
            new ExportParams("学生班级数据", "导出人:" + sysUser.getRealname(), "学生班级"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequiresPermissions("stu:stu_class:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<StuClassVO> list = ExcelImportUtil.importExcel(file.getInputStream(), StuClassVO.class,
                    params);
                for (StuClassVO page : list) {
                    StuClassInfo po = new StuClassInfo();
                    BeanUtils.copyProperties(page, po);
                    stuClassService.saveMain(po);
                }
                return Result.OK("文件导入成功！数据行数:" + list.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.OK("文件导入失败！");
    }

}
