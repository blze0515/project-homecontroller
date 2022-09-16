package com.bootreact.hmct.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

//요청이 왔을 때 함께 전달해오는 token을 받아서 유효성 검사를 하고
//token 안에서 username을 꺼내서 사용하기 위한 필터
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		
		try {
			//request에서 token을 꺼내오기
			String token = parseBearerToken(request);
			
			//토큰 검사 및 시큐리티 등록
			if(token != null && !token.equalsIgnoreCase("null")) {
				//username 가져오기.(위조된 경우 예외처리됨) 
				String username = jwtTokenProvider.validateAndGetUsername(token);
				
				//유효성 검사된 토큰은 security에 등록
				AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.NO_AUTHORITIES);
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authenticationToken);
				
				SecurityContextHolder.setContext(securityContext);
			}			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Could not set user authentication");
		}
		
		filterChain.doFilter(request, response);
	}
	
	//요청 헤더를 파싱하여 Bearer 토큰을 리턴
	private String parseBearerToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		
		return null;
	}
}
