var obj = getRequest();
var pageData;
var vue;
var id=obj.id||"";
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
window.onload = function(){
	showEdit();
	
	pageData = isUpdata()||'';
	
	//upLoadImg('#chbtn',{"busType":"bj_xm_sgjd_ecjgzx_qt"});
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
		}
	});
	
	if(id){
		//加载图片
		//initImgList("bj_xm_sgjd_ecjgzx_qt",id,"1","fileIds","img-list",true);
	}
	
}
//判断是否更新
function isUpdata(){
	if(id){
		var o={};
		$bjAjax({
			url:progressSecQtByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdEcjgzxQtId:id
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
		dtmbgrq:systemdate,
		chrwz:'',
		chrsgwz:'',
		chrbz:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
isSure(function(){
	var data = getFromData("myform");
	$bjAjax({
		url:progressSecSaveQtApiPath,
		data:data,
		type:"post",
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_secqtDetail.html?id="+data.id);
			});
		}
	});
})
}


function outPage(){
	toUrl("project_progress_record_secqtDetail.html?id="+id);
}