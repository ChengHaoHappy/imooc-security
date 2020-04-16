/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;

	//获取授权码
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

	//获取accessToken
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	/**
	 *
	 * @param appId     clientId
	 * @param appSecret  clientSecret
	 */
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.
	 * lang.String)
	 */
	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}
