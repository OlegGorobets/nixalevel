package com.nixalevel.servlets.servlet;

import com.nixalevel.servlets.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MainServlet", value = "/main")
public class MainServlet extends HttpServlet {
    private static final List<User> userList = new ArrayList<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = new User(request.getLocalAddr(), request.getHeader("User-Agent"), new Date());
        userList.add(user);
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("userInfo.jsp").forward(request, response);
    }
}
