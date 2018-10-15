dtPicker('#dtmfzrq');
dtPicker('#dtmyxrq');
$bjAjax({
	url:baseApixkz,
	type:"post",
	data:{
		xmid:getCookie("id"),
		xkzlx:1
	},
	success:function(data){
		document.getElementById("chrxkzbh").value = data.chrxkzbh;
		document.getElementById("chrffbm").value = data.chrffbm;
		document.getElementById("dtmfzrq").value = data.dtmfzrq;
		document.getElementById("dtmyxrq").value = data.dtmyxrq;
	}
})

function save(){
	var data = getFromData("myform");
	data["intxkzlx"] = "1";
	data["intxmid"] = getCookie("id");
	$bjAjax({
		url:baseApixkzSave,
		type:"post",
		data:data,
		success:function(data){
			if(data){
				toUrl("project_basis_constructionPermit.html");
			}
			
		}
	})

}
