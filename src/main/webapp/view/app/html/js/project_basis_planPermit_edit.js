
function save(){
	var data = getFromData("myform");
	data["intxkzlx"] = "1";
	data["id"] = getCookie("id");
	$bjAjax({
		url:baseApixkzSave,
		type:"post",
		data:data,
		success:function(data){
			bjConsole(data);
			document.getElementById("chrxkzbh").innerText = data.chrxkzbh;
			document.getElementById("chrffbm").innerText = data.chrffbm;
			document.getElementById("dtmfzrq").innerText = data.dtmfzrq;
			document.getElementById("dtmyxrq").innerText = data.dtmyxrq;
		}
	})
}