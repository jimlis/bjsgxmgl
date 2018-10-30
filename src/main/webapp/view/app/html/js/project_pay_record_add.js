var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
var dwData=[];
var inthtje=0;
var bgbhData=[];
window.onload = function(){
	upLoadFile('#chbtn',{"busType":"bj_xm_gckyzfqk"});
	
	pageData = isUpdata()||'';
	
	//判断是否更新；
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	
	dwData=getXmdwmdData(intxmid,pageData.intdwlx);
	
	getBgbhList(pageData.intdwlx,pageData.intdwmcid,id);
	
	getHtje();
	
	getQsAndJe(pageData.intdwlx,pageData.intdwmcid,id);
	
	getGgjeSum();//设置潜在变更总金额
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			lxPicker: function (event) {
				vuePicker(pageData,"chrdwlx",[{"text":"顾问单位","value":"1"},{"text":"施工单位","value":"2"},{"text":"其他单位","value":"3"}],"intdwlx");
			},
			dwPicker: function (event) {
				vuePicker(pageData,"chrdwmc",dwData,"intdwmcid",function(result){
					pageData.inthtje=result.inthtje||0;
				});
			},
			splcPicker: function () {
				vuePicker(pageData,"chrsplczt",[{"text":"待审批","value":"1"},{"text":"总部审批A","value":"2"},
					{"text":"总部审批B","value":"3"},{"text":"业主","value":"4"}],"intsplcztid");
			}
		},
		watch:{
			intdwlx:function(val,old){
				dwData=getXmdwmdData(intxmid,val);
				this.intdwmcid="";
				this.inthtje="";
			},
			intdwmcid:function(val,old){
				getBgbhList(this.intdwlx,val,id);
				getQsAndJe(this.intdwlx,val,id);
				getGgjeSum();//设置潜在变更总金额
				//设置期数
				this.intbcsqqs=new Number(this.yfqs)+1;
			}
		},
		 computed: {
			    BA: function () {
			    	var B=this.intqzbgzje;
			    	var A=this.inthtje;
			      return A?new Number((new Number(B)/new Number(A))).toFixed(2):0;
			    },
			    DE:function(){
			    	var D=this.intbqsqje;
			    	var E=this.intbqhsffje;
			    	return E?new Number((new Number(D)/new Number(E))).toFixed(2):0;
			    },
			    CA:function(){
			    	var C=this.yfje;
			    	var A=this.inthtje;
			    	return A?new Number((new Number(C)/new Number(A))).toFixed(2):0;
			    },
			    CEA:function(){
			    	var C=this.yfje;
			    	var E=this.intbqhsffje;
			    	var A=this.inthtje;
			    	return A?new Number(((new Number(C)+new Number(E))/new Number(A))).toFixed(2):0;
			    },
			    CAB:function(){
			    	var C=this.yfje;
			    	var A=this.inthtje;
			    	var B=this.intqzbgzje;
			    	if(!A)return 0;
			    	if(!B)return 0;
			    	return new Number((new Number(C)/(new Number(A)+new Number(B)))).toFixed(2);
			    },
			    CEAB:function(){
			    	var C=this.yfje;
			    	var E=this.intbqhsffje;
			    	var A=this.inthtje;
			    	var B=this.intqzbgzje;
			    	if(!A)return 0;
			    	if(!B)return 0;
			    	return new Number(((new Number(C)+new Number(E))/(new Number(A)+new Number(B)))).toFixed(2);
			    }
			 }
	});
	
	if(id){
		//加载文件
		initFileList("bj_xm_gckyzfqk",id,"1","fileIds","file-list",true);
	}
	
}

function getHtje(){
	if(id){
		if(dwData){
			for(i in dwData){
				if(dwData[i].value==pageData.intdwmcid){
					pageData.inthtje=dwData[i].inthtje||0;
					break;
				}
			}
		}
	}else{
		pageData.inthtje=0
	}
}

//获取变更情况
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
				bgthid:-1,
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

//获取发放期数和金额
function getQsAndJe(dwlx,dwmcid,xmgczfid){
	var map={};
	if(dwlx&&dwmcid){
		$bjAjax({
			url:payApiGetQsAndJe,
			type:"post",
			async:false,
			data:{
				xmid:intxmid,
				dwlx:dwlx,
				dwmcid:dwmcid,
				xmgczfid:(xmgczfid||"")
			},
			success:function(data){
				if(data){
					map=data;
				}
			}
		});
	}
	pageData.yfqs=(map.yfqs||0);
	pageData.yfje=(map.yfje||0);
}

/**
 * 设置潜在变更金额
 * @returns
 */
function getGgjeSum(){
	if(bgbhData){
		var intbggs=0;
		for(i in bgbhData){
			if(bgbhData[i].intsfqd==0){
				intbggs+=Number(bgbhData[i].intbggs||0);
			}
		}
		pageData.intqzbgzje=intbggs;
		return;
	}
	pageData.intqzbgzje=0;
}


//判断是否更新
function isUpdata(){
	if(id){
		var o={};
		$bjAjax({
			url:payApiDetail,
			type:"post",
			async:false,
			data:{
				xmGckyzfqkId:id
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
		intdwlx:'',
		chrdwlx:'',
		intdwmcid:'',
		chrdwmc:'',
		intbcsqqs:'',
		intbqsqje:'',
		intbqhsffje:'',
		chrbz:'',
		intsplcztid:'',
		chrsplczt:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	
	var data = getFromData("myform");
	$bjAjax({
		url:payApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_pay_record_detail.html?id="+data.id);
			});
		}
	});
}
function outPage(){
	toUrl("project_pay_record.html");
}