var obj=getRequest()
var sgwzid = obj.sgwzid||"";
var chrsgwz = obj.chrsgwz||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var ztjdsJson=[];
var vue;
window.onload = function(){
	upLoadImg('#chbtn',{"busType":"bj_xm_sgjd_ztjgsg"});
	//获取层数
	var ztPickerData=getXmjdListByParam(xmid,'zt',0,parentId);
	if(ztPickerData){
		ztjdsJson=ztPickerData;
	}
	//判断是否更新；
	var result = isUpdata()||'';
	if(result==''){
		//创建数据Model；
		pageData = buildModel();
	}else{
		ztjdsJson = result.ztjdsJson;
		delete result.ztjdsJson;
		pageData=result;
	}
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: {
			pagedata:pageData,
			ztjdsJson:ztjdsJson
		},
		methods: {
			sfwcPicker: function (event) {
				getRelPicker([{value:'1',text:'完成'},{value:'0',text:'未完成'}],function(selectItems){
					vue.pagedata.intsfwc=selectItems[0].value;
					vue.pagedata.chrsfwc = selectItems[0].text;
				});
			},
			datePicker: function (type,index) {
				var selectItems = getDtPicker(function(selectItems){
					var value = selectItems.value;
					vue.setdataPicker(type,index,value);
				});
				
			},
			setdataPicker:function(type,index,value){
				if(type=="lc"){
					vue.$set(this.ztjdsJson[index],"dtmwcsj",value);
				}else{
					vue.$set(this.pagedata,"dtmwcrq",value);
				}
			},
			addlc:function(){
				var zjcinfo = {chrjdmc:"",dtmwcsj:""}
				this.ztjdsJson.push(zjcinfo);
			},
			dellc:function(key){
				this.ztjdsJson.splice(key,1);
			},
		},
	});
	
//	if(id){
//		//加载图片
//		initImgList("bj_xm_sgjd_ztjgsg",id,"1","fileIds","img-list",true);
//	}
}


//判断是否更新
function isUpdata(){
//	if(id){
//		var result={};
//		$bjAjax({
//			url:progressZtjgByIdApiPath,
//			type:"post",
//			async:false,
//			data:{
//				xmSgjdJcsgId:id
//			},
//			success:function(data){
//				result = data;
//			}
//		});
//		return result;
//	}
//	return '';
}
//创建数据Model
function buildModel(){
	var model = {
		intxmid:xmid,
		dtmbgrq:sysdate,
		intsgwzd:'',
		chrsgwz:chrsgwz,
		intsgwzid:'',
		chrsgwzms:'',
		intsfwc:'',
		chrsfwc:'',
		dtmwcrq:'',
		chrsgwzms:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	mui.confirm("将新增一条新的报告记录，\n是否确定更新？","提示",['是','否'],function(seletitem){
		console.log(seletitem);
		if(seletitem.index==0){
			var data = restore(this.vue.$data.pagedata);
			var ztjdsJson = JSON.stringify(this.vue.$data.ztjdsJson);
			var fileIds = "";
			data["ztjdsJson"] = ztjdsJson;
			data["fileIds"] = fileIds;
			console.log(data);
			$bjAjax({
				url:progressZtjgPath+"newsave",
				data:data,
				type:"post",
				success:function(result){
					bjToast("保存成功！");
				}
			});
		}
	});
	
}
function outPage(){
	toUrl("project_progress_record.html");
}