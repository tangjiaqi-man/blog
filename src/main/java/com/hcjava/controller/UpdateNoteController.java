package com.hcjava.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.NoteService;
import com.hcjava.uil.NoteResult;

@Controller
public class UpdateNoteController {
@Resource
private NoteService noteService;
@RequestMapping("/note/update.do")
@ResponseBody
public NoteResult LoadSeva(String noteId,String title,String body) {
	 NoteResult result = noteService.SaveLoginNote(noteId, title, body);
	return result;
	
}
}
