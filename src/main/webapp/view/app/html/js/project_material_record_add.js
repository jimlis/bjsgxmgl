var obj = getRequest("id");
var xmid = getCookie("id");
var sysdate=bjGetSysDate();

window.onload = function(){
    relPicker("chrclyblx",[{"text":"土建","value":"1"},{"text":"机电","value":"2"},{"text":"装修","value":"3"},{"text":"园林","value":"4"},
        {"text":"其他","value":"5"}],"intclyblx");
	
    relPicker("chrsfdtp",[{"text":"是","value":"1"},{"text":"否","value":"0"}],"intsfdtp");

    relPicker("chrsplczt",[{"text":"带审批","value":""},{"text":"总部审批A","value":""},{"text":"总部审批B","value":""},{"text":"业主","value":""}],"intsplczt");

    upLoadFile('#chbtn',{"busType":"bj_xm_clybspjl"});
    
    isUpdata();
    
    getSgdw();
    
}
//获得施工单位
function getSgdw(){
	var data = getXmdwmdData(xmid,2);
	 relPicker("chrsgdw",data,"intsgdw");
}
//判断是否是修改
function isUpdata(){
	if(obj.id){
		$bjAjax({
			url:materialApiDetail,
			type:"post",
			data:{
				xmClybspjlId:obj.id
			},
			success:function(data){
				var id=data.id||"";
				document.getElementById("dtmgxrq").value=data.dtmgxrq||"";
				document.getElementById("intclyblx").value=data.intclyblx||"";
				document.getElementById("intsgdw").value=data.intsgdw||"";
				document.getElementById("intsfdtp").value=data.intsfdtp||"";
				document.getElementById("chrybmc").value=data.chrybmc||"";
				document.getElementById("chrybwz").value=data.chrybwz||"";
				document.getElementById("chrgfbz").value=data.chrgfbz||"";
				document.getElementById("chrbz").value=data.chrbz||"";
				initFileList("bj_xm_clybspjl",id,"1","fileIds","file-list",true);
				var xmClybspjlJszlList=data.xmClybspjlJszlList||[];
				for(i in xmClybspjlJszlList){
					addpp(xmClybspjlJszlList[i]);
				}
			}
		});
	}else{
		document.getElementById("dtmgxrq").value=sysdate;
	}
}
//增加品牌资料
var ppSum=0;
function addpp(data){
	var jszlid="",chrpp="",chrjscl="";
	if(data){
		jszlid=data.id||"";
		chrpp=data.chrpp||"";
		chrjscl=data.chrjscl||"";
	}
	ppSum +=1;
	var id;
	if(obj.id){
		id=obj.id;
	}else{
		id="";
	}
	var addLi=document.createElement('div');
	addLi.className="bj-broder pp";
	addLi.id=''+ppSum;
	addLi.innerHTML+=`
			<input id="id" class="bj-input bj-p-black-font" type="hidden" value="`+jszlid+`"/>
			<input id="intclybspjlid" class="bj-input bj-p-black-font" type="hidden" value="`+id+`" />
			<input id="chrpp" class="bj-input bj-p-black-font" type="text" placeholder="品牌" value="`+chrpp+`"/>
			<input id="chrjscl" class="bj-input bj-p-black-font" type="text" placeholder="资料" value="`+chrjscl+`"/>
			<button class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="deletePp('`+ppSum+`','`+jszlid+`')">删除</button>
	`;
	
	mui("#pp-list")[0].appendChild(addLi);
}
var deleteJszlIds="";
//删除品牌资料
function deletePp(id,jszlid){
	mui("#pp-list")[0].removeChild(mui("#"+id)[0]);
	if(jszlid){
		deleteJszlIds+=deleteJszlIds+",";
	}
}
//得到品牌资料json
function getpp(){
	var pps = document.getElementsByClassName("pp");
	var datas=[];
	for(var index=0 ;index<pps.length;index++ ){
		var item = pps[index].childNodes;
		var id,intclybspjlid,chrpp,chrjscl;
		for(i in item){
			if(item[i].id=="id"){
				id=item[i].value;
			}
			if(item[i].id=="intclybspjlid"){
				intclybspjlid=item[i].value;
			}
			if(item[i].id=="chrpp"){
				chrpp=item[i].value;
			}
			if(item[i].id=="chrjscl"){
				chrjscl=item[i].value;
			}
		}
		var a = {
			id:id,
			intclybspjlid:intclybspjlid,
			chrpp:chrpp,
			chrjscl:chrjscl
		}
		datas.push(a);
	}
	var dataJson = JSON.stringify(datas);
	return dataJson;
}

function save(){
    var data = getFromData("myform");
	data["intxmid"] = getCookie("id");
	data["intbgrid"] = getCookie("chrdlrid");
	data["chrbgrmc"] = getCookie("chrdlrmc");
	data["deleteJszlIds"] = deleteJszlIds;
	data["xmClybspjlJszlJson"] = getpp();
	$bjAjax({
		url:materialApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjConsole(data);
			bjToast("保存成功！",function(){
				toUrl("project_material_record_details.html?id="+data.id);
			});
		}
	});
}
