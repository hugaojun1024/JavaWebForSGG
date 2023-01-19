package com.atguigu.fruit.servlets;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-05-[13:02]-周二
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取参数
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新

        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //4.资源跳转
        //服务器端的内部转发
//        super.processTemplate("index",request,response);
//        request.getRequestDispatcher("index.html").forward(request, response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取fruitList，然后覆盖到session中，这样index.html页面是显示的session中的数据才是最新的
        response.sendRedirect("index");
    }
}
