var obj = getRequest("id");
var xmid = getCookie("id");

window.onload = function(){
    relPicker("chrclyblx",[{"text":"土建","value":"1"},{"text":"机电","value":"2"},{"text":"装修","value":"3"},{"text":"园林","value":"4"},
        {"text":"其他","value":"5"}],"intclyblx");
	
    relPicker("chrsfdtp",[{"text":"是","value":""},{"text":"否","value":""}],"intsfdtp");

    relPicker("chrsplczt",[{"text":"带审批","value":""},{"text":"总部审批A","value":""},{"text":"总部审批B","value":""},{"text":"业主","value":""}],"intsplczt");

    //upLoadImg('#chbtn',{"busType":"bj_xm_clybspjl"});
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
}
//增加品牌资料
var ppSum=0;
function addpp(){
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
			<input id="id" class="bj-input bj-p-black-font" type="hidden" />
			<input id="intclybspjlid" class="bj-input bj-p-black-font" type="hidden" value="`+id+`" />
			<input id="chrpp" class="bj-input bj-p-black-font" type="text" placeholder="品牌" />
			<input id="chrjscl" class="bj-input bj-p-black-font" type="text" placeholder="资料" />
			<button class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="deletePp('`+ppSum+`')">删除</button>
	`;
	
	mui("#pp-list")[0].appendChild(addLi);
}
//删除品牌资料
function deletePp(id){
	mui("#pp-list")[0].removeChild(mui("#"+id)[0]);
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
	getpp();
    var data = getFromData("myform");
	data["intxmid"] = getCookie("id");
	data["chrdlrid"] = getCookie("chrdlrid");
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
