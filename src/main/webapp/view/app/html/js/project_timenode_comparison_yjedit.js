var obj=getRequest();
var intxmid = getCookie("id");
var gqjdbjid=obj.gqjdbjid||"";
var chrjdlx=obj.chrjdlx||"";
var pageData ={};
var num=0;
window.onload= function(){
    pageData =gqjdbjid?getPageData():[];
    num=pageData?pageData.length:0;
	//数据绑定
	var vue = new Vue({
		el: '#app',
		data: {list:pageData,gqjdbjid:gqjdbjid},
		methods: {
			addNode:function(){
				var nodeDiv=document.createElement("div");
				nodeDiv.setAttribute("name","nodeDiv");
				nodeDiv.className="mui-media mui-col-xs-12 mui-col-sm-12";
				nodeDiv.style="border-bottom:solid 1px dodgerblue ;padding: 11px 15px;";
				nodeDiv.innerHTML=
					'<input class="bj-input" name="id" type="hidden"></input>'+
					'<input class="bj-input" name="intxh"  type="number" placeholder="请输入序号"></input>'+
                    '<input class="bj-input" name="chrjdmc" type="text" placeholder="请输入名称"></input>'+
                    '<input class="bj-input" name="dtmjhwcsj" data-options=\'{"type":"date"}\' id="dtmjhwcsj'+num+'"  readonly="readonly" type="text" placeholder="请输入计划完成时间"></input>'+
                    '<button class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="delNode(\'\',this)">删除</button>';
                var node=document.getElementById("node");
                node.appendChild(nodeDiv);
                dtPicker('#dtmjhwcsj'+num);
                num++;
			},
			v_delNode:function(nodeid,event){
				delNode(nodeid,event.target);
			},
			datePicker:function(index,name){
				vueArrDtPicker(pageData,index,name);
			}
		}
	});
}

var deleteGqjdbjIds="";
function delNode(nodeid,obj){
	if(nodeid){
		deleteGqjdbjIds+=nodeid+",";
	}
	obj.parentNode.remove();
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


function getGqjdbjJson(){
	var ghzbDivDoms=document.getElementsByName("nodeDiv");
	var datas=[];
	for(var index=0 ;index<ghzbDivDoms.length;index++ ){
		var item = ghzbDivDoms[index].childNodes;
		var id,chrzbmc,chrzbz,chrzdw;
		for(i in item){
			if(item[i].name=="id"){
				id=item[i].value||null;
			}
			if(item[i].name=="intxh"){
				intxh=item[i].value||null;
			}
			if(item[i].name=="chrjdmc"){
				chrjdmc=item[i].value||"";
			}
			if(item[i].name=="dtmjhwcsj"){
				dtmjhwcsj=item[i].value||"";
			}
		}
		var a = {
			id:id,
			intxh:intxh,
			chrjdmc:chrjdmc,
			dtmjhwcsj:dtmjhwcsj
		}
		datas.push(a);
	}
	var dataJson = JSON.stringify(datas);
	return dataJson;
}

function save(){
isSure(function(){
	var data = {};
	data["xmid"]=intxmid;
	data["jdlx"]=chrjdlx;
	data["gqjdbjJson"]=getGqjdbjJson();
	data["deleteGqjdbjIds"]=(deleteGqjdbjIds.length>0?deleteGqjdbjIds.substring(0,deleteGqjdbjIds.length-1):deleteGqjdbjIds);
	$bjAjax({
		url:timenodeSaveBatchPath,
		type:"post",
		data:data,
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_timenode_comparison_yjedetails.html?chrjdlx="+chrjdlx);
			});
		}
	})
})
}


function outPage(){
	toUrl("project_timenode_comparison.html");
}