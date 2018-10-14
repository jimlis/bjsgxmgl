$bjAjax({
	url:baseApixkz,
	type:"post",
	data:{
		xmid:getCookie("id"),
		xkzlx:2
	},
	success:function(data){
		bjConsole(data);
		document.getElementById("chrxkzbh").innerText = data.chrxkzbh;
		document.getElementById("chrffbm").innerText = data.chrffbm;
		document.getElementById("dtmfzrq").innerText = data.dtmfzrq;
		document.getElementById("dtmyxrq").innerText = data.dtmyxrq;
	}
})

function edit(){
	toUrl("project_basis_constructionPermit_edit.html");
}