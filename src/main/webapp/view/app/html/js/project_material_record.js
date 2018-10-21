
var xmid = getCookie("id");
//初始化必要条件
window.onload=function(){
	bjConsole(123);
	$bjAjax({
		url:materialApiList,
		type:"post",
		data:{
			xmid:xmid
		},
		success:function(array){
			var tjl,jdl,zxl,yll,qtl
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq||"";
			  	var intclyblx = item.intclyblx||"";
			  	var chrybmc = item.chrybmc||"";
			  	var id = item.id;
			  	if(intclyblx=="1"){
			  		tjl = true;
			  		mui("#tjl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="2"){
			  		jdl = true;
			  		mui("#jdl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="3"){
			  		zxl = true;
			  		mui("#zxl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="4"){
			  		yll = true;
			  		mui("#yll")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="5"){
			  		qtl = true;
			  		mui("#qtl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			});
			if(!tjl){
			  		mui("#tjl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
			  	}
			  	if(!jdl){
			  		mui("#jdl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
			  	}
			  	if(!zxl){
			  		mui("#zxl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
			  	}
			  	if(!yll){
			  		mui("#yll")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
			  	}
			  	if(!qtl){
			  		mui("#qtl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" )">没有相关数据，请上传数据</li>`;
			  	}
		}
	})
}

/**
 *详情
 */
function openDetails(id){
    var address = "project_material_record_details.html?id="+id;
    toUrl(address);
}

/**
 *新增
 */
function add(){
    var address = "project_material_record_add.html";
    toUrl(address);
}
function outPage(){
	toUrl("project_detail_list.html");
}