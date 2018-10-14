var obj = getRequest("id");
if(obj.id){
	$bjAjax({
		url:materialApiDetail,
		type:"post",
		data:{
			xmClybspjlId:obj.id
		},
		success:function(data){
			document.getElementById("dtmgxrq").innerText=data.dtmgxrq;
			document.getElementById("intclyblx").innerText=data.intclyblx;
			document.getElementById("intsgdw").innerText=data.intsgdw;
			document.getElementById("intsfdtp").innerText=data.intsfdtp;
			document.getElementById("chrybmc").innerText=data.chrybmc;
			document.getElementById("chrybwz").innerText=data.chrybwz;
			document.getElementById("chrgfbz").innerText=data.chrgfbz;
			document.getElementById("chrbz").innerText=data.chrbz;
		}
	});
}
window.onload = function(){
    relPicker("chrclyblx",[{"text":"土建","value":""},{"text":"机电","value":""},{"text":"装修","value":""},{"text":"园林","value":""},
        {"text":"其他","value":""}],"intclyblx");

    relPicker("chrsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}],"intsgdw");

    relPicker("intsfdtp",[{"text":"是","value":""},{"text":"否","value":""}],"chrsfdtp");

    relPicker("intsplczt",[{"text":"带审批","value":""},{"text":"总部审批A","value":""},{"text":"总部审批B","value":""},{"text":"业主","value":""}],"chrsplczt");

    upLoadImg('#chbtn',{"busType":"bj_xm_clybspjl"});
}

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
				toUrl("project_material_record_details.html?id="data.id);
			});
		}
	});
}
