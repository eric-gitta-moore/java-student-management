package org.jeecg.modules.stu.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.stu.entity.Subject;
import org.jeecg.modules.stu.vo.SubjectPage;
import org.jeecg.modules.stu.service.ISubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;


 /**
 * @Description: 科目管理
 * @Author: jeecg-boot
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Api(tags="科目管理")
@RestController
@RequestMapping("/stu/subject")
@Slf4j
public class SubjectController {
	@Autowired
	private ISubjectService subjectService;
	
	/**
	 * 分页列表查询
	 *
	 * @param subject
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "科目管理-分页列表查询")
	@ApiOperation(value="科目管理-分页列表查询", notes="科目管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Subject>> queryPageList(Subject subject,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Subject> queryWrapper = QueryGenerator.initQueryWrapper(subject, req.getParameterMap());
		Page<Subject> page = new Page<Subject>(pageNo, pageSize);
		IPage<Subject> pageList = subjectService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param subjectPage
	 * @return
	 */
	@AutoLog(value = "科目管理-添加")
	@ApiOperation(value="科目管理-添加", notes="科目管理-添加")
    @RequiresPermissions("stu:stu_subject:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SubjectPage subjectPage) {
		Subject subject = new Subject();
		BeanUtils.copyProperties(subjectPage, subject);
		subjectService.saveMain(subject);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param subjectPage
	 * @return
	 */
	@AutoLog(value = "科目管理-编辑")
	@ApiOperation(value="科目管理-编辑", notes="科目管理-编辑")
    @RequiresPermissions("stu:stu_subject:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SubjectPage subjectPage) {
		Subject subject = new Subject();
		BeanUtils.copyProperties(subjectPage, subject);
		Subject subjectEntity = subjectService.getById(subject.getId());
		if(subjectEntity==null) {
			return Result.error("未找到对应数据");
		}
		subjectService.updateMain(subject);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "科目管理-通过id删除")
	@ApiOperation(value="科目管理-通过id删除", notes="科目管理-通过id删除")
    @RequiresPermissions("stu:stu_subject:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		subjectService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "科目管理-批量删除")
	@ApiOperation(value="科目管理-批量删除", notes="科目管理-批量删除")
    @RequiresPermissions("stu:stu_subject:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.subjectService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "科目管理-通过id查询")
	@ApiOperation(value="科目管理-通过id查询", notes="科目管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Subject> queryById(@RequestParam(name="id",required=true) String id) {
		Subject subject = subjectService.getById(id);
		if(subject==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(subject);

	}
	

    /**
    * 导出excel
    *
    * @param request
    * @param subject
    */
    @RequiresPermissions("stu:stu_subject:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Subject subject) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<Subject> queryWrapper = QueryGenerator.initQueryWrapper(subject, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<Subject> subjectList = subjectService.list(queryWrapper);

      // Step.3 组装pageList
      List<SubjectPage> pageList = new ArrayList<SubjectPage>();
      for (Subject main : subjectList) {
          SubjectPage vo = new SubjectPage();
          BeanUtils.copyProperties(main, vo);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "科目管理列表");
      mv.addObject(NormalExcelConstants.CLASS, SubjectPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("科目管理数据", "导出人:"+sysUser.getRealname(), "科目管理"));
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
    @RequiresPermissions("stu:stu_subject:importExcel")
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
              List<SubjectPage> list = ExcelImportUtil.importExcel(file.getInputStream(), SubjectPage.class, params);
              for (SubjectPage page : list) {
                  Subject po = new Subject();
                  BeanUtils.copyProperties(page, po);
                  subjectService.saveMain(po);
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
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
