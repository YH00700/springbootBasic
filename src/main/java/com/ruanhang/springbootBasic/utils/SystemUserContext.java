package com.ruanhang.springbootBasic.utils;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.shiro.SecurityUtils.getSubject;

public class SystemUserContext {
    private static Logger logger = LoggerFactory.getLogger(SystemUserContext.class);


    /**
     * 获取当前登录者对象UserId
     */
    public static Long getPrincipalUserId() {
        try {
            Subject subject = getSubject();
            if (subject != null) {
                SystemPrincipal principal = (SystemPrincipal) subject.getPrincipal();
                if (principal != null) {
                    return NumberUtils.toLong(principal.getId());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 从t_user中获取当前用户
     *
     * @return 取不到返回 null
     */
    /*public static UserVO getUser() {
        SystemPrincipal principal = getPrincipal();
        UserVO user = null;
        if (principal != null) {
            user = userService.getUserById(NumberUtils.toLong(principal.getId()));
        }
        return user;
    }*/

    /**
     * 根据ID获取用户并缓存
     *
     * @param id
     * @return 取不到返回null
     */
    /*public UserVO getUser(Long id) {
        *//*UserVO user = cacheService.getObjectByBtye(USER_CACHE_ID + id);*//*
        *//*if (user == null) {*//*
        UserVO user = userService.getUserById(id);
        if (user == null) {
            return null;
        }
            *//*cacheService.setObjectByBtye(USER_CACHE_ID + user.getId(), user, TTL.D1);
            cacheService.setObjectByBtye(USER_CACHE_USER_NAME + user.getUserName(), user, TTL.D1);
        }*//*
        return user;
    }*/


}
