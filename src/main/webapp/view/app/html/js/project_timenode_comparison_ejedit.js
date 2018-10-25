var obj=getRequest()
var id = obj.id||"";
var gqjdbjid=obj.gqjdbjid||"";
var chrjdlx=obj.chrjdlx||"";
var intxmid=getCookie("id");
var pageData;
var vue;
var index=0;
var msg=chrjdlx=="jc"?"基础":(chrjdlx=="zt"?"主体":"");
window.onload = function(){
	debugger;
    pageData =gqjdbjid?getPageData():[];
    index=pageData?pageData.length:0;
		//数据绑定
	var vue = new Vue({
			el: '#app',
			data: {list:pageData,gqjdbjid:gqjdbjid,msg:msg},
			methods: {
				addDl: function () {
					newaddDl({});
				},
				datePicker: function (name) {
					vueDtPicker(pageData,name);
				}
			}
	});
	
	/*var listMap=pageData.xmSgjdSwgwsgJdListMap;
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
			}
			
		}
	}*/
}

var lxIndex=0;
function newaddDl(data){
	var id=data.id||"";
	var chrjdmc=data.chrjdmc||"";
	var lxDiv=document.createElement("div");
	lxDiv.setAttribute("name","oneDiv");
	lxDiv.style.cssText="padding-top: 4px;";
	lxDiv.innerHTML=
		'<div><input type="hidden" placeholder="" name="intgqjdbjid" value="'+id+'">'+
		'<input type="hidden" placeholder="" name="chrlx" value="1">'+
		'<input type="text" placeholder="请输入栋楼名称" name="chrjdmc" value="'+chrjdmc+'"  style="width:100%"></div>'+
		'<div></div>'+
		'<span><button id="addOne'+lxIndex+'" type="button" class="mui-btn mui-btn-primary" style="margin-top: 2px;margin-right:65px" onclick="addOne({},this)">新增'+msg+'</button>'+
		'<button type="button" class="mui-btn mui-btn-danger" style="margin-top: 2px;" onclick="delOne(\''+id+'\',this)">删除栋楼</button></span>';
    var div=document.getElementById("div");
    div.appendChild(lxDiv);
    var tempLxIndex=lxIndex;
    lxIndex++;
    return document.getElementById("addOne"+tempLxIndex);
}

function addOne(data,obj){
	    	data=data||{};
	    var id=data.id||"";
	    var intxh=data.intxh||"";
	    var chrjdmc=data.chrjdmc||"";
	    var dtmjhwcsj=data.dtmjhwcsj||"";
	var ulDiv=obj.parentNode.previousSibling;
	var ulDom=document.createElement("ul");
		ulDom.className='<ul class="mui-table-view bj-background-inherit"';
	
	var delBtnLi=document.createElement("li");
		delBtnLi.style.cssText="padding: 5px 20px 0px 20px;";
		delBtnLi.innerHTML='<button class="mui-btn mui-btn-danger" type="button"  onclick="delOne(\''+id+'\',this,event)">删除'+msg+'</button>';
	
	var xhLi=document.createElement("li");
		xhLi.style.cssText="padding: 5px 20px 0px 20px;";
		xhLi.innerHTML='<input class="bj-input" name="intgqjdbjid" value="'+id+'" type="hidden"></input>'+
		    '<input class="bj-input" name="chrlx" value="0" type="hidden"></input>'+
			'序号：<input class="bj-input" name="intxh" value="'+intxh+'" type="number"></input>';
		
	var jdmcLi=document.createElement("li");
		jdmcLi.style.cssText="padding: 5px 20px 0px 20px;";
		jdmcLi.innerHTML='名称：<input class="bj-input" name="chrjdmc" value="'+chrjdmc+'" type="text"></input>';
		
	var jhwcsjLi=document.createElement("li");
		jhwcsjLi.style.cssText="padding: 5px 20px 0px 20px;";
		jhwcsjLi.innerHTML='计划完成时间：<input  data-options=\'{"type":"date"}\' class="bj-input" id="dtmjhwcsj'+index+'" name="dtmjhwcsj" value="'+dtmjhwcsj+'" type="text"></input>';
		
		ulDom.appendChild(delBtnLi);
		ulDom.appendChild(xhLi);
		ulDom.appendChild(jdmcLi);
		ulDom.appendChild(jhwcsjLi);
		ulDiv.appendChild(ulDom);
		
		dtPicker('#dtmjhwcsj'+index);
		
		index++;
}

