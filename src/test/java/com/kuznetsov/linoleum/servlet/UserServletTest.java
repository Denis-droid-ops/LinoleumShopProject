package com.kuznetsov.linoleum.servlet;

import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.service.UserService;
import com.kuznetsov.linoleum.testData.UserTestData;
import com.kuznetsov.linoleum.util.JspHelper;
import org.h2.engine.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServletTest {
    @Mock
    private HttpSession httpSession;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private RequestDispatcher requestDispatcher;
    @InjectMocks
    private UserServlet userServlet;

    @Test
    void doGet() throws ServletException, IOException {
        Mockito.doReturn(new ArrayList<UserDto>()).when(userService).findAll();
        Mockito.doReturn(requestDispatcher).when(httpServletRequest).getRequestDispatcher(JspHelper.getPath("content"));
        userServlet.doGet(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest,Mockito.times(1)).getRequestDispatcher(JspHelper.getPath("content"));
        Mockito.verify(httpServletRequest,Mockito.never()).getSession();
        Mockito.verify(requestDispatcher).forward(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletRequest).setAttribute("users",userService.findAll());
    }

    @Test
    void doPostDeleteWhenOnlyOneAdmin() throws IOException, ServletException {
        Mockito.doReturn("delete").when(httpServletRequest).getParameter("action");
        List<UserDto> userDtoList = UserTestData.ALL_USERS.stream().map(u->new UserDto(u.getId()
        ,u.getName(),u.getEmail(),u.getPhoneNumber(),u.getRole())).collect(Collectors.toList());
        Mockito.doReturn(userDtoList).when(userService).findAll();

        userServlet.doPost(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletResponse).sendRedirect("/users?deleteError");
    }

    @Test
    void doPostDeleteWhenMoreThanOneAdmin() throws IOException, ServletException {
        Mockito.doReturn("delete").when(httpServletRequest).getParameter("action");
        List<UserDto> userDtoList = UserTestData.ALL_USERS.stream().map(u->new UserDto(u.getId()
                ,u.getName(),u.getEmail(),u.getPhoneNumber(),u.getRole())).collect(Collectors.toList());
        userDtoList.add(new UserDto(5,"Yuppi","rrr@mail.ru",89775442244L, Role.ADMIN));
        Mockito.doReturn(userDtoList).when(userService).findAll();
        Mockito.doReturn("5").when(httpServletRequest).getParameter("id");
        Mockito.doReturn(true).when(userService).delete(5);
        Mockito.doReturn(httpSession).when(httpServletRequest).getSession();

        userServlet.doPost(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).getSession();
        Mockito.verify(httpSession).invalidate();
        Mockito.verify(httpServletResponse).sendRedirect("/");
    }

    @Test
    void doPostChangeWhenOnlyOneAdmin() throws IOException, ServletException {
        Mockito.doReturn("change").when(httpServletRequest).getParameter("action");
        List<UserDto> userDtoList = UserTestData.ALL_USERS.stream().map(u->new UserDto(u.getId()
                ,u.getName(),u.getEmail(),u.getPhoneNumber(),u.getRole())).collect(Collectors.toList());
        Mockito.doReturn(userDtoList).when(userService).findAll();
        Mockito.doReturn(httpSession).when(httpServletRequest).getSession();
        Mockito.doReturn(userDtoList.get(0)).when(httpSession).getAttribute("user");

        Mockito.doReturn("1").when(httpServletRequest).getParameter("id");
        Mockito.doReturn("USER").when(httpServletRequest).getParameter("role");

        userServlet.doPost(httpServletRequest,httpServletResponse);

        Mockito.verify(httpServletRequest).getSession();
        Mockito.verify(httpSession).getAttribute("user");
        Mockito.verify(httpServletResponse).sendRedirect("/users?changeError");
    }

    @Test
    void doPostChangeWhenMoreThanOneAdmin() throws IOException, ServletException {
        Mockito.doReturn("change").when(httpServletRequest).getParameter("action");
        List<UserDto> userDtoList = UserTestData.ALL_USERS.stream().map(u->new UserDto(u.getId()
                ,u.getName(),u.getEmail(),u.getPhoneNumber(),u.getRole())).collect(Collectors.toList());
        userDtoList.add(new UserDto(5,"Yuppi","rrr@mail.ru",89775442244L, Role.ADMIN));
        Mockito.doReturn(userDtoList).when(userService).findAll();
        Mockito.doReturn(httpSession).when(httpServletRequest).getSession();
        Mockito.doReturn(userDtoList.get(4)).when(httpSession).getAttribute("user");

        Mockito.doReturn("5").when(httpServletRequest).getParameter("id");
        Mockito.doReturn("USER").when(httpServletRequest).getParameter("role");

        userServlet.doPost(httpServletRequest,httpServletResponse);

        Mockito.verify(userService).updateRole(new UpdateUserRoleDto("5","USER"));
        Mockito.verify(httpServletRequest).getSession();
        Mockito.verify(httpSession).getAttribute("user");
        Mockito.verify(httpServletResponse).sendRedirect("/users");
    }

}
