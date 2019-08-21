
// 加载用户笔记本列表
function loadUserBooks() {
	// 1.获取参数登陆列表传过来的id以uid用cookie绑定表示
	var userId = getCookie("uid");
	// 2.检查参数格式
	if (userId == null) {
		window.location.herf= "log_in.html";
	} else {
		// 3.发送ajax
		$.ajax({
			url : base_path + "/book/loadbooks.do",
			type : "post",
			data : {
				"userId" : userId
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					// 查询成功,获取返回的笔记本集合
					var books = result.data;
					// 循环生成列表元素
					for (var i = 0; i < books.length; i++) {
						// 从循环中获取列表id
						var bookId = books[i].cn_notebook_id;
						// 从循环中获取列表的名称name
						var bookName = books[i].cn_notebook_name;
						// 遍历后台数据插入createBookLi方法中
						createBookli(bookName, bookId);
					}
				}
			},
			erroe : function() {
				alert("加载笔记本异常")
			}
		});
	}
}
// 创建笔记本li元素
function createBookli(bookName, bookId) {
	// 构建li元素
	var sli = "";
	sli += '<li class="online"><a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'
			+ bookName + '</a></li>';
	// 将bookId绑定到li元素上
	// 将sli从js中拿到
	var $li = $(sli);
	$li.data("bookId", bookId);
	// 将li元素追加到ui列表中
	$("#book_ul").append($li)

}
// 添加笔记本列表
// 1.获取参数userId bookName
function addBook() {
	// 获取参数userId
	var userId = getCookie("uid");
	var bookName = $("#input_notebook").val().trim();
	// 2.参数格式检查
	// 创建笔记本时传入参数不为空
	var ok = true;
	if (bookName == "") {
		ok = false;
		$("#notebook_span").html("参数为空");
	}
	// 3.发送Ajax
	if (ok) {
		$.ajax({
			url : base_path + "/book/add.do",
			type : "post",
			data : {
				"userId" : userId,
				"bookName" : bookName
			},
			dataType : "json",
			success : function(result) {
				if (result.status == 0) {
					// 获取回调的bookName参数
					var bookName = result.data.cn_notebook_name;
					// 获取回调的bookId参数
					var bookId = result.data.cn_notebook_id;
					createBookli(bookName, bookId);
					// 提示笔记本创建成功
					alert(result.msg);
				}
			},
			error : function() {
				alert("笔记本创建异常");
			}
		});
	}
}
