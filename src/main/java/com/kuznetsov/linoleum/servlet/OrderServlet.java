package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithDeliveryAddress;
import com.kuznetsov.linoleum.entity.OrderWithLayout;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.service.*;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private final OrderWithLayoutService orderWithLayoutService = OrderWithLayoutService.getInstance();
    private final OrderWithDeliveryAddressService orderWithDeliveryAddressService = OrderWithDeliveryAddressService.getInstance();

    private final OrderService orderService = OrderService.getInstance();
    private final FragmentService fragmentService = FragmentService.getInstance();
    private final FragmentWithoutLayoutService fragmentWithoutLayoutService = FragmentWithoutLayoutService.getInstance();
    private String transporting = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("order")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         if(req.getParameter("action")==null) {
             transporting = req.getParameter("radioDefault");
             req.getSession().setAttribute("transporting", transporting);
         }
        System.out.println(transporting+" lfkkkkhkj");
        if(req.getParameter("action")!=null && req.getParameter("action").equals("enterOrderDetails")){
            String transportingDate = req.getParameter("transportingDate");
            String apartmentNum = req.getParameter("apartmentNum");
            if(req.getSession().getAttribute("withoutLFragments")!=null && transporting.equals("DELIVERY")){
                String dCity = req.getParameter("city");
                String dStreet = req.getParameter("street");
                String dHomeNum = req.getParameter("homeNum");
                CreateDeliveryAddressDto createDeliveryAddressDto = new CreateDeliveryAddressDto(dCity,dStreet,dHomeNum);
                CreateOrderDto createOrderWithDeliveryAddressDto =
                        new CreateOrderDto(transporting
                                ,transportingDate
                                ,req.getSession().getAttribute("cost").toString()
                                ,apartmentNum
                                ,((UserDto)req.getSession().getAttribute("user")).getId().toString()
                                ,((LinoleumDto)req.getSession().getAttribute("orderLinoleum")).getId().toString()
                                ,createDeliveryAddressDto);
                OrderWithDeliveryAddress orderWithDeliveryAddress = orderWithDeliveryAddressService.save(createOrderWithDeliveryAddressDto);
                orderService.getWithoutLayoutFragments().stream().forEach(f->{
                    f.setOrderId(orderWithDeliveryAddress.getId().toString());
                    fragmentWithoutLayoutService.save(f);
                });
                req.getSession().setAttribute("order",orderWithDeliveryAddress);


            }else {
                if (req.getSession().getAttribute("customLayout")!=null &&(Boolean) req.getSession().getAttribute("customLayout")) {
                    CreateLayoutDto createLayoutDto = (CreateLayoutDto) req.getSession().getAttribute("createLayoutDto");
                    CreateOrderDto createOrderWithNewLayoutDto =
                            new CreateOrderDto(transporting
                                    , transportingDate
                                    , req.getSession().getAttribute("cost").toString()
                                    , apartmentNum
                                    , ((UserDto) req.getSession().getAttribute("user")).getId().toString()
                                    , ((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString()
                                    , createLayoutDto);
                    OrderWithLayout orderWithLayout = orderWithLayoutService.save(createOrderWithNewLayoutDto);
                    orderService.getCustomLayoutFragments().forEach(f->{
                        f.setLayoutNameId(orderWithLayout.getLayout().getLayoutName().getId().toString());
                        fragmentService.save(f);
                    });
                    req.getSession().setAttribute("order",orderWithLayout);
                }else {
                    if(req.getSession().getAttribute("withoutLFragments")!=null && transporting.equals("PICKUP")){
                        CreateOrderDto createSimpleOrderDto =
                                new CreateOrderDto(transporting
                                        ,transportingDate
                                        ,req.getSession().getAttribute("cost").toString()
                                        ,apartmentNum
                                        ,((UserDto)req.getSession().getAttribute("user")).getId().toString()
                                        ,((LinoleumDto)req.getSession().getAttribute("orderLinoleum")).getId().toString());
                        Order order = orderService.save(createSimpleOrderDto);
                        orderService.getWithoutLayoutFragments().forEach(f->{
                            f.setOrderId(order.getId().toString());
                            fragmentWithoutLayoutService.save(f);
                        });
                        req.getSession().setAttribute("order",order);
                    }else {
                        CreateOrderDto createOrderExistsLayoutDto =
                                new CreateOrderDto(transporting
                                        , transportingDate
                                        , req.getSession().getAttribute("cost").toString()
                                        , apartmentNum
                                        , ((UserDto) req.getSession().getAttribute("user")).getId().toString()
                                        , ((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString()
                                        , ((LayoutDto) req.getSession().getAttribute("layoutDto")).getId().toString());
                        req.getSession().setAttribute("order",orderWithLayoutService.save(createOrderExistsLayoutDto));
                    }
            }
            }

         resp.sendRedirect("/orderCreateSuccess");
        }else {
            resp.sendRedirect("/order");
        }
    }
}
