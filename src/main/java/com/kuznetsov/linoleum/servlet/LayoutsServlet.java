package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.CreateLayoutDto;
import com.kuznetsov.linoleum.dto.UpdateLayoutTypeDto;
import com.kuznetsov.linoleum.entity.LayoutType;
import com.kuznetsov.linoleum.service.LayoutService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/layouts")
public class LayoutsServlet extends HttpServlet {
    private final LayoutService layoutService = LayoutService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("layouts", layoutService.findAll());
        if(req.getParameter("addLayout")!=null){
            req.getRequestDispatcher(JspHelper.getPath("addLayoutForm")).forward(req,resp);
        }else {
            req.getRequestDispatcher(JspHelper.getPath("layouts")).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")!=null && req.getParameter("action").equals("deleteLayout")){
            layoutService.delete(Integer.parseInt(req.getParameter("id")));
        }else {
            if(req.getParameter("action")!=null && req.getParameter("action").equals("updateLType")){
               layoutService.updateLType(new UpdateLayoutTypeDto(req.getParameter("id")
                       ,req.getParameter("lType")));
            }else {
                String city = req.getParameter("city");
                String street = req.getParameter("street");
                String homeNum = req.getParameter("homeNum");
                String roomCount = req.getParameter("roomCount");
                String layoutRowType = req.getParameter("layoutRowType");
                String layoutName = req.getParameter("layoutNameId");

                CreateLayoutDto createLayoutDto = new CreateLayoutDto(city, street, homeNum, roomCount, layoutRowType, layoutName);
                layoutService.save(createLayoutDto);
            }
        }
        resp.sendRedirect("/admin/layouts");
    }
}
