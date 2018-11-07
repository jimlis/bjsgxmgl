var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var sgwz=obj.sgwz||"";
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var dlhData;
window.onload = function(){
	upLoadFile('#chbtn',{"busType":"bj_xm_sgjd_dtsbazsg"});
	
	//dlhData=getXmjdListByParam(xmid,'jc',1,"");
	
	//判断是否更新；
	pageData = isUpdata();
	
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
		initFileList("bj_xm_sgjd_dtsbazsg",id,"1","fileIds","file-list",true);
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
		var result={};
		$bjAjax({
			url:progressElevatorByParamApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdDtsbazsgId:id,
				xmid:xmid,
				sgwz:sgwz,
				fwlx:"xz"
			},
			success:function(data){
				result = data;
			}
		});
		return result;
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
function outPage(){
	toUrl("project_progress_record_elevatorDetail.html?id="+id);
}