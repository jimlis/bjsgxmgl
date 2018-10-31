var obj=getRequest();
var sysData = bjGetSysDate();
var chrdlrmc = getCookie("chrdlrmc");
var chrdlrid = getCookie("chrdlrid");
var xmid= getCookie("id");
var id=obj.id||"";
var ldData=[];
window.onload = function(){
	ldData=getXmdlListByXmid(xmid);
	upLoadImg('#chbtn',{"busType":"bj_xm_zpjl"});
	relPicker("chrbglb",[{"text":"整体形象进度","value":"1"},{"text":"栋楼形象进度","value":"2"},{"text":"隐蔽工程形象进度","value":"3"}],"intbglb",intbglbChange);
	isUpdate();
}
var test=function(){
}
function  intbglbChange(result){
	var value=result.value||"";
	var chrqtms=document.getElementById("chrqtms");
	var qtms=document.getElementById("qtms");
	var chrpswzms=document.getElementById("chrpswzms");
	var chrpswz=document.getElementById("chrpswz");
	if(value==1){//整体形象进度
		chrqtms.value="";
		chrpswzms.value="";
		chrpswz.value="";
		chrpswzms.onclick=test;
		qtms.style.display="none";
	}else if(value==2){//栋楼形象进度
		chrqtms.value="";
		chrpswzms.value="";
		chrpswz.value="";
		relPicker("chrpswzms",ldData,"chrpswz");
		qtms.style.display="none";
	}else if(value==3){//隐蔽工程形象进度
		chrpswzms.value="";
		chrpswz.value="";
		relPicker("chrpswzms",[{"text":"土建","value":"1"},{"text":"机电","value":"2"},{"text":"装饰","value":"3"}
		,{"text":"装修","value":"4"},{"text":"园林","value":"5"},{"text":"其他","value":"6"}],"chrpswz",chrpswzmsChange);
		qtms.style.display="none";
	}
}

function chrpswzmsChange(result){
	var value=result.value||"";
	var qtms=document.getElementById("qtms");
	var chrqtms=document.getElementById("chrqtms");
	if(value==6){//其他
		qtms.style.display="block";
	}else{
		chrqtms.value="";
		qtms.style.display="none";
	}
}

function isUpdate(){
	if(id){
		$bjAjax({
			url:photoApiDetailText,
			type:"post",
			data:{
				xmZpjlId:id
			},
			success:function(data){
				var chrpswz=data.chrpswz||"";
				var intbglb=data.intbglb||"";
				document.getElementById("dtmbgrq").value = data.dtmbgrq||"";
				document.getElementById("intbglb").value = intbglb;
				document.getElementById("chrbglb").value = data.chrbglb||"";
				document.getElementById("chrpswz").value = chrpswz;
				document.getElementById("chrpswzms").value = data.chrpswzms||"";
				document.getElementById("chrqtms").value = data.chrqtms||"";
				document.getElementById("intbgrid").value = data.intbgrid||"";
				document.getElementById("chrbgrmc").value = data.chrbgrmc||"";
				document.getElementById("chrzpms").value = data.chrzpms||"";
				initImgList("bj_xm_zpjl",id,"1","fileIds","img-list",true);
				if(intbglb==2){
					relPicker("chrpswzms",ldData,"chrpswz");
				}else if(chrpswz==3&&intbglb==3){
					relPicker("chrpswzms",[{"text":"弱电","value":"1"},{"text":"机电","value":"2"},{"text":"其他","value":"3"}],"chrpswz",chrpswzmsChange);
					document.getElementById("qtms").style.display="block";
				}
				
			}
		});
	}else{
		document.getElementById("dtmbgrq").value = sysData;
		document.getElementById("chrbgrmc").value = chrdlrmc;
		document.getElementById("intbgrid").value = chrdlrid;
	}
}

function save(){
	var data = getFromData("myform");
	var intbglb=data.intbglb||"";
	var chrpswzms=data.chrpswzms||"";
	if(intbglb==1){
		data["chrpswz"] = chrpswzms;
	}
	data["id"] = id;
	data["intxmid"] = getCookie("id");
	$bjAjax({
		url:photoApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjConsole(data);
			bjToast("保存成功！",function(){
				toUrl("project_photo_record_detail.html?id="+(data.id||""));
			});
		}
	});
}
function outPage(){
	toUrl("project_photo_record_list.html");
}