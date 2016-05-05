package com.example.weixi.resourceserver.Servlet;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.weixi.resourceserver.Tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/4/30.
 */
public class ChengJiServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String userNum = req.getParameter("usernum");
        String userJson = req.getParameter("userjson");
        SQLiteDatabase chengJiDB = Tools.getChengJiDB();
        String tableName=Tools.getTableName(date);
        ContentValues values=new ContentValues();
        values.put("stunum",userNum);
        values.put("json",userJson);
        chengJiDB.insert(tableName,null,values);
    }
}
