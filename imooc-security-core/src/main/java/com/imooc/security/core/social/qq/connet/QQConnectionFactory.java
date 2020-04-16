/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.imooc.security.core.social.qq.api.QQ;

/**
 * 实现QQConnectionFactory，
 * 把QQServiceProvider，QQAdapter放入工厂中
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

	/**
	 *
	 * @param providerId 提供商的唯一表示
	 * @param appId
	 * @param appSecret
	 */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
