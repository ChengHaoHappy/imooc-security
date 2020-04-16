/**
 * 
 */
package com.imooc.security.core.social.support;

import lombok.Data;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 继承默认的社交登录配置，加入自定义的后处理逻辑
 * 可配置url
 * 
 * @author zhailiang
 *
 */@Data
public class ImoocSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

	public ImoocSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		if (socialAuthenticationFilterPostProcessor != null) {
			socialAuthenticationFilterPostProcessor.process(filter);
		}
		return (T) filter;
	}

}
