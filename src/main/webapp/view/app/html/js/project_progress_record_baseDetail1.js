var obj = getRequest();
var sgwzid=obj.sgwzid||"";
var chrsgwzmc = obj.chrsgwzmc||"";
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
//	
//	if(xmid){
//		//加载图片
//		initImgList("bj_xm_sgjd_jcsg",id,"1","fileIds","img-list",false);
//	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:progressJcsgByXmidAndSgwzidApiPath,
		type:"post",
		async:false,
		data:{
			sgwzid:sgwzid,
			xmid:xmid
		},
		success:function(data){
			if(data){
				o=data;
				o["chrsgwzmc"]=chrsgwzmc;
			}else{
				bjToast("无数据，请更新数据");
			}
		}
	});
	console.log(o);
	return o;
}

function edit(){
	toUrl("project_progress_record_baseAdd1.html?intsgwzid="+sgwzid+"&chrsgwzmc="+chrsgwzmc);
}


function outPage(){
	toUrl("project_progress_record.html");
}