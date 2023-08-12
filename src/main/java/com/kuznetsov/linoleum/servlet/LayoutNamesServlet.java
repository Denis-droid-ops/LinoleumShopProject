package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.CreateLayoutDto;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.dto.UpdateLayoutTypeDto;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.service.LayoutNameService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/layoutNames")
public class LayoutNamesServlet extends HttpServlet {
    private final LayoutNameService layoutNameService = LayoutNameService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("layoutNames",layoutNameService.findAll());
        if(req.getParameter("addLayoutName")!=null){
            req.getRequestDispatcher(JspHelper.getPath("addLayoutNameForm")).forward(req,resp);
        }else {
            req.getRequestDispatcher(JspHelper.getPath("layoutNames")).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")!=null && req.getParameter("action").equals("deleteLayoutName")){
            layoutNameService.delete(Integer.parseInt(req.getParameter("id")));
        }else {
             CreateLayoutNameDto createLayoutDto = new CreateLayoutNameDto(req.getParameter("lName"));
             layoutNameService.save(createLayoutDto);
        }
        resp.sendRedirect("/admin/layoutNames");
    }

}
