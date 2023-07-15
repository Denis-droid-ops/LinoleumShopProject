package com.kuznetsov.linoleum.servlet;


import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.LayoutRowType;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.service.FragmentService;
import com.kuznetsov.linoleum.service.LayoutNameService;
import com.kuznetsov.linoleum.service.LayoutService;
import com.kuznetsov.linoleum.service.OrderService;
import com.kuznetsov.linoleum.util.JspHelper;
import com.kuznetsov.linoleum.validator.CreateFragmentWithoutLayoutValidator;
import com.kuznetsov.linoleum.validator.CreateLayoutValidator;
import com.kuznetsov.linoleum.validator.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/orderLayout")
public class OrderLayoutServlet extends HttpServlet {

    private final LayoutService layoutService = LayoutService.getInstance();
    private final FragmentService fragmentService = FragmentService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final LayoutNameService layoutNameService = LayoutNameService.getInstance();
    private final CreateLayoutValidator createLayoutValidator = CreateLayoutValidator.getInstance();
    private final CreateFragmentWithoutLayoutValidator createFragmentWithoutLayoutValidator = CreateFragmentWithoutLayoutValidator.getInstance();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("another")!=null && req.getParameter("another").equals("true")){
            req.getSession().removeAttribute("cost");
            req.getSession().removeAttribute("customLFragments");

        }
        if(req.getSession().getAttribute("withoutLayout")==null) {
            String withoutLayout = req.getParameter("withoutLayout");
            req.getSession().setAttribute("withoutLayout", withoutLayout);
        }
        req.getRequestDispatcher(JspHelper.getPath("orderLayout")).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action")!=null && req.getParameter("action").equals("enterSize")){
            req.getSession().setAttribute("cost",null);
            String width = req.getParameter("width");
            String length = req.getParameter("length");
            //createDto used, because creating is new fragment;orderId is null, because order not created yet
            CreateFragmentWithoutLayoutDto createFragmentWithoutLayoutDto =
                    new CreateFragmentWithoutLayoutDto(width,length,null);
            ValidationResult validationResult1 = createFragmentWithoutLayoutValidator.isValid(createFragmentWithoutLayoutDto);
            if(!validationResult1.isValid()){
                req.setAttribute("errors",validationResult1.getErrors());
                doGet(req,resp);
            }else {
                orderService.getWithoutLayoutFragments().add(createFragmentWithoutLayoutDto);
                req.getSession().setAttribute("withoutLFragments", orderService.getWithoutLayoutFragments());
                resp.sendRedirect("/orderLayout");
            }
        }else {
            if(req.getParameter("action")!=null && req.getParameter("action").equals("calculateCost")) {
                int cost = orderService
                         .calcCostForWithoutLayoutFragments((LinoleumDto) req.getSession().getAttribute("orderLinoleum"));
                req.getSession().setAttribute("cost",cost);
                resp.sendRedirect("/orderLayout");
              }else {
                String city = req.getParameter("city");
                String street = req.getParameter("street");
                String homeNum = req.getParameter("homeNum");
                String roomCount = req.getParameter("roomCount");
                String layoutRowType = req.getParameter("layoutRowType");

                // creating CreateLayoutNameDto for validation, object may be not used if layout exist in layout table
                List<LayoutName> layoutNames = layoutNameService.findAll();
                int idx = layoutNames.get(layoutNames.size()-1).getId()+1;
                CreateLayoutNameDto createLayoutNameDto = new CreateLayoutNameDto("CUSTOM"+idx);
                CreateLayoutDto createLayoutDto = new CreateLayoutDto(city, street, homeNum, roomCount, layoutRowType, createLayoutNameDto);

                //using CreateLayoutValidator for all validations(also dto validation)
                ValidationResult validationResult2 = createLayoutValidator.isValid(createLayoutDto);
                if(!validationResult2.isValid()){
                    req.setAttribute("errors",validationResult2.getErrors());
                    doGet(req,resp);
                }else {
                    //checking layout existing in table, CreateLayoutDto in this case is not use
                    Optional<LayoutDto> layoutDto = layoutService.findByManyFields(city, street, homeNum, Integer.valueOf(roomCount), LayoutRowType.valueOf(layoutRowType));
                    if (layoutDto.isPresent()) {
                        req.getSession().setAttribute("layoutDto", layoutDto.get());
                        int id = layoutDto.get().getLayoutName().getId();
                        req.getSession().setAttribute("layoutFragments", fragmentService.findAllByLayoutNameId(layoutDto.get().getLayoutName().getId()));
                    } else {
                        //using CreateLayoutDto, which was created earlier
                        req.getSession().setAttribute("customLayout", true);
                        req.getSession().setAttribute("createLayoutDto", createLayoutDto);
                    }

                    resp.sendRedirect("/orderFragments");
                }
            }
        }

    }


}
