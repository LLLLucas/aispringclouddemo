package com.lucas.repository;

import com.lucas.entity.User;

import java.util.List;

public interface UserRepository {
   User login(String username,String password);
}
