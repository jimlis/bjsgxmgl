var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var systemdate = bjGetSysDate();
var systemdate1 = bjGetSysDate("yyyy-MM-dd E a HH:mm");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
var bgbhData=[];
var dwData=[];
var nowHtje=0;
var splctzs = new Array();
var isSp=hasPermission("bj:spqx");
var bjje=hasPermission("bj:bjje");
window.onload = function(){
	showEdit();
	upLoadFile('#chbtn',{"busType":"bj_xm_bgsqjl"});
	pageData = isUpdata()||'';
	//判断是否更新；
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	pageData["isSp"] = isSp;
	pageData["intbgrid"] = chrdlrid;
	pageData["chrbgrmc"] = chrdlrmc;
	
	if(!bjje){
		pageData["intbggs"] ='*';
		pageData["intqzbgzje"] ='*';
		pageData["inthtzb"] ='*';
	};
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			bglxPicker: function () {
				vuePicker(pageData,"chrbgsqlx",[{"text":"顾问变更","value":"1"},{"text":"工程变更","value":"2"},
					{"text":"其他","value":"3"}],"intbgsqlx");
			},
			dwPicker: function (event) {
				vuePicker(pageData,"chrdwmc",dwData,"intdwmcid",function(result){
					nowHtje=result.inthtje||0;
				});
			},
			splcPicker: function () {
				vuePicker(pageData,"chrsprmc",splctzs,"intsplczt",function(item){
					pageData.chruserid = item.chruserid;
				});
			},
			sfPicker: function () {
				vuePicker(pageData,"chrsfqd",[{"text":"是","value":"1"},{"text":"否","value":"0"}],"intsfqd");
			},
			qtbgbhPicker: function () {
				vuePicker(pageData,"chrqtbgbh",bgbhData,"intbgthid");
			},
			txsp:function(){
				var contentText = pageData.chrsprmc+"，您有一个关于“工程/顾问变更记录待审批！”\n"+systemdate1
				var userid = pageData.chruserid;
				ftxsp(contentText,userid);
			},
			datePicker: function (key) {
				vueDtPicker(pageData,key);
			},
			spztPicker: function () {
				vuePicker(pageData,"chrspztmc",[{"text":"正在审批","value":"wwc"},{"text":"未通过审批","value":"wtg"},{"text":"通过审批","value":"tg"}],"chrspzt");
			}
		},
		watch:{
			intbgsqlx:function(val,oldVal){
				bglxChange(val);
			},
			intdwmcid:function(val,oldVal){
				dwChange(val);
			},
			intsfqd:function(val,oldVal){
				this.intbgthid="";
				this.intqzbgzje="";
				this.inthtzb="";
			},
			intbgthid:function(val,oldVal){
				this.intqzbgzje="";
				this.inthtzb="";
			}
		}
	});
	if(id){
		bglxChange(pageData.intbgsqlx||"");
		if(dwData){
			for(i in dwData){
				if(dwData[i].value==pageData.intdwmcid){
					nowHtje=dwData[i].inthtje||0;
					break;
				}
			}
		}
		onShqt();
		
		getBgbhList(pageData.intbgsqlx||"",pageData.intdwmcid||"",id)
		//加载图片
		initFileList("bj_xm_bgsqjl",id,"1","fileIds","file-list",true);
	}
	
}

function bglxChange(bgsqlx){
	dwData=getXmdwmdData(intxmid,bgsqlx);
}

function dwChange(dwid){
	getBgbhList(pageData.intbgsqlx||"",dwid,id);
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
					o["dtmgxrq"]=systemdate;
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
		dtmbgrq:'',
		intbgsqlx:'',
		chrbgsqlx:'',
		intdwmcid:'',
		chrdwmc:'',
		intbgthid:'',
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
		dtmspztrq:'',
		chrsprmc:'',
		chruserid:'',
		chrspjg:"",
		chrspztmc:'',
		chrspzt:''
	}
	return model;
}

function getBgbhList(bgsqlx,dwmcid,nowId){
	if(bgsqlx){
		$bjAjax({
			url:changeApiList,
			type:"post",
			async:false,
			data:{
				xmid:intxmid,
				bgsqlx:bgsqlx,
				dwmcid:dwmcid,
				bgthid:-2,
				nowBgsqjlId:(nowId||"")
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

/**
 * 设置潜在变更金额
 * @returns
 */
function setGgjeSum(){
	var intqzbgzjeDom=document.getElementById("intqzbgzje");
	if(bgbhData){
		var intbggs=Number(document.getElementById("intbggs").value||0);
		var intsfqd=pageData.intsfqd||"";
		var intbgthid=pageData.intbgthid||"";
		for(i in bgbhData){
			//bgbhData[i].intsfqd==0&&
			if((!(intsfqd==1&&intbgthid==bgbhData[i].value))){
				intbggs+=Number(bgbhData[i].intbggs||0);
			}
		}
		pageData.intqzbgzje=intbggs;
		setHtzb(intbggs);
		return;
	}
	pageData.intqzbgzje=0;
	setHtzb(0);
}
/**
 * 设置合同占比
 * @returns
 */
function setHtzb(qzbgzje){
	var inthtzbDom=document.getElementById("inthtzb");
	if(nowHtje==0){
		pageData.inthtzb=0;
		return;
	}
	pageData.inthtzb=new Number((qzbgzje/nowHtje)*100).toFixed(3);
}
//监听是否取替之前变更申请，得到审批流程状态内容
function onShqt(){
	splctzs=[];
	var sfqt = pageData.intsfqd;
	if(sfqt==1){
		//是,获取审批类型为“变更记录是”
		getSplcList("bgjls");
	}else if(sfqt==0){
		//是,获取审批类型为“变更记录是”
		getSplcList("bgjlf");
		
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
//保存数据
function save(){
	isSure(function(){
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
	});
}
function outPage(){
	toUrl("project_change_record.html");
}