package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.service.OrderService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@WebServlet("/cancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
    OrderService orderService = OrderService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService.clearWithoutLayoutFragments();
        orderService.clearCustomLayoutFragments();
        Collections.list(req.getSession().getAttributeNames()).stream().filter(s->!s.equals("user")).forEach(s->req.getSession().removeAttribute(s));
        if(req.getHeader("referer")!=null && req.getHeader("referer").equals("http://localhost:8080/orderCreateSuccess")
                && req.getParameter("action")!=null && req.getParameter("action").equals("ordersView")){
            resp.sendRedirect("/orders");
        }else {
            resp.sendRedirect("/");
        }
    }
}
