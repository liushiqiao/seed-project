package com.xh.lesson.utils;


import org.springframework.stereotype.Component;

/**
 * @ClassName: StaticContextInitializer
 * TODO:类文件简单描述
 * @Author: lsq
 * @CreateDate: 2019/9/26 10:07
 * @UpdateUser: lsq
 * @UpdateDate: 2019/9/26 10:07
 * @Version: 0.0.1
 */
@Component
public class StaticInitializerUtil {
   private TokenSettings tokenSettings;

    public StaticInitializerUtil(TokenSettings tokenSettings) {
        JwtTokenUtil.setTokenSettings(tokenSettings);
    }
}
