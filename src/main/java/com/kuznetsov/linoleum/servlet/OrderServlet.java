package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithDeliveryAddress;
import com.kuznetsov.linoleum.entity.OrderWithLayout;
import com.kuznetsov.linoleum.service.*;
import com.kuznetsov.linoleum.util.JspHelper;
import com.kuznetsov.linoleum.validator.CreateDeliveryAddressValidator;
import com.kuznetsov.linoleum.validator.CreateTranspDateApartmentValidator;
import com.kuznetsov.linoleum.validator.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private final OrderWithLayoutService orderWithLayoutService = OrderWithLayoutService.getInstance();
    private final OrderWithDeliveryAddressService orderWithDeliveryAddressService = OrderWithDeliveryAddressService.getInstance();

    private final OrderService orderService = OrderService.getInstance();
    private final FragmentService fragmentService = FragmentService.getInstance();
    private final FragmentWithoutLayoutService fragmentWithoutLayoutService = FragmentWithoutLayoutService.getInstance();
    private final CreateDeliveryAddressValidator createDeliveryAddressValidator = CreateDeliveryAddressValidator.getInstance();
    private final CreateTranspDateApartmentValidator createTranspDateApartmentValidator = CreateTranspDateApartmentValidator.getInstance();
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

        if(req.getParameter("action")!=null && req.getParameter("action").equals("enterOrderDetails")){
            String transportingDate = req.getParameter("transportingDate");
            String apartmentNum = req.getParameter("apartmentNum");

            CreateOrderDto createOrderDto = CreateOrderDto.builder().transportingDate(transportingDate)
                    .apartmentNum(apartmentNum).build();
            ValidationResult validationResult1 = createTranspDateApartmentValidator.isValid(createOrderDto);
            if(!validationResult1.isValid()){
                req.setAttribute("errors",validationResult1.getErrors());
                doGet(req,resp);
            }else {

                if (req.getSession().getAttribute("withoutLFragments") != null && transporting.equals("DELIVERY")) {
                    String dCity = req.getParameter("city");
                    String dStreet = req.getParameter("street");
                    String dHomeNum = req.getParameter("homeNum");
                    CreateDeliveryAddressDto createDeliveryAddressDto = new CreateDeliveryAddressDto(dCity, dStreet, dHomeNum);
                    ValidationResult validationResult2 = createDeliveryAddressValidator.isValid(createDeliveryAddressDto);
                    if (!validationResult2.isValid()) {
                        req.setAttribute("errors", validationResult2.getErrors());
                        doGet(req, resp);
                    }else {

                        CreateOrderDto createOrderWithDeliveryAddressDto =
                                createOrderDto.builder().transporting(transporting)
                                        .transportingDate(transportingDate)
                                        .cost(req.getSession().getAttribute("cost").toString())
                                        .apartmentNum(apartmentNum)
                                        .userId(((UserDto) req.getSession().getAttribute("user")).getId().toString())
                                        .linoleumId(((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString())
                                        .createDeliveryAddressDto(createDeliveryAddressDto).build();
                        //new CreateOrderDto(transporting
                        //       ,transportingDate
                        //     ,req.getSession().getAttribute("cost").toString()
                        //    ,apartmentNum
                        //  ,((UserDto)req.getSession().getAttribute("user")).getId().toString()
                        //,((LinoleumDto)req.getSession().getAttribute("orderLinoleum")).getId().toString()
                        //,createDeliveryAddressDto);
                        OrderWithDeliveryAddress orderWithDeliveryAddress = orderWithDeliveryAddressService.save(createOrderWithDeliveryAddressDto);
                        orderService.getWithoutLayoutFragments().forEach(f -> {
                            f.setOrderId(orderWithDeliveryAddress.getId().toString());
                            fragmentWithoutLayoutService.save(f);
                        });
                        req.getSession().setAttribute("order", orderWithDeliveryAddress);

                    }
                } else {
                    if (req.getSession().getAttribute("customLayout") != null && (Boolean) req.getSession().getAttribute("customLayout")) {
                        CreateLayoutDto createLayoutDto = (CreateLayoutDto) req.getSession().getAttribute("createLayoutDto");
                        CreateOrderDto createOrderWithNewLayoutDto = createOrderDto.builder().transporting(transporting)
                                .transportingDate(transportingDate)
                                .cost(req.getSession().getAttribute("cost").toString())
                                .apartmentNum(apartmentNum)
                                .userId(((UserDto) req.getSession().getAttribute("user")).getId().toString())
                                .linoleumId(((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString())
                                .createLayoutDto(createLayoutDto).build();
                          /*  new CreateOrderDto(transporting
                                    , transportingDate
                                    , req.getSession().getAttribute("cost").toString()
                                    , apartmentNum
                                    , ((UserDto) req.getSession().getAttribute("user")).getId().toString()
                                    , ((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString()
                                    , createLayoutDto);

                           */
                        OrderWithLayout orderWithLayout = orderWithLayoutService.save(createOrderWithNewLayoutDto);
                        orderService.getCustomLayoutFragments().forEach(f -> {
                            f.setLayoutNameId(orderWithLayout.getLayout().getLayoutName().getId().toString());
                            Fragment newFragment = fragmentService.save(f);
                            fragmentService.saveWithOrder(newFragment.getId(),orderWithLayout.getId());
                        });
                        req.getSession().setAttribute("order", orderWithLayout);
                    } else {
                        if (req.getSession().getAttribute("withoutLFragments") != null && transporting.equals("PICKUP")) {
                            CreateOrderDto createSimpleOrderDto = createOrderDto.builder().transporting(transporting)
                                    .transportingDate(transportingDate)
                                    .cost(req.getSession().getAttribute("cost").toString())
                                    .apartmentNum(apartmentNum)
                                    .userId(((UserDto) req.getSession().getAttribute("user")).getId().toString())
                                    .linoleumId(((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString())
                                    .build();
                          /*      new CreateOrderDto(transporting
                                        ,transportingDate
                                        ,req.getSession().getAttribute("cost").toString()
                                        ,apartmentNum
                                        ,((UserDto)req.getSession().getAttribute("user")).getId().toString()
                                        ,((LinoleumDto)req.getSession().getAttribute("orderLinoleum")).getId().toString());

                           */
                            Order order = orderService.save(createSimpleOrderDto);
                            orderService.getWithoutLayoutFragments().forEach(f -> {
                                f.setOrderId(order.getId().toString());
                                fragmentWithoutLayoutService.save(f);
                            });
                            req.getSession().setAttribute("order", order);
                        } else {
                            CreateOrderDto createOrderExistsLayoutDto = createOrderDto.builder().transporting(transporting)
                                    .transportingDate(transportingDate)
                                    .cost(req.getSession().getAttribute("cost").toString())
                                    .apartmentNum(apartmentNum)
                                    .userId(((UserDto) req.getSession().getAttribute("user")).getId().toString())
                                    .linoleumId(((LinoleumDto) req.getSession().getAttribute("orderLinoleum")).getId().toString())
                                    .layoutId(((LayoutDto) req.getSession().getAttribute("layoutDto")).getId().toString()).build();

                            List<FragmentDto> choosedFragments = (List<FragmentDto>) req.getSession().getAttribute("choosedFragments");
                            OrderWithLayout savedOrderWithLayout = orderWithLayoutService.save(createOrderExistsLayoutDto);
                            choosedFragments.stream().forEach(f->{
                                fragmentService.saveWithOrder(f.getId(),savedOrderWithLayout.getId());
                            });
                            req.getSession().setAttribute("order", savedOrderWithLayout);


                        }
                    }
                }

                resp.sendRedirect("/orderCreateSuccess");
            }
        }else {
            resp.sendRedirect("/order");
        }
    }
}
