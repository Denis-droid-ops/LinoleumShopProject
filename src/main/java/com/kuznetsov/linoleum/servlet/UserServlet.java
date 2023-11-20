package com.kuznetsov.linoleum.servlet;



import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.service.UserService;
import com.kuznetsov.linoleum.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/users")
public class UserServlet extends HttpServlet {
    private UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users",userService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean onlyOneAdmin = userService.findAll().stream().map(userDto -> userDto.getRole().name()).filter(name->name.equals("ADMIN")).count()<2;
        if(req.getParameter("action").equals("delete")){
            if(onlyOneAdmin){
                resp.sendRedirect("/admin/users?deleteError");
            }else {
                String id = req.getParameter("id");
                userService.delete(Integer.valueOf(id));
                req.getSession().invalidate();
                resp.sendRedirect("/");
            }
        }else {
            UserDto userDto = (UserDto) req.getSession().getAttribute("user");
            Integer adminId = userDto.getId();
            String id = req.getParameter("id");
            String role = req.getParameter("role");
            if(onlyOneAdmin && adminId==Integer.valueOf(id)){
                resp.sendRedirect("/admin/users?changeError");
            }else {
                UpdateUserRoleDto updateUserRoleDto = new UpdateUserRoleDto(id, role);
                userService.updateRole(updateUserRoleDto);
                resp.sendRedirect("/admin/users");
            }
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
