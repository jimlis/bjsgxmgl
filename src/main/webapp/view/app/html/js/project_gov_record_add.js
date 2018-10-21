var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
window.onload = function(){
	pageData = isUpdata()||'';
	var dict=getDictMapByTypes("xcbm,xclb");
	//初始化數據
	var lbPickerData =dict["xclb"]||{} ;
	var bmPickerData = dict["xcbm"]||{};
	upLoadImg('#chbtn',{"busType":"bj_xm_zfxcyzxys"});
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
	
	if(id){
		//加载图片
		initImgList("bj_xm_zfxcyzxys",id,"1","fileIds","img-list",true);
	}
	
}
//判断是否更新
function isUpdata(){
	if(id){
		var o={};
		$bjAjax({
			url:xmzfxcyzxysApiDetail,
			type:"post",
			async:false,
			data:{
				xmZfxcyzxysId:obj.id
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
		intxclb:'',
		chrxclb:'',
		intxcbm:'',
		chrxcbm:'',
		chrxcry:'',
		dtmxcrq:'',
		chrzb:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	
	var data = getFromData("myform");
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


function outPage(){
	toUrl("project_gov_record.html");
}