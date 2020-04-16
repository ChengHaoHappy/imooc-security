/**
 * 
 */
package com.imooc.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQ登录配置项
 * 
 * 
 * @author zhailiang
 *
 */
@Data
public class QQProperties extends SocialProperties {
	
	/**
	 * 第三方id，用来决定发起第三方登录的url，默认是 qq。
	 * 服务提供商标识
	 */
	private String providerId = "qq";
}
