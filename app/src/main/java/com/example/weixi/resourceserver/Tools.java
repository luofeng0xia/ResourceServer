package com.example.weixi.resourceserver;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by weixi on 2016/4/28.
 */
public class Tools {

    public static String getTableName(String date) {
        System.out.println("############"+date);
        String tableName=date;
        tableName = tableName.replace("年", "");
        tableName=tableName.replace("月","");
        tableName=tableName.replace("日","");
        tableName=tableName.replace(":","");
        tableName=tableName.replace(" ","");
        tableName="zy"+tableName;
        System.out.println("***********"+tableName);
        return tableName;
    }

    public static List<String> getListFromString(String s){
        s=s.replace("[","");
        s=s.replace("]","");
        String[] strings = s.split(",");
        List<String> list=new ArrayList<String>();
        for (String str:strings
             ) {
            list.add(str.trim());
        }
        return list;
    }

        public static void writeDate(String date){
            SharedPreferences sp=getSP("zuoyeSP");
            if (sp!=null){
                sp.edit().putString("zydate",date).apply();
            }
        }

    /**
     * 创建一个存每个人成绩的成绩表
     * @param tableName 表名
     */
      public static void createCJTable(String tableName){
          SQLiteDatabase db = getChengJiDB();
          db.execSQL("CREATE TABLE "+tableName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,stunum,json)");
      }

    /**
     * 创建存所有成绩表表名的成绩单表
     * @param tableName 表名
     */
    public static void createCJNTable(String tableName){
        SQLiteDatabase db = getChengJiDB();
        db.execSQL("CREATE TABLE "+tableName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,name)");
    }


    public static SQLiteDatabase getChengJiDB() {
        MySqliteHelper mHelper=new MySqliteHelper("chengji.db");
        return mHelper.getWritableDatabase();
    }
    public static SQLiteDatabase getWenTiDB() {
        MySqliteHelper mHelper=new MySqliteHelper("wenti.db");
        return mHelper.getWritableDatabase();
    }

    public static SharedPreferences getSP(String spName){
          Context mContext=WebService.getmContext();
          if (mContext!=null){
              return mContext.getSharedPreferences(spName, Context.MODE_PRIVATE);
          }else {
              return null;
          }
      }

      public static String getZuoyeDate(){
          if (getSP("zuoyeSP")!=null){
              return getSP("zuoyeSP").getString("zydate",MyContacts.defValue);
          }else{
//              System.out.println("sp里面无法取到这个数");
              return null;
          }
      }

    // 流转化成字符串
    public static String inputStream2String(InputStream is) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1)
        {
            baos.write(i);
        }
        return baos.toString();
    }

    /**
     * 把输入流转化成文件
     * @param is 输入流
     * @param file 文件
     * @throws Exception
     */
    public static void inputStream2File(InputStream is, File file)
            throws Exception
    {
        InputStream inputSteam = is;
        BufferedInputStream fis = new BufferedInputStream(inputSteam);
        FileOutputStream fos = new FileOutputStream(file);
        int f;
        while ((f = fis.read()) != -1)
        {
            fos.write(f);
            fos.flush();
        }
        fos.close();
        fis.close();
        inputSteam.close();
    }

    /**
     * 返回一个文件给客户端
     * @param path 文件路径
     * @param resp 响应
     */
    public static void returnFile(String path,HttpServletResponse resp){
            File file = new File(path);
            System.out.println("*************"+path);
            long length = file.length();
            resp.setContentLength((int) length);
            try {
                OutputStream out = resp.getOutputStream();
                FileInputStream stream = null;
                stream = new FileInputStream(file);
                int count = -1;
                byte[] buffer = new byte[1024];
                while ((count = stream.read(buffer)) != -1) {
                    out.write(buffer, 0, count);
                    SystemClock.sleep(100);
                    out.flush();
                }
                stream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
}
