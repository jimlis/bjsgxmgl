var obj = getRequest();
window.onload = function(){
	showEdit();
   	var id = obj.id||"";
   	$bjAjax({
		url:quaRecodeByIdApiPath,
		data:{
			xmZlqxbgId:id
		},
		type:'post',
		success:function(data){
			//服务器返回响应，根据响应结果，分析是否登录成功；
			document.getElementById("chrbgrmcSpan").innerText = data.chrbgrmc||"";
			document.getElementById("dtmbgrqSpan").innerText = data.dtmbgrq||"";
			document.getElementById("chrbzSpan").innerText = data.chrbz||"";
			document.getElementById("chrqxmsSpan").innerText = data.chrqxms||"";
			document.getElementById("chrqxwzSpan").innerText = data.chrqxwz||"";
			document.getElementById("dtmzlqxfxrqSpan").innerText = data.dtmzlqxfxrq||"";
			document.getElementById("dtmgxrqSpan").innerText = data.dtmgxrq||"";
			document.getElementById("dtmtzrqSpan").innerText = data.dtmtzrq||"";
			document.getElementById("dtmzgwcrqSpan").innerText = data.dtmzgwcrq||"";
			document.getElementById("chrbgrmcSpan").innerText = data.chrbgrmc||"";
			document.getElementById("chrsgdwSpan").innerText = data.chrsgdw||"";
			if(data.intqxlx=='1'){
				document.getElementById("chrqxlxSpan").innerText = "土建";
			}else if(data.intyblx=='2'){
				document.getElementById("chrqxlxSpan").innerText = "机电";
			}else if(data.intyblx=='3'){
				document.getElementById("chrqxlxSpan").innerText = "装修";
			}else if(data.intyblx=='4'){
				document.getElementById("chrqxlxSpan").innerText = "园林";				
			}else{
				document.getElementById("chrqxlxSpan").innerText = "其他";
			}
			//加载图片
			initImgList("bj_xm_zlqxbg",id,"1","fileIds","img-list",false);
			initImgList("bj_xm_zlqxbg",id,"2","fileIds","img-list_zgwc",false);
		},
	});
   
   
}

/*
*编辑
 */
function  edit() {
    var address = "project_quality_report_add.html?id=" + obj.id;
    toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_quality_report.html");
}