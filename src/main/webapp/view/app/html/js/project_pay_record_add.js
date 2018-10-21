var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
var dwData=[];
window.onload = function(){
	upLoadFile('#chbtn',{"busType":"bj_xm_gckyzfqk"});
	
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
			lxPicker: function (event) {
				vuePicker(pageData,"chrdwlx",[{"text":"顾问单位","value":"1"},{"text":"施工单位","value":"2"},{"text":"其他单位","value":"3"}],"intdwlx");
			},
			dwPicker: function (event) {
				vuePicker(pageData,"chrdwmc",dwData,"intdwmcid");
			},
			splcPicker: function () {
				vuePicker(pageData,"chrsplczt",[{"text":"待审批","value":"1"},{"text":"总部审批A","value":"2"},
					{"text":"总部审批B","value":"3"},{"text":"业主","value":"4"}],"intsplcztid");
			}
		},
		watch:{
			intdwlx:function(val,old){
				dwData=getXmdwmdData(intxmid,val);
			}
		}
	});
	
	if(id){
		//加载文件
		initFileList("bj_xm_gckyzfqk",id,"1","fileIds","file-list",true);
	}
	
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