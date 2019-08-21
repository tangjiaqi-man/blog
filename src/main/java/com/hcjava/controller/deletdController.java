package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.NoteService;
import com.hcjava.uil.NoteResult;

@Controller
public class deletdController {
@Resource
private NoteService noteService;
@RequestMapping("/note/delete.do")
@ResponseBody
public NoteResult delete(String noteId) {
	NoteResult result = noteService.deleteNote(noteId);
	return result;
}
}
