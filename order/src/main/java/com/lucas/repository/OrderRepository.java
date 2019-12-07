package com.lucas.repository;

import com.lucas.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findAllByUId(int index,int limit,int uid);
    public int countByUid(int uid);
    public int countByState();
    public List<Order> findAllByState(int index,int limit);
    public void updateState(long id);
}
