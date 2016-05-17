package com.example.weixi.resourceserver.Servlet;

import android.util.Log;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/5/16.
 */
public class FeedbackServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String content=req.getParameter("content");
        String contact=req.getParameter("contact");
        String type=req.getParameter("type");
        String s_version=req.getParameter("s_version");
        Log.d("feedback","content: "+content+" contact: "+contact+" type: "+type+" s_version: "+s_version);
    }
}
