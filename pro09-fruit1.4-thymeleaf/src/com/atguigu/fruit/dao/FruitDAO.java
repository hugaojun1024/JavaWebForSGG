package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author hugaojun Email:nat17185546@163.com
 * @create 2022-07-03-[15:00]-周日
 */
public interface FruitDAO {
    //获取所有的库存列表信息
    List<Fruit> getFruitList();
}
