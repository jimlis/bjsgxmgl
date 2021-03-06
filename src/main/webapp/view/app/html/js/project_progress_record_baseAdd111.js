var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var jcPickerData=[];
window.onload = function(){
	//初始化數據
	var sgPickerData=getSG();
	if(sgPickerData){
		var oo={"text":"其他","value":"-1"};
		sgPickerData.push(oo);
	}
	upLoadImg('#chbtn',{"busType":"bj_xm_sgjd_jcsg"});
	//判断是否更新；
	pageData = isUpdata()||'';
	
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	
	getJC(pageData.intsgwzid||"");
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			sgPicker: function (event) {
				vuePicker(pageData,"chrsgwzmc",sgPickerData,"intsgwzid");
			},
			jcPicker: function (event) {
				vuePicker(pageData,"chrjclx",jcPickerData,"intjclx");
			},
			datePicker: function (event) {
				vueDtPicker(pageData,"dtmjzqrq");
			}
		},
		watch: {
			intsgwzid: function(curVal,oldVal){
				if(curVal!=-1){
					getJC(curVal);
				}
				this.chrsgwzms="";
				this.intjclx="";
				this.chrjclx="";
		    },
		}
	});
	
	if(id){
		//加载图片
		initImgList("bj_xm_sgjd_jcsg",id,"1","fileIds","img-list",true);
	}
}

function setWcl(){
	var intzjczsl=pageData.intzjczsl||0;
	var intzjcwcl=pageData.intzjcwcl||0;
	if(intzjczsl==0||intzjcwcl==0){
		pageData.intwcl=0;
	}else{
		pageData.intwcl=parseInt((intzjcwcl/intzjczsl)*100);
	}
}

//判断是否更新
function isUpdata(){
	if(id){
		var result={};
		$bjAjax({
			url:progressJcsgByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdJcsgId:id
			},
			success:function(data){
				result = data;
			}
		});
		return result;
	}
	return '';
}
//创建数据Model
function buildModel(){
	var model = {
		id:id,
		intxmid:xmid,
		dtmbgrq:sysdate,
		intsgwzid:'',
		chrsgwzmc:'',
		chrsgwzms:'',
		intjclx:'',
		chrjclx:'',
		intzjczsl:'',
		intzjcwcl:'',
		intwcl:'',
		dtmjzqrq:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}
//初始化下拉框【施工位置】数据
function getSG(){
	return getXmjdListByParam(xmid,'jc',1,"");
}
//初始化下拉框【基础类型】数据
function getJC(parentId){
	//var result=[{"text":"独立基础","value":"1"},{"text":"筏板","value":"2"},{"text":"桩基础","value":"3"}];
	//return result;
	jcPickerData=getXmjdListByParam(xmid,'jc',0,parentId);
}

//保存数据
function save(){
	var data = getFromData("myform");
	$bjAjax({
		url:progressJcsgSaveApiPath,
		data:data,
		type:"post",
		success:function(result){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_baseDetail.html?id="+result.id);
			});
		}
	});
}
function outPage(){
	toUrl("project_progress_record.html");
}