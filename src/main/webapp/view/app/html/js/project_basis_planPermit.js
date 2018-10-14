var xmid = getCookie("id");
$bjAjax({
	url:baseApixkz,
	type:"post",
	data:{
		xmid:xmid,
		xkzlx:"1"
	},
	success:function(data){
		var chrxkzbh = data.chrxkzbh,
		chrffbm = data.chrffbm,
		dtmfzrq = data.dtmfzrq,
		dtmyxrq = data.dtmyxrq;
		document.getElementById("chrxkzbh").innerText =chrxkzbh;
		document.getElementById("chrffbm").innerText =chrffbm;
		document.getElementById("dtmfzrq").innerText =dtmfzrq;
		document.getElementById("dtmyxrq").innerText =dtmyxrq;
	}
});
function edit(){
	toUrl("project_basis_planPermit_edit.html");
}
