package com.imooc.security.app.social.impl;

import com.imooc.security.core.social.support.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * APP 环境下社交登录后处理器
 * Created By ChengHao On 2020/4/20
 */
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    //设置成返回令牌的imoocAuthenticationSuccessHandler
    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(imoocAuthenticationSuccessHandler);
    }
}
