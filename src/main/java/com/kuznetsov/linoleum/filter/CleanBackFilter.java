package com.kuznetsov.linoleum.filter;

import com.kuznetsov.linoleum.service.OrderService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.net.URL;
import java.util.Collections;

@WebFilter(filterName = "filter4",urlPatterns = "/*")
public class CleanBackFilter implements Filter {
    private OrderService orderService = OrderService.getInstance();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        final String HOST_WITH_PORT = req.getScheme()+"://"+req.getHeader("Host");
        if((req.getHeader("referer")!=null && req.getHeader("referer").equals(HOST_WITH_PORT+"/orderCreateSuccess"))
                || req.getParameter("cancelOrder")!=null){
            orderService.clearWithoutLayoutFragments();
            orderService.clearCustomLayoutFragments();
            Collections.list(req.getSession().getAttributeNames()).stream().filter(s->!s.equals("user")).forEach(s->req.getSession().removeAttribute(s));
        }

        if((req.getHeader("referer")!=null &&(req.getHeader("referer").startsWith(HOST_WITH_PORT+"/orderLayout") ||
                req.getHeader("referer").equals(HOST_WITH_PORT+"/orderFragments") ||
                req.getHeader("referer").equals(HOST_WITH_PORT+"/order")))
                && (!req.getRequestURI().equals("/order") && !req.getRequestURI().equals("/orderLayout")
                && !req.getRequestURI().startsWith("/resources")
                && !req.getRequestURI().equals("/orderFragments") && !req.getRequestURI().equals("/orderCreateSuccess") && !req.getRequestURI().equals("/logout")
                && !req.getRequestURI().equals("/login") && req.getParameter("cancelOrder")==null && req.getParameter("another")==null )){

            URL url = new URL(req.getHeader("referer"));
            Cookie cookie = new Cookie("orderPage",url.getPath());
            cookie.setMaxAge(24*60*60);
            cookie.setPath("/");
            resp.addCookie(cookie);

        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
