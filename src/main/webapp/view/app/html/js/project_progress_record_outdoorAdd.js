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
				newaddLx({});
			},
			datePicker: function (name) {
				vueDtPicker(pageData,name);
			}
		}
	});
	
	if(id){
		//加载图片
		initFileList("bj_xm_sgjd_swgwsg",id,"1","fileIds","file-list",true);
	}
	
	//init 
	var listMap=pageData.xmSgjdSwgwsgJdListMap;
	if(listMap){
		var listMap=pageData.xmSgjdSwgwsgJdListMap;
		var lxMap=pageData.xmSgjdSwgwsgLxMap||{};
		for(key in listMap){
			
			var addLxDom=newaddLx(lxMap[key]);
			
			var list=listMap[key]||[];
			for(i in list){
				var tempIndex=index;
				addQy(list[i],addLxDom);
				var fileIdName="fileIds"+tempIndex;
				var chrbtnName="chbtn"+tempIndex;
				var fileListName="file-list"+tempIndex;
				
				//加载文件
				initFileList("bj_xm_sgjd_swgwsg_jd",list[i].id,"1",fileIdName,fileListName,true);
			}
			
		}
	}
	
}

var lxIndex=0;
function newaddLx(data){
	var id=data.id||"";
	var chrswgwlx=data.chrswgwlx||"";
	var lxDiv=document.createElement("div");
	lxDiv.setAttribute("name","lxDiv");
	lxDiv.style.cssText="padding-top: 4px;";
	lxDiv.innerHTML=
		'<div><input type="hidden" placeholder="" name="intswgwlxid" value="'+id+'">'+
		'<input type="text" placeholder="请输入类型名称" name="chrswgwlx" value="'+chrswgwlx+'"  style="width:100%"></div>'+
		'<div></div>'+
		'<span><button id="addLx'+lxIndex+'" type="button" class="mui-btn mui-btn-primary" style="margin-top: 2px;margin-right:65px" onclick="addQy({},this)">新增区域</button>'+
		'<button type="button" class="mui-btn mui-btn-danger" style="margin-top: 2px;" onclick="delLx(\''+id+'\',this)">删除类型</button></span>';
    var xLul=document.getElementById("xLul");
    xLul.appendChild(lxDiv);
    var tempLxIndex=lxIndex;
    lxIndex++;
    return document.getElementById("addLx"+tempLxIndex);
}

var deleteSwgwlxIds="";
function delLx(swgwlxId,obj){
	if(swgwlxId){
		deleteSwgwlxIds+=swgwlxId+",";
	}
	obj.parentNode.parentNode.remove();
}

function addQy(data,obj){
	    data=data||{};
	    var id=data.id||"";
	    var intwcl=data.intwcl||"";
	    var chrsgqy=data.chrsgqy||"";
	    var chrbz=data.chrbz||"";
	var ulDiv=obj.parentNode.previousSibling;
	var ulDom=document.createElement("ul");
		ulDom.className='<ul class="mui-table-view bj-background-inherit"';
	
	var fileIdName="fileIds"+index;
	var chrbtnName="chbtn"+index;
	var fileListName="file-list"+index;
	var delBtnLi=document.createElement("li");
		delBtnLi.style.cssText="padding: 5px 20px 0px 20px;";
		delBtnLi.innerHTML='<button class="mui-btn mui-btn-danger" type="button"  onclick="delQy(\''+id+'\',this,event)">删除区域</button>';
	
	var sgqyLi=document.createElement("li");
		sgqyLi.style.cssText="padding: 5px 20px 0px 20px;";
	//	sgqyLi.className="mui-table-view-cell mui-collapse";
		sgqyLi.innerHTML='<input class="bj-input" name="intswgwsgjdid" value="'+id+'" type="hidden"></input>'+
			'<input class="bj-input" id="'+fileIdName+'" name="fileIds" type="hidden"></input>'+
			'施工区域：<input class="bj-input" name="chrsgqy" value="'+chrsgqy+'" type="text"></input>';
		
	var wclLi=document.createElement("li");
		wclLi.style.cssText="padding: 5px 20px 0px 20px;";
		//wclLi.className="mui-table-view-cell mui-collapse";
		wclLi.innerHTML='完成量：<input class="bj-input" name="intwcl" value="'+intwcl+'" type="number"></input>';
		
	var wcqkLi=document.createElement("li");
		wcqkLi.style.cssText="padding: 5px 20px 0px 20px;";
		//wcqkLi.className="mui-table-view-cell mui-collapse";
		wcqkLi.innerHTML='完成情况：<div id="uploader" class="wu-example"><div class="btns">'+
			'<button id="'+chrbtnName+'" type="button">选择文件</button></div><div id="'+fileListName+'"></div>';
	
	var bzLi=document.createElement("li");
		bzLi.style.cssText="padding: 5px 20px 0px 20px;";
		//bzLi.className="mui-table-view-cell mui-collapse";
		bzLi.innerHTML='备注：<input class="bj-input" name="chrbz" value="'+chrbz+'" type="text"></input>';
		
		ulDom.appendChild(delBtnLi);
		ulDom.appendChild(sgqyLi);
		ulDom.appendChild(wclLi);
		ulDom.appendChild(wcqkLi);
		ulDom.appendChild(bzLi);
		
		ulDiv.appendChild(ulDom);
		
		//刷新上传控件
		upLoadFile('#'+chrbtnName,{"busType":"bj_xm_xkz","fileIdsName":fileIdName,"fileListName":fileListName});
		index++;
}

