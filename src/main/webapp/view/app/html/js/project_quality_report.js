
//初始化必要条件
window.onload=function(){
	showEdit();
	showQuaReportList('1','tjQua');
	showQuaReportList('2','jdQua');
	showQuaReportList('3','zxQua');
	showQuaReportList('4','ylQua');
	showQuaReportList('5','qtQua');
	tyclClick("#list");
}
function openDetails(id){
	toUrl("project_quality_report_details.html?id="+id);
}
function openAdd(){
	toUrl("project_quality_report_add.html");
}


function showQuaReportList(temptype,idName){
	
	$bjAjax({
		url:quaRecodeListApiPath,
		type:"post",
		data:{
			xmid:getCookie("id"),
			qxlx:temptype
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
			  	var dtmzgwcrq = item.dtmzgwcrq||"";
			  	var chrqxms = item.chrqxms||"";
			  	var yjHtml="";
			  	if(dtmzgwcrq!=""){
			    	yjHtml = "<span class='img-g'></span>";
			    }else {
			    	yjHtml = "<span class='img-r'></span>";
			    }
			  	var id = item.id;
			  	var chrqxwz = item.chrqxwz||"";
			  	html +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+yjHtml+`&nbsp;&nbsp;&nbsp;&nbsp;`+dtmgxrq+`&nbsp;&nbsp;&nbsp;&nbsp;`+chrqxwz+`&nbsp;&nbsp;&nbsp;&nbsp;`+chrqxms+`</li>`;
			})
			mui("#" + idName+"Span")[0].innerText=array.length;
			mui("#" + idName)[0].innerHTML=html;
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