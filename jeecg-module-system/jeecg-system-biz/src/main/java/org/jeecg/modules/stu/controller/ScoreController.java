package org.jeecg.modules.stu.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import org.jeecg.modules.stu.entity.Score;
import org.jeecg.modules.stu.service.IScoreService;
import org.jeecg.modules.stu.vo.ScorePage;
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
 * @Description: 学生成绩
 * @Author: jeecg-boot
 * @Date: 2022-12-29
 * @Version: V1.0
 */
@Api(tags = "学生成绩")
@RestController
@RequestMapping("/stu/score")
@Slf4j
public class ScoreController {

    @Autowired
    private IScoreService scoreService;

    /**
     * 分页列表查询
     *
     * @param score
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "学生成绩-分页列表查询")
    @ApiOperation(value = "学生成绩-分页列表查询", notes = "学生成绩-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<Score>> queryPageList(Score score,
        @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
        HttpServletRequest req) {
        QueryWrapper<Score> queryWrapper = QueryGenerator.initQueryWrapper(score, req.getParameterMap());
        Page<Score> page = new Page<Score>(pageNo, pageSize);
        IPage<Score> pageList = scoreService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param scorePage
     * @return
     */
    @AutoLog(value = "学生成绩-添加")
    @ApiOperation(value = "学生成绩-添加", notes = "学生成绩-添加")
    @RequiresPermissions("stu:stu_score:add")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody ScorePage scorePage) {
        LambdaQueryWrapper<Score> query = new LambdaQueryWrapper<>();
        query.eq(Score::getCourseId, scorePage.getCourseId());
        query.eq(Score::getStudentId, scorePage.getStudentId());
        Score scoreExist = scoreService.getOne(query);
        if (scoreExist != null) {
            return Result.error("成绩已经存在");
        }
        Score score = new Score();
        BeanUtils.copyProperties(scorePage, score);
        scoreService.saveMain(score);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param scorePage
     * @return
     */
    @AutoLog(value = "学生成绩-编辑")
    @ApiOperation(value = "学生成绩-编辑", notes = "学生成绩-编辑")
    @RequiresPermissions("stu:stu_score:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT, RequestMethod.POST})
    public Result<String> edit(@RequestBody ScorePage scorePage) {
        Score score = new Score();
        BeanUtils.copyProperties(scorePage, score);
        Score scoreEntity = scoreService.getById(score.getId());
        if (scoreEntity == null) {
            return Result.error("未找到对应数据");
        }
        scoreService.updateMain(score);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "学生成绩-通过id删除")
    @ApiOperation(value = "学生成绩-通过id删除", notes = "学生成绩-通过id删除")
    @RequiresPermissions("stu:stu_score:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name = "id", required = true) String id) {
        scoreService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "学生成绩-批量删除")
    @ApiOperation(value = "学生成绩-批量删除", notes = "学生成绩-批量删除")
    @RequiresPermissions("stu:stu_score:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.scoreService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    //@AutoLog(value = "学生成绩-通过id查询")
    @ApiOperation(value = "学生成绩-通过id查询", notes = "学生成绩-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Score> queryById(@RequestParam(name = "id", required = true) String id) {
        Score score = scoreService.getById(id);
        if (score == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(score);

    }


    /**
     * 导出excel
     *
     * @param request
     * @param score
     */
    @RequiresPermissions("stu:stu_score:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Score score) {
        // Step.1 组装查询条件查询数据
        QueryWrapper<Score> queryWrapper = QueryGenerator.initQueryWrapper(score, request.getParameterMap());
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

        //配置选中数据查询条件
        String selections = request.getParameter("selections");
        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            queryWrapper.in("id", selectionList);
        }
        //Step.2 获取导出数据
        List<Score> scoreList = scoreService.list(queryWrapper);

        // Step.3 组装pageList
        List<ScorePage> pageList = new ArrayList<ScorePage>();
        for (Score main : scoreList) {
            ScorePage vo = new ScorePage();
            BeanUtils.copyProperties(main, vo);
            pageList.add(vo);
        }

        // Step.4 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "学生成绩列表");
        mv.addObject(NormalExcelConstants.CLASS, ScorePage.class);
        mv.addObject(NormalExcelConstants.PARAMS,
            new ExportParams("学生成绩数据", "导出人:" + sysUser.getRealname(), "学生成绩"));
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
    @RequiresPermissions("stu:stu_score:importExcel")
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
                List<ScorePage> list = ExcelImportUtil.importExcel(file.getInputStream(), ScorePage.class, params);
                for (ScorePage page : list) {
                    Score po = new Score();
                    BeanUtils.copyProperties(page, po);
                    scoreService.saveMain(po);
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
