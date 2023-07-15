package com.kuznetsov.linoleum.filter;

import com.kuznetsov.linoleum.dto.UserDto;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    private static final Set<String> PUBLIC_PATH = Set.of("/login","/registration","/logout");


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(uri) || isAdmin(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String prev = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse)servletResponse).sendRedirect(prev!=null? prev:"/login");
        }
    }

    private boolean isAdmin(ServletRequest servletRequest) {
        UserDto userDto =(UserDto) ((HttpServletRequest)servletRequest).getSession().getAttribute("user");
        if(userDto==null) return false;
        return userDto.getRole().name().equals("ADMIN");
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(path->uri.startsWith(path)) || uri.equals("/");
    }

}
