showEdit();
var obj=getRequest();
var sgwzid = obj.sgwzid||"";
var intsgwzid = obj.intsgwzid||"";
var chrsgwzmc = obj.chrsgwzmc||"";

var xmid=getCookie("id");
var jcid = obj.jcid||"";
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var ztjdsJson=[];
var vue;
window.onload = function(){
	upLoadImg('#chbtn',{"busType":"bj_xm_sgjd_ztjgsg"});
	
	if(intsgwzid=="-1"){
		//如果空的时候创建
		if(!jcid){
			//创建数据Model；
			pageData = buildModel();
		}else{
			//判断是否更新；
			var result = isUpdata()||'';
			if(!result.id){
				//创建数据Model；
				pageData = buildModel();
				pageData["intsgwzd"] = result.intsgwzd;
				pageData["chrShowAddress"] = result.chrShowAddress;
			}else{
				ztjdsJson = result.ztjgKzList||"";
				delete result.ztjgKzList;
				pageData=result;
				pageData["dtmbgrq"]=sysdate;
			}
		}
	}else{
		//判断是否更新；
		var result = isUpdata()||'';
//		if(!result.id){
//			//创建数据Model；
//			pageData = buildModel();
//			pageData["intsgwzd"] = result.intsgwzd;
//			pageData["chrShowAddress"] = result.chrShowAddress;
//			ztjdsJson = result.ztjgKzList||"";
//		}else{
			ztjdsJson = result.ztjgKzList||"";
			delete result.ztjgKzList;
			pageData=result;
			pageData["dtmbgrq"]=sysdate;
//		}
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
				},false,true);
				
			},
			setdataPicker:function(type,index,value){
				if(type=="lc"){
					vue.$set(this.ztjdsJson[index],"dtmwcsj",value);
				}else{
					vue.$set(this.pagedata,"dtmwcrq",value);
				}
			},
			addlc:function(){
				var lcinfo = {chrjdmc:"",dtmwcsj:""}
				this.ztjdsJson.push(lcinfo);
			},
			dellc:function(key){
				this.ztjdsJson.splice(key,1);
			},
			
		},
	});
	
	if(pageData.id){
		//加载图片
		initImgList("bj_xm_sgjd_ztjgsg",pageData.id,"1","fileIds","img-list",true);
	}
}

//判断是否更新
function isUpdata(){
		var o={};
		var id = (obj.jcid?obj.jcid:"");
		$bjAjax({
			url:progressZtjgPath+"getXmSgjdZtjgsgListByXmidAndSgwzd",
			type:"post",
			async:false,
			data:{
				sgwzd:sgwzid,
				xmid:xmid,
				fwlx:"xz",
				id:id
			},
			success:function(data){
				if(data){
					o=data;
				}
			}
		});
		return o;
}
//创建数据Model
function buildModel(){
	var model = {
		intxmid:xmid,
		dtmbgrq:sysdate,
		intsgwzd:intsgwzid,
		chrShowAddress:chrsgwzmc,
		chrsgwzms:'',
		intsfwc:'',
		chrsfwc:'',
		dtmwcrq:'',
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
			for(i in this.vue.$data.ztjdsJson){
				this.vue.$data.ztjdsJson[i].dtmwcsj=this.vue.$data.ztjdsJson[i].dtmwcsj||null;
			}
			var ztjdsJson = JSON.stringify(this.vue.$data.ztjdsJson);
			var fileIds = document.getElementById("fileIds").value;
			data["ztjdsJson"] = ztjdsJson;
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
	toUrl("project_progress_record_bodyDetail.html?sgwzid="+sgwzid);
}