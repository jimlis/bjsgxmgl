showEdit();
var obj=getRequest();
var intsgwzid = obj.intsgwzid||"";
var chrsgwzmc = obj.chrsgwzmc||"";
var jcid = obj.jcid||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var zjcs=new Array();
var	dljcs=new Array();
var vue;
window.onload = function(){
	upLoadImg('#chbtn',{"busType":"bj_xm_sgjd_jcsgnew"});
	if(intsgwzid=="-1"){
		//如果空的时候创建
		if(!jcid){
			//创建数据Model；
			pageData = buildModel();
		}else{
			//判断是否更新；
			var result = isUpdata()||'';
			if(!result){
				//创建数据Model；
				pageData = buildModel();
			}else{
				zjcs = result.zjcs||[];
				dljcs = result.dljcs||[];
//				result.dtmbgrq=sysdate;
				pageData = result;
				delete pageData.zjcs;
				delete pageData.dljcs;
			}
		}
	}else{
		//判断是否更新；
		var result = isUpdata()||'';
		if(!result){
			//创建数据Model；
			pageData = buildModel();
		}else{
			zjcs = result.zjcs||[];
			dljcs = result.dljcs||[];
			result.dtmbgrq=sysdate;
			pageData = result;
			delete pageData.zjcs;
			delete pageData.dljcs;
		}
	}
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: {
			pagedata:pageData,
			zjcs:zjcs,
			dljcs:dljcs,
			zjcinfo:{zjcmc:"",zjcjzsrq:""},
			dljcinfo:{zjcmc:"",zjcjzsrq:""},
			fileIds:'',
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
				},false,true);
				
			},
			setdataPicker:function(type,index,value){
				if(type=="zjc"){
					vue.$set(this.zjcs[index],"dtmjzsrq",value);
				}else if(type=="dljc"){
					vue.$set(this.dljcs[index],"dtmjzsrq",value);
				}else{
					vue.$set(this.pagedata,"dtmwcrq",value);
				}
			},
			addzjc:function(){
				var zjcinfo = {chrjcmc:"",dtmjzsrq:""}
				this.zjcs.push(zjcinfo);
			},
			delzjc:function(key){
				this.zjcs.splice(key,1);
			},
			adddljc:function(){
				var dljcinfo = {chrjcmc:"",dtmjzsrq:""}
				this.dljcs.push(dljcinfo);
			},
			deldljc:function(key){
				this.dljcs.splice(key,1);
			}
		},
		watch: {
		}
	});
	
	if(pageData.id){
		//加载图片
		initImgList("bj_xm_sgjd_jcsgnew",pageData.id,"1","fileIds","img-list",false);
	}
	
}


//判断是否更新
function isUpdata(){
	if(intsgwzid&&xmid){
		var id = (obj.jcid?obj.jcid:"");
		var result={};
		$bjAjax({
			url:progressJcsgByXmidAndSgwzidApiPath,
			type:"post",
			async:false,
			data:{
				xmid:xmid,
				sgwzid:intsgwzid,
				fwlx:"xz",
				id:id
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
		intxmid:xmid,
		dtmbgrq:sysdate,
		intsgwzid:intsgwzid,
		chrsgwz:chrsgwzmc,
		chrsgwzms:'',
		intsfwc:'',
		chrsfwc:'',
		dtmwcrq:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc,
		
	}
	return model;
}

//保存数据
function save(){
	isSure(function(){
		console.log(seletitem);
		if(seletitem.index==0){
			var data = restore(this.vue.$data.pagedata);
			for(i in this.vue.$data.dljcs){
				this.vue.$data.dljcs[i].dtmjzsrq=this.vue.$data.dljcs[i].dtmjzsrq||null;
			}
			for(i in this.vue.$data.zjcs){
				this.vue.$data.zjcs[i].dtmjzsrq=this.vue.$data.zjcs[i].dtmjzsrq||null;
			}
			var dljcsJson = JSON.stringify(this.vue.$data.dljcs);
			var zjcsJson = JSON.stringify(this.vue.$data.zjcs);
			var fileIds = document.getElementById("fileIds").value;
			data["dljcsJson"] = dljcsJson;
			data["zjcsJson"] = zjcsJson;
			data["fileIds"] = fileIds;
			var id = (obj.jcid?obj.jcid:"");
			delete data.id;
			data["id"]=id;
			for(i in data){
				if(!data[i]){
					data[i]="";
				}
			}
			console.log(data);
			$bjAjax({
				url:progressJcsgNewSaveApiPath,
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
	toUrl("project_progress_record_baseDetail.html?sgwzid="+intsgwzid);
}


