var obj=getRequest()
var id = obj.id||"";
var sgwz=obj.sgwz||"";
var xmid=getCookie("id");
var pageData;
var vue;
window.onload = function(){
	
	//判断是否更新；
	pageData = isUpdata();
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData
	});
	
	if(pageData.id){
		//加载图片
		initFileList("bj_xm_sgjd_dtsbazsg",pageData.id,"1","fileIds","file-list",false);
	}
	
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
				fwlx:"cx"
			},
			success:function(data){
				id=data.id||"";
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


function edit(){
	toUrl("project_progress_record_elevatorAdd.html?id="+id+"&sgwz="+sgwz);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_progress_record.html");
}