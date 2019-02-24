var isEdit=showEdit1();
var isCk=hasPermission("bj:ckje");
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
			var inthtje = clJe(item.inthtje?item.inthtje.toFixed(2):"");
			mui("#gwdwNr")[0].innerHTML +=`<tr>
						      <td data-label="单位类型名称">`+chrdwlxmc+`</td>
						      <td data-label="单位名称">`+chrdwmc+`</td>
						      <td data-label="合同金额"><span >`+inthtje+`</span></td>
						    </tr>`;
		})
		var two=data["2"]||[];
		mui.each(two,function(index,item){
		  	var chrdwlxmc = item.chrdwlxmc;
		  	var chrdwmc = item.chrdwmc;
			var inthtje = clJe(item.inthtje?item.inthtje.toFixed(2):"");
			mui("#sgdwNr")[0].innerHTML +=`<tr>
						      <td data-label="单位类型名称">`+chrdwlxmc+`</td>
						      <td data-label="单位名称">`+chrdwmc+`</td>
						      <td data-label="合同金额"><span >`+inthtje+`</span></td>
						    </tr>`;
		})
		var three=data["3"]||[];
		mui.each(three,function(index,item){
		  	var chrdwlxmc = item.chrdwlxmc;
		  	var chrdwmc = item.chrdwmc;
			var inthtje =clJe(item.inthtje?item.inthtje.toFixed(2):"");
			mui("#qtdwNr")[0].innerHTML +=`<tr>
						      <td data-label="单位类型名称">`+chrdwlxmc+`</td>
						      <td data-label="单位名称">`+chrdwmc+`</td>
						      <td data-label="合同金额"><span >`+inthtje+`</span></td>
						    </tr>`;
		})
	}
});

function clJe(je){
	return (isEdit||isCk)?je:"*";
}

function edit(){
	toUrl("project_basis_buildUnits_edit.html");
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_basis_list.html");
}