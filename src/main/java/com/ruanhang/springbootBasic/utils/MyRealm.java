package com.ruanhang.springbootBasic.utils;

import com.ruanhang.springbootBasic.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.addAll(account.getRoles());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限 todo
        info.addObjectPermissions(account.getObjectPermissions());
        return info;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser user = new SysUser();
        user.setUserName(token.getUsername());
        SysUser sysUser = sysUserService.getUserByUserName(user);
        if (sysUser != null) {
            return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
        }
        return null;
    }
}
