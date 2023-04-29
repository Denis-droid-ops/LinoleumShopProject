package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.service.UserService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        userService.login(email,password).ifPresentOrElse(user->onLoginSucess(user,req,resp)
                                                                ,()->onLoginFail(req,resp));
    }

    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/login?error&email="+req.getParameter("email"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onLoginSucess(UserDto userDto,HttpServletRequest req,HttpServletResponse resp){
        req.getSession().setAttribute("user",userDto);
        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
