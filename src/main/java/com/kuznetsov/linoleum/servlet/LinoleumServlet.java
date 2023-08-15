package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.LinoleumFilter;
import com.kuznetsov.linoleum.service.LinoleumService;
import com.kuznetsov.linoleum.service.UserService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("")
public class LinoleumServlet extends HttpServlet {
    private final LinoleumService linoleumService = LinoleumService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("another") != null && req.getParameter("another").equals("true")) {
                req.getSession().removeAttribute("cost");
        }
        if(req.getParameter("action")!=null && req.getParameter("action").equals("sorting")){
            String field = "";
            Integer minPrice = 0;
            Integer maxPrice = 0;
            if(req.getParameter("flexRadioDefault")!=null) {
                field = req.getParameter("flexRadioDefault");
            }
            if(req.getParameter("min")!="") {
                minPrice = Integer.parseInt(req.getParameter("min"));
            }
            if(req.getParameter("max")!="") {
                maxPrice = Integer.parseInt(req.getParameter("max"));
            }
            req.setAttribute("linoleums",linoleumService.findAll(new LinoleumFilter(maxPrice,minPrice),field));
        }else {
            req.setAttribute("linoleums", linoleumService.findAll());
        }

        req.getRequestDispatcher(JspHelper.getPath("linoleums")).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

           if(req.getSession().getAttribute("user")==null){
              resp.sendRedirect("/?purchaseError");
             }else {
                String linoleumId = req.getParameter("linoleumId");
                req.getSession().setAttribute("orderLinoleum", linoleumService.findById(Integer.valueOf(linoleumId)).get());
                resp.sendRedirect("/orderLayout");
            }

    }


}
