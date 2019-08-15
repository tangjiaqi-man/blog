//log_in.html主处理
$(function() {
	// 页面加载完毕
	// 给登陆按钮绑定单击事件
	$("#login").click(checkLogin);
})
// 登陆处理
// 点击事件绑定成功
function checkLogin() {
	// 1.获取请求参数值 name password
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();

	// 2.检查参数格式
	$("#count_span").html("");
	$("#password_span").html("")
	var ok = true;
	if (name == "") {
		ok = false;
		$("#count_span").html("用户名为空");
	}
	if (password == "") {
		ok = false;
		$("#password_span").html("密码为空")
	}
	// 3.发送ajax
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
				// result就是服务器返回的json结果
				if (result.status == 0) {// 登陆成功
					var user = result.data;// 获取返回的user信息
					// 写入cookie,2小时过期
					addCookie("uid", user.cn_user_id, 2);
					addCookie("uname", user.cn_user_name, 2);
					// 成功登陆后跳转到主界面
					window.location.href = "edit.html";
				} else if (result.status == 1) {// 用户名不存在
					$("#count_span").html(result.msg);
				} else if (result.status == 2) {// 密码错误
					$("#password_span").html(result.msg)
				}
			},
			error : function() {
				alert("登陆异常");
			}
		});
	}

}