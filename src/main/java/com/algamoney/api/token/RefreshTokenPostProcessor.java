package com.algamoney.api.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return returnType.getMethod().getName().equals("postAccessToken");
	}

	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter returnType,
			MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
			ServerHttpRequest request, ServerHttpResponse response) {
		
		HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
		
		DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) body;
		
		String refreshToken = body.getRefreshToken().getValue();

		addRefreshTokenInTheCookie(refreshToken, httpServletRequest, httpServletResponse);
		removeRefreshTokenFromBody(defaultOAuth2AccessToken);
		
		return body;
	}

	private void addRefreshTokenInTheCookie(String refreshToken, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Cookie refreshtokenCookie = new Cookie("refreshToken", refreshToken);
		refreshtokenCookie.setHttpOnly(true);
		refreshtokenCookie.setSecure(false); // TODO: switch to true in production
		refreshtokenCookie.setPath(httpServletRequest.getContextPath() + "/oauth/token");
		refreshtokenCookie.setMaxAge(2592000); // 30 days
		
		httpServletResponse.addCookie(refreshtokenCookie);
	}
	
	private void removeRefreshTokenFromBody(DefaultOAuth2AccessToken defaultOAuth2AccessToken) {
		defaultOAuth2AccessToken.setRefreshToken(null);
	}
}
