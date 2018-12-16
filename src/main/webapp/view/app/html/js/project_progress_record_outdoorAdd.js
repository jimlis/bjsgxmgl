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
				vueDtPicker(pageData,name,false,true);
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
			var tempLxIndex=lxIndex;
			var addLxDom=newaddLx(lxMap[key]);
			
			var list=listMap[key]||[];
			for(i in list){
				var tempIndex=index;
				addQyTable([list[i]],"tbody"+tempLxIndex);
			}
			
		}
	}
	
}

var lxIndex=0;
function newaddLx(data){
	var id=data.id||"";
	var chrswgwlx=data.chrswgwlx||"";
	var fileIds=data.fileIds||"";
	
	var lxDiv=document.createElement("div");
	var table=document.createElement("table");
	var thead=document.createElement("thead");
	var tbody=document.createElement("tbody");
	var tbodyId="tbody"+lxIndex;
	    tbody.setAttribute("id",tbodyId);
	lxDiv.setAttribute("name","lxDiv");
	lxDiv.style.cssText="padding-top: 4px;";
	lxDiv.innerHTML=
		'<input type="hidden" placeholder="" name="intswgwlxid" value="'+id+'">'+
		'<input type="hidden" placeholder="" name="fileIds" value="'+fileIds+'">'+
		'<button type="button" class="mui-btn mui-btn-danger" style="margin: 2px;" onclick="delLx(\''+id+'\',this)">-</button>'+
		'<button type="button" class="mui-btn mui-btn-primary" style="margin: 2px;" onclick="addQyTable([],\''+tbodyId+'\')">+</button>'+
		'<input type="text" placeholder="请输入类型名称" name="chrswgwlx" value="'+chrswgwlx+'"  style="width:100%">';
    var xLul=document.getElementById("xLul");
		thead.innerHTML='<tr><th style="width: 30px;">操作</th><th>施工区域</th><th>完成量</th>'+
		'<th>完成情况</th><th>备注</th></tr>';
		table.appendChild(thead);
		table.appendChild(tbody);
		lxDiv.appendChild(table);
    xLul.appendChild(lxDiv);
   // addQyTable([],tbodyId);
    var tempLxIndex=lxIndex;
    lxIndex++;
    return document.getElementById("addLx"+tempLxIndex);
}

var deleteSwgwlxIds="";
function delLx(swgwlxId,obj){
	if(swgwlxId){
		deleteSwgwlxIds+=swgwlxId+",";
	}
	obj.parentNode.remove();
}

