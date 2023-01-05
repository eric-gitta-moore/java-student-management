package org.jeecg.modules.stu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.jeecg.modules.stu.dto.resp.CourseScoreStatDTO;
import org.jeecg.modules.stu.entity.TeachingPlan;
import org.jeecg.modules.stu.service.IScoreService;
import org.jeecg.modules.stu.service.ITeachingPlanService;
import org.jeecg.modules.stu.vo.TeachingPlanVO;
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
import java.util.stream.Collectors;


/**
 * @Description: 教学计划
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Api(tags = "教学计划")
@RestController
@RequestMapping("/stu/teachingPlan")
@Slf4j
public class TeachingPlanController {

    @Autowired
    private ITeachingPlanService teachingPlanService;

    @Autowired
    private IScoreService scoreService;

    /**
     * 分页列表查询
     *
     * @param teachingPlan
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "教学计划-分页列表查询")
    @ApiOperation(value = "教学计划-分页列表查询", notes = "教学计划-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<TeachingPlanVO>> queryPageList(TeachingPlan teachingPlan,
        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
        HttpServletRequest req) {
        QueryWrapper<TeachingPlan> queryWrapper = QueryGenerator.initQueryWrapper(teachingPlan, req.getParameterMap());
        Page<TeachingPlan> page = new Page<>(pageNo, pageSize);
        IPage<TeachingPlan> pageList = teachingPlanService.page(page, queryWrapper);
        List<String> courseIds = pageList.getRecords().stream().map(TeachingPlan::getId).collect(Collectors.toList());

        IPage<TeachingPlanVO> result = pageList.convert(e -> {
            TeachingPlanVO teachingPlanVO = new TeachingPlanVO();
            BeanUtils.copyProperties(e, teachingPlanVO);
            return teachingPlanVO;
        });

        // 加入课程统计信息
        Map<String, CourseScoreStatDTO> courseScoreStat = scoreService.getCourseScoreStat(courseIds);
        result.getRecords().forEach(r -> {
            CourseScoreStatDTO stat = courseScoreStat.get(r.getId());
            if (stat == null) {
                return;
            }
            r.setStudentCount(stat.getCount());
            r.setPassCount(stat.getPassCount());
            r.setScoreAverage(String.format("%.2f", stat.getAverage()));
            r.setPassRatio(String.format("%.2f", stat.getPassCount() / Double.valueOf(stat.getCount()) * 100));
        });
        return Result.OK(result);
    }

    /**
     * 添加
     *
     * @param teachingPlanVO
     * @return
     */
    @AutoLog(value = "教学计划-添加")
    @ApiOperation(value = "教学计划-添加", notes = "教学计划-添加")
    @RequiresPermissions("stu:stu_teaching_plan:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody TeachingPlanVO teachingPlanVO) {
        TeachingPlan teachingPlan = new TeachingPlan();
        BeanUtils.copyProperties(teachingPlanVO, teachingPlan);
        teachingPlanService.saveMain(teachingPlan);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param teachingPlanVO
     * @return
     */
    @AutoLog(value = "教学计划-编辑")
    @ApiOperation(value = "教学计划-编辑", notes = "教学计划-编辑")
    @RequiresPermissions("stu:stu_teaching_plan:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody TeachingPlanVO teachingPlanVO) {
        TeachingPlan teachingPlan = new TeachingPlan();
        BeanUtils.copyProperties(teachingPlanVO, teachingPlan);
        TeachingPlan teachingPlanEntity = teachingPlanService.getById(teachingPlan.getId());
        if (teachingPlanEntity == null) {
            return Result.error("未找到对应数据");
        }
        teachingPlanService.updateMain(teachingPlan);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "教学计划-通过id删除")
    @ApiOperation(value = "教学计划-通过id删除", notes = "教学计划-通过id删除")
    @RequiresPermissions("stu:stu_teaching_plan:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        teachingPlanService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "教学计划-批量删除")
    @ApiOperation(value = "教学计划-批量删除", notes = "教学计划-批量删除")
    @RequiresPermissions("stu:stu_teaching_plan:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.teachingPlanService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "教学计划-通过id查询")
    @ApiOperation(value = "教学计划-通过id查询", notes = "教学计划-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<TeachingPlan> queryById(@RequestParam(name = "id", required = true) String id) {
        TeachingPlan teachingPlan = teachingPlanService.getById(id);
        if (teachingPlan == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(teachingPlan);

    }


    /**
     * 导出excel
     *
     * @param request
     * @param teachingPlan
     */
    @RequiresPermissions("stu:stu_teaching_plan:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TeachingPlan teachingPlan) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<TeachingPlan> queryWrapper = QueryGenerator.initQueryWrapper(teachingPlan,
            request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        //Step.2 获取导出数据
        List<TeachingPlan> teachingPlanList = teachingPlanService.list(queryWrapper);

        // Step.3 组装pageList
        List<TeachingPlanVO> pageList = new ArrayList<TeachingPlanVO>();
        for (TeachingPlan main : teachingPlanList) {
            TeachingPlanVO vo = new TeachingPlanVO();
            BeanUtils.copyProperties(main, vo);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "教学计划列表");
        mv.addObject(NormalExcelConstants.CLASS, TeachingPlanVO.class);
        mv.addObject(NormalExcelConstants.PARAMS,
            new ExportParams("教学计划数据", "导出人:" + sysUser.getRealname(), "教学计划"));
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
    @RequiresPermissions("stu:stu_teaching_plan:importExcel")
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
                List<TeachingPlanVO> list = ExcelImportUtil.importExcel(file.getInputStream(), TeachingPlanVO.class,
                    params);
                for (TeachingPlanVO page : list) {
                    TeachingPlan po = new TeachingPlan();
                    BeanUtils.copyProperties(page, po);
                    teachingPlanService.saveMain(po);
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
