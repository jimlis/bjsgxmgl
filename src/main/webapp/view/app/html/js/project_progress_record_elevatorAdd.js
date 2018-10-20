var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var dlhData;
window.onload = function(){
	upLoadFile('#chbtn',{"busType":"bj_xm_xkz"});
	
	dlhData=getXmdlListByXmid(xmid);
	
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
			dlhPicker:function(event){
				vuePicker(pageData,"chrsgwz",dlhData,"intsgwz",null);
			},dataPicker:function(domid){
				vueDtPicker(pageData,domid);
			}
		}
	});
	
	if(id){
		//加载图片
		initFileList("bj_xm_xkz",id,"1","fileIds","file-list",true);
	}
	
}

var deleteIds="";
function deletelc(id,obj){
	if(id){
		deleteIds+=id+",";
	}
	obj.parentNode.parentNode.remove();
}

//判断是否更新
function isUpdata(){
	if(id){
		var result={};
		$bjAjax({
			url:progressElevatorByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdDtsbazsgId:id
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
		dtmgxrq:sysdate,
		intsgwz:'',
		chrsgwz:'',
		chrdtbh:'',
		dtmdhrq:'',
		dtmyjrq:'',
		dtmwcrq:'',
		intwcbl:'',
		intazbl:'',
		dtmyxrq:'',
		dtmysrq:'',
		dtmdqrq:'',
		chrbz:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	var data = getFromData("myform");
	data.deleteWclIds=(deleteIds.length>0?deleteIds.substring(0,deleteIds.length-1):deleteIds);
	$bjAjax({
		url:progressElevatorSaveApiPath,
		data:data,
		type:"post",
		success:function(result){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_elevatorDetail.html?id="+result.id);
			});
		}
	});
}
