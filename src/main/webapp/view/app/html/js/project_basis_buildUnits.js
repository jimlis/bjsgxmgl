var xmid = getCookie("id");
$bjAjax({
	url:baseApiCjdw,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		if(!data)return;
		var chrcjdwmc = data.chrcjdwmc,
		chrdjdwmc = data.chrdjdwmc;
		document.getElementById("chrcjdwmc").innerText =chrcjdwmc;
		document.getElementById("chrdjdwmc").innerText =chrdjdwmc;
	}
});
$bjAjax({
	url:baseApiDwmd,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		
	}
});

function edit(){
	toUrl("project_basis_buildUnits_edit.html");
}
