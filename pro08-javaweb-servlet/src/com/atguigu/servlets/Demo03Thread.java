package com.atguigu.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 线程不安全
 * @author hugaojun Email:nat17185546@163.com
 * @create 2023-07-29-[下午 6:37]-周六
 */
@WebServlet("/demoThead")
public class Demo03Thread extends HttpServlet {

    // 线程不安全
    private int count = 0;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        count++;
        resp.getWriter().println("count: " + count);
    }
}
