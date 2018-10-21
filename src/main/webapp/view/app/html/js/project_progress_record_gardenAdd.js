var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var index=0;
window.onload = function(){
	
	upLoadFile('#chbtn',{"busType":"bj_xm_sgjd_ylsg"});
	
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
				newaddLx({});
			},
			datePicker: function (name) {
				vueDtPicker(pageData,name);
			}
		}
	});
	
	if(id){
		//加载图片
		initFileList("bj_xm_sgjd_ylsg",id,"1","fileIds","file-list",true);
	}
	
	//init 
	var list=pageData.xmSgjdYlsgJdList||[];
			debugger;
	for(i in list){
		var tempIndex=index;
		newaddLx(list[i]);
		var fileIdName="fileIds"+tempIndex;
		var chrbtnName="chbtn"+tempIndex;
		var fileListName="file-list"+tempIndex;
		
		//加载文件
		initFileList("bj_xm_sgjd_ylsg_jd",list[i].id,"1",fileIdName,fileListName,true);
	}
			
}

function newaddLx(data){
	    data=data||{};
	    var id=data.id||"";
	    var intwcl=data.intwcl||"";
	    var chrlxmc=data.chrlxmc||"";
	    var chrsgqy=data.chrsgqy||"";
	    var chrzb=data.chrzb||"";
	    
	    var lxDiv=document.createElement("div");
		lxDiv.setAttribute("name","lxDiv");
		lxDiv.style.cssText="padding-top: 4px;";    
	
	var ulDom=document.createElement("ul");
		ulDom.className='<ul class="mui-table-view bj-background-inherit"';
	
	var fileIdName="fileIds"+index;
	var chrbtnName="chbtn"+index;
	var fileListName="file-list"+index;
	var delBtnLi=document.createElement("li");
		delBtnLi.style.cssText="padding: 5px 0px 0px 0px;";
		delBtnLi.innerHTML='<button class="mui-btn mui-btn-danger" type="button"  onclick="delLx(\''+id+'\',this,event)">删除区域</button>';
	
	var lxmcLi=document.createElement("li");
		lxmcLi.style.cssText="padding: 5px 0px 0px 0px;";
		lxmcLi.innerHTML='<input class="bj-input" name="intylsgjdid" value="'+id+'" type="hidden"></input>'+
		'<input class="bj-input" id="'+fileIdName+'" name="fileIds" type="hidden"></input>'+
		'类型名称：<input class="bj-input" name="chrlxmc" value="'+chrlxmc+'" type="text"></input>';
		
		
	var wclLi=document.createElement("li");
		wclLi.style.cssText="padding: 5px 0px 0px 0px;";
		wclLi.innerHTML='完成量：<input class="bj-input" name="intwcl" value="'+intwcl+'" type="number"></input>';
		
	var wcqkLi=document.createElement("li");
		wcqkLi.style.cssText="padding: 5px 0px 0px 0px;";
		wcqkLi.innerHTML='完成情况：<div id="uploader" class="wu-example"><div class="btns">'+
			'<button id="'+chrbtnName+'" type="button">选择文件</button></div><div id="'+fileListName+'"></div>';
	
	var bzLi=document.createElement("li");
		bzLi.style.cssText="padding: 5px 0px 0px 0px;";
		bzLi.innerHTML='备注：<input class="bj-input" name="chrzb" value="'+chrzb+'" type="text"></input>';
		
		ulDom.appendChild(delBtnLi);
		ulDom.appendChild(lxmcLi);
		ulDom.appendChild(wclLi);
		ulDom.appendChild(wcqkLi);
		ulDom.appendChild(bzLi);
		
		lxDiv.appendChild(ulDom);
		
		document.getElementById("xLul").appendChild(lxDiv);
		
		//刷新上传控件
		upLoadFile('#'+chrbtnName,{"busType":"bj_xm_sgjd_ylsg_jd","fileIdsName":fileIdName,"fileListName":fileListName});
		index++;
}

var deleteYlsgjdIds="";
function delLx(ylsgjdId,obj){
	if(ylsgjdId){
		deleteYlsgjdIds+=ylsgjdId+",";
	}
	obj.parentNode.parentNode.parentNode.remove();
}

function getylsglxAndJdJson(){
	var lxDivDom=document.getElementsByName("lxDiv");
	var arr=[];
	for(var index=0;index<lxDivDom.length;index++){
		var item = lxDivDom[index].childNodes;
		var lxUl=item[0];
		var lxLiNodes=lxUl.childNodes;
		var lxObj={};
		for(var j=0;j<lxLiNodes.length;j++){
				var lxLiNodesDom=lxLiNodes[j].childNodes;
				for(var m=0;m<lxLiNodesDom.length;m++){
					if(lxLiNodesDom[m].name=="intylsgjdid"){
						lxObj["id"]=lxLiNodesDom[m].value||null;
					}
					if(lxLiNodesDom[m].name=="chrlxmc"){
						lxObj["chrlxmc"]=lxLiNodesDom[m].value||"";
					}
					if(lxLiNodesDom[m].name=="fileIds"){
						lxObj["fileIds"]=lxLiNodesDom[m].value||"";
					}
					if(lxLiNodesDom[m].name=="intwcl"){
						lxObj["intwcl"]=lxLiNodesDom[m].value||null;
					}
					if(lxLiNodesDom[m].name=="chrzb"){
						lxObj["chrzb"]=lxLiNodesDom[m].value||"";
					}
			}
		}
		arr.push(lxObj);
	}
	return JSON.stringify(arr);
}

//判断是否更新
function isUpdata(){
	if(id){
		var result={};
		$bjAjax({
			url:progressGardenByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdYlsgId:id
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
		dtmsprq:'',
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
	var data = getFromData("myform");
		data["ylsgJdJson"]=getylsglxAndJdJson();
		data["deleteYlsgjdIds"]=deleteYlsgjdIds;
	$bjAjax({
		url:progressGardenSaveApiPath,
		data:data,
		type:"post",
		success:function(result){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_gardenDetail.html?id="+(result.id||""));
			});
		}
	});
}
