
var obj = getRequest("id");
if(obj.id){
	$bjAjax({
		url:changeApiDetail,
		type:"post",
		data:{
			xmZfxcyzxysId:obj.id
		},
		success:function(data){
			document.getElementById("dtmgxrq").innerText=data.dtmgxrq;
			document.getElementById("intbgsqlx").innerText=data.intbgsqlx;
			document.getElementById("chrbgsqbh").innerText=data.chrbgsqbh;
			document.getElementById("chrbgsqmc").innerText=data.chrbgsqmc;
			document.getElementById("intsfqd").innerText=data.intsfqd;
			document.getElementById("").innerText=data.;
			document.getElementById("chrbgxq").innerText=data.chrbgxq;
			document.getElementById("intgqyx").innerText=data.intgqyx;
			document.getElementById("intbggs").innerText=data.intbggs;
			document.getElementById("").innerText=data.;
			document.getElementById("inthtzb").innerText=data.inthtzb;
			document.getElementById("chrbz").innerText=data.chrbz;
		}
	});
}

relPicker("intbgsqlxxs",[{"text":"顾问变更","value":"1"},{"text":"工程变更","value":"2"},{"text":"其他","value":"3"}],"intbgsqlx");
relPicker("intsfqdxs",[{"text":"是","value":"1"},{"text":"否","value":"0"}],"intsfqd");
    
/*window.onload = function(){
    

    relPicker("chrbgsqwj",[{"text":"待审批","value":""},{"text":"总审批A","value":""},{"text":"总审批B","value":""},{"text":"业主","value":""}],"chrbgsqwj");

    upLoadImg('#chbtn',{"busType":"bj_xm_bgsqjl"});
}*/

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
