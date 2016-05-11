package com.example.weixi.resourceserver.Servlet;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理题库上传
 * Created by weixi on 2016/5/10.
 */
public class UploadTikuServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        File dir = new File(MyContacts.tikuPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem item : list) {
                String name = item.getFieldName();
                InputStream is = item.getInputStream();
                if (name.contains("tiku")) {
                    String fileName = item.getName();
                    File file = new File(dir,fileName);
                    try {
                        Tools.inputStream2File(is, file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
}
