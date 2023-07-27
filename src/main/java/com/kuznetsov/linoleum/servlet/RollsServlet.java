package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.CreateRollDto;
import com.kuznetsov.linoleum.service.LinoleumService;
import com.kuznetsov.linoleum.service.RollService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/rolls")
public class RollsServlet extends HttpServlet {
    private final RollService rollService = RollService.getInstance();
    private final LinoleumService linoleumService = LinoleumService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (req.getParameter("addRoll")!=null){
           req.setAttribute("linoleums",linoleumService.findAll());
           req.getRequestDispatcher(JspHelper.getPath("addRollForm")).forward(req,resp);
       }else {
           req.setAttribute("rolls", rollService.findAll());
           req.getRequestDispatcher(JspHelper.getPath("rolls")).forward(req, resp);
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String partNum = req.getParameter("partNum");
        String width = req.getParameter("width");
        String length = req.getParameter("length");
        String linoleumId = req.getParameter("linoleum");
        CreateRollDto createRollDto = new CreateRollDto(partNum,width,length,linoleumId);
        rollService.save(createRollDto);
        resp.sendRedirect("/admin/rolls");
    }
}
