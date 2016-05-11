package com.example.weixi.resourceserver.Servlet;

import android.content.ContentValues;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/4/28.
 */
public class BzzyServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        File filePath=new File(MyContacts.zuoyePath);
        if (!filePath.exists()){
            if (filePath.mkdir()){
                System.out.println("创建成功");
            }else{
                System.out.println("创建失败");
            }
        }
        String fileName="zuoye.json";
        File file=new File(MyContacts.zuoyePath,fileName);
        String bzzy = req.getParameter("bzzy");
        FileOutputStream fos=new FileOutputStream(file);
        fos.write(bzzy.getBytes());
        String date=req.getParameter("date");

        // 创建一个某次课堂作业成绩单的表
        Tools.createCJTable(Tools.getTableName(date));

        // 创建一个存成绩单名字的表
        Tools.createCJNTable("cjntable");
        ContentValues values=new ContentValues();
        values.put("name",date);
        Tools.getChengJiDB().insert("cjntable",null,values);

        Tools.writeDate(date);
        fos.flush();
        fos.close();
    }
}
