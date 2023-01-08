package com.example.core.base.controller;

import com.diboot.core.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author w
 */
@Slf4j
public abstract class BaseRestController<E extends BaseEntity<K>, K extends Serializable> extends
    com.diboot.core.controller.BaseCrudRestController<E, K> {


}
