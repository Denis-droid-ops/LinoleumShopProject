package com.kuznetsov.linoleum.filter;

import com.kuznetsov.linoleum.dto.UserDto;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "filter1",urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    private static final Set<String> PUBLIC_PATH = Set.of("/login","/registration","/logout");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) request).getRequestURI();
        if(isPublicPath(uri) || isLogged(request) || uri.matches(".*(css|jpg|png|gif|js)")){
            chain.doFilter(request,response);
        }else {
            String prev = ((HttpServletRequest) request).getHeader("referer");
            ((HttpServletResponse)response).sendRedirect(prev!=null? prev:"/login");
        }
    }

    private boolean isLogged(ServletRequest servletRequest){
        UserDto userDto =(UserDto) ((HttpServletRequest)servletRequest).getSession().getAttribute("user");
        if(userDto==null) return false;
        return true;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(path->uri.startsWith(path)) || uri.equals("/");
    }
}

