package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.CreateFragmentDto;
import com.kuznetsov.linoleum.dto.FragmentDto;
import com.kuznetsov.linoleum.dto.LinoleumDto;
import com.kuznetsov.linoleum.service.FragmentService;
import com.kuznetsov.linoleum.service.OrderService;
import com.kuznetsov.linoleum.util.JspHelper;
import com.kuznetsov.linoleum.validator.CreateFragmentDtoValidator;
import com.kuznetsov.linoleum.validator.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/orderFragments")
public class OrderFragmentsServlet extends HttpServlet {
    private final FragmentService fragmentService = FragmentService.getInstance();
    private final OrderService orderService = OrderService.getInstance();
    private final CreateFragmentDtoValidator createFragmentDtoValidator = CreateFragmentDtoValidator.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("orderFragments")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action") != null && req.getParameter("action").equals("enterSize")) {
            req.getSession().setAttribute("cost", null);
            String width = req.getParameter("width");
            String length = req.getParameter("length");
            String fType= req.getParameter("fragmentType");
            //createDto used, because creating is new fragment;layoutNameId is null, because order not created yet
            CreateFragmentDto enteredFragmentDto = new CreateFragmentDto(width, length, fType, null);
            ValidationResult validationResult1 = createFragmentDtoValidator.isValid(enteredFragmentDto);
            if (!validationResult1.isValid()){
                req.setAttribute("errors",validationResult1.getErrors());
                doGet(req,resp);
            }else {
                orderService.getCustomLayoutFragments().add(enteredFragmentDto);
                orderService.getCustomLayoutFragments().stream().forEach(f -> System.out.println(f));
                req.getSession().setAttribute("customLFragments", orderService.getCustomLayoutFragments());
                resp.sendRedirect("/orderFragments");
            }
        } else {
            if (req.getParameter("action") != null && req.getParameter("action").equals("calculateCost")) {
                int cost = orderService
                        .calcCostForCustomLayoutFragments((LinoleumDto) req.getSession().getAttribute("orderLinoleum"));
                req.getSession().setAttribute("cost", cost);
                resp.sendRedirect("/orderFragments");
            } else {

                String[] fragmentIdList = req.getParameterValues("fragmentId");
                if (fragmentIdList == null) {
                    resp.sendRedirect("/orderFragments?checkError");
                } else {
                    List<FragmentDto> orderFragments = Arrays.stream(fragmentIdList)
                            .map(f -> fragmentService.findById(Integer.valueOf(f)).get()).collect(Collectors.toList());
                    int cost = orderService
                            .calcCost((LinoleumDto) req.getSession().getAttribute("orderLinoleum"), orderFragments);
                    req.getSession().setAttribute("cost", cost);
                    req.getSession().setAttribute("choosedFragments", orderFragments);
                    resp.sendRedirect("/orderFragments");
                }
            }
        }
    }


}
