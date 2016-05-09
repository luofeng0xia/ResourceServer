package com.example.weixi.resourceserver.Servlet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/5/2.
 */
public class TiwenServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String tiwen = req.getParameter("tiwen");
        String rTiwen = tiwen.replace("[", "");
        rTiwen=rTiwen.replace("]","");
        SQLiteDatabase wenTiDB = Tools.getWenTiDB();
        wenTiDB.execSQL("CREATE TABLE if not exists "+ MyContacts.WENTITABLENAME+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,timu_id,timu_title,isanswer,answertext)");
        String[] strings = rTiwen.split(",");
        for (String s:strings
             ) {
            String timuId=s.trim();
            Cursor cursor = wenTiDB.query(MyContacts.WENTITABLENAME, null, "timu_id = ?", new String[]{timuId}, null, null, null);
            if (cursor.getCount()==0){
                ContentValues values=new ContentValues();
                values.put("timu_id",timuId);
                values.put("isanswer","false");
                wenTiDB.insert(MyContacts.WENTITABLENAME,null,values);
            }
        }
    }
}
