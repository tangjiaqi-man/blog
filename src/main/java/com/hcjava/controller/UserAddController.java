package com.hcjava.controller;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.ResultType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcjava.service.UserService;
import com.hcjava.uil.NoteResult;

@Controller
public class UserAddController {
	@Resource
	private UserService userService;
	@RequestMapping("/user/add.do")
	@ResponseBody
	public NoteResult add(String name,String password,String nick) {
		 NoteResult result = userService.addUser(name, password, nick);
		return result;
	}
}
