var obj = getRequest();
window.onload = function(){
	
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
