package com.hcjava.dao;

import com.hcjava.pojo.User;

public interface UserDao {
public User findByName(String name);
}
