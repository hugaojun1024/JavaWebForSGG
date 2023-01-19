package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-07-[17:33]-周四
 */

/*
@WebServlet(urlPatterns = {"/demo01"},
        initParams = {
            @WebInitParam(name="hello", value = "world"),
            @WebInitParam(name ="uname", value = "jim")
        }
        )
*/
public class Demo01Servlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue = " + initValue);

        ServletContext servletContext = getServletContext();
        String initParameter = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("initParameter: " + initParameter);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext servletContext = req.getServletContext();
        System.out.println("servletContext: " + servletContext);

        ServletContext servletContext1 = req.getSession().getServletContext();
        System.out.println("servletContext1" + servletContext1);

    }
}

//Servlet生命周期：实例化、初始化、服务、销毁