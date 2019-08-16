//log_in.html主处理
$(function(){
	//给登陆页面绑定单击事件
	$("#login").click(checkLogin)
})
//登陆处理
function checkLogin(){
	//获取请求参数
 var name=$("#count").val().trm();
 var password=$("#password").val().trm();
 //检查参数格式
 var ok=true;
 if(name=""){
	 ok=false;
	 $("#count_span").html("用户签名为空")
 }
 if(password=""){
	 ok=false;
	 $("#password").hml("密码为空")
 }
//3.发送Ajax
 if(ok){
	 $.ajax({
		 url:base_path+"/user/login.do",
		 type:"post",
		 data:{
			 "name":name,
			 "password":password
		 },dataType:"json",
			 success:function(result){
		 	//result就是服务器返回json结果
		 if(result.status==0){//登陆成功的情况
			 var user=result.data;//获取返回的user信息
			 //写入cookie,2个小时过期
			 addCokie("uid",user.cn_user_id,2);
			 addCokie("uname",user.cn_user_name,2);
			 //成功登陆后跳转到主界面
			 window.location.href="edit.html" 
		 } else if(result.statud==1){//用户不存在的情况
			 $("#count_span").html(result.msg);
		 } else if(result.statud==2){//密码错误
		 $("#count_span").html(result.msg);
		 }
		 },
		 error:function(){
			 alert("登陆异常")
		 }
	 });
	 
	 
 }
 

}