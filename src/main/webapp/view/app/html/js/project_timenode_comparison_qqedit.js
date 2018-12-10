var obj=getRequest();
var xmid=getCookie("id");
var vue;
window.onload = function(){
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: {
			qqbj:[],
		},
		beforeCreate: function(){
			$bjAjax({
				url:timenodeListApiPath,
				type:"post",
				//async:false,
				data:{
					gqjdbjid:"",
					xmid:intxmid,
					jdlx:"qqbj"
				},
				success:function(data){
					if(data){
						vue.$data.qqbj = data;
					}
				}
			});
		},
		methods: {
			datePicker: function (type,index) {
				var selectItems = getDtPicker(function(selectItems){
					var value = selectItems.value;
					vue.setdataPicker(type,index,value);
				});
				
			},
			setdataPicker:function(type,index,value){
				vue.$set(this.pagedata,"dtmjhwcsj",value);
			},
			addqqbj:function(){
				var zjcinfo = {chrjcmc:"",dtmjzsrq:""}
				this.zjcs.push(zjcinfo);
			},
			delqqbj:function(key){
				this.zjcs.splice(key,1);
			},
		},
	});
}



//保存数据
function save(){
	mui.confirm("将新增一条新的报告记录，\n是否确定更新？","提示",['是','否'],function(seletitem){
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


