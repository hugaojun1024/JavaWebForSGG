package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-03-[15:01]-周日
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        //select * from t_fruit LIMIT (PageNo - 1)*5 , 5;
        return super.executeQuery("select * from t_fruit where fname like ? or remark like ? limit ? , 5", "%"+keyword+"%", "%"+keyword+"%",(pageNo - 1) * 5);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ?", fid);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fname = ?, price = ?, fcount = ?, remark = ? where fid = ?";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ?", fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values (0, ?, ?, ?, ?)";
        super.executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
    }

    @Override
    //java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.Integer
    public int getFruitCount(String keyword) {
        return ((Long)super.executeComplexQuery("select count(*) from t_fruit where fname like ? or remark like ?", "%"+keyword+"%", "%"+keyword+"%")[0]).intValue();
    }
}

