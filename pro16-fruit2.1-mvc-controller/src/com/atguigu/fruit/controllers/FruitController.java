package com.atguigu.fruit.controllers;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-06-[13:28]-周三
 */
public class FruitController {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (pageNo == null) {
            pageNo = 1;
        }
        // 设置当前页，默认值1
//        int pageNo = 1;

//        String oper = request.getParameter("oper");

        //如果oper!=null 说明 通过表单的查询按钮点击过来的
        //如果oper是空的，说明 不是通过表单的查询按钮点击过来的
//        String keyword = null;

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1， keyword应该从请求参数中获取
            pageNo = 1;
//            keyword = request.getParameter("keyword");
            //如果keyword为null，需要设置为空字符串""，否则查询时会拼接成 %null% , 我们期望的是 %%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            //将keyword保存（覆盖）到session中
            session.setAttribute("keyword", keyword);
        } else {
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域中获取
//            String pageNoStr = request.getParameter("pageNo");
//            if (StringUtil.isNotEmpty(pageNoStr)) {
//                pageNo = Integer.parseInt(pageNoStr);//如果从请求中读取到pageNo，则类型转换。否则，pageNo默认就是1
//            }
            //如果不是点击的查询按钮，那么查询是基于session中保存的现有keyword进行查询
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        // 重新更新当前页的值，保存到session作用域
        session.setAttribute("pageNo", pageNo);

        FruitDAO fruitDAO = new FruitDAOImpl();
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);

        session.setAttribute("fruitList", fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount + 5 - 1) / 5;
        /*
        总记录条数       总页数
        1               1
        5               1
        6               2
        10              2
        11              3
        fruitCount      (fruitCount + 5 - 1) /5
         */
        session.setAttribute("pageCount", pageCount);

        //此处的视图名称是 index
        //那么thymeleaf会将这个 逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ：   index
        //物理视图名称 ：   view-prefix + 逻辑视图名称 + view-suffix
        //所以真实的视图名称是：      /       index       .html
        //super.processTemplate("index", request, response);
        return "index";
    }

    private String add(String fname, Integer price, Integer fcount, String remark, HttpServletRequest request) {

        Fruit fruit = new Fruit(0, fname, price, fcount, remark);
        fruitDAO.addFruit(fruit);

        return "redirect:fruit.do";
    }

    private String del(Integer fid) {
        if (fid != null){
            fruitDAO.delFruit(fid);
//            super.processTemplate("index", request, response);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String edit(Integer fid, HttpServletRequest request) {
        if (fid != null){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit", fruit);
//            super.processTemplate("edit", request, response);
            return "edit";
        }
        return "error";
    }

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) {

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        //4.资源跳转
        //服务器端的内部转发
//        super.processTemplate("index",request,response);
//        request.getRequestDispatcher("index.html").forward(request, response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取fruitList，然后覆盖到session中，这样index.html页面是显示的session中的数据才是最新的
        //response.sendRedirect("fruit.do");

        return "redirect:fruit.do";
    }
}
