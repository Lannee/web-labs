package ru.lannee.web.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.lannee.web.security.service.UserService;

import java.io.IOException;

public class AuthTokenFilter extends GenericFilterBean { //extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            UserDetails userDetails = getUserDetails(request);
//
//            if (userDetails != null) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities()
//                );
//
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception e) {
//            logger.error("Cannot set user authentication: { }", e);
//        }
//
//        filterChain.doFilter(request, response);
//    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        String token = parseJwt((HttpServletRequest) req);
        if(token == null) {
            filterChain.doFilter(req, res);
            return;
        }

        if(!jwtUtils.validateJwtToken(token)) {
            filterChain.doFilter(req, res);
        }
    }

//    public UserDetails getUserDetails(HttpServletRequest request) {
//        String jwt = parseJwt(request);
//
//        if (jwt == null || !jwtUtils.validateJwtToken(jwt))
//            return null;
//
//        String username = jwtUtils.getUserNameFromJwtToken(jwt);
//
//        return userService.loadUserByUsername(username);
//    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        String bearerPrefix = "Bearer ";
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(bearerPrefix)){
            return headerAuth.substring(bearerPrefix.length()).trim();}

        return null;
    }
}