var deleteGqjdbjIds="";
function delOne(gqjdbjId,obj){
	if(gqjdbjId){
		deleteGqjdbjIds+=gqjdbjId+",";
	}
	obj.parentNode.parentNode.remove();
}

function getGqjdbjJson(){
	var oneDivDom=document.getElementsByName("oneDiv");
	var arr=[];
	for(var index=0;index<oneDivDom.length;index++){
		var item = oneDivDom[index].childNodes;
		var dlObj={};
		var intgqjdbjid=null,chrlx="",chrjdmc="",childList=[];
		var dlDiv=item[0];
		var dlDivNodes=dlDiv.childNodes;
		for(var j=0;j<dlDivNodes.length;j++){
			if(dlDivNodes[j].name=="intgqjdbjid"){
				intgqjdbjid=dlDivNodes[j].value||null;
			}
			if(dlDivNodes[j].name=="chrlx"){
				chrlx=dlDivNodes[j].value||"";
			}
			if(dlDivNodes[j].name=="chrjdmc"){
				chrjdmc=dlDivNodes[j].value||"";
			}
		}
		
		var twoDiv=item[1];
		var twoUlNodes=twoDiv.childNodes;
		for(var j=0;j<twoUlNodes.length;j++){
			var twoUl=twoUlNodes[j];
			var twoLiNodes=twoUl.childNodes;
			var twoObj={};
			for(var k=0;k<twoLiNodes.length;k++){
				var twoLi=twoLiNodes[k];
				var twoLiNodesDom=twoLi.childNodes;
				for(var m=0;m<twoLiNodesDom.length;m++){
					if(twoLiNodesDom[m].name=="intgqjdbjid"){
						twoObj["id"]=twoLiNodesDom[m].value||null;
					}
					if(twoLiNodesDom[m].name=="intxh"){
						twoObj["intxh"]=twoLiNodesDom[m].value||null;
					}
					if(twoLiNodesDom[m].name=="chrlx"){
						twoObj["chrlx"]=twoLiNodesDom[m].value||"";
					}
					if(twoLiNodesDom[m].name=="chrjdmc"){
						twoObj["chrjdmc"]=twoLiNodesDom[m].value||"";
					}
					if(twoLiNodesDom[m].name=="dtmjhwcsj"){
						twoObj["dtmjhwcsj"]=twoLiNodesDom[m].value||null;
					}
				}
			}
			childList.push(twoObj);
		}
		dlObj["id"]=intgqjdbjid;
		dlObj["chrlx"]=chrlx;
		dlObj["chrjdmc"]=chrjdmc;
		dlObj["childList"]=childList;
		arr.push(dlObj);
	}
	return JSON.stringify(arr);
}

//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:timenodeListApiPath,
		type:"post",
		async:false,
		data:{
			gqjdbjid:gqjdbjid,
			xmid:intxmid,
			jdlx:chrjdlx
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
	var model = {list:[]};
	return model;
}

function save(){
	var data = {};
	data["xmid"]=intxmid;
	data["jdlx"]=chrjdlx;
	if(gqjdbjid&&pageData[0].intfjdid){
		data["gqjdbjJson"]=getOneJson();
	}else{
		data["gqjdbjJson"]=getGqjdbjJson();
	}
	
	data["deleteGqjdbjIds"]=(deleteGqjdbjIds.length>0?deleteGqjdbjIds.substring(0,deleteGqjdbjIds.length-1):deleteGqjdbjIds);
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
}

function outPage(){
	toUrl("project_timenode_comparison.html");
}

function getOneJson(){
   var oneUl=document.getElementsByName("oneUl");
   if(!(oneUl&&oneUl.length>0))return JSON.stringify([]);
   var intgqjdbjid=document.getElementById("intgqjdbjid").value;
   var intxh=document.getElementById("intxh").value;
   var chrjdmc=document.getElementById("chrjdmc").value;
   var dtmjhwcsj=document.getElementById("dtmjhwcsj").value;
   var oneObj={};
   oneObj.id=intgqjdbjid;
   oneObj.intxh=intxh;
   oneObj.chrjdmc=chrjdmc;
   oneObj.dtmjhwcsj=dtmjhwcsj;
   var oneArr=[];
   oneArr.push(oneObj);
   return JSON.stringify(oneArr);
}