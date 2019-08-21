package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.NoteService;
import com.hcjava.uil.NoteResult;

@Controller
public class UpdateNote {
@Resource
private NoteService noteService;
@RequestMapping("book/update.do")
@ResponseBody
public NoteResult update(String noteId,String bookId) {
	NoteResult result = noteService.updateNote(noteId, bookId);
	return result;
	
}
}
