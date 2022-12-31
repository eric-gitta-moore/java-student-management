package org.jeecg.modules.test.service.impl;

import org.jeecg.modules.test.entity.CesOrderMain;
import org.jeecg.modules.test.entity.CesOrderGoods;
import org.jeecg.modules.test.entity.CesOrderCustomer;
import org.jeecg.modules.test.mapper.CesOrderGoodsMapper;
import org.jeecg.modules.test.mapper.CesOrderCustomerMapper;
import org.jeecg.modules.test.mapper.CesOrderMainMapper;
import org.jeecg.modules.test.service.ICesOrderMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 商城订单表
 * @Author: jeecg-boot
 * @Date:   2022-12-31
 * @Version: V1.0
 */
@Service
public class CesOrderMainServiceImpl extends ServiceImpl<CesOrderMainMapper, CesOrderMain> implements ICesOrderMainService {

	@Autowired
	private CesOrderMainMapper cesOrderMainMapper;
	@Autowired
	private CesOrderGoodsMapper cesOrderGoodsMapper;
	@Autowired
	private CesOrderCustomerMapper cesOrderCustomerMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(CesOrderMain cesOrderMain, List<CesOrderGoods> cesOrderGoodsList,List<CesOrderCustomer> cesOrderCustomerList) {
		cesOrderMainMapper.insert(cesOrderMain);
		if(cesOrderGoodsList!=null && cesOrderGoodsList.size()>0) {
			for(CesOrderGoods entity:cesOrderGoodsList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderGoodsMapper.insert(entity);
			}
		}
		if(cesOrderCustomerList!=null && cesOrderCustomerList.size()>0) {
			for(CesOrderCustomer entity:cesOrderCustomerList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderCustomerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(CesOrderMain cesOrderMain,List<CesOrderGoods> cesOrderGoodsList,List<CesOrderCustomer> cesOrderCustomerList) {
		cesOrderMainMapper.updateById(cesOrderMain);
		
		//1.先删除子表数据
		cesOrderGoodsMapper.deleteByMainId(cesOrderMain.getId());
		cesOrderCustomerMapper.deleteByMainId(cesOrderMain.getId());
		
		//2.子表数据重新插入
		if(cesOrderGoodsList!=null && cesOrderGoodsList.size()>0) {
			for(CesOrderGoods entity:cesOrderGoodsList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderGoodsMapper.insert(entity);
			}
		}
		if(cesOrderCustomerList!=null && cesOrderCustomerList.size()>0) {
			for(CesOrderCustomer entity:cesOrderCustomerList) {
				//外键设置
				entity.setOrderMainId(cesOrderMain.getId());
				cesOrderCustomerMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		cesOrderGoodsMapper.deleteByMainId(id);
		cesOrderCustomerMapper.deleteByMainId(id);
		cesOrderMainMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			cesOrderGoodsMapper.deleteByMainId(id.toString());
			cesOrderCustomerMapper.deleteByMainId(id.toString());
			cesOrderMainMapper.deleteById(id);
		}
	}
	
}
