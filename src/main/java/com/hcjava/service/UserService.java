package com.hcjava.service;

import com.hcjava.uil.NoteResult;

public interface UserService {
	public NoteResult checkLogin(String name,String password);
	public NoteResult addUser(String name,String password,String nick);
}
