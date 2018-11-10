var obj=getRequest()
var gqjdbjid=obj.gqjdbjid||"";
var intxmid=getCookie("id");
var pageData={};
var vue;
window.onload = function(){
		//数据绑定
	vue = new Vue({
			el: '#app',
			data: {
				pageData:pageData,
				deleteId:"",
			},
			beforeCreate: function(){
				$bjAjax({
					url:timenodeZtListApiPath,
					type:"post",
//					async:false,
					data:{
						gqjdbjid:gqjdbjid,
						xmid:intxmid,
						jdlx:"zt"
					},
					success:function(data){
						if(data){
							vue.$data.pageData = data[0];
						}
					}
				});
			},
			methods: {
				datePicker: function (index) {
					var selectItems = getDtPicker(function(selectItems){
						var value = selectItems.value;
						vue.setdataPicker(index,value);
					});
					
				},
				setdataPicker:function(index,value){
					vue.$set(this.pageData.childList[index],"dtmjhwcsj",value);
				},
				add:function(){
					var info = {intfjdid:gqjdbjid,intxh:"",chrjdmc:"",dtmjhwcsj:""}
					this.pageData.childList.push(info);
				},
				del:function(key){
					var delObj = this.pageData.childList[key];
					if(delObj.id){
						this.deleteId+=delObj.id+",";
					}
					this.pageData.childList.splice(key,1);
				},
				
			}
	});
	
}
function inputTest(childList){
    for(i in childList){
    		var a=childList[i].dtmjhwcsj
    		if(!a){
    			bjToast("日期不能为空");
    			return false;
    		}
    }
    return true;
}

function save(){
isSure(function(){
	var data = {};
	data["xmid"]=intxmid;
	data["jdlx"]="zt";
	var childList = restore(this.vue.$data.pageData.childList);
	var test = inputTest(childList);
	if(!test){
		return;
	}
//	for(i in childList){
//		if(!childList[i]){
//			childList[i]="";
//		}
//	}
	data["gqjdbjJson"]= JSON.stringify(childList);
	var delid = restore(this.vue.$data.deleteId);
	data["deleteGqjdbjIds"]=delid.substring(0,delid.length-1);
	$bjAjax({
		url:timenodeSaveBatchPath,
		type:"post",
		data:data,
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_timenode_comparison_ejedetails.html?chrjdlx="+chrjdlx);
			});
		}
	})
})
}

function outPage(){
	toUrl("project_timenode_comparison.html");
}

