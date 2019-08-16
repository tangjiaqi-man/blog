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
		// 判断用户名是否存在
		User user = userDao.findByName(name);
		if (user == null) {
			noteResult.setStatus(1);
			noteResult.setMsg("用户名不存在");
			return noteResult;
		}
		String md5;
		try {
			md5 = NoteUti.md5(password);
			// 2.判断密码是否正确
			if (!user.getCn_user_password().equals(md5)) {
				noteResult.setStatus(2);
				noteResult.setMsg("密码错误");
				return noteResult;
			}
		} catch (Exception e) {
			throw new NoteException("密码加密异常", e);
		}
		// 登陆成功
		noteResult.setStatus(0);
		noteResult.setMsg("登陆成功");
		user.setCn_user_password("");// 把密码屏蔽不返回
		noteResult.setData(user);
		return noteResult;
	}

	public NoteResult addUser(String name, String password, String nick) {
		NoteResult result=new NoteResult();
		 //检查是否重名
		  User user = userDao.findByName(name);
		 if(user!=null) {
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		 }
		 
		 try {
			//执行注册
			 User user2 = new User();
			 String uuid = NoteUti.createUUID();//使用工具类生成随机id
			 user2.setCn_user_id(uuid);//设置用户id
			 user2.setCn_user_name(name);//设置用户名称
			 String md5 = NoteUti.md5(password);
			 user2.setCn_user_password(md5);//设置用户密码
			 user2.setCn_user_nick(nick);//设置用户密码
			 userDao.save(user2);
			result.setStatus(0);
			result.setMsg("注册成功");
			 return result;
		} catch (Exception e) {
		throw new NoteException("用户注入异常", e);
		}
		
	}

}
