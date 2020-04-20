/**
 * 
 */
package com.imooc.security.app.social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.social.support.ImoocSpringSocialConfigurer;

/**
 * Spring容器的所有Bean初始化的时候都需要经过BeanPostProcessor接口的两个方法
 *
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {


	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	//替换core里设置的注册页
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(StringUtils.equals(beanName, "imoocSocialSecurityConfig")){
			ImoocSpringSocialConfigurer config = (ImoocSpringSocialConfigurer)bean;
			config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
			return config;
		}
		return bean;
	}

}
