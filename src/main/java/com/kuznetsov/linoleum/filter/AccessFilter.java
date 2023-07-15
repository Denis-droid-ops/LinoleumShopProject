package com.kuznetsov.linoleum.filter;

import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.service.OrderService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@WebFilter("/*")
public class AccessFilter implements Filter {
    OrderService orderService = OrderService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if((uri.equals("/orderFragments") || uri.equals("/orderLayout") || uri.equals("/order") || uri.equals("/orderCreateSuccess")) && req.getHeader("referer")==null){
            orderService.clearWithoutLayoutFragments();
            orderService.clearCustomLayoutFragments();
            Collections.list(req.getSession().getAttributeNames()).stream().filter(s->!s.equals("user")).forEach(s->req.getSession().removeAttribute(s));
            resp.sendRedirect("/");
        }else {

            chain.doFilter(request, response);
        }

    }




}
