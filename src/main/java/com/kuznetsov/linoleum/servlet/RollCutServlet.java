package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.FragmentDto;
import com.kuznetsov.linoleum.dto.FragmentWithoutLayoutDto;
import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.dto.RollDto;
import com.kuznetsov.linoleum.entity.OrderStatus;
import com.kuznetsov.linoleum.exception.StockException;
import com.kuznetsov.linoleum.service.FragmentService;
import com.kuznetsov.linoleum.service.FragmentWithoutLayoutService;
import com.kuznetsov.linoleum.service.OrderService;
import com.kuznetsov.linoleum.service.RollService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/order/rollCutting")
public class RollCutServlet extends HttpServlet {
    private final RollService rollService = RollService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final FragmentWithoutLayoutService fragmentWithoutLayoutService = FragmentWithoutLayoutService.getInstance();
    private final FragmentService fragmentService = FragmentService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("rollCutting")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("id");
        OrderDto orderDto = orderService.findById(Integer.parseInt(orderId)).get();
        String linoleumName = orderDto.getLinoleum().getName();
        List<RollDto> rollDtos = new ArrayList<>();
        try {
            if (orderDto.getLayout() == null) {
                List<FragmentWithoutLayoutDto> fragmentWithoutLayoutDtoList = fragmentWithoutLayoutService.findAllByOrderId(orderDto.getId());
                req.setAttribute("fragmentsWL", fragmentWithoutLayoutDtoList);
                fragmentWithoutLayoutDtoList.forEach(f -> rollDtos.add(rollService.cutRoll(f, linoleumName)));
            } else {
                List<FragmentDto> fragmentDtoList = fragmentService.findAllWithOrdersByOrderId(orderDto.getId());
                req.setAttribute("fragments", fragmentDtoList);
                fragmentDtoList.forEach(f -> rollDtos.add(rollService.cutRoll(f, linoleumName)));
            }
            req.setAttribute("rollDtos",rollDtos);
            req.getSession().setAttribute("token","refresh");
            orderService.updateStatus(Integer.parseInt(orderId), OrderStatus.CUTTED);
            req.getRequestDispatcher(JspHelper.getPath("rollCutting")).forward(req,resp);
        }catch (StockException e){
           req.getSession().setAttribute("stockErrors",e.getErrors());
           req.getSession().setAttribute("errorFragment",e.getFragmentDto());
           resp.sendRedirect("/admin/orders");
        }

       }
}
