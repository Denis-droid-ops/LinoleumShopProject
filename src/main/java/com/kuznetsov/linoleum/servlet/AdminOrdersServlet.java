package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.OrderDto;

import com.kuznetsov.linoleum.entity.OrderStatus;
import com.kuznetsov.linoleum.service.*;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/orders")
public class AdminOrdersServlet extends HttpServlet {
    private final OrderService orderService = OrderService.getInstance();
    private final FragmentService fragmentService = FragmentService.getInstance();
    private final FragmentWithoutLayoutService fragmentWithoutLayoutService = FragmentWithoutLayoutService.getInstance();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders",orderService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("orders")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action")!=null && req.getParameter("action").equals("updateStatus")){
            orderService.updateStatus(Integer.parseInt(req.getParameter("id")), OrderStatus.COMPLETED);
            resp.sendRedirect("/admin/orders");
        }else{
            if(req.getParameter("action")!=null && req.getParameter("action").equals("deleteOrder")){
               orderService.delete(Integer.parseInt(req.getParameter("id")));
               resp.sendRedirect("/admin/orders");
            }else {
                OrderDto orderDto = orderService.findById(Integer.valueOf(req.getParameter("id"))).get();
                req.setAttribute("order", orderDto);
                if (orderDto.getLayout() == null) {
                    req.setAttribute("fragmentsWL", fragmentWithoutLayoutService.findAllByOrderId(orderDto.getId()));
                } else {
                    req.setAttribute("fragments", fragmentService.findAllWithOrdersByOrderId(orderDto.getId()));
                }
                req.getRequestDispatcher(JspHelper.getPath("orderDetails")).forward(req, resp);
            }
        }
    }
}
