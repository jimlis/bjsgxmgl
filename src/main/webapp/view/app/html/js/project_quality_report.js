
//初始化必要条件
window.onload=function(){
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
				mui("#" + idName)[0].innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
				return;
			}
			var html="";
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq;
			  	var id = item.id;
			  	var chrqxwz = item.chrqxwz||"";
			  	html +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`&nbsp;&nbsp;&nbsp;&nbsp;`+chrqxwz+`</li>`;
			})
			mui("#" + idName)[0].innerHTML=html;
			//mui("#" + idName)[0].style.display="none";
		}
	})
}

function outPage(){
	toUrl("project_detail_list.html");
}