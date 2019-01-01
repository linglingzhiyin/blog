package com.decision;

import java.util.Collection;

import com.authority.UriGrantedAuthority;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

public class UriAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

	@Override
	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
		String uri = getRequestUri(fi);
		if (authentication.getAuthorities().contains(new UriGrantedAuthority(uri))) {
			return ACCESS_GRANTED;
		}
		return ACCESS_DENIED;
	}
	
	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}
	
	private String getRequestUri(FilterInvocation fi) {
		String uri = fi.getRequestUrl();
		int qs = uri.indexOf("?");
		if (qs > 0) {
			return uri.substring(0, qs);
		}
		return uri;
	}
}
