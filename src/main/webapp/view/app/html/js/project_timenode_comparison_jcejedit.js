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
    pageData =gqjdbjid?getPageData():getPageData();
    index=pageData?pageData.length:0;
		//数据绑定
	var vue = new Vue({
			el: '#app',
			data: {list:pageData,gqjdbjid:gqjdbjid,msg:msg,chrjdlx:chrjdlx},
			methods: {
				addDl: function () {
					addOne({});
				},
				datePicker: function (name) {
					vueDtPicker(pageData,name);
				}
			}
	});
	
}

function addOne(data){
	    	data=data||{};
	    var id=data.id||"";
	    var intxh=data.intxh||"";
	    var chrjdmc=data.chrjdmc||"";
	    var dtmjhwcsj=data.dtmjhwcsj||"";
	
	    var lxDiv=document.createElement("div");
			lxDiv.setAttribute("name","oneDiv");
			lxDiv.style.cssText="padding-top: 4px;";
		var div=document.getElementById("div");
			div.appendChild(lxDiv);
	var ulDom=document.createElement("ul");
		ulDom.className='mui-table-view bj-background-inherit';
	
	var delBtnLi=document.createElement("li");
		delBtnLi.style.cssText="padding: 5px 20px 0px 20px;";
		delBtnLi.innerHTML='<button class="mui-btn mui-btn-danger" type="button"  onclick="delOne(\''+id+'\',this,event)">删除栋楼</button>';
	
	var xhLi=document.createElement("li");
		xhLi.style.cssText="padding: 5px 20px 0px 20px;";
		xhLi.innerHTML='<input class="bj-input" name="intgqjdbjid" value="'+id+'" type="hidden"></input>'+
		    '<input class="bj-input" name="chrlx" value="0" type="hidden"></input>'+
			'序号：<input class="bj-input" size="4" length="4" name="intxh" value="'+intxh+'" type="number"></input>';
		
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
		lxDiv.appendChild(ulDom);
		
		dtPicker('#dtmjhwcsj'+index);
		
		index++;
}

var deleteGqjdbjIds="";
function delOne(gqjdbjId,obj){
	if(gqjdbjId){
		deleteGqjdbjIds+=gqjdbjId+",";
	}
	obj.parentNode.parentNode.parentNode.remove();
}

function getGqjdbjJson(){
	var oneDivDom=document.getElementsByName("oneDiv");
	var arr=[];
	for(var index=0;index<oneDivDom.length;index++){
		var item = oneDivDom[index].childNodes;
		var oneUl=item[0];
		var oneLiNodes=oneUl.childNodes;
		var oneObj={};
		for(var j=0;j<oneLiNodes.length;j++){
			var oneLi=oneLiNodes[j];
			var oneLiNodesAll=oneLi.childNodes;
			
			for(var k=0;k<oneLiNodesAll.length;k++){
				var oneLiNodesDom=oneLiNodesAll[k];
					if(oneLiNodesDom.name=="intgqjdbjid"){
						oneObj["id"]=oneLiNodesDom.value||null;
					}
					if(oneLiNodesDom.name=="intxh"){
						oneObj["intxh"]=oneLiNodesDom.value||null;
					}
					if(oneLiNodesDom.name=="chrlx"){
						oneObj["chrlx"]=oneLiNodesDom.value||"";
					}
					if(oneLiNodesDom.name=="chrjdmc"){
						oneObj["chrjdmc"]=oneLiNodesDom.value||"";
					}
					if(oneLiNodesDom.name=="dtmjhwcsj"){
						oneObj["dtmjhwcsj"]=oneLiNodesDom.value||null;
					}
			}
		}
		arr.push(oneObj);
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
isSure(function(){
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
				toUrl("project_timenode_comparison_jcejedetails.html?chrjdlx="+chrjdlx);
			});
		}
	})
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