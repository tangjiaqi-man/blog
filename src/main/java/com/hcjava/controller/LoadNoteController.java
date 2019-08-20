package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.NoteService;
import com.hcjava.uil.NoteResult;

@Controller
public class LoadNoteController {
	@Resource
	private NoteService noteService;
	@RequestMapping("/note/load.do")
	@ResponseBody
	public NoteResult LoadByNoteId(String noteId) {
	NoteResult result = noteService.LoginNote(noteId);
		return result;
	}
}
