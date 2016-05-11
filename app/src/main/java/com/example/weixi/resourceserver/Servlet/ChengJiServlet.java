package com.example.weixi.resourceserver.Servlet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import org.json.JSONArray;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/4/30.
 */
public class ChengJiServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String tag=req.getParameter("tag");
        SQLiteDatabase chengJiDB = Tools.getChengJiDB();
        switch (tag){
            case "jiaojuan":
                String date = req.getParameter("date");
                String userNum = req.getParameter("usernum");
                String userJson = req.getParameter("userjson");
                String tableName=Tools.getTableName(date);
                ContentValues values=new ContentValues();
                values.put("stunum",userNum);
                values.put("json",userJson);
                chengJiDB.insert(tableName,null,values);
                break;
            case "timelist":
                Cursor nameCursor = chengJiDB.query(MyContacts.CJTIMETABLENAME, new String[]{"name"},
                        null, null, null, null, null);
                List<String> timeList=new ArrayList<String>();
                while (nameCursor.moveToNext()){
                    timeList.add(nameCursor.getString(0));
                }
                nameCursor.close();
                OutputStream nameOS=resp.getOutputStream();
                nameOS.write(timeList.toString().getBytes());
                nameOS.flush();
                nameOS.close();
                break;
            case "chengjilist":
                String title = req.getParameter("title");
                Tools.getTableName(title);
                Cursor chengjiCursor = chengJiDB.query(Tools.getTableName(title),
                        new String[]{"json"}, null, null, null, null, "stunum");
                List<String> chengjiList=new ArrayList<String>();
                while (chengjiCursor.moveToNext()){
                    chengjiList.add(chengjiCursor.getString(0));
                }
                chengjiCursor.close();
                OutputStream chengjiOS=resp.getOutputStream();
                chengjiOS.write(chengjiList.toString().getBytes());
                chengjiOS.flush();
                chengjiOS.close();
                break;
        }

    }
}
