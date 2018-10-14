
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
			
			/*if(array.length==0){
				mui("#" + idName)[0].innerHTML='没有相关数据，请上传数据';
				return;
			}*/
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq;
			  	var id = item.id;
				mui("#" + idName)[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`</li>`;
			})
			
			//mui("#" + idName)[0].style.display="none";
		}
	})
}

