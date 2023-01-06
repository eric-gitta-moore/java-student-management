package com.example.modules.diboot.service.impl;

import com.diboot.core.entity.Dictionary;
import com.diboot.core.service.DictionaryServiceExtProvider;
import com.diboot.core.vo.DictionaryVO;
import com.diboot.core.vo.LabelValue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author w
 */
@Service
public class DictionaryServiceExtProviderImpl implements DictionaryServiceExtProvider {

    /**
     * 绑定字典的label
     *
     * @param voList
     * @param setFieldName
     * @param getFieldName
     * @param type
     */
    @Override
    public void bindItemLabel(List voList, String setFieldName, String getFieldName, String type) {

    }

    /**
     * 获取字典类型对应的子项键值对
     *
     * @param dictType
     * @return
     */
    @Override
    public List<LabelValue> getLabelValueList(String dictType) {
        return null;
    }

    /**
     * 是否存在某字典类型定义
     *
     * @param dictType
     * @return
     */
    @Override
    public boolean existsDictType(String dictType) {
        return false;
    }

    /**
     * 创建字典及子项
     *
     * @param dictionaryVO
     * @return
     */
    @Override
    public boolean createDictAndChildren(DictionaryVO dictionaryVO) {
        return false;
    }

    /**
     * 查询字典定义的List（不含子项）
     *
     * @return
     */
    @Override
    public List<Dictionary> getDictDefinitionList() {
        return null;
    }

    /**
     * 查询字典VOList（含子项）
     *
     * @return
     */
    @Override
    public List<DictionaryVO> getDictDefinitionVOList() {
        return null;
    }
}
