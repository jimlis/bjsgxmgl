var obj = getRequest();
var id=obj.id||"";
//初始化显示数据
window.onload = function(){
	//得到数据
	var pageData = getPageData();
	
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData
	});
	
	if(id){
		initFileList("bj_xm_sgjd_swgwsg",id,"1","fileIds","file-list",false);
	}
	
	//加载施工进度完成情况
	if(pageData&&pageData.xmSgjdSwgwsgJdListMap){
		var listMap=pageData.xmSgjdSwgwsgJdListMap;
		for(key in listMap){
			if(typeof(key)!='function'){
				var list=listMap[key];
				if(list&&list.length>0){
					for(i in list){
						initFileList("bj_xm_sgjd_swgwsg_jd",list[i].id,"1","","fileIds_"+key+"_"+list[i].id,false);
					}
				}
			}
		}
	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:progressOutDoorByIdApiPath,
		type:"post",
		async:false,
		data:{
			xmSgjdSwgwsgId:id
		},
		success:function(data){
			debugger
			if(data){
				o=data;
			}
		}
	});
	return o;
}

function edit(){
	toUrl("project_progress_record_outdoorAdd.html?id="+id);
}
function outPage(){
	toUrl("project_progress_record.html");
}