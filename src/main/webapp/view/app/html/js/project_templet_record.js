//初始化必要条件
window.onload=function(){
	$bjAjax({
		url:tempRecodeListApiPath,
		type:"post",
		data:{
			xmid:getCookie("id"),
		},
		success:function(array){
			var tjtemp,jdtemp,zxtemp,yltemp,qttemp;
			var tjtempnum=0,jdtempnum=0,zxtempnum=0,yltempnum=0,qttempnum=0;
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq||"";
			  	var intyblx = item.intyblx||"";
			  	var id = item.id;
			  	if(intyblx=="1"){
			  		tjtemp = true;
			  		tjtempnum++;
			  		mui("#tjtemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmgxrq+`</li>`;
			  	}
			  	if(intyblx=="2"){
			  		jdtemp = true;
			  		jdtempnum++;
			  		mui("#jdtemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmgxrq+`</li>`;
			  	}
			  	if(intyblx=="3"){
			  		zxtemp = true;
			  		zxtempnum++;
			  		mui("#zxtemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmgxrq+`</li>`;
			  	}
			  	if(intyblx=="4"){
			  		yltemp = true;
			  		yltempnum++;
			  		mui("#yltemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmgxrq+`</li>`;
			  	}
			  	if(intyblx=="5"){
			  		qttemp = true;
			  		qttempnum++;
			  		mui("#qttemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmgxrq+`</li>`;
			  	}
			});
			if(!tjtemp){
			  		mui("#tjtemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
		  	}
		  	if(!jdtemp){
		  		mui("#jdtemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
		  	}
		  	if(!zxtemp){
		  		mui("#zxtemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
		  	}
		  	if(!yltemp){
		  		mui("#yltemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
		  	}
		  	if(!qttemp){
		  		mui("#qttemp")[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" )">没有相关数据，请上传数据</li>`;
		  	}
		  	mui("#tjtempspan")[0].innerText=tjtempnum;
		  	mui("#jdtempspan")[0].innerText=jdtempnum;
		  	mui("#zxtempspan")[0].innerText=zxtempnum;
		  	mui("#yltempspan")[0].innerText=yltempnum;
		  	mui("#qttempspan")[0].innerText=qttempnum;
		}
	})
	

	tyclClick("#list");
}
function openNext(id){
	toUrl("project_templet_record_detail.html?id="+id);
}
function openAdd(){
	toUrl("project_templet_record_add.html");
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}


