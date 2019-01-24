showEdit();
var obj=getRequest();
var xmid=getCookie("id");
var pageData;
var vue;
var zxys=[];
var jgys=[];
var deleteGqjdbjIds="";
window.onload = function(){
	
	initData();
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: {
			zxys:zxys,
			jgys:jgys
		},
		methods: {
			datePicker: function (type,index) {
				var selectItems = getDtPicker(function(selectItems){
					var value = selectItems.value;
					vue.setdataPicker(type,index,value);
				},true,false);
				
			},
			setdataPicker:function(type,index,value){
				if(type=="zxys"){
					vue.$set(this.zxys[index],"dtmjhwcsj",value);
				}else if(type=="jgys"){
					vue.$set(this.jgys[index],"dtmjhwcsj",value);
				}
			},
			addzxys:function(){
				var zxysInfo = {id:null,intxh:null,chrjdmc:"",dtmjhwcsj:"",chrjdlx:"zxys"}
				this.zxys.push(zxysInfo);
			},
			delzxys:function(key){
				var nowId=this.zxys[key].id;
				if(nowId){
					deleteGqjdbjIds+=nowId+",";
				}
				this.zxys.splice(key,1);
			},
			addjgys:function(){
				var jgysInfo = {id:null,intxh:null,chrjdmc:"",dtmjhwcsj:"",chrjdlx:"jgys"}
				this.jgys.push(jgysInfo);
			},
			deljgys:function(key){
				var nowId=this.jgys[key].id;
				if(nowId){
					deleteGqjdbjIds+=nowId+",";
				}
				this.jgys.splice(key,1);
			}
		},
		watch: {
		}
	});
	
}


//判断是否更新
function initData(){
	var result={};
	$bjAjax({
		url:timenodeZxjgMapListByXmidApiPath,
		type:"post",
		async:false,
		data:{
			xmid:xmid
		},
		success:function(data){
			if(data){
				zxys=data.zxys||[];
				jgys=data.jgys||[];
				result = data;
			}
		}
	});
	return result;
}

//保存数据
function save(){
	isSure(function(){
			var data={};
			var jsonArr=[];
				jsonArr=jsonArr.concat(vue.$data.zxys,vue.$data.jgys);
			var gqjdbjJson = JSON.stringify(jsonArr);
			data["xmid"] = xmid;
			data["gqjdbjJson"] = gqjdbjJson;
			data["deleteGqjdbjIds"]=(deleteGqjdbjIds.length>0?deleteGqjdbjIds.substring(0,deleteGqjdbjIds.length-1):deleteGqjdbjIds);
			$bjAjax({
				url:timenodeSaveBatchZxjgPath,
				data:data,
				type:"post",
				success:function(result){
					bjToast("保存成功！",function(){
						outPage();
					});
				}
			});
	});
	
}
function outPage(){
	toUrl("project_timenode_comparison.html?");
}


