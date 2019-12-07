package com.lucas.repository;

import com.lucas.entity.Menu;
import com.lucas.entity.Type;

import java.util.List;

public interface TypeRepository {
    public Type findById(long id);
    public List<Type> findAll();
}
