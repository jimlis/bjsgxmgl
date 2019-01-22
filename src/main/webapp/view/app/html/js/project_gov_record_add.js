var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var xclb=obj.xclb||"";
var gqjdid=obj.gqjdid||"";
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
var flag=false;
window.onload = function(){
	showEdit();
	pageData = isUpdata()||'';
	var dict=getDictMapByTypes("xcbm,xclb");
	//初始化數據
	var lbPickerData =dict["xclb"]||[] ;
		removeZxjg(lbPickerData);
	var bmPickerData = dict["xcbm"]||[];
	upLoadImg('#chbtn',{"busType":"bj_xm_zfxcyzxys"});
	//判断是否更新；
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	if(xclb=='zxys' || xclb=='jgys'){
		pageData["chrlbmc"]="验收";
	}else{
		pageData["chrlbmc"]="巡查";
	}
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			lbPicker: function (event) {
				if(flag){
					return;
				}
				vuePicker(pageData,"chrxclb",lbPickerData,"intxclb");
			},
			bmPicker: function (event) {
				vuePicker(pageData,"chrxcbm",bmPickerData,"intxcbm");
			},
			datePicker: function (event) {
				vueDtPicker(pageData,"dtmxcrq",false,true);
			}
		}
	});
	
	if(id){
		//加载图片
		initImgList("bj_xm_zfxcyzxys",id,"1","fileIds","img-list",true);
	}
	
}

function removeZxjg(arr){
	if(arr){
		for(i in arr){
			if(arr[i].value=="zxys"){
				arr.splice(i,1);
			}
		}
		
		for(i in arr){
			if(arr[i].value=="jgys"){
				arr.splice(i,1);
			}
		}
	}
}

//判断是否更新
function isUpdata(){
	if(xclb=="zxys"||xclb=="jgys"){
		var o={};
		flag=true;
		document.getElementById("chrxclb").classList.add("bj-disable");
		$bjAjax({
			url:xmzxyszfxcyzxysApi,
			type:"post",
			async:false,
			data:{
				xmid:intxmid,
				xclb:xclb,
				gqjdid:gqjdid,
				fwlx:"xz"
			},
			success:function(data){
				if(data){
					id=data.id||"";
					o=data;
					o["dtmgxrq"]=systemdate;
				}
			}
		});
		return o;
	}else{
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
						o["dtmgxrq"]=systemdate;
					}
				}
			});
			return o;
		}
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
		chrbgrmc:chrdlrmc,
		fileIds:''
	}
	return model;
}

//保存数据
function save(){
	isSure(function(){
		console.log(seletitem);
		if(seletitem.index==0){
			var data = getFromData("myform");
			if(xclb=="zxys"||xclb=="jgys"){
				data["id"]="";
			}
			$bjAjax({
				url:xmzfxcyzxysApiSave,
				data:data,
				type:"post",
				success:function(data){
					bjToast("保存成功！",function(){
						toUrl("project_gov_record_details.html?id="+data.id+"&xclb="+xclb+"&gqjdid="+gqjdid);
					});
				}
			});
		}
	});
}


function outPage(){
	toUrl("project_gov_record.html");
}