package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.CreateFragmentDto;
import com.kuznetsov.linoleum.dto.UpdateFragmentDto;
import com.kuznetsov.linoleum.entity.FragmentType;
import com.kuznetsov.linoleum.entity.LayoutType;
import com.kuznetsov.linoleum.service.FragmentService;
import com.kuznetsov.linoleum.service.LayoutNameService;
import com.kuznetsov.linoleum.service.LayoutService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/fragments")
public class FragmentsServlet extends HttpServlet {
    private final FragmentService fragmentService = FragmentService.getInstance();
    private final LayoutNameService layoutNameService = LayoutNameService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("fragments",fragmentService.findAll());
        req.setAttribute("layoutNames", layoutNameService.findAll());
        if(req.getParameter("updateFragment")!=null){
            req.setAttribute("entityFragment"
            , fragmentService.findById(Integer.parseInt(req.getParameter("id"))).get());
            req.getRequestDispatcher(JspHelper.getPath("addFragmentForm")).forward(req,resp);
        }else {
            if(req.getParameter("addFragment")!=null){
                req.getRequestDispatcher(JspHelper.getPath("addFragmentForm")).forward(req,resp);
            }else {
                req.getRequestDispatcher(JspHelper.getPath("fragments")).forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")!=null && req.getParameter("action").equals("deleteFragment")){
            fragmentService.delete(Integer.parseInt(req.getParameter("id")));
        }else {
            if (req.getParameter("action") != null && req.getParameter("action").equals("updateFragment")) {
               fragmentService.update(new UpdateFragmentDto(req.getParameter("id")
               ,req.getParameter("width")
               ,req.getParameter("length")
               ,req.getParameter("fragmentType")
               ,req.getParameter("layoutNameId")));

            } else {
                fragmentService.save(new CreateFragmentDto(req.getParameter("width")
                        ,req.getParameter("length")
                        ,req.getParameter("fragmentType")
                        ,req.getParameter("layoutNameId")));

            }

        }
        resp.sendRedirect("/admin/fragments");
    }
}
