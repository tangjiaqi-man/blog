
// 加载用户笔记本列表
function loadUserBooks() {
	// 1.获取参数
	var userId = getCookie("uid");
	// 2.检查参数格式
	if (userId == null) {
		window.location.href = "log_in.html";
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
					for (var i = 0; i < books.length;i++) {
						//获取bookId
						var bookId=books[i].cn_notebook_id;
						//获取bookName
						var bookName=books[i].cn_notebook_name
						//遍历后台数据插入createBookLi方法中
						createBookLi(bookName, bookId)
					}
				}

			},
			error : function() {
				alert("加载笔记本列表异常")
			}
		});
	}

}
// 创建笔记本li元素
/*
 * <!-- <li class="online"><a class='checked'><i class="fa fa-book"
 * title="online" rel="tooltip-bottom"></i> 默认笔记本</a></li> -->
 */
function createBookLi(bookName, bookId) {
	// 构建li元素
	var sli = "";
	sli += '<li class="online"><a  ><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'
			+ bookName + '</a></li>';
//将bookId绑定到Li元素上
	var $li = $(sli);
	$li.data("bookId",bookId);
	//将li元素追加到ui列表中
	$("#book_ul").append($li);
	
}
