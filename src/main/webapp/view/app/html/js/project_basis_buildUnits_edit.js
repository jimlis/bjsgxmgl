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
		 	var id = item.id+"";
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
			types:getCookie("id")
		},
		success:function(data){
			mui.each(data,function(index,item){
			  	var dwlx = item.name;
			 	var dwmc = item.id;
				mui(".mui-content .mui-table-view .mui-collapse-content")[0].innerHTML +=`
					<h5 class="bj-title2-font">`+dwlx+`：<span class="bj-p-gray-font">`+dwmc+`</span></h5>
				`;
			})
		}
	})
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
