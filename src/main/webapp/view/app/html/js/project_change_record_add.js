var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
var bgbhData=[];
window.onload = function(){
	upLoadFile('#chbtn',{"busType":"bj_xm_bgsqjl"});
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
			bglxPicker: function () {
				vuePicker(pageData,"chrbgsqlx",[{"text":"顾问变更","value":"1"},{"text":"工程变更","value":"2"},
					{"text":"其他","value":"3"}],"intbgsqlx");
			},
			splcPicker: function () {
				vuePicker(pageData,"chrsplczt",[{"text":"待审批","value":"1"},{"text":"总部审批A","value":"2"},
					{"text":"总部审批B","value":"3"},{"text":"业主","value":"4"}],"intsplczt");
			},
			sfPicker: function () {
				vuePicker(pageData,"chrsfqd",[{"text":"是","value":"1"},{"text":"否","value":"0"}],"intsfqd");
			},
			qtbgbhPicker: function () {
				vuePicker(pageData,"chrqtbgbh",bgbhData,"");
			}
		},
		watch:{
			intbgsqlx:function(val,oldVal){
				bglxChange(val);
			}
		}
	});
	
	if(id){
		//加载图片
		initFileList("bj_xm_bgsqjl",id,"1","fileIds","file-list",true);
	}
	
}

function bglxChange(bgsqlx){
	getBgbhList(bgsqlx);
	pageData.chrsfqd="";
	pageData.chrqtbgbh="";
	pageData.intqzbgzje="";
	pageData.inthtzb="";
}

//判断是否更新
function isUpdata(){
	if(id){
		var o={};
		$bjAjax({
			url:changeApiDetail,
			type:"post",
			async:false,
			data:{
				xmBgsqjlId:id
			},
			success:function(data){
				if(data){
					o=data;
				}
			}
		});
		return o;
	}
	return '';
}
//创建数据Model
function buildModel(){
	var model = {
		id:id,
		intxmid:intxmid,
		dtmgxrq:systemdate,
		intbgsqlx:'',
		chrbgsqlx:'',
		chrbgsqbh:'',
		chrbgsqmc:'',
		intsfqd:'',
		chrsfqd:'',
		chrqtbgbh:'',
		chrbgxq:'',
		intgqyx:'',
		intbggs:'',
		intqzbgzje:'',
		inthtzb:'',
		chrbz:'',
		intsplczt:'',
		chrsplczt:''
	}
	return model;
}

function getBgbhList(bgsqlx){
	if(bgsqlx){
		$bjAjax({
			url:changeApiList,
			type:"post",
			async:false,
			data:{
				xmid:intxmid,
				bgsqlx:bgsqlx
			},
			success:function(data){
				if(data){
					bgbhData=data;
					for(i in bgbhData){
						bgbhData[i].text=bgbhData[i].chrbgsqbh||"";
						bgbhData[i].value=bgbhData[i].id||"";
					}
				}
			}
		});
	}else{
		bgbhData=[];
	}
}

function setGgjeSum(){
	var intqzbgzjeDom=document.getElementById("intqzbgzje");
	if(bgbhData){
		var intbggs=document.getElementById("intbggs").value||0;
		for(i in bgbhData){
			if(bgbhData[i].intsfqd==0){
				intbggs+=Number(bgbhData[i].intbggs||0);
			}
		}
		intqzbgzjeDom.value=intbggs;
		return;
	}
	intqzbgzjeDom.value=0;
}

//保存数据
function save(){
	
	var data = getFromData("myform");
	$bjAjax({
		url:changeApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_change_record_details.html?id="+data.id);
			});
		}
	});
}
function outPage(){
	toUrl("project_change_record.html");
}