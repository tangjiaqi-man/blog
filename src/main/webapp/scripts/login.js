//log_in.html主处理
$(function() {
	// 给登陆页面绑定单击事件
	$("#login").click(checkLogin)
	$("#regist_button").click(registUser)
})
// 登陆处理
function checkLogin() {
	// 获取请求参数
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	// 检查参数格式
	$("#count_span").html("");
	$("#password_span").html("");
	var ok = true;
	if (name == "") {
		ok = false;
		$("#count_span").html("用户签名为空");
	}
	
	if (password =="") {
		ok = false;
		$("#password_span").html("密码为空");
	}
	// 3.发送Ajax
	if (ok) {
		$.ajax({
			url : base_path + "/user/login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
			},
			dataType : "json",
			success : function(result) {
				// result就是服务器返回json结果
				if (result.status == 0) {// 登陆成功的情况
					var user = result.data;// 获取返回的user信息
					// 写入cookie,2个小时过期
					
					addCookie("uid", user.cn_user_id, 2);
					addCookie("uname", user.cn_user_name, 2);
					// 成功登陆后跳转到主界面
					window.location.href = "edit.html";
					//window.location.href = "edit.html";
				} else if (result.status == 1) {// 用户不存在的情况
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {// 密码错误
					$("#count_span").html(result.msg);
				}
			},
			error : function() {
				alert("登陆异常")
			}
		});
	}
}
// 添加注册页面
function registUser() {
	// 1.获取请求参数
	var name = $("#regist_username").val().trim();
	var password = $("#regist_password").val().trim();
	var nick = $("#nickname").val().trim();
	var f_password = $("#final_password").val().trim();
	// 2.检查参数格式
	$("warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	var ok = true;
	if (name == "") {
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用户名为空")
	}
	if (password == "") {
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码为空")
	}
	else if (password.length < 6) {
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码长度太短")
	}
	if (f_password == "") {
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("确认密码为空")
	} else if (f_password != password) {
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("密码不相等")
	}
	// 发送ajax
	if (ok) {
		$.ajax({
			url : base_path + "/user/add.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password,
				"nick" : nick
			},
			dataType : "json",
			// 回调函数
			success : function(result) {
				// 对回调函数进行判断
				if (result.status == 0) {// 登陆成功的情况
					$("#back").click();
				}
				if (result.status == 1) {//用户名被占用
					ok = false;
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg)
				}
			},
			error : function() {
				alert("注册异常")
			}
		})
	}
}