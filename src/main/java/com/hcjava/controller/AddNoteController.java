package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.NoteService;
import com.hcjava.uil.NoteResult;

@Controller
public class AddNoteController {
@Resource
private NoteService noteservice;
@RequestMapping("/note/add.do")
@ResponseBody
public NoteResult add(String bookId, String userId,String title) {
	NoteResult result = noteservice.addNote(bookId,userId, title);
	return result;
}
}
