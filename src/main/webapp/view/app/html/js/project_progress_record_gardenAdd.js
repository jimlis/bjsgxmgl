
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
				newaddLx([]);
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
	    newaddLx(list);
			
}

function newaddLx(data){
		var fileIdName="",chrbtnName="",fileListName="";
		
		var tbody=document.getElementById("one_tbody");
		var html="";
		    if(data&&data.length>0){
		    	for(i in data){
		    		fileIdName="fileIds"+index;
		    		chrbtnName="chbtn"+index;
		    		fileListName="file-list"+index;
		    		var rowData=data[i];
		    		var rowid=rowData.id||"";
		    		var fileIds=rowData.fileIds||"";
		    		var chrlxmc=rowData.chrlxmc||"";
		    		var intwcl=rowData.intwcl||"";
		    		var chrzb=rowData.chrzb||"";
			    	var tr=document.createElement("tr");
			    	tr.innerHTML=
					'<td data-label="类型名称"><input class="bj-input bj-p-black-font" type="hidden" name="intylsgjdid" value="'+rowid+'"  placeholder="请输入" />'+
					'<input class="bj-input bj-p-black-font" name="chrlxmc" type="text" value="'+chrlxmc+'"   placeholder="请输入" /><input class="bj-input" id="'+fileIdName+'" name="fileIds"  value="'+fileIds+'"  type="hidden"></input></td>'+
					'<td data-label="完成量"><input class="bj-input bj-p-black-font" name="intwcl" type="number" value="'+intwcl+'"  placeholder="请输入" /></td>'+
					'<td data-label="完成情况"><div  id="uploader" class="wu-example"><div class="btns" style="text-align: left;">'+'<button id="'+chrbtnName+'" type="button" style="text-align: left;">选择文件</button></div><div id="'+fileListName+'"></div>'+'</td>'+
					'<td data-label="备注"><input class="bj-input bj-p-black-font" name="chrzb" type="text" value="'+chrzb+'"  placeholder="请输入" /></td>'+
					'<td ><button class="mui-btn mui-btn-danger" type="button" onclick="delLx(\'\',this)">-</button></td>';
			    	tbody.appendChild(tr);
			    	//刷新上传控件
					upLoadFile('#'+chrbtnName,{"busType":"bj_xm_sgjd_swgwsg_jd","fileIdsName":fileIdName,"fileListName":fileListName});
					initFileList("bj_xm_sgjd_ylsg_jd",rowData.id,"1",fileIdName,fileListName,true);
					index++;
		    	}
		    	
		    }else{
		    	var tr=document.createElement("tr");
		    	fileIdName="fileIds"+index;
	    		chrbtnName="chbtn"+index;
	    		fileListName="file-list"+index;
		    	tr.innerHTML=
				'<td data-label="类型名称"><input class="bj-input bj-p-black-font" type="hidden" name="intylsgjdid"   placeholder="请输入" />'+
				'<input class="bj-input bj-p-black-font" name="chrlxmc" type="text"  placeholder="请输入" /><input class="bj-input" id="'+fileIdName+'" name="fileIds" type="hidden"></input></td>'+
				'<td data-label="完成量"><input class="bj-input bj-p-black-font" name="intwcl" type="number"  placeholder="请输入" /></td>'+
				'<td data-label="完成情况"><div  id="uploader" class="wu-example"><div class="btns" style="text-align: left;">'+'<button id="'+chrbtnName+'" type="button" style="text-align: left;">选择文件</button></div><div id="'+fileListName+'"></div>'+'</td>'+
				'<td data-label="备注"><input class="bj-input bj-p-black-font" name="chrzb" type="text"  placeholder="请输入" /></td>'+
				'<td ><button class="mui-btn mui-btn-danger" type="button" onclick="delLx(\'\',this)">-</button></td>';
		    	tbody.appendChild(tr);
		    	//刷新上传控件
				upLoadFile('#'+chrbtnName,{"busType":"bj_xm_sgjd_ylsg_jd","fileIdsName":fileIdName,"fileListName":fileListName});
				index++;
		    }
		
}

var deleteYlsgjdIds="";
function delLx(ylsgjdId,obj){
	if(ylsgjdId){
		deleteYlsgjdIds+=ylsgjdId+",";
	}
	obj.parentNode.parentNode.remove();
}

function getylsglxAndJdJson(){
	var one_tbody=document.getElementById("one_tbody");
	var trNodes=one_tbody.childNodes;
	var arr=[];
	for(var i=0;i<trNodes.length;i++){
		var tdNodes = trNodes[i].childNodes;
		var lxObj={};
		for(var j=0;j<tdNodes.length;j++){
				var tdNodesDom=tdNodes[j].childNodes;
				for(var m=0;m<tdNodesDom.length;m++){
					if(tdNodesDom[m].name=="intylsgjdid"){
					//	lxObj["id"]=tdNodesDom[m].value||null;
					}
					if(tdNodesDom[m].name=="chrlxmc"){
						lxObj["chrlxmc"]=tdNodesDom[m].value||"";
					}
					if(tdNodesDom[m].name=="fileIds"){
						lxObj["fileIds"]=tdNodesDom[m].value||"";
					}
					if(tdNodesDom[m].name=="intwcl"){
						lxObj["intwcl"]=tdNodesDom[m].value||null;
					}
					if(tdNodesDom[m].name=="chrzb"){
						lxObj["chrzb"]=tdNodesDom[m].value||"";
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
			url:progressGardenByParamApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdYlsgId:id,
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
function outPage(){
	toUrl("project_progress_record_gardenDetail.html?id="+id);
}