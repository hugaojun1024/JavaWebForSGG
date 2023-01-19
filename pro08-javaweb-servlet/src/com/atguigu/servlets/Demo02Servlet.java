package com.atguigu.servlets;

//演示Servlet的生命周期

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-06-30-[14:59]-周四
 */
public class Demo02Servlet extends HttpServlet {

//    private Demo02Servlet(){    // 实例化Servlet类[com.atguigu.servlets.Demo02Servlet]异常
//        System.out.println("正在实例化...");
//    }

    public Demo02Servlet(){
        System.out.println("正在实例化...");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务...");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁...");
    }

}
