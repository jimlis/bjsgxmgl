var obj = getRequest();
if(obj.id){
	$bjAjax({
		url:xmzfxcyzxysApiDetail,
		type:"post",
		data:{
			xmZfxcyzxysId:obj.id
		},
		success:function(data){
			document.getElementById("dtmgxrq").innerText=data.dtmgxrq;
			document.getElementById("intxclb").innerText=data.intxclb;
			document.getElementById("intxcbm").innerText=data.intxcbm;
			document.getElementById("chrxcry").innerText=data.chrxcry;
			document.getElementById("dtmxcrq").innerText=data.dtmxcrq;
			document.getElementById("chrzb").innerText=data.chrzb;
			document.getElementById("chrbgrmc").innerText=data.chrbgrmc;
		}
	});

}
window.onload = function(){
    relPicker("intxclbxs",[{"text":"定期巡查","value":""},{"text":"非定期巡查","value":""},{"text":"专项验收","value":""},{"text":"竣工验收","value":""}],"intxclb");

    relPicker("intxcbmxs",[{"text":"市规划局","value":""},{"text":"区规划局","value":""},{"text":"质监站巡查","value":""},{"text":"安监站巡查","value":""},
        {"text":"业主方巡查","value":""},{"text":"负责验收部门","value":""}],"intxcbm");

    upLoadImg('#chbtn',null,{"busType":"bj_xm_zfxcyzxys"});

    dtPicker('#dtmxcrq');
}
function save(){
	var data = getFromData("myform");
	data["intxmid"] = getCookie("id");
	if(obj.id){
		data["id"] = obj.id;
	}
	$bjAjax({
		url:xmzfxcyzxysApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_change_record_details.html"+data.id);
			});
		}
	});
}
