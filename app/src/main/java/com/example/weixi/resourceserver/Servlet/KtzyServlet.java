package com.example.weixi.resourceserver.Servlet;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/4/28.
 */
public class KtzyServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String date = req.getParameter("date");
        String zuoyeDate=Tools.getSPDate();
        if (zuoyeDate!=null){
            if (date.equals(zuoyeDate)){
                System.out.println("已经是最新的作业");
            }else {
                String filePath=MyContacts.zuoyePath +"/zuoye.json";
                Tools.returnFile(filePath,resp);
            }
        }else {
            System.out.println("sp里没有取到作业的时间数据");
        }
    }
}
