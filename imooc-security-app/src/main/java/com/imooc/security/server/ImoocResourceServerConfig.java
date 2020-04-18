/**
 * 
 */
package com.imooc.security.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import com.imooc.security.app.authentication.openid.OpenIdAuthenticationSecurityConfig;
import com.imooc.security.core.authentication.FormAuthenticationConfig;
import com.imooc.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.core.authorize.AuthorizeConfigManager;
import com.imooc.security.core.validate.code.ValidateCodeSecurityConfig;

import javax.annotation.Resource;

/**
 * 资源服务器配置
 * 
 * @author zhailiang
 *
 */
@Configuration
@EnableResourceServer
public class ImoocResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Resource
	protected AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

	@Resource
	protected AuthenticationFailureHandler imoocAuthenticationFailureHandler;

	@Resource
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Resource
	private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

	@Resource
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Resource
	private SpringSocialConfigurer imoocSocialSecurityConfig;

	@Resource
	private AuthorizeConfigManager authorizeConfigManager;

	@Resource
	private FormAuthenticationConfig formAuthenticationConfig;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		formAuthenticationConfig.configure(http);
		
		http.apply(validateCodeSecurityConfig)
				.and()
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(imoocSocialSecurityConfig)
				.and()
			.apply(openIdAuthenticationSecurityConfig)
				.and()
			.csrf().disable();
		
		authorizeConfigManager.config(http.authorizeRequests());
	}
	
}