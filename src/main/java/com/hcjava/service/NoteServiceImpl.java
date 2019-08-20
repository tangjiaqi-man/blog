package com.hcjava.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.NoteDao;
import com.hcjava.pojo.Note;
import com.hcjava.uil.NoteResult;
import com.hcjava.uil.NoteUti;

@Service
public class NoteServiceImpl implements NoteService {
	@Resource
	private NoteDao noteDao;

	public NoteResult LoginNotes(String bookId) {
		NoteResult result = new NoteResult();
		List<Map> list = noteDao.findByBookId(bookId);
		result.setStatus(0);
		result.setMsg("查询完成");
		result.setData(list);
		return result;
	}

	public NoteResult LoginNote(String noteId) {
		NoteResult result = new NoteResult();
		List<Map> list = noteDao.findbyNoteId(noteId);
		result.setStatus(0);
		result.setMsg("查询完成");
		result.setData(list);
		return result;
	}
	public NoteResult SaveLoginNote(String noteId , String title , String body) {
		NoteResult result = new NoteResult();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int row = noteDao.dynamicUpate(note);
		if(row==1) {
			result.setStatus(0);;
			result.setMsg("保存完毕");
			return result;
		}else {
			result.setStatus(1);;
			result.setMsg("失败");
			return result;
		}
	}
	public NoteResult addNote(String bookId, String userId,String title) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		
		note.setCn_note_body("");
		
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_id(new NoteUti().createUUID());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_status_id("1");
		note.setCn_note_title(title);
		note.setCn_note_type_id("1");
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		noteDao.addNote(note);
		result.setStatus(0);
		result.setMsg("创建笔记本列表文件完成");
		result.setData(note);
		return result;
	}
}
