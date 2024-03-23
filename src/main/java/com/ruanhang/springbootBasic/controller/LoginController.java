package com.ruanhang.springbootBasic.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * 登录控制层
 */
@RestController
public class LoginController {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url) {
        return url;
    }

    /**
     * 获取系统登录信息
     * @param username
     * @param password
     * @return
     */
    /*@PostMapping("/getInfo")
    public Result getInfo(String username, String password) {
        return sysUserService.getInfo();
    }
*/


    @PostMapping("/login")
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unauth() {
        return "未授权没有访问权限";
    }
}
