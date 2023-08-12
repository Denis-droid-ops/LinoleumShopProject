package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.service.LinoleumService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024*1024)
@WebServlet("/admin/linoleumHandling")
public class LinoleumHandlingServlet extends HttpServlet {
    private final LinoleumService linoleumService = LinoleumService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("linoleums",linoleumService.findAll());
        if(req.getParameter("updateLinoleum")!=null){
            req.setAttribute("entityLinoleum"
                    , linoleumService.findById(Integer.parseInt(req.getParameter("id"))).get());
            req.getRequestDispatcher(JspHelper.getPath("addLinoleumForm")).forward(req,resp);
        }else {
            if(req.getParameter("addLinoleum")!=null){
                req.getRequestDispatcher(JspHelper.getPath("addLinoleumForm")).forward(req,resp);
            }else {
                req.getRequestDispatcher(JspHelper.getPath("linoleumHandling")).forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action")!=null && req.getParameter("action").equals("deleteLinoleum")){
            linoleumService.delete(Integer.parseInt(req.getParameter("id")));
        }else {
            if (req.getParameter("action") != null && req.getParameter("action").equals("updateLinoleum")) {
                long lo = req.getPart("image").getSize();
                if(req.getPart("image").getSize()!=0){
                    UpdateLinoleumDto updateWithNewImage = new UpdateLinoleumDto(req.getParameter("id")
                            ,req.getParameter("name")
                            ,req.getParameter("protect")
                            ,req.getParameter("thickness")
                            ,req.getParameter("price")
                            ,req.getPart("image"));
                    linoleumService.update(updateWithNewImage);
                }else {
                    UpdateLinoleumDto updateWithDefaultImage = new UpdateLinoleumDto(req.getParameter("id")
                            ,req.getParameter("name")
                            ,req.getParameter("protect")
                            ,req.getParameter("thickness")
                            ,req.getParameter("price")
                            ,req.getParameter("imagePath"));
                    linoleumService.update(updateWithDefaultImage);
                }


            } else {
                linoleumService.save(new CreateLinoleumDto(req.getParameter("name")
                        ,req.getParameter("protect")
                        ,req.getParameter("thickness")
                        ,req.getParameter("price")
                        ,req.getPart("image")));

            }

        }
        resp.sendRedirect("/admin/linoleumHandling");
    }
}
