var obj = getRequest();
window.onload = function(){
	showEdit();
   	var id = obj.id;
   	$bjAjax({
		url:safeReportByIdApiPath,
		data:{
			xmAqbgId:id
		},
		type:'post',
		success:function(data){
			//服务器返回响应，根据响应结果，分析是否登录成功；
			document.getElementById("chraqwtbsSpan").innerText = data.chraqwtbs||"";
			document.getElementById("dtmbgrqSpan").innerText = data.dtmbgrq||"";
			document.getElementById("chraqwtwzSpan").innerText = data.chraqwtwz||"";
			document.getElementById("chrbgrmcSpan").innerText = data.chrbgrmc||"";
			document.getElementById("chrzbSpan").innerText = data.chrzb||"";
			document.getElementById("dtmgxrqSpan").innerText = data.dtmgxrq||"";
			document.getElementById("dtmtzrqSpan").innerText = data.dtmtzrq||"";
			document.getElementById("dtmwczgrqSpan").innerText = data.dtmwczgrq||"";
			document.getElementById("chrsgdwSpan").innerText = data.chrsgdw||"";
			 ;
			//加载图片
			initImgList("bj_xm_aqbg",id,"1","fileIds","img-list",false);
			initImgList("bj_xm_aqbg",id,"2","fileIds","img-list_zgwc",false);
		},
	});
   
   
}

/*
*编辑
 */
function  openAdd() {
    var address = "project_saft_report_add.html?id=" + obj.id;
    toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_safe_report.html");
}