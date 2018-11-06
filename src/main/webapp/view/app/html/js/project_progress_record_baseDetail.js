var obj = getRequest();
var sgwzid=obj.sgwzid||"";
var chrsgwzmc = obj.chrsgwzmc||"";
var xmid=getCookie("id");
//初始化显示数据
window.onload = function(){
	//得到数据
	var pageData = getPageData();
	if(!pageData.zjcs){
		pageData.zjcs=[];
	};
	if(!pageData.dljcs){
		pageData.dljcs=[];
	};
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData
	});
	
	if(pageData.id){
		//加载图片
		initImgList("bj_xm_sgjd_jcsgnew",pageData.id,"1","fileIds","img-list",false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	var id = (obj.jcid?obj.jcid:"");
	$bjAjax({
		url:progressJcsgByXmidAndSgwzidApiPath,
		type:"post",
		async:false,
		data:{
			sgwzid:sgwzid,
			xmid:xmid,
			fwlx:"cx",
			id:id
		},
		success:function(data){
			console.log(data);
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
	var id = (obj.jcid?obj.jcid:"");
	toUrl("project_progress_record_baseAdd.html?intsgwzid="+sgwzid+"&chrsgwzmc="+chrsgwzmc+"&jcid="+id);
}


function outPage(){
	toUrl("project_progress_record.html");
}