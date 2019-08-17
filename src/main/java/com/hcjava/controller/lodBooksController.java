package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.BookService;
import com.hcjava.service.BookServiceImpl;
import com.hcjava.uil.NoteResult;

@Controller
public class lodBooksController {
@Resource
private BookService bookService;
@RequestMapping("/book/loadbooks.do")
@ResponseBody
public NoteResult loadbooks(String userId) {
	NoteResult result = bookService.loadUserBooks(userId);
	return result;
	
}
}
