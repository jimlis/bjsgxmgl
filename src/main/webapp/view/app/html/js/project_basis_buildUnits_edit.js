var xmid = getCookie("id");
var dwmdArr=[{"text":"顾问单位名单","value":"1"},{"text":"施工单位名单","value":"2"},{"text":"其他工作单位名单","value":"3"}];
//showEdit();
var bjje=hasPermission("bj:bjje");
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

see();


relPicker("intlxmdxs",dwmdArr,"intlxmd");

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

function getDwmdmcById(dwmdid){
	if(dwmdid){
		for(i in dwmdArr){
			if(dwmdArr[i].value==dwmdid){
				return dwmdArr[i].text;
			}
		}
	}
	return "";
}

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
				console.log(item);
			  	var chrdwlxmc = item.chrdwlxmc||"";
			  	var intdwlxid = item.intdwlxid||"";
			  	var intlxmd = item.intlxmd||"";
			  	var intlxmdmc = getDwmdmcById(item.intlxmd)||"";
		  		var chrdwmc = item.chrdwmc||"";
		  		var inthtje = item.inthtje||"";
		  		if(bjje){
		  			mui("#yjdwNr")[0].innerHTML +=`<tr>
		  			  <td data-label="单位类型">`+intlxmdmc+`</td>
				      <td data-label="单位类型">`+chrdwlxmc+`</td>
				      <td data-label="单位名称">`+chrdwmc+`</td>
				      <td data-label="合同金额">`+inthtje+`</td>
				      <td data-label="操作"><span style="" onclick="updateyjdw(`+(item.id||"")+`,'`+chrdwmc+`','`+chrdwlxmc+`','`+intdwlxid+`','`+intlxmd+`','`+inthtje+`',this)" class="bj-red bj-hand">修改</span>
				      <span style="" onclick="delyjdw(`+(item.id||"")+`,'`+chrdwmc+`',this)" class="bj-red bj-hand">删除</span></td>
				    </tr>`;
		  		}else{
		  			mui("#yjdwNr")[0].innerHTML +=`<tr>
		  			  <td data-label="单位类型">`+intlxmdmc+`</td>
				      <td data-label="单位类型">`+chrdwlxmc+`</td>
				      <td data-label="单位名称">`+chrdwmc+`</td>
				      <td data-label="合同金额">*</td>
				      <td data-label="操作"><span style="" onclick="updateyjdw(`+(item.id||"")+`,'`+chrdwmc+`','`+chrdwlxmc+`','`+intdwlxid+`','`+intlxmd+`','`+inthtje+`',this)" class="bj-red bj-hand">修改</span>
				      <span style="" onclick="delyjdw(`+(item.id||"")+`,'`+chrdwmc+`',this)" class="bj-red bj-hand">删除</span></td>
				    </tr>`;
		  		}
		  		
			})
		//	mui("#yjdwDiv")[0].innerHTML =htmls;
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
/**
 * 更新已建单位
 * @param id 主键id
 * @param chrdwmc 单位名称
 * @param chrdwlxmc 单位类型名称
 * @param intdwlxid 单位类型
 * @param intlxmd 名单类型
 * @param inthtje 合同金额
 * @param obj 
 * @returns
 */
function updateyjdw(id,chrdwmc,chrdwlxmc,intdwlxid,intlxmd,inthtje,obj){
	document.getElementById("id").value=id||"";
	document.getElementById("chrdwmc").value=chrdwmc||"";
	document.getElementById("intdwlxidxs").value=chrdwlxmc||"";
	document.getElementById("intdwlxid").value=intdwlxid||"";
	document.getElementById("intlxmd").value=intlxmd||"";
	document.getElementById("intlxmdxs").value=getDwmdmcById(intlxmd)||"";
	document.getElementById("inthtje").value=inthtje||"";
	document.body.scrollTop = document.documentElement.scrollTop = 0
}

//保存
function save(){
isSure(function(){

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

});
}
function outPage(){
	toUrl("project_basis_list.html");
}