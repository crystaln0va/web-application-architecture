package com.example.property_management.jwt;


import com.example.property_management.Security.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

    final String authorizatioHeader = request.getHeader("Authorization");
    String email = null;
    String token = null;

        if(authorizatioHeader!=null && authorizatioHeader.startsWith("Bearer")){
            token = authorizatioHeader.substring(7);
            try{
                email = jwtUtils.getUsernameFromToken(token);
            }catch (ExpiredJwtException e){
                e.printStackTrace();
            }
        }


        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if(jwtUtils.validateToken(token)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
