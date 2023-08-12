package com.kuznetsov.linoleum.filter;


import com.kuznetsov.linoleum.service.OrderService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


@WebFilter(filterName = "filter3", urlPatterns = "/*")
public class AccessFilter implements Filter {
    OrderService orderService = OrderService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if(((uri.equals("/orderFragments") || uri.equals("/orderLayout") || uri.equals("/order") || uri.equals("/orderCreateSuccess") || uri.equals("/admin/order/rollCutting") || uri.equals("/admin/orderDetails")) && req.getHeader("referer")==null)
                || (req.getSession().getAttribute("token")!=null && uri.equals("/admin/order/rollCutting") && req.getHeader("referer").equals("http://localhost:8080/admin/orders"))){
            orderService.clearWithoutLayoutFragments();
            orderService.clearCustomLayoutFragments();
            req.getSession().removeAttribute("token");
            Collections.list(req.getSession().getAttributeNames()).stream().filter(s->!s.equals("user")).forEach(s->req.getSession().removeAttribute(s));
            if(uri.equals("/admin/order/rollCutting")){
                resp.sendRedirect("/admin/orders");
            }else {
                resp.sendRedirect("/");
            }
        }else {
            chain.doFilter(request, response);
        }

    }




}