function addQyTable(data,tbodyid){
	var fileIdName="fileIds"+index;
	var chrbtnName="chbtn"+index;
	var fileListName="file-list"+index;
	var tbody=document.getElementById(tbodyid);
	var html="";
	    if(data&&data.length>0){
	    	for(i in data){
	    		var rowData=data[i];
	    		var rowid=rowData.id||"";
	    		var fileIds=rowData.fileIds||"";
	    		var chrsgqy=rowData.chrsgqy||"";
	    		var intwcl=rowData.intwcl||"";
	    		var chrbz=rowData.chrbz||"";
		    	var tr=document.createElement("tr");
		    	tr.innerHTML=
		    		'<td ><button class="mui-btn mui-btn-danger" type="button" onclick="delQy(\'\',this)">-</button></td>'+
				'<td data-label="施工区域"><input class="bj-input bj-p-black-font" type="hidden" name="intswgwsgjdid" value="'+rowid+'"  placeholder="请输入" />'+
				'<input class="bj-input bj-p-black-font" name="chrsgqy" type="text" value="'+chrsgqy+'"   placeholder="请输入" /><input class="bj-input" id="'+fileIdName+'" name="fileIds"  value="'+fileIds+'"  type="hidden"></input></td>'+
				'<td data-label="完成量"><input class="bj-input bj-p-black-font" name="intwcl" type="number" value="'+intwcl+'"  placeholder="请输入" /></td>'+
				'<td data-label="完成情况"><div  id="uploader" class="wu-example"><div class="btns" style="text-align: left;">'+'<button id="'+chrbtnName+'" type="button" style="text-align: left;">选择文件</button></div><div id="'+fileListName+'"></div>'+'</td>'+
				'<td data-label="备注"><input class="bj-input bj-p-black-font" name="chrbz" type="text" value="'+chrbz+'"  placeholder="请输入" /></td>';
		    	tbody.appendChild(tr);
		    	//刷新上传控件
				upLoadFile('#'+chrbtnName,{"busType":"bj_xm_sgjd_swgwsg_jd","fileIdsName":fileIdName,"fileListName":fileListName});
				initFileList("bj_xm_sgjd_swgwsg_jd",rowData.id,"1",fileIdName,fileListName,true);
				index++;
	    	}
	    	
	    }else{
	    	var tr=document.createElement("tr");
	    	tr.innerHTML=
	   	 	'<td ><button class="mui-btn mui-btn-danger" type="button" onclick="delQy(\'\',this)">-</button></td>'+
			'<td data-label="施工区域"><input class="bj-input bj-p-black-font" type="hidden" name="intswgwsgjdid"   placeholder="请输入" />'+
			'<input class="bj-input bj-p-black-font" name="chrsgqy" type="text"  placeholder="请输入" /><input class="bj-input" id="'+fileIdName+'" name="fileIds" type="hidden"></input></td>'+
			'<td data-label="完成量"><input class="bj-input bj-p-black-font" name="intwcl" type="number"  placeholder="请输入" /></td>'+
			'<td data-label="完成情况"><div  id="uploader" class="wu-example"><div class="btns" style="text-align: left;">'+'<button id="'+chrbtnName+'" type="button" style="text-align: left;">选择文件</button></div><div id="'+fileListName+'"></div>'+'</td>'+
			'<td data-label="备注"><input class="bj-input bj-p-black-font" name="chrbz" type="text"  placeholder="请输入" /></td>';
	    	tbody.appendChild(tr);
	    	//刷新上传控件
			upLoadFile('#'+chrbtnName,{"busType":"bj_xm_sgjd_swgwsg_jd","fileIdsName":fileIdName,"fileListName":fileListName});
			index++;
	    }
		
		
}

function addQy(data,obj){
	    data=data||{};
	    var id=data.id||"";
	    var intwcl=data.intwcl||"";
	    var chrsgqy=data.chrsgqy||"";
	    var chrbz=data.chrbz||"";
	var ulDiv=obj.parentNode.previousSibling;
	var ulDom=document.createElement("ul");
		ulDom.className='mui-table-view bj-background-inherit';
	
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
		var intswgwlxid=null,fileIds="",chrswgwlx="",xmSgjdSwgwsgJdList=[];
		var intswgwlxid=item[0].value||null;
		var fileIds=item[1].value||"";
		var chrswgwlx=item[2].value||"";
		
		var qytrNodes=lxDivDom[index].lastElementChild.childNodes[1].childNodes;
		for(var j=0;j<qytrNodes.length;j++){
			var qyTr=qytrNodes[j];
			var qyTdNodes=qyTr.childNodes;
			var qyObj={};
			for(var k=0;k<qyTdNodes.length;k++){
				var qyTd=qyTdNodes[k];
				var qyTdNodesDom=qyTd.childNodes;
				for(var m=0;m<qyTdNodesDom.length;m++){
					if(qyTdNodesDom[m].name=="intswgwsgjdid"){
					//	qyObj["id"]=qyTdNodesDom[m].value||null;
					}
					if(qyTdNodesDom[m].name=="fileIds"){
							qyObj["fileIds"]=qyTdNodesDom[m].value||"";
					}
					if(qyTdNodesDom[m].name=="fileIds"){
						qyObj["fileIds"]=qyTdNodesDom[m].value||"";
					}
					if(qyTdNodesDom[m].name=="chrsgqy"){
						qyObj["chrsgqy"]=qyTdNodesDom[m].value||"";
					}
					if(qyTdNodesDom[m].name=="intwcl"){
						qyObj["intwcl"]=qyTdNodesDom[m].value||null;
					}
					if(qyTdNodesDom[m].name=="chrbz"){
						qyObj["chrbz"]=qyTdNodesDom[m].value||"";
					}
				}
			}
			xmSgjdSwgwsgJdList.push(qyObj);
		}
		lxObj["id"]=intswgwlxid;
		lxObj["fileIds"]=fileIds;
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
				xmSgjdSwgwsgId:id,
				xmid:xmid,
				fwlx:"xz"
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
isSure(function(){
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
})
}
function outPage(){
	toUrl("project_progress_record_outdoorDetail.html?id="+id);
}