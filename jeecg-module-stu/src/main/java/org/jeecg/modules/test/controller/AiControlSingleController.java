package org.jeecg.modules.test.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.test.entity.AiControlSingle;
import org.jeecg.modules.test.service.IAiControlSingleService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 表单控件@单表
 * @Author: jeecg-boot
 * @Date:   2023-01-02
 * @Version: V1.0
 */
@Api(tags="表单控件@单表")
@RestController
@RequestMapping("/test/aiControlSingle")
@Slf4j
public class AiControlSingleController extends JeecgController<AiControlSingle, IAiControlSingleService> {
	@Autowired
	private IAiControlSingleService aiControlSingleService;
	
	/**
	 * 分页列表查询
	 *
	 * @param aiControlSingle
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "表单控件@单表-分页列表查询")
	@ApiOperation(value="表单控件@单表-分页列表查询", notes="表单控件@单表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<AiControlSingle>> queryPageList(AiControlSingle aiControlSingle,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AiControlSingle> queryWrapper = QueryGenerator.initQueryWrapper(aiControlSingle, req.getParameterMap());
		Page<AiControlSingle> page = new Page<AiControlSingle>(pageNo, pageSize);
		IPage<AiControlSingle> pageList = aiControlSingleService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param aiControlSingle
	 * @return
	 */
	@AutoLog(value = "表单控件@单表-添加")
	@ApiOperation(value="表单控件@单表-添加", notes="表单控件@单表-添加")
	@RequiresPermissions("test:ai_control_single:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody AiControlSingle aiControlSingle) {
		aiControlSingleService.save(aiControlSingle);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param aiControlSingle
	 * @return
	 */
	@AutoLog(value = "表单控件@单表-编辑")
	@ApiOperation(value="表单控件@单表-编辑", notes="表单控件@单表-编辑")
	@RequiresPermissions("test:ai_control_single:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody AiControlSingle aiControlSingle) {
		aiControlSingleService.updateById(aiControlSingle);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表单控件@单表-通过id删除")
	@ApiOperation(value="表单控件@单表-通过id删除", notes="表单控件@单表-通过id删除")
	@RequiresPermissions("test:ai_control_single:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		aiControlSingleService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表单控件@单表-批量删除")
	@ApiOperation(value="表单控件@单表-批量删除", notes="表单控件@单表-批量删除")
	@RequiresPermissions("test:ai_control_single:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.aiControlSingleService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "表单控件@单表-通过id查询")
	@ApiOperation(value="表单控件@单表-通过id查询", notes="表单控件@单表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<AiControlSingle> queryById(@RequestParam(name="id",required=true) String id) {
		AiControlSingle aiControlSingle = aiControlSingleService.getById(id);
		if(aiControlSingle==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(aiControlSingle);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param aiControlSingle
    */
    @RequiresPermissions("test:ai_control_single:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, AiControlSingle aiControlSingle) {
        return super.exportXls(request, aiControlSingle, AiControlSingle.class, "表单控件@单表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("test:ai_control_single:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, AiControlSingle.class);
    }

}
