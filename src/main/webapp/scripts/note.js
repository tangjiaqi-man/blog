//根据笔记本加载笔记列表
function loadNotes() {
	// alert("笔记列表登陆正常")
	// 1.获取参数
	var bookId = $(this).data("bookId");
	// 2.参数校验

	// 设置笔记本li选择效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 3.发送ajax
	$.ajax({
		url : base_path + "/note/loadnotes.do",
		type : "post",
		data : {
			"bookId" : bookId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var notes = result.data;
				// 清空
				// $("#note_ul li").empty();清空子代
				$("#note_ul li").remove();// 删除自己和子代
				// 循环生成列表笔记li元素
				for (var i = 0; i < notes.length; i++) {
					// 获取笔记的id
					var noteId = notes[i].cn_note_id;
					// 获取笔记的title
					var noteTitle = notes[i].cn_note_title;
					// 创建一个笔记li
					createNoteLi(noteId, noteTitle)
				}
			}
		},
		erreor : function() {
			alert("加载笔记列表异常")
		}
	});
}

// 创建笔记li元素
function createNoteLi(noteId, noteTitle) {
	var sli = '<li class="online">';
	sli += '<a>';
	sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '
			+ noteTitle
			+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli += '</a>';
	sli += '<div class="note_menu" tabindex="-1">';
	sli += '<dl>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli += '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli += '</dl>';
	sli += '</div>';
	sli += '</li>';
	// 把noteId绑定到li元素上
	var $li = $(sli);
	$li.data("noteId", noteId);
	// 将li追加到笔记列表中
	$("#note_ul").append($li);
}
// 根据笔记ID加载笔记信息
function loadNote() {
	// 1.获取参数
	var noteId = $(this).data("noteId");
	// 2.格式检查
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 3.发送ajax
	$.ajax({
		url : base_path + "/note/load.do",
		type : "post",
		data : {
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			// 获取标题
			if (result.status == 0) {
				var note = result.data;

				var title = note[0].cn_note_title;
				// 获取笔记内容
				var body = note[0].cn_note_body;
				// 设置到编辑区
				$("#input_note_title").val(title);
				// 用UEditor复文本编辑器设置内容
				um.setContent(body);
			}

		},
		error : function() {
			alert("笔记本查询异常")
		}
	});
}
// 保存笔记
function updateNote() {
	// 获取参数
	var title = $("#input_note_title").val().trim();
	var body = um.getContent();
	// 获取选中的笔记li元素
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	// 参数格式检查
	$("#note_title_span").html("");
	var ok=true;
	if ($li.length == 0) {
		ok=false;
		alert("请选择要保存的笔记");
	} else if (title == "") {
		ok=false;
		$("#note_title_span").html("<font color='red'>标题不能为空</font>");
	}

	// 发送Ajax
	if(ok){
	$.ajax({
		url : base_path + "/note/update.do",
		type : "post",
		data : {
			"title" : title,
			"body" : body,
			"noteId" : noteId
		},
		dataType : "json",
		success : function(result) {
			if (result.status == 0) {
				var sli = "";
				sli += '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i> '
						+ title
						+ '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				// 将选中的li的元素a内容替换
				$li.find("a").html(sli);
				// 提示成功
				alert(result.msg);
				
			}
		},
		error : function() {
			alert("笔记保存异常")
		}
	});
	}
}
//添加笔记
function addNote(){
	
	//1.获取参数  bookId userId title body
	//var bookId = $(this).data("bookId");
	//var bookId=$(this).data("bookId");
	var userId = getCookie("uid");
	//var body = um.getContent();
	var title=$("#input_note").val().trim();
	var $li=$("#book_ul a.checked").parent();
	var bookId=$li.data("bookId");
	//2.参数格式检查
	var ok=true;
	if(userId==null){
		ok=false;
		window.location.href="log_in.html"
	}
	if(title==""){
		ok=false;
		$("#note_span").html("笔记名为空");
	}if(ok){		
		//3.发送ajax回调函数
		$.ajax({
			url:base_path+"/note/add.do",
			typr:"post",
			data:{"userId":userId,"bookId":bookId,"title":title},
			dataType:"json",
			success:function(result){
				if(result.status==0){
				var noteId=	result.data.cn_note_id;
				var noteTitle=result.data.cn_note_title
				createNoteLi(noteId, noteTitle);
				//result.msg不用加括号
				alert(result.msg);
				}
			},
			error:function(){
				alert("添加笔记异常");
			}
		});
	}
}

