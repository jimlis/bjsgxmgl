var obj = getRequest();
var id=obj.id||"";
var xmid=getCookie("id");
//初始化显示数据
window.onload = function(){
	//得到数据
	var pageData = getPageData();
	
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData
	});
	debugger;
	if(id){
		initFileList("bj_xm_sgjd_ylsg",id,"1","fileIds","file-list",false);
	}
	
	//加载施工进度完成情况
	var list=pageData.xmSgjdYlsgJdList||[];
	for(i in list){
		initFileList("bj_xm_sgjd_ylsg_jd",list[i].id,"1","","fileIds_"+list[i].id,false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:progressGardenByParamApiPath,
		type:"post",
		async:false,
		data:{
			xmSgjdYlsgId:id,
			xmid:xmid,
			fwlx:"cx"
		},
		success:function(data){
			if(data){
				id=data.id||"";
				o=data;
			}
		}
	});
	return o;
}

function edit(){
	toUrl("project_progress_record_gardenAdd.html?id="+id);
}
function outPage(){
	toUrl("project_progress_record.html");
}