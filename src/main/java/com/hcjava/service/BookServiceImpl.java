package com.hcjava.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.BookDao;
import com.hcjava.pojo.Book;
import com.hcjava.uil.NoteResult;
@Service
public class BookServiceImpl implements BookService{
	@Resource
	private BookDao bookDao;
	public NoteResult loadUserBooks(String userId) {
		NoteResult result = new NoteResult();
		//根据用户id查询笔记本信息
		List<Book> list = bookDao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("查询笔记本完毕");
		result.setData(list);
		return result;
	}

}
