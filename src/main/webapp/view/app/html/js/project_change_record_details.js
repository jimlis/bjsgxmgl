var obj = getRequest();
var id=obj.id||"";
var isCk=hasPermission("bj:ckje");
//初始化显示数据
window.onload = function(){
	showEdit();
	
	//得到数据
	var pageData = getPageData();
	pageData["isCk"] = isCk;
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData
	});
	
	if(id){
		initFileList("bj_xm_bgsqjl",id,"1","fileIds","file-list",false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:changeApiDetail,
		type:"post",
		async:false,
		data:{
			xmBgsqjlId:id
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
}

function edit(){
	toUrl("project_change_record_add.html?id="+id);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_change_record.html");
}

