var xmid = getCookie("id");
$bjAjax({
	url:baseApiCjdw,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		if(!data)return;
		bjConsole(data);
		var chrcjdwmc = data.chrcjdwmc,
		chrdjdwmc = data.chrdjdwmc;
		document.getElementById("chrcjdwmc").innerText =chrcjdwmc;
		document.getElementById("chrdjdwmc").innerText =chrdjdwmc;
	}
});
$bjAjax({
	url:baseApiDwmd,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		var one=data["1"]||[];
		mui.each(one,function(index,item){
			bjConsole(item);
		  	var name = item.name;
		  	var lx = item.lx;
		 	var id = item.id;
			mui(".mui-content .mui-table-view")[0].innerHTML +="<li class=\"mui-table-view-cell\" onclick=\"openNext('"+lx+"','"+id+"','"+name+"');\">"+name+"</li>";
		})
		var two=data["2"]||[];
		mui.each(two,function(index,item){
		  	var name = item.name;
		  	var lx = item.lx;
		 	var id = item.id;
			mui(".mui-content .mui-table-view")[0].innerHTML +="<li class=\"mui-table-view-cell\" onclick=\"openNext('"+lx+"','"+id+"','"+name+"');\">"+name+"</li>";
		})
		var three=data["3"]||[];
		mui.each(three,function(index,item){
		  	var name = item.name;
		  	var lx = item.lx;
		 	var id = item.id;
			mui(".mui-content .mui-table-view")[0].innerHTML +="<li class=\"mui-table-view-cell\" onclick=\"openNext('"+lx+"','"+id+"','"+name+"');\">"+name+"</li>";
		})
	}
});

function edit(){
	toUrl("project_basis_buildUnits_edit.html");
}
