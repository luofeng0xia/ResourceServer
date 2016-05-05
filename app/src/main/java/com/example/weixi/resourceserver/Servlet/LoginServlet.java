package com.example.weixi.resourceserver.Servlet;

import com.example.weixi.resourceserver.Servlet.BaseServlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);

        String loginResp;
        String teacherPass="123456";
        String password = req.getParameter("password");
        if (teacherPass.equals(password)){
            loginResp="right";
        }else {
            loginResp="wrong";
        }
        OutputStream out=resp.getOutputStream();
        resp.setContentLength(loginResp.length());
        byte[] buffer=loginResp.getBytes();
        out.write(buffer);
        out.flush();
        out.close();
    }
}
