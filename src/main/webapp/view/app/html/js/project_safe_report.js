
window.onload=function(){
	showEdit();
	showList('aqjcjl');
	tyclClick("#list");
}
/**
 *详情
 */
function openDetails(id){
    var address = "project_saft_report_details.html?id="+id;
    toUrl(address);
}

/**
 *新增
 */
function openAdd(){
    var address = "project_saft_report_add.html";
    toUrl(address);
}

function showList(idName){

	$bjAjax({
		url:safeReportListApiPath,
		type:"post",
		data:{
			xmid:getCookie("id")
		},
		success:function(array){
			
			if(array.length==0){
				mui("#" + idName+"Span")[0].innerText=0;
				mui("#" + idName)[0].innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
				return;
			}
			var html="";
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq;
			  	var dtmwczgrq = item.dtmwczgrq||"";
			  	var chraqwtbs = item.chraqwtbs||"";
			  	var yjHtml="";
				if(dtmwczgrq!=""){
			    	yjHtml = "<span class='img-g'></span>";
			    }else {
			    	yjHtml = "<span class='img-r'></span>";
			    }
			  	var id = item.id;
			  	var chraqwtwz=item.chraqwtwz||'';
			  	html+=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;&nbsp;&nbsp;`+chraqwtwz+`&nbsp;&nbsp;&nbsp;&nbsp;`+chraqwtbs+`</li>`;
			  	
			})
			mui("#" + idName+"Span")[0].innerText=array.length;
			mui("#" + idName)[0].innerHTML =html;
			//tyclClick("#list");
			//mui("#" + idName)[0].style.display="none";
		}
	})
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}