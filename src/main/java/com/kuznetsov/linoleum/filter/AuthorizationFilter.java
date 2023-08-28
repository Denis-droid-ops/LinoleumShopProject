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


@WebFilter(filterName = "filter2",urlPatterns = { "/admin/order/rollCutting","/admin/orderDetails", "/admin/orders","/admin/rolls","/admin/users",
        "/admin/fragments","/admin/layoutNames","/admin/layouts","/admin/linoleumHandling","/admin/linoleumHandling"})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(isAdmin(servletRequest)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            String prev = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse)servletResponse).sendRedirect(prev!=null? prev:"/");
        }
    }

    private boolean isAdmin(ServletRequest servletRequest) {
        UserDto userDto =(UserDto) ((HttpServletRequest)servletRequest).getSession().getAttribute("user");
        if(userDto==null) return false;
        return userDto.getRole().name().equals("ADMIN");
    }

}