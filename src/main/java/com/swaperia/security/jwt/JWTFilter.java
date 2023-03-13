package com.swaperia.security.jwt;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.swaperia.security.UserDetailsImpl;
import com.swaperia.security.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = resolveToken(request);
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			if (StringUtils.isNotEmpty(email) && (null == SecurityContextHolder.getContext().getAuthentication())) {
				UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(email);
				if (jwtTokenUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext()
                            .setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		} catch (IllegalArgumentException e) {
            logger.error("Unable to fetch JWT Token");
        } catch (ExpiredJwtException e) {
            logger.error("JWT Token is expired");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
		filterChain.doFilter(request, response);	
	}

	private String resolveToken(HttpServletRequest request) {
		String requestTokenHeader = request.getHeader(AUTHORIZATION_HEADER);
		
		if (StringUtils.startsWith(requestTokenHeader, "Bearer ")) {
			String jwtToken = requestTokenHeader.substring(7);
			System.out.println(jwtToken);
			return jwtToken;
		}
		return null;
	}
}