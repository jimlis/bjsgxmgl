var obj = getRequest();
var gqjdid=obj.gqjdid||"";
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
	
	if(pageData.id){
		//加载图片
		initFileList("bj_xm_sgjd_qqbj",pageData.id,"1","fileIds","file-list",false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:progressGetQqbjApiPath,
		type:"post",
		async:false,
		data:{
			xmid:xmid,
			gqjdid:gqjdid,
			fwlx:"cx",
		},
		success:function(data){
			if(data){
				o=data;
			}else{
				bjToast("无数据，请更新数据");
			}
		}
	});
	
	return o;
}

function edit(){
	toUrl("project_progress_record_qqbjAdd.html?gqjdid="+gqjdid);
}


function outPage(){
	toUrl("project_progress_record.html");
}