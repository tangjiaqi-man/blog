package com.hcjava.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.BookDao;
import com.hcjava.pojo.Book;
import com.hcjava.uil.NoteResult;
import com.hcjava.uil.NoteUti;

@Service
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;

	public NoteResult loadUserBooks(String userId) {
		NoteResult result = new NoteResult();
		// �����û�id��ѯ�ʼǱ���Ϣ
		List<Book> list = bookDao.findByUserId(userId);
		result.setStatus(0);
		result.setMsg("��ѯ�ʼǱ����");
		result.setData(list);
		return result;

	}

	public NoteResult AddBooks(String userId, String bookName) {
		/**
		 * ���õ�ǰ̨����userId����,�������ݿ�����ɶ����ݿ�����
		 * 
		 */
		NoteResult result = new NoteResult();
		Timestamp timestamp = new Timestamp(new Date().getTime());
		Book book = new Book();
		book.setCn_notebook_createtime(timestamp);
		String uuid = NoteUti.createUUID();
		book.setCn_notebook_id(uuid);
		book.setCn_notebook_desc(null);
		book.setCn_notebook_name(bookName);
		book.setCn_user_id(userId);
		bookDao.addBook(book);
		result.setStatus(0);
		result.setMsg("�������");
		result.setData(book);
		return result;
	}

}
