var obj = getRequest();
var pageData;
var vue;
window.onload = function(){
	pageData = isUpdata()||'';
	//初始化數據
	var lbPickerData = [{"text":"定期巡查","value":""},{"text":"非定期巡查","value":""},{"text":"专项验收","value":""},{"text":"竣工验收","value":""}];
	var bmPickerData = [{"text":"市规划局","value":""},{"text":"区规划局","value":""},{"text":"质监站巡查","value":""},{"text":"安监站巡查","value":""},{"text":"业主方巡查","value":""},{"text":"负责验收部门","value":""}];
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
			lbPicker: function (event) {
				vuePicker(pageData,"chrxclb",lbPickerData,"intxclb");
			},
			bmPicker: function (event) {
				vuePicker(pageData,"chrxcbm",bmPickerData,"intxcbm");
			},
			datePicker: function (event) {
				vueDtPicker(pageData,"dtmxcrq");
			},
		}
	});
}
//判断是否更新
function isUpdata(){
	if(obj.id){
		$bjAjax({
			url:xmzfxcyzxysApiDetail,
			type:"post",
			data:{
				xmZfxcyzxysId:obj.id
			},
			success:function(data){
				return data;
			}
		});
	}
	return '';
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
//初始化下拉框数据
function init(){
	xcPickerData = [{"text":"定期巡查","value":""},{"text":"非定期巡查","value":""},{"text":"专项验收","value":""},{"text":"竣工验收","value":""}];

//  relPicker("chrxcbm",[{"text":"市规划局","value":""},{"text":"区规划局","value":""},{"text":"质监站巡查","value":""},{"text":"安监站巡查","value":""},
//      {"text":"业主方巡查","value":""},{"text":"负责验收部门","value":""}],"intxcbm");
//
//  dtPicker('#dtmxcrq');
    //upLoadImg('#chbtn',null,{"busType":"bj_xm_zfxcyzxys"});

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
