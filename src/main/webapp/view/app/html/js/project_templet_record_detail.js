var obj = getRequest();
window.onload = function(){
	showEdit();
   	var id = obj.id;
   	$bjAjax({
		url:tempRecodeByIdApiPath,
		data:{
			xmYbsgjlId:id
		},
		type:'post',
		success:function(data){
			//服务器返回响应，根据响应结果，分析是否登录成功；
			document.getElementById("chrbgrmc").innerText = data.chrbgrmc||"";
			document.getElementById("chrbz").innerText = data.chrbz||"";
			document.getElementById("chrybms").innerText = data.chrybms||"";
			document.getElementById("chrybwz").innerText = data.chrybwz||"";
			document.getElementById("dtmgxrq").innerText = data.dtmgxrq||"";
			document.getElementById("dtmsprq").innerText = data.dtmsprq||"";
			document.getElementById("dtmwcrq").innerText = data.dtmwcrq||"";
			if(data.intyblx=='1'){
				document.getElementById("chryblx").innerText = "土建";
			}else if(data.intyblx=='2'){
				document.getElementById("chryblx").innerText = "机电";
			}else if(data.intyblx=='3'){
				document.getElementById("chryblx").innerText = "装修";
			}else if(data.intyblx=='4'){
				document.getElementById("chryblx").innerText = "园林";				
			}else{
				document.getElementById("chryblx").innerText = "其他";
			}
			initImgList("bj_xm_ybsgjl",id,"1","fileIds","img-list",false);
		},
	});
   
   
}

/*
*编辑
 */
function  openNext() {
    var address = "project_templet_record_add.html?id=" + obj.id;
    toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_templet_record.html");
}