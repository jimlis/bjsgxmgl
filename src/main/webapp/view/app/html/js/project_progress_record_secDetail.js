showEdit();
var obj=getRequest();
var id = obj.id||"";
var did = obj.did||"";
var xmid=getCookie("id");
var vue;
var pageData={};
window.onload = function(){
	
	//判断是否更新；
	pageData = isUpdata();
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData
	});
	
}

//判断是否更新
function isUpdata(){
		var result={};
		$bjAjax({
			url:progressSecByParamApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdEcjgzxId:id,
				xmid:xmid,
				did:did,
				fwlx:"cx"
			},
			success:function(data){
				id=data.id||"";
				result = data;
			}
		});
		return result;
}

function edit(){
	toUrl("project_progress_record_secAdd.html?id="+id+"&did="+did);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_progress_record.html");
}