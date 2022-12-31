package org.jeecg.modules.test.service.impl;

import org.jeecg.modules.test.entity.CesOrderCustomer;
import org.jeecg.modules.test.mapper.CesOrderCustomerMapper;
import org.jeecg.modules.test.service.ICesOrderCustomerService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 订单客户
 * @Author: jeecg-boot
 * @Date:   2022-12-31
 * @Version: V1.0
 */
@Service
public class CesOrderCustomerServiceImpl extends ServiceImpl<CesOrderCustomerMapper, CesOrderCustomer> implements ICesOrderCustomerService {
	
	@Autowired
	private CesOrderCustomerMapper cesOrderCustomerMapper;
	
	@Override
	public List<CesOrderCustomer> selectByMainId(String mainId) {
		return cesOrderCustomerMapper.selectByMainId(mainId);
	}
}
