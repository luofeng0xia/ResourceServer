package com.example.weixi.resourceserver.Servlet;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.SystemClock;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.MySqliteHelper;
import com.example.weixi.resourceserver.Servlet.BaseServlet;
import com.example.weixi.resourceserver.Tools;
import com.example.weixi.resourceserver.WebService;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        File filePath=new File(MyContacts.path);
        if (!filePath.exists()){
            if (filePath.mkdir()){
                System.out.println("创建成功");
            }else{
                System.out.println("创建失败");
            }
        }
        String fileName="zuoye.json";
        File file=new File(MyContacts.path,fileName);
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
