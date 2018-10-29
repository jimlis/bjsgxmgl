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
		  	var chrdwlxmc = item.chrdwlxmc;
		  	var chrdwmc = item.chrdwmc;
			var inthtje = item.inthtje||"";
			mui("#gw")[0].innerHTML +=`
				<h5 class="bj-title2-font">`+chrdwlxmc+`：<span class="bj-p-gray-font">`+chrdwmc+`</span>`+
				`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同金额：<span class="bj-p-gray-font">`+inthtje+`</span>`+
				`</h5>`;
		})
		var two=data["2"]||[];
		mui.each(two,function(index,item){
		  	var chrdwlxmc = item.chrdwlxmc;
		  	var chrdwmc = item.chrdwmc;
			var inthtje = item.inthtje||"";
			mui("#sg")[0].innerHTML +=`
				<h5 class="bj-title2-font">`+chrdwlxmc+`：<span class="bj-p-gray-font">`+chrdwmc+`</span>`+
				`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同金额：<span class="bj-p-gray-font">`+inthtje+`</span>`+
				`</h5>`;
		})
		var three=data["3"]||[];
		mui.each(three,function(index,item){
		  	var chrdwlxmc = item.chrdwlxmc;
		  	var chrdwmc = item.chrdwmc;
			var inthtje = item.inthtje||"";
			mui("#qt")[0].innerHTML +=`
				<h5 class="bj-title2-font">`+chrdwlxmc+`：<span class="bj-p-gray-font">`+chrdwmc+`</span>`+
				`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同金额：<span class="bj-p-gray-font">`+inthtje+`</span>`+
				`</h5>`;
		})
	}
});

function edit(){
	toUrl("project_basis_buildUnits_edit.html");
}
function outPage(){
	toUrl("project_basis_list.html");
}