var deleteSwgwjdIds="";
function delQy(swgwjdId,obj){
	if(swgwjdId){
		deleteSwgwjdIds+=swgwjdId+",";
	}
	obj.parentNode.parentNode.remove();
}

function getSglxAndJdJson(){
	var lxDivDom=document.getElementsByName("lxDiv");
	var arr=[];
	for(var index=0;index<lxDivDom.length;index++){
		var item = lxDivDom[index].childNodes;
		var lxObj={};
		var intswgwlxid=null,chrswgwlx="",xmSgjdSwgwsgJdList=[];
		var lxDiv=item[0];
		var lxDivNodes=lxDiv.childNodes;
		for(var j=0;j<lxDivNodes.length;j++){
			if(lxDivNodes[j].name=="intswgwlxid"){
				intswgwlxid=lxDivNodes[j].value||null;
			}
			if(lxDivNodes[j].name=="chrswgwlx"){
				chrswgwlx=lxDivNodes[j].value||"";
			}
		}
		
		var qydiv=item[1];
		var qyulNodes=qydiv.childNodes;
		for(var j=0;j<qyulNodes.length;j++){
			var qyUl=qyulNodes[j];
			var qyLiNodes=qyUl.childNodes;
			var qyObj={};
			for(var k=0;k<qyLiNodes.length;k++){
				var qyLi=qyLiNodes[k];
				var qyLiNodesDom=qyLi.childNodes;
				for(var m=0;m<qyLiNodesDom.length;m++){
					if(qyLiNodesDom[m].name=="intswgwsgjdid"){
						qyObj["id"]=qyLiNodesDom[m].value||null;
					}
					if(qyLiNodesDom[m].name=="fileIds"){
						qyObj["fileIds"]=qyLiNodesDom[m].value||"";
					}
					if(qyLiNodesDom[m].name=="chrsgqy"){
						qyObj["chrsgqy"]=qyLiNodesDom[m].value||"";
					}
					if(qyLiNodesDom[m].name=="intwcl"){
						qyObj["intwcl"]=qyLiNodesDom[m].value||null;
					}
					if(qyLiNodesDom[m].name=="chrbz"){
						qyObj["chrbz"]=qyLiNodesDom[m].value||"";
					}
				}
			}
			xmSgjdSwgwsgJdList.push(qyObj);
		}
		lxObj["id"]=intswgwlxid;
		lxObj["chrswgwlx"]=chrswgwlx;
		lxObj.xmSgjdSwgwsgJdList=xmSgjdSwgwsgJdList;
		arr.push(lxObj);
	}
	return JSON.stringify(arr);
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
				xmSgjdSwgwsgId:id
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
		data["sglxAndJdJson"]=getSglxAndJdJson();
		data["deleteSwgwlxIds"]=deleteSwgwlxIds;
		data["deleteSwgwjdIds"]=deleteSwgwjdIds;
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
function outPage(){
	toUrl("project_progress_record.html");
}