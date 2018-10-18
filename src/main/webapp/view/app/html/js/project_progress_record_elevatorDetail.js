var obj=getRequest()
var id = obj.id||"";
var pageData;
var vue;
window.onload = function(){
	
	//判断是否更新；
	pageData = isUpdata()||'';
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData
	});
	
	if(id){
		//加载图片
		initFileList("bj_xm_sgjd_dtsbazsg",id,"1","fileIds","file-list",false);
	}
	
}

//判断是否更新
function isUpdata(){
	debugger;
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


function edit(){
	toUrl("project_progress_record_elevatorAdd.html?id="+id);
}
