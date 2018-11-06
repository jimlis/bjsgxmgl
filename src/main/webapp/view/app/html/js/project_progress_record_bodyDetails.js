var obj = getRequest();
var id=obj.id||"";
var sgwzid=obj.sgwzid||"";
var chrsgwz = obj.chrsgwz||"";
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
	
	if(id){
		//加载图片
		initImgList("bj_xm_sgjd_ztjgsg",id,"1","fileIds","img-list",false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:progressZtjgPath+"getXmSgjdZtjgsgListByXmidAndSgwzd",
		type:"post",
		async:false,
		data:{
			sgwzd:sgwzid,
			xmid:xmid
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
	toUrl("project_progress_record_bodyAdd.html?sgwzid="+sgwzid+"&chrsgwz="+chrsgwz);
}
function outPage(){
	toUrl("project_progress_record.html");
}