/**
 * 
 */
package com.imooc.security.core.social.support;

import lombok.Data;

/**
 * @author zhailiang
 *
 */
@Data
public class SocialUserInfo {
	
	private String providerId;
	
	private String providerUserId;
	
	private String nickname;
	
	private String headimg;

}
