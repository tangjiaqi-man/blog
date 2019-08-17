package com.hcjava.dao;

import java.util.List;

import com.hcjava.pojo.Book;

public interface BookDao {
 public List<Book>findByUserId(String userId);
}
