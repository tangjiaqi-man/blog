package com.hcjava.service;

import com.hcjava.pojo.Book;
import com.hcjava.uil.NoteResult;
//service 前台传过来的数据传入参数,参数传入接口进行对数据库的交互
public interface BookService {
	public NoteResult loadUserBooks(String userId);
	public NoteResult AddBooks(String  userId, String bookName);
}