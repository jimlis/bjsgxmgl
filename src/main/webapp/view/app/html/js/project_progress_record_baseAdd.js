var id = getRequest().id;
var pageData;
var vue;
window.onload = function(){
	debugger
	//初始化數據
	var sgPickerData  ;
	var jcPickerData = getJC();
	//判断是否更新；
	pageData = isUpdata()||'';
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			sgPicker: function (event) {
				vuePicker(pageData,"chrxclb",sgPickerData,"intxclb");
			},
			jcPicker: function (event) {
				vuePicker(pageData,"chrxcbm",jcPickerData,"intxcbm");
			},
			datePicker: function (event) {
				vueDtPicker(pageData,"dtmxcrq");
			},
		}
	});
}
//判断是否更新
function isUpdata(){
	var result={};
	if(id){
		$bjAjax({
			url:progressJCApiGet,
			type:"post",
			async:false,
			data:{
				xmSgjdJcsgId:obj.id
			},
			success:function(data){
				result = data;
			}
		});
	}
	return result;
}
//创建数据Model
function buildModel(){
	var model = {
		id:'1',
		intxmid:'3',
		dtmgxrq:'2018-9-8',
		intxclb:'',
		chrxclb:'',
		intxcbm:'',
		chrxcbm:'',
		chrxcry:'',
		dtmxcrq:'',
		chrzb:'',
		intbgrid:'52',
		chrbgrmc:'测试'
	}
	return model;
}
//初始化下拉框【施工位置】数据
function getSG(){
	var result=new Array();
	$bjAjax({
			url:----,
			type:"post",
			async:false,
			data:{
				xmSgjdJcsgId:obj.id
			},
			success:function(data){
				result = data;
			}
	});
	return result;
}
//初始化下拉框【基础类型】数据
function getJC(){
	var result=[{"text":"独立基础","value":"1"},{"text":"筏板","value":"2"},{"text":"桩基础","value":"3"}];
	debugger
	return result;
}

//保存数据
function save(){
	
	var data = getFromData("myform");
	bjConsole(data);
	if(obj.id){
		data["id"] = obj.id;
	}
	$bjAjax({
		url:xmzfxcyzxysApiSave,
		data:data,
		type:"post",
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_gov_record_details.html?id="+data.id);
			});
		}
	});
}
