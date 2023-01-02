package org.jeecg.modules.test.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 表单控件@单表
 * @Author: jeecg-boot
 * @Date:   2023-01-02
 * @Version: V1.0
 */
@Data
@TableName("ai_control_single")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ai_control_single对象", description="表单控件@单表")
public class AiControlSingle implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**单价*/
	@Excel(name = "单价", width = 15)
    @ApiModelProperty(value = "单价")
    private java.math.BigDecimal price;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
    private java.lang.String name;
	/**密码*/
	@Excel(name = "密码", width = 15)
    @ApiModelProperty(value = "密码")
    private java.lang.String miMa;
	/**字典下拉*/
	@Excel(name = "字典下拉", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "字典下拉")
    private java.lang.String xiala;
	/**字典单选*/
	@Excel(name = "字典单选", width = 15, dicCode = "sex")
	@Dict(dicCode = "sex")
    @ApiModelProperty(value = "字典单选")
    private java.lang.String danxuan;
	/**字典多选*/
	@Excel(name = "字典多选", width = 15, dicCode = "urgent_level")
	@Dict(dicCode = "urgent_level")
    @ApiModelProperty(value = "字典多选")
    private java.lang.String duoxuan;
	/**开关*/
	@Excel(name = "开关", width = 15)
    @ApiModelProperty(value = "开关")
    private java.lang.String kaiguan;
	/**日期*/
	@Excel(name = "日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "日期")
    private java.util.Date riqi;
	/**年月日时分秒*/
	@Excel(name = "年月日时分秒", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "年月日时分秒")
    private java.util.Date nyrsfm;
	/**时间*/
	@Excel(name = "时间", width = 15)
    @ApiModelProperty(value = "时间")
    private java.lang.String shijian;
	/**文件*/
	@Excel(name = "文件", width = 15)
    @ApiModelProperty(value = "文件")
    private java.lang.String wenjian;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private java.lang.String tupian;
	/**多行文本框*/
	@Excel(name = "多行文本框", width = 15)
    @ApiModelProperty(value = "多行文本框")
    private java.lang.String dhwb;
	/**字典下拉多选框*/
	@Excel(name = "字典下拉多选框", width = 15, dicCode = "urgent_level")
	@Dict(dicCode = "urgent_level")
    @ApiModelProperty(value = "字典下拉多选框")
    private java.lang.String xldx;
	/**字典表下拉搜索框*/
	@Excel(name = "字典表下拉搜索框", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "字典表下拉搜索框")
    private java.lang.String xlss;
	/**popup弹窗*/
	@Excel(name = "popup弹窗", width = 15)
    @ApiModelProperty(value = "popup弹窗")
    private java.lang.String popup;
	/**popback*/
	@Excel(name = "popback", width = 15)
    @ApiModelProperty(value = "popback")
    private java.lang.String popback;
	/**分类字典树*/
	@Excel(name = "分类字典树", width = 15)
    @ApiModelProperty(value = "分类字典树")
    private java.lang.String flzds;
	/**部门选择*/
	@Excel(name = "部门选择", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "部门选择")
    private java.lang.String bmxz;
	/**用户选择*/
	@Excel(name = "用户选择", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "用户选择")
    private java.lang.String yhxz;
	/**富文本*/
	@Excel(name = "富文本", width = 15)
    @ApiModelProperty(value = "富文本")
    private java.lang.String fwb;
	/**markdown*/
	@Excel(name = "markdown", width = 15)
    private transient java.lang.String markdownString;

    private byte[] markdown;

    public byte[] getMarkdown(){
        if(markdownString==null){
            return null;
        }
        try {
            return markdownString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMarkdownString(){
        if(markdown==null || markdown.length==0){
            return "";
        }
        try {
            return new String(markdown,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
	/**省市区*/
	@Excel(name = "省市区", width = 15)
    @ApiModelProperty(value = "省市区")
    private java.lang.String shq;
	/**联动组件一*/
	@Excel(name = "联动组件一", width = 15)
    @ApiModelProperty(value = "联动组件一")
    private java.lang.String ldzuy;
	/**联动组件二*/
	@Excel(name = "联动组件二", width = 15)
    @ApiModelProperty(value = "联动组件二")
    private java.lang.String ldzje;
	/**联动组件三*/
	@Excel(name = "联动组件三", width = 15)
    @ApiModelProperty(value = "联动组件三")
    private java.lang.String ldzjs;
	/**自定义树*/
	@Excel(name = "自定义树", width = 15, dictTable = "sys_category", dicText = "name", dicCode = "id")
	@Dict(dictTable = "sys_category", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "自定义树")
    private java.lang.String zdys;
	/**double类型*/
	@Excel(name = "double类型", width = 15)
    @ApiModelProperty(value = "double类型")
    private java.lang.Double yuanjia;
	/**integer类型*/
	@Excel(name = "integer类型", width = 15)
    @ApiModelProperty(value = "integer类型")
    private java.lang.Integer geshu;
	/**唯一检验*/
	@Excel(name = "唯一检验", width = 15)
    @ApiModelProperty(value = "唯一检验")
    private java.lang.String jycs;
	/**输入2到10位的字母*/
	@Excel(name = "输入2到10位的字母", width = 15)
    @ApiModelProperty(value = "输入2到10位的字母")
    private java.lang.String province;
	/**自定义查询*/
	@Excel(name = "自定义查询", width = 15)
    @ApiModelProperty(value = "自定义查询")
    private java.lang.String zdmrz;
	/**字典表下拉*/
	@Excel(name = "字典表下拉", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "字典表下拉")
    private java.lang.String zdbxl;
	/**字典表单选*/
	@Excel(name = "字典表单选", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "字典表单选")
    private java.lang.String zdbdx;
	/**字典表多选*/
	@Excel(name = "字典表多选", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "字典表多选")
    private java.lang.String zdbduoxuan;
	/**字典表下拉多选*/
	@Excel(name = "字典表下拉多选", width = 15, dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
	@Dict(dictTable = "sys_depart", dicText = "depart_name", dicCode = "id")
    @ApiModelProperty(value = "字典表下拉多选")
    private java.lang.String zdbxldx;
	/**字典表带条件下拉*/
	@Excel(name = "字典表带条件下拉", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "字典表带条件下拉")
    private java.lang.String zddtjxl;
	/**字典表带条件单选*/
	@Excel(name = "字典表带条件单选", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "字典表带条件单选")
    private java.lang.String zddtjdx;
	/**字典表带条件多选*/
	@Excel(name = "字典表带条件多选", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "字典表带条件多选")
    private java.lang.String zddtjduox;
	/**字典表带条件下拉多选*/
	@Excel(name = "字典表带条件下拉多选", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "字典表带条件下拉多选")
    private java.lang.String zddtjxldx;
	/**字典表带条件下拉搜索*/
	@Excel(name = "字典表带条件下拉搜索", width = 15, dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user where username like '%a%'", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "字典表带条件下拉搜索")
    private java.lang.String zddtjxlss;
}
