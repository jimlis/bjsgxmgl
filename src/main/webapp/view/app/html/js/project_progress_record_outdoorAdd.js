var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
window.onload = function(){
	
	upLoadFile('#chbtn',{"busType":"bj_xm_sgjd_swgwsg"});
	
	//判断是否更新；
	pageData = isUpdata()||'';
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			addLx: function () {
				var lxDiv=document.createElement("div");
				lxDiv.setAttribute("name","lxLi");
				lxDiv.style.cssText="padding-top: 4px;";
			  
				//lxLi.className="mui-table-view-cell mui-collapse mui-active";
				lxDiv.innerHTML=
					'<a href="#"><input type="text" placeholder="请输入类型名称" style="width:100%"></a>'+
					'<ul class="mui-table-view bj-background-inherit"></ul>'+
					'<span><button type="button" class="mui-btn mui-btn-primary" style="margin-top: 2px;margin-right:65px" onclick="addQy({},this)">新增区域</button>'+
					'<button type="button" class="mui-btn mui-btn-danger" style="margin-top: 2px;" onclick="delLx(\'\',this)">删除类型</button></span>';
                var xLul=document.getElementById("xLul");
                xLul.appendChild(lxDiv);
			},
			datePicker: function (name) {
				vueDtPicker(pageData,name);
			}
		}
	});
	
	if(id){
		//加载图片
		upLoadFile('#chbtn',{"busType":"bj_xm_sgjd_swgwsg"});
	}
}

var deleteSwgwlxIds="";
function delLx(swgwlxId,obj){
	if(swgwlxId){
		deleteSwgwlxIds+=swgwlxId+",";
	}
	obj.parentNode.parentNode.remove();
}

var index=0;
function addQy(data,obj){
	var ulDom=obj.parentNode.previousSibling;
	var fileIdName="fileIds"+index;
	var chrbtnName="chbtn"+index;
	var fileListName="file-list"+index;
	var delBtnLi=document.createElement("li");
		delBtnLi.style.cssText="padding: 5px 20px 0px 20px;";
		delBtnLi.innerHTML='<button class="mui-btn mui-btn-danger" type="button"  onclick="delQy(\'\',this,event)">删除区域</button>';
	
	var sgqyLi=document.createElement("li");
		sgqyLi.style.cssText="padding: 5px 20px 0px 20px;";
	//	sgqyLi.className="mui-table-view-cell mui-collapse";
		sgqyLi.innerHTML='<input class="bj-input" name="id" type="hidden"></input>'+
			'<input class="bj-input" id="'+fileIdName+'" name="fileIds" type="hidden"></input>'+
			'施工区域：<input class="bj-input" name="chrsgqy" type="text"></input>';
		
	var wclLi=document.createElement("li");
		wclLi.style.cssText="padding: 5px 20px 0px 20px;";
		//wclLi.className="mui-table-view-cell mui-collapse";
		wclLi.innerHTML='完成量：<input class="bj-input" name="intwcl" type="text"></input>';
		
	var wcqkLi=document.createElement("li");
		wcqkLi.style.cssText="padding: 5px 20px 0px 20px;";
		//wcqkLi.className="mui-table-view-cell mui-collapse";
		wcqkLi.innerHTML='完成情况：<div id="uploader" class="wu-example"><div class="btns">'+
			'<button id="'+chrbtnName+'" type="button">选择文件</button></div><div id="'+fileListName+'"></div>';
	
	var bzLi=document.createElement("li");
		bzLi.style.cssText="padding: 5px 20px 0px 20px;";
		//bzLi.className="mui-table-view-cell mui-collapse";
		bzLi.innerHTML='备注：<input class="bj-input" name="chrbz" type="text"></input>';
		
		ulDom.appendChild(delBtnLi);
		ulDom.appendChild(sgqyLi);
		ulDom.appendChild(wclLi);
		ulDom.appendChild(wcqkLi);
		ulDom.appendChild(bzLi);
		
		//刷新上传控件
		upLoadFile('#'+chrbtnName,{"busType":"bj_xm_xkz","fileIdsName":fileIdName,"fileListName":fileListName});
		index++;
}

var deleteSwgwjdIds="";
function delQy(swgwjdId,obj){
	if(swgwjdId){
		deleteSwgwjdIds+=swgwjdId+",";
	}
	obj.parentNode.nextSibling.remove();
	obj.parentNode.nextSibling.remove();
	obj.parentNode.nextSibling.remove();
	obj.parentNode.nextSibling.remove();
	obj.parentNode.remove();
}

//判断是否更新
function isUpdata(){
	if(id){
		var result={};
		$bjAjax({
			url:progressOutDoorByIdApiPath,
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
		dtmsprq:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	var data = getFromData("myform");
	$bjAjax({
		url:progressOutDoorSaveApiPath,
		data:data,
		type:"post",
		success:function(result){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_outdoorDetail.html?id="+(result.id||""));
			});
		}
	});
}
