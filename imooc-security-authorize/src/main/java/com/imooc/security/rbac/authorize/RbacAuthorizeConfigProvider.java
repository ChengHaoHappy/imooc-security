/**
 * 
 */
package com.imooc.security.rbac.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.imooc.security.core.authorize.AuthorizeConfigProvider;

/**
 * 权限模块授权配置
 * @author zhailiang
 */
@Component
@Order(Integer.MAX_VALUE) //控制anyRequest()要放在最后
public class RbacAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config
			.antMatchers(HttpMethod.GET, "/fonts/**").permitAll()
			.antMatchers(HttpMethod.GET, 
					"/**/*.html",
					"/admin/me",
					"/resource").authenticated()
			.anyRequest()
				.access("@rbacService.hasPermission(request, authentication)");//具体的鉴权逻辑
		return true;
	}

}
