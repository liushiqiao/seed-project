package com.xh.lesson.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName: CustomPasswordToken
 * TODO:类文件简单描述
 * @Author: lsq
 * @CreateDate: 2019/9/6 17:58
 * @UpdateUser: lsq
 * @UpdateDate: 2019/9/6 17:58
 * @Version: 0.0.1
 */
public class CustomPasswordToken  extends UsernamePasswordToken {
    private String token;

    public CustomPasswordToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }
}
