package org.jeecg.core.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author w
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<K extends Serializable> extends com.diboot.core.entity.BaseEntity<K> {


}
