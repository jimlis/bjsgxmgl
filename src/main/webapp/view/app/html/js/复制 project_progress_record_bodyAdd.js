var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var dlcsData;
var ztPickerData=[];
var sgPickerData=[];
window.onload = function(){
	upLoadImg('#chbtn',{"busType":"bj_xm_sgjd_ztjgsg"});
	
	//判断是否更新；
	pageData = isUpdata()||'';
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	sgPickerData=getSG();
	
	getZt(pageData.intsgwzd||"");
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			dlcsPicker:function(event){
				vuePicker(pageData,"chrShowAddress",sgPickerData,"intsgwzd");
			},
			datePicker: function (event) {
				vueDtPicker(pageData,"dtmjzqrq");
			},
			ztPicker: function (event) {
				vuePicker(pageData,"chrsgwzc",ztPickerData,"intsgwzc");
			}
		},
		watch: {
			intsgwzd: function(curVal,oldVal){
					getZt(curVal);
				this.chrsgwzc="";
		    }
		}
	});
	
	if(id){
		//加载图片
		initImgList("bj_xm_sgjd_ztjgsg",id,"1","fileIds","img-list",true);
	}
}

function getSG(){
	return getXmjdListByParam(xmid,'zt',1,"");
}

function getZt(parentId){
	ztPickerData=getXmjdListByParam(xmid,'zt',0,parentId);
}

/*function initDLAndCs(vueData,address,dl,cs,funResult){
	var userPicker = new mui.PopPicker({
		layer:2
	});
	userPicker.setData(dlcsData);
	userPicker.show(function(items) {
		vueData[address] = items[0].text+"-"+items[1].text;
		if(dl){
           vueData[dl]=items[0].value;
		}
		if(cs){
	       vueData[cs]=items[1].value;
		}
		if(funResult){
			funResult(items);
		}
		//返回 false 可以阻止选择框的关闭
		//return false;
	});
}

function getDLAndCsData(xmid){
	var arr=[];
	$bj_post_ajax({
		url:projectApiDlAndCs,
		data:{"xmid":xmid},
		async:false,
		success:function(data){
			if(data){
				arr=data;
			}
		}
	});
	return arr;
}*/

//判断是否更新
function isUpdata(){
	if(id){
		var result={};
		$bjAjax({
			url:progressZtjgByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdJcsgId:id
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
		dtmbgrq:sysdate,
		chrShowAddress:'',
		intsgwzd:'',
		intsgwzc:'',
		chrsgwzc:'',
		intwcl:'',
		dtmjzqrq:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	var data = getFromData("myform");
	$bjAjax({
		url:progressZtjgSaveApiPath,
		data:data,
		type:"post",
		success:function(result){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_bodyDetail.html?id="+result.id);
			});
		}
	});
}
function outPage(){
	toUrl("project_progress_record.html");
}