package com.cms.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtUtils tokenGenerator;
    @Autowired
    private CustomUserDetailServiceImpl customUserDetailsService;


    @Override

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwt = getJWTFromRequest(request);
        System.out.println("hee");
        if (StringUtils.hasText(jwt) && tokenGenerator.validateToken(jwt)) {
            String username = tokenGenerator.getUsernameFromJWT(jwt);
            tokenGenerator.generateToken("sdsd");
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                    userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String jwt = null;
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher(AntPathMatcher.DEFAULT_PATH_SEPARATOR);
        boolean res = excludeFilter.stream().anyMatch(path->antPathMatcher.match(path,uri));
        return res;
    }

    public static List<String>  excludeFilter = Arrays.asList(SecurityConfig.allowPath);
}
