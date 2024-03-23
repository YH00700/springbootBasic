package com.ruanhang.springbootBasic.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 树形结构工具类使用子节点集合注解
 *
 * @see com.zhaoshang800.base.commons.tool.TreeUtils
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TreeChildren {

}
