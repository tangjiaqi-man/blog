package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.NoteService;
import com.hcjava.uil.NoteResult;

@Controller
public class NoteContorller {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/loadnotes.do")
	@ResponseBody
	public NoteResult lodNotes(String bookId) {
		NoteResult result = noteService.LoginNotes(bookId);
		return result;
	}
}
