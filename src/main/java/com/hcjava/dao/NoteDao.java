package com.hcjava.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hcjava.pojo.Note;

public interface NoteDao {
	public List<Map> findByBookId(String bookId);

	public List<Map> findbyNoteId(String noteId);
	
	public void updateByNoteId(@Param("noteId")String noteId , @Param("title")String title ,@Param("body") String body);
	public int dynamicUpate(Note note);
	public void addNote(Note note);
	public void delet(String noteId);
	
}
