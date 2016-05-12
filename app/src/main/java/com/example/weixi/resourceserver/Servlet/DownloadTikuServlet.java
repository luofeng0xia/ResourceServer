package com.example.weixi.resourceserver.Servlet;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/5/11.
 */
public class DownloadTikuServlet extends BaseServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String tag = req.getParameter("downloadtag");

        switch (tag){
            case "islast":
                updateRequest(req,resp,null);
                break;
            case "download":
                String tikuPath=MyContacts.tikuPath+"/lasttiku";
                updateRequest(req,resp,tikuPath);
                break;
        }
    }

    private void updateRequest(HttpServletRequest req, HttpServletResponse resp, String filePath){
        String version = req.getParameter("version");
        String lastVersion=Tools.getSP("tiku").getString("version","0");
        if (!version.equals(lastVersion)){
            try {
                OutputStream os=resp.getOutputStream();
                if (filePath==null){
                    os.write(lastVersion.getBytes());
                    os.flush();
                    os.close();
                }else {
                    Tools.returnFile(filePath,resp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
