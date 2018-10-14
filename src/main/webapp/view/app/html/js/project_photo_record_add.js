var sysData = getCookie("sysData");
var chrdlrmc = getCookie("chrdlrmc");
var chrdlrid = getCookie("chrdlrid");
$bjAjax({
	url:projectApiDl,
	type:"post",
	data:{
		xmZpjlId:obj.id
	},
	success:function(data){
		document.getElementById("dtmbgrq").innerText = sysData;
		document.getElementById("chrbgrmc").innerText = chrdlrmc;
		relPicker("intbglbxs",[{{"text":"整体形象进度","value":"1"},{"text":"栋楼形象进度","value":"2"},{"text":"隐蔽工程形象进度","value":"3"}}],"intbglb");
		relPicker("chrpswzxs",lbData,"chrpswz");
	}
});
/*window.onload = function(){
	 relPicker('showTypePicker',);
	upLoadImg('#chbtn','#upbtn');
}*/
function save(){
	var data = getFromData("myform");
	data["intxmid"] = getCookie("id");
	data["chrdlrid"] = getCookie("chrdlrid");
	$bjAjax({
		url:photoApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjConsole(data);
			bjToast("保存成功！",function(){
				toUrl("project_photo_record_detail.html?id="data.id);
			});
		}
	});
}
