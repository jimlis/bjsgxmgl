var obj = getRequest("id");
var xmid = getCookie("id");
var pageData;
var sysdate=bjGetSysDate();
var systemdate1 = bjGetSysDate("yyyy-MM-dd E a HH:mm");
var splctzs = new Array();
window.onload = function(){
	showEdit();
	pageData = isUpdata()||'';
	//判断是否更新；
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
    //数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			sfPicker: function () {
				vuePicker(pageData,"chrsfdtp",[{"text":"是","value":"1"},{"text":"否","value":"0"}],"intsfdtp");
			},
			splcPicker: function () {
				vuePicker(pageData,"chrsprmc",splctzs,"intsplczt",function(item){
					//pageData.chrsprmc = item.chruserid;
					pageData.chruserid = item.chruserid;
				});
			},
			txsp:function(){
				var contentText = pageData.chrsprmc+"，您有一个关于“新增材料样板审批记录待审批！”\n"+systemdate1
				var userid = pageData.chruserid;
				ftxsp(contentText,userid);
			},
			spztPicker: function () {
				vuePicker(pageData,"chrspztmc",[{"text":"未完成","value":"wwc"},{"text":"未通过","value":"wtg"},{"text":"通过","value":"tg"}],"chrspzt");
			}
		},
		watch: {
			intsfdtp:function(newVal,oldVal){
				onShqt(newVal);
			}
		}
	});
	
	onShqt();
	
	dtPicker('#dtmbgrq');
	
	dtPicker('#dtmspztrq');
	
    relPicker("chrclyblx",[{"text":"土建","value":"1"},{"text":"机电","value":"2"},{"text":"装修","value":"3"},{"text":"园林","value":"4"},
        {"text":"其他","value":"5"}],"intclyblx");

    upLoadFile('#chbtn',{"busType":"bj_xm_clybspjl"});
    
    isUpdata();
    
    getSgdw();
    
    
}
//获得施工单位
function getSgdw(){
	var data = getXmdwmdData(xmid,2);
	 relPicker("chrsgdw",data,"intsgdw");
}

var flag=true;

//判断是否是修改
function isUpdata(){
	var row={};
	if(obj.id){
		$bjAjax({
			url:materialApiDetail,
			type:"post",
			data:{
				xmClybspjlId:obj.id
			},
			async:false,
			success:function(data){
				data.chrsprmc=data.chrsplczt||"";
				row=data;
				var id=data.id||"";
				document.getElementById("dtmgxrq").value=data.dtmgxrq||"";
				document.getElementById("dtmbgrq").value=data.dtmbgrq||"";
				document.getElementById("intclyblx").value=data.intclyblx||"";
				document.getElementById("chrclyblx").value=data.chrclyblx||"";
				document.getElementById("intsgdw").value=data.intsgdw||"";
				document.getElementById("chrsgdw").value=data.chrsgdw||"";
				//document.getElementById("intsfdtp").value=data.intsfdtp||"";
				//document.getElementById("chrsfdtp").value=data.chrsfdtp||"";
				document.getElementById("chrybmc").value=data.chrybmc||"";
				document.getElementById("chrybwz").value=data.chrybwz||"";
				document.getElementById("chrgfbz").value=data.chrgfbz||"";
				document.getElementById("chrppmc").value=data.chrppmc||"";
				document.getElementById("chrbz").value=data.chrbz||"";
				document.getElementById("intsplczt").value=data.intsplczt||"";
				document.getElementById("dtmspztrq").value=data.dtmspztrq||"";
				document.getElementById("chrspjg").value=data.chrspjg||"";
				document.getElementById("chrspzt").value=data.chrspzt||"";
				document.getElementById("chrspztmc").value=data.chrspztmc||"";
				//document.getElementById("chrsplczt").value=data.chrsplczt||"";
				initFileList("bj_xm_clybspjl",id,"1","fileIds","file-list",true);
				if(flag){
					var xmClybspjlJszlList=data.xmClybspjlJszlList||[];
					for(i in xmClybspjlJszlList){
						addpp(xmClybspjlJszlList[i]);
					}
					flag=false;
				}
			}
		});
	}else{
		document.getElementById("dtmgxrq").value=sysdate;
	}
	for(i in row){
		return row;
	}
	return '';
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
			<input id="jszlid" class="bj-input bj-p-black-font" type="hidden" value="`+jszlid+`"/>
			<input id="intclybspjlid" class="bj-input bj-p-black-font" type="hidden" value="`+id+`" />
			<input id="chrpp" class="bj-input bj-p-black-font" type="text" placeholder="参数名称" value="`+chrpp+`"/>
			<input id="chrjscl" class="bj-input bj-p-black-font" type="text" placeholder="参数资料" value="`+chrjscl+`"/>
			<button class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="deletePp('`+ppSum+`','`+jszlid+`')">-</button>
	`;
	
	mui("#pp-list")[0].appendChild(addLi);
}
var deleteJszlIds="";
//删除品牌资料
function deletePp(id,jszlid){
	mui("#pp-list")[0].removeChild(mui("#"+id)[0]);
	if(jszlid){
		deleteJszlIds+=jszlid+",";
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
			if(item[i].id=="jszlid"){
				id=item[i].value||null;
			}
			if(item[i].id=="intclybspjlid"){
				intclybspjlid=item[i].value||null;
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

//创建数据Model
function buildModel(){
	var model = {
		intsfdtp:'',
		chrsfdtp:'',
		intsplczt:'',
		chrsprmc:'',
		chruserid:'',
		chrspztmc:'',
		chrspzt:''
	}
	return model;
}

//监听是否替代品，得到审批流程状态内容
function onShqt(nowVal){
	splctzs=[];
	var sfqt = nowVal||pageData.intsfdtp;
	if(sfqt==1){
		//是,获取审批类型为“变更记录是”
		getSplcList("clybspjls");
	}else if(sfqt==0){
		//是,获取审批类型为“变更记录是”
		getSplcList("clybspjlf");
		
	}
}
//根据splclx审批流程状态获取list数据
function getSplcList(lx){
	$bjAjax({
			url:splcztBySplclxApiPath,
			type:"post",
			async:false,
			data:{
				splclx:lx
			},
			success:function(data){
				if(data){
					for(i in data){
						var obj ={text:"",value:"",chruserid:""};
						obj.text = data[i].chrsprmc;
						obj.value = data[i].id;
						obj.chruserid = data[i].chruserid;
						splctzs.push(obj);
					}
				}
			}
		});
}
//提醒审批
function ftxsp(contentText,chruserid){
	if(chruserid){
		$bjAjax({
			url:splcTxByUseridApiPath,
			type:"post",
			async:false,
			data:{
				contentText:contentText,
				userId:chruserid
			},
			success:function(data){
				if(data){
					bjToast("已提醒！")
				}
			}
		});
	}else{
		bjToast("该流程未设置UserId，不能进行提醒，联系管理员添加。")
	}
}

function save(){
	mui.confirm("将新增一条新的报告记录，\n是否确定更新？","提示",['是','否'],function(seletitem){
		console.log(seletitem);
		if(seletitem.index==0){
		    var data = getFromData("myform");
		    data["id"] = (obj.id||"");
			data["intxmid"] = getCookie("id");
			data["intbgrid"] = getCookie("chrdlrid");
			data["chrbgrmc"] = getCookie("chrdlrmc");
			data["deleteJszlIds"] =(deleteJszlIds.length>0?deleteJszlIds.substring(0,deleteJszlIds.length-1):deleteJszlIds);
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
	});
}
function outPage(){
	toUrl("project_material_record.html");
}