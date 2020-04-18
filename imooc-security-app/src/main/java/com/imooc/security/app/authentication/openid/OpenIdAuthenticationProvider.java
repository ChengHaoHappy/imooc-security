/**
 * 
 */
package com.imooc.security.app.authentication.openid;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * @author zhailiang
 *
 */
@Data
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

	private SocialUserDetailsService userDetailsService;

	private UsersConnectionRepository usersConnectionRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;
		
		Set<String> providerUserIds = new HashSet<>();
		providerUserIds.add((String) authenticationToken.getPrincipal());
		Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authenticationToken.getProviderId(), providerUserIds);
		
		if(CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		String userId = userIds.iterator().next();
		
		UserDetails user = userDetailsService.loadUserByUserId(userId);

		if (user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		OpenIdAuthenticationToken authenticationResult = new OpenIdAuthenticationToken(user, user.getAuthorities());
		
		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
