var xmid = getCookie("id");
$bjAjax({
	url:photoApiList,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		
	}
});


/**
 * 跳转项目详情列表
 */
function openNext(id){
		toUrl("project_photo_record_detail.html?id="+id);
}
function add(){
		toUrl("project_photo_record_add.html");
}