var xmid = getCookie("id");
$bjAjax({
	url:baseApiCjdw,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		if(!data)return;
		var chrcjdwmc = data.chrcjdwmc,
		chrdjdwmc = data.chrdjdwmc;
		document.getElementById("chrcjdwmc").value =chrcjdwmc;
		document.getElementById("chrdjdwmc").value =chrdjdwmc;
	}
});


relPicker("intlxmdxs",[{"text":"顾问单位名单","value":"1"},{"text":"施工单位名单","value":"2"},{"text":"其他工作单位名单","value":"3"}],"intlxmd");

$bjAjax({
	url:dictApiPath,
	type:"post",
	data:{
		types:"intdwlxid"
	},
	success:function(data){
		var dws = data.intdwlxid;
		var intdwlxidxs = new Array();
		mui.each(dws,function(index,item){
		  	var name = item.name;
		 	var id = item.value+"";
		 	var it = {"text":name,"value":id};
		 	intdwlxidxs[index]=it;
		});
		relPicker("intdwlxidxs",intdwlxidxs,"intdwlxid");
	}
});
//打开下拉列表
function see(){
	$bjAjax({
		url:baseApiYjdw,
		type:"post",
		data:{
			xmid:getCookie("id")
		},
		success:function(data){
			var htmls="";
			mui.each(data,function(index,item){
			  	var chrdwlxmc = item.chrdwlxmc||"";
		  		var chrdwmc = item.chrdwmc||"";
				htmls +=`
					<h5 class="bj-title2-font">`+chrdwlxmc+`：<span class="bj-p-gray-font">`+chrdwmc+`</span><span style="position: fixed; right: 10px;" onclick="delyjdw(`+(item.id||"")+`,'`+chrdwmc+`',this)" class="bj-red bj-hand">删除<span></h5>
				`;
			})
			mui(".mui-content .mui-table-view .mui-collapse-content")[0].innerHTML =htmls;
		}
	})
}

/**
 * 删除已建单位
 * @param id 主键id
 * @param chrdwmc 单位名称
 * @param obj 
 * @returns
 */
function delyjdw(id,chrdwmc,obj){
	if(id){
		mui.confirm('删除不可以恢复，确定删除"'+chrdwmc+'"单位？',"提示",['确定','取消'],function(data){
			var index=data.index;
			if(index==0){//确定
	            $bj_post_ajax({"url":dwmdApiDeleteById+ "/"+id,success:function (result) {
	            			obj.parentNode.remove();
	                }});
			}

		});
	}
}

//保存
function save(){
	var data = getFromData("myform");
	data["intxmid"] = getCookie("id");
	
	$bjAjax({
		url:baseApiCjdwSave,
		data:data,
		type:"post",
		success:function(data){
			bjConsole(data);
			bjToast("保存成功！",function(){
				toUrl("project_basis_buildUnits.html");
			});
		}
	});
}
function outPage(){
	toUrl("project_basis_list.html");
}