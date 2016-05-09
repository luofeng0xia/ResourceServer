package com.example.weixi.resourceserver;

import com.example.weixi.resourceserver.Servlet.BzzyServlet;
import com.example.weixi.resourceserver.Servlet.CServlet;
import com.example.weixi.resourceserver.Servlet.ChengJiServlet;
import com.example.weixi.resourceserver.Servlet.DayiServlet;
import com.example.weixi.resourceserver.Servlet.KtzyServlet;
import com.example.weixi.resourceserver.Servlet.LoginServlet;
import com.example.weixi.resourceserver.Servlet.TiwenServlet;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServlertConfig {
	public static void config(ServletContextHandler handler) {
		handler.addServlet(new ServletHolder(new CServlet()), "/c");
		handler.addServlet(new ServletHolder(new LoginServlet()), "/login");
		handler.addServlet(new ServletHolder(new BzzyServlet()), "/bzzy");
		handler.addServlet(new ServletHolder(new KtzyServlet()), "/ktzy");
		handler.addServlet(new ServletHolder(new ChengJiServlet()), "/chengji");
		handler.addServlet(new ServletHolder(new TiwenServlet()), "/tiwen");
		handler.addServlet(new ServletHolder(new DayiServlet()), "/dayi");
	}
}
