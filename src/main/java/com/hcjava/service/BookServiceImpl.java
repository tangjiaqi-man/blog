package com.hcjava.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.BookDao;
import com.hcjava.pojo.Book;
import com.hcjava.uil.NoteResult;
@Service
public class BookServiceImpl implements BookService{
	@Resource
	private BookDao bookDao;
	public NoteResult loadUserBooks(String userId) {
		NoteResult result = new NoteResult();
		//�����û�id��ѯ�ʼǱ���Ϣ
		List<Book> list = bookDao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("��ѯ�ʼǱ����");
		result.setData(list);
		return result;
	}

}
