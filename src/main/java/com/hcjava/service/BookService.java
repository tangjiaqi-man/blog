package com.hcjava.service;

import com.hcjava.pojo.Book;
import com.hcjava.uil.NoteResult;
//service ǰ̨�����������ݴ������,��������ӿڽ��ж����ݿ�Ľ���
public interface BookService {
	public NoteResult loadUserBooks(String userId);
	public NoteResult AddBooks(String  userId, String bookName);
}