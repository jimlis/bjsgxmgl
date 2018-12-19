showEdit();
var obj=getRequest();
var intxmid = getCookie("id");
var id=obj.id||"";
window.onload= function(){
	var pageData = getPageData();
	//数据绑定
	var vue = new Vue({
		el: '#app',
		data: {list:pageData},
		methods: {
			addGhzb:function(){
				var ghzbDiv=document.createElement("div");
				ghzbDiv.setAttribute("name","ghzbDiv");
				ghzbDiv.className="mui-media mui-col-xs-12 mui-col-sm-12";
				ghzbDiv.style="border-bottom:solid 1px dodgerblue ;padding: 11px 15px;";
				ghzbDiv.innerHTML=
					'<input class="bj-input" name="id" type="hidden"></input>'+
					'<input class="bj-input" name="chrzbmc"  type="text" placeholder="请输入指标"></input>'+
                    '<input class="bj-input" name="chrzbz" type="text" placeholder="请输入指标值"></input>'+
                    '<input class="bj-input" name="chrzdw" type="text" placeholder="请输入值单位"></input>'+
                    '<button class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="delGhzb(\'\',this)">删除</button>';
                var ghzb=document.getElementById("ghzb");
                ghzb.appendChild(ghzbDiv);
			},
			v_delGhzb:function(ghzbid,event){
				delGhzb(ghzbid,event.target);
			}
		}
	});
}

var deleteGhzbIds="";
function delGhzb(ghzbid,obj){
	if(ghzbid){
		deleteGhzbIds+=ghzbid+",";
	}
	obj.parentNode.remove();
}


//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:targetDataListApiPath,
		type:"post",
		async:false,
		data:{
			xmid:intxmid
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
}

function getGhzbJson(){
	var ghzbDivDoms=document.getElementsByName("ghzbDiv");
	var datas=[];
	for(var index=0 ;index<ghzbDivDoms.length;index++ ){
		var item = ghzbDivDoms[index].childNodes;
		var id,chrzbmc,chrzbz,chrzdw;
		for(i in item){
			if(item[i].name=="id"){
				id=item[i].value||null;
			}
			if(item[i].name=="chrzbmc"){
				chrzbmc=item[i].value||"";
			}
			if(item[i].name=="chrzbz"){
				chrzbz=item[i].value||"";
			}
			if(item[i].name=="chrzdw"){
				chrzdw=item[i].value||"";
			}
		}
		var a = {
			id:id,
			chrzbmc:chrzbmc,
			chrzbz:chrzbz,
			chrzdw:chrzdw
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
	data["deleteGhzbIds"]=(deleteGhzbIds.length>0?deleteGhzbIds.substring(0,deleteGhzbIds.length-1):deleteGhzbIds);
    data["ghzbJson"]=getGhzbJson();
	$bjAjax({
		url:targetDataSavePath,
		type:"post",
		data:data,
		success:function(data){
			console.log(data);
			bjToast("保存成功！",function(){
				toUrl("project_basis_targetData.html?id="+intxmid);
			});
		}
	})
})
}

//更新
function edit(){
	toUrl("project_basis_targetData_edit.html?id="+intxmid);
}
function outPage(){
	toUrl("project_basis_list.html");
}