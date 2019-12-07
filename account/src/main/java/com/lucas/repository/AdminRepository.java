package com.lucas.repository;

import com.lucas.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
