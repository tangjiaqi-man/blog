package com.hcjava.service;

import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hcjava.dao.UserDao;
import com.hcjava.pojo.User;
import com.hcjava.uil.NoteException;
import com.hcjava.uil.NoteResult;
import com.hcjava.uil.NoteUti;
import com.mysql.jdbc.NotUpdatable;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public NoteResult checkLogin(String name, String password) {
		NoteResult noteResult = new NoteResult();
		// �ж��û����Ƿ����
		User user = userDao.findByName(name);
		if (user == null) {
			noteResult.setStatus(1);
			noteResult.setMsg("�û���������");
			return noteResult;
		}
		String md5;
		try {
			md5 = NoteUti.md5(password);
			// 2.�ж������Ƿ���ȷ
			if (!user.getCn_user_password().equals(md5)) {
				noteResult.setStatus(2);
				noteResult.setMsg("�������");
				return noteResult;
			}
		} catch (Exception e) {
			throw new NoteException("��������쳣", e);
		}
		// ��½�ɹ�
		noteResult.setStatus(0);
		noteResult.setMsg("��½�ɹ�");
		user.setCn_user_password("");// ���������β�����
		noteResult.setData(user);
		return noteResult;
	}

	public NoteResult addUser(String name, String password, String nick) {
		NoteResult result=new NoteResult();
		 //����Ƿ�����
		  User user = userDao.findByName(name);
		 if(user!=null) {
			result.setStatus(1);
			result.setMsg("�û����ѱ�ռ��");
			return result;
		 }
		 
		 try {
			//ִ��ע��
			 User user2 = new User();
			 String uuid = NoteUti.createUUID();//ʹ�ù������������id
			 user2.setCn_user_id(uuid);//�����û�id
			 user2.setCn_user_name(name);//�����û�����
			 String md5 = NoteUti.md5(password);
			 user2.setCn_user_password(md5);//�����û�����
			 user2.setCn_user_nick(nick);//�����û�����
			 userDao.save(user2);
			result.setStatus(0);
			result.setMsg("ע��ɹ�");
			 return result;
		} catch (Exception e) {
		throw new NoteException("�û�ע���쳣", e);
		}
		
	}

}
