package com.example.weixi.resourceserver.Servlet;

import android.os.Environment;
import android.os.SystemClock;

import com.example.weixi.resourceserver.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(HttpServletResponse.SC_OK);

        String tag = req.getParameter("path");
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/webinfos/c"+tag;
        //String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/webinfos"+"/" + "c"+"/1.jpg";
        Tools.returnFile(path,resp);
    }
}
