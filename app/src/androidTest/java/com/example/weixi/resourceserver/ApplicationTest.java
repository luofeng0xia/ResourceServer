package com.example.weixi.resourceserver;

import android.app.Application;
import android.os.Environment;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void test1(){
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WebInfos"+"/"  + "c";
        System.out.println("path: "+path);
    }
}