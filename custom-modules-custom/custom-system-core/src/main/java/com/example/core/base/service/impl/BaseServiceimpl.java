package com.example.core.base.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.example.core.base.mapper.BaseMapper;
import com.example.core.base.service.IBaseService;

/**
 * @author w
 */
@Slf4j
public abstract class BaseServiceimpl<M extends BaseMapper<E>, E> extends
    com.diboot.core.service.impl.BaseServiceImpl<M, E> implements IBaseService<E> {


}
