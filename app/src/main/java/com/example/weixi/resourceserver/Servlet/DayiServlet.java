package com.example.weixi.resourceserver.Servlet;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.weixi.resourceserver.MyContacts;
import com.example.weixi.resourceserver.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/5/8.
 */
public class DayiServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        String dayitag = req.getParameter("dayitag");
        SQLiteDatabase db= Tools.getWenTiDB();
        String responseStr="";
        switch (dayitag){
            case "question":
                if (Tools.tabIsExist(db,MyContacts.WENTITABLENAME)) {
                    List<String> wentiList = new ArrayList<String>();
                    Cursor cursor = db.query(MyContacts.WENTITABLENAME, new String[]{"timu_id"},
                            "isanswer = ?", new String[]{"false"}, null, null, null);
                    while (cursor.moveToNext()) {
                        wentiList.add(cursor.getString(0));
                    }
                    responseStr=wentiList.toString();
                }
                OutputStream out=resp.getOutputStream();
                out.write(responseStr.getBytes());
                out.flush();
                out.close();
                break;
            case "tijiaojieda":
                String timuId=req.getParameter("timuid");
                String answerText=req.getParameter("answertext");
                String timuTitle = req.getParameter("timutitle");
                ContentValues values=new ContentValues();
                values.put("isanswer","true");
                values.put("timu_title",timuTitle);
                values.put("answertext",answerText);
                db.update(MyContacts.WENTITABLENAME,values,"timu_id = ?",new String[]{timuId});
                break;
            case "getanswer":
                String timuStr = req.getParameter("timulist");
                ArrayList<String> reqList= (ArrayList<String>) Tools.getListFromString(timuStr);

                if (Tools.tabIsExist(db,MyContacts.WENTITABLENAME)){
                    Cursor answerCursor = db.query(MyContacts.WENTITABLENAME, new String[]{"timu_id","timu_title", "answertext"},
                            "isanswer= ?" ,new String[]{"true"}, null, null, null);
                    JSONArray answers=new JSONArray();
                    while (answerCursor.moveToNext()){
                        String answerTimuId=answerCursor.getString(0);
                        if (reqList.contains(answerTimuId)){
                            JSONObject answer=new JSONObject();
                            String answerTimuTitle = answerCursor.getString(1);
                            String answerTimuText=answerCursor.getString(2);
                            try {
                                answer.put("timuId",answerTimuId);
                                answer.put("timuTitle",answerTimuTitle);
                                answer.put("answerText",answerTimuText);
                                answers.put(answer);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    answerCursor.close();
                    responseStr=answers.toString();
                }

                OutputStream os=resp.getOutputStream();
                os.write(responseStr.getBytes());
                os.flush();
                os.close();
                break;
        }

    }
}
