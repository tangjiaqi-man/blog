package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.BookService;
import com.hcjava.uil.NoteResult;

@Controller
public class AddBookController {
@Resource
private BookService bookService;
@RequestMapping("/book/add.do")
@ResponseBody
public NoteResult BookAdd(String userId,String bookName) {
	NoteResult result = bookService.AddBooks(userId, bookName);
	return result;
}
}
