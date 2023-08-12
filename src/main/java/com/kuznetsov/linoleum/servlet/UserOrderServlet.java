package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.OrderStatus;
import com.kuznetsov.linoleum.service.FragmentService;
import com.kuznetsov.linoleum.service.FragmentWithoutLayoutService;
import com.kuznetsov.linoleum.service.OrderService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders")
public class UserOrderServlet extends HttpServlet {
    private final OrderService orderService = OrderService.getInstance();
    private final FragmentWithoutLayoutService fragmentWithoutLayoutService = FragmentWithoutLayoutService.getInstance();
    private final FragmentService fragmentService = FragmentService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = ((UserDto)req.getSession().getAttribute("user")).getId();
        req.setAttribute("userOrders",orderService.findAllByUserId(userId));
        req.getRequestDispatcher(JspHelper.getPath("userOrders")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDto orderDto = orderService.findById(Integer.valueOf(req.getParameter("id"))).get();
        req.setAttribute("order", orderDto);
            if (orderDto.getLayout() == null) {
                req.setAttribute("fragmentsWL", fragmentWithoutLayoutService.findAllByOrderId(orderDto.getId()));
            } else {
                req.setAttribute("fragments", fragmentService.findAllByLayoutNameId(orderDto.getLayout().getLayoutName().getId()));
            }
            req.getRequestDispatcher(JspHelper.getPath("orderDetails")).forward(req, resp);
        }


}
