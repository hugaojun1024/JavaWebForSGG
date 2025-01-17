package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-03-[14:36]-周日
 */

//演示服务器端内部转发，以及客户端重定向
public class Demo06Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("demo06....");
        //服务器端内部转发
        request.getRequestDispatcher("demo07").forward(request, response);

        //客户端重定向
//        response.sendRedirect("demo07");
    }
}
