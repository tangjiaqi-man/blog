package com.hcjava.service;

import com.hcjava.uil.NoteResult;

public interface NoteService {
	public NoteResult LoginNotes(String bookId);

	public NoteResult LoginNote(String noteId);

	public NoteResult SaveLoginNote(String noteId ,String title,String body);
	public NoteResult addNote(String bookId,String userId,String title);
}
