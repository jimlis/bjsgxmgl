showEdit();
$bjAjax({
	url:baseApixkz,
	type:"post",
	data:{
		xmid:getCookie("id"),
		xkzlx:2
	},
	success:function(data){
		var id=data.id||"";
		document.getElementById("chrxkzbh").innerText = data.chrxkzbh||"";
		document.getElementById("chrffbm").innerText = data.chrffbm||"";
		document.getElementById("dtmfzrq").innerText = data.dtmfzrq||"";
		document.getElementById("dtmyxrq").innerText = data.dtmyxrq||"";
		initFileList("bj_xm_xkz",id,"1","fileIds","file-list",false);
	}
})

function edit(){
	toUrl("project_basis_constructionPermit_edit.html");
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_basis_list.html");
}