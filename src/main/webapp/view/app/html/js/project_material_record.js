
var xmid = getCookie("id");
//初始化必要条件
window.onload=function(){
	showEdit();
	$bjAjax({
		url:materialApiList,
		type:"post",
		data:{
			xmid:xmid
		},
		success:function(array){
			var tjl,jdl,zxl,yll,qtl;
			var tjlnum=0,jdlnum=0,zxlnum=0,yllnum=0,qtlnum=0;
			
			var tjlGrenum=0,jdlGrenum=0,zxlGrenum=0,yllGrenum=0,qtlGrenum=0;
			var tjlRednum=0,jdlRednum=0,zxlRednum=0,yllRednum=0,qtlRednum=0;
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq||"";
			  	var intclyblx = item.intclyblx||"";
			  	var chrybmc = item.chrybmc||"";
			  
			  	var id = item.id;
			  	var ztid = item.ztid;
			  	var chrspzt = item.chrspzt;
			  	var yjHtml="";
			  	if(intclyblx=="1"){
			  		if(chrspzt!='wwc'){
			  			tjlGrenum++;
				    	yjHtml = "<span class='img-g'></span>";
				    }else {//未完成就是正在审批
				    	tjlRednum++;
				    	yjHtml = "<span class='img-r'></span>";
				    }
			  		tjl = true;
			  		tjlnum++;
			  		mui("#tjl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="2"){
			  		if(chrspzt!='wwc'){
			  			jdlGrenum++;
				    	yjHtml = "<span class='img-g'></span>";
				    }else {
				    	jdlRednum++;
				    	yjHtml = "<span class='img-r'></span>";
				    }
			  		jdl = true;
			  		jdlnum++;
			  		mui("#jdl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="3"){
			  		if(chrspzt!='wwc'){
			  			zxlGrenum++;
				    	yjHtml = "<span class='img-g'></span>";
				    }else {
				    	zxlRednum++;
				    	yjHtml = "<span class='img-r'></span>";
				    }
			  		zxl = true;
			  		zxlnum++;
			  		mui("#zxl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="4"){
			  		if(chrspzt!='wwc'){
			  			yllGrenum++;
				    	yjHtml = "<span class='img-g'></span>";
				    }else {
				    	yllRednum++;
				    	yjHtml = "<span class='img-r'></span>";
				    }
			  		yll = true;
			  		yllnum++;
			  		mui("#yll")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
			  	}
			  	if(intclyblx=="5"){
			  		if(chrspzt!='wwc'){
			  			qtlGrenum++;
				    	yjHtml = "<span class='img-g'></span>";
				    }else {
				    	qtlRednum++;
				    	yjHtml = "<span class='img-r'></span>";
				    }
			  		qtl = true;
			  		qtlnum++;
			  		mui("#qtl")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;`+chrybmc+`</li>`;
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
				
			  	mui("#tjlSpan")[0].innerText=tjlnum;
			  	mui("#tjlGenSpan")[0].innerText=tjlGrenum;
			  	mui("#tjlRedSpan")[0].innerText=tjlRednum;
			  	
			  	mui("#jdlSpan")[0].innerText=jdlnum;
				mui("#jdlGenSpan")[0].innerText=jdlGrenum;
			  	mui("#jdlRedSpan")[0].innerText=jdlRednum;
			  	
			  	mui("#zxlSpan")[0].innerText=zxlnum;
			  	mui("#zxlGenSpan")[0].innerText=zxlGrenum;
			  	mui("#zxlRedSpan")[0].innerText=zxlRednum;
			  	
			  	mui("#yllSpan")[0].innerText=yllnum;
				mui("#yllGenSpan")[0].innerText=yllGrenum;
			  	mui("#yllRedSpan")[0].innerText=yllRednum;
			  	
			  	mui("#qtlSpan")[0].innerText=qtlnum;
			  	mui("#qtlGenSpan")[0].innerText=qtlGrenum;
			  	mui("#qtlRedSpan")[0].innerText=qtlRednum;
			  	
			  	tyclClick("#list");
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
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}