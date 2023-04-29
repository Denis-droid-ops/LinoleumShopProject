package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.service.UserService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");
        System.out.println();
        CreateUserDto createUserDto = new CreateUserDto(name,email,password,phoneNumber);
        try {
            userService.save(createUserDto);
            resp.sendRedirect("/login?success");
        }catch(ValidationException e){
            req.setAttribute("errors",e.getErrors());
            doGet(req,resp);
        }
    }
}
