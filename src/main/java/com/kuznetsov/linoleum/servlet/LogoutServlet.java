package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    OrderService orderService = OrderService.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        eraseCookie(req,resp);
        orderService.clearWithoutLayoutFragments();
        orderService.clearCustomLayoutFragments();
        resp.sendRedirect("/login");
    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
         List<String> names = Arrays.stream(cookies).map(cookie -> cookie.getName()).collect(Collectors.toList());
        if (!names.isEmpty()){
            names.stream().forEach(n->{
                Cookie cookie = new Cookie(n,"");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            });
        }
    }
}
