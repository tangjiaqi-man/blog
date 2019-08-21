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

	public NoteResult SaveLoginNote(String noteId, String title, String body) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int row = noteDao.dynamicUpate(note);
		if (row == 1) {
			result.setStatus(0);
			;
			result.setMsg("保存完毕");
			return result;
		} else {
			result.setStatus(1);
			;
			result.setMsg("失败");
			return result;
		}
	}

	public NoteResult addNote(String bookId, String userId, String title) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_id(NoteUti.createUUID());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_title(title);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		noteDao.addNote(note);
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note);
		return result;
	}

	public NoteResult deleteNote(String noteId) {
		NoteResult result = new NoteResult();
		noteDao.delet(noteId);
		result.setStatus(0);
		result.setMsg("删除成功");
		return result;
	}

	public NoteResult updateNote(String noteId, String bookId) {
		NoteResult result = new NoteResult();
		Note note = new Note();
		note.setCn_notebook_id(bookId);
		note.setCn_note_id(noteId);
		int i = noteDao.dynamicUpate(note);
		if(i==1) {
			result.setStatus(0);
			result.setMsg("移动成功");
			result.setData(note);
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("移动失败");
			return result;
		}
		
	}
}
