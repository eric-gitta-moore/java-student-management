package org.jeecg.modules.stu.service;

import org.jeecg.modules.stu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;

/**
 * @Description: 科目管理
 * @Author: jeecg-boot
 * @Date:   2022-12-29
 * @Version: V1.0
 */
public interface ISubjectService extends IService<Subject> {

	/**
	 * 添加一对多
	 *
	 * @param subject
	 */
	public void saveMain(Subject subject) ;
	
	/**
	 * 修改一对多
	 *
   * @param subject
	 */
	public void updateMain(Subject subject);
	
	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
