
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
			/*
			if(array.length==0){
				mui("#" + idName)[0].innerHTML='没有相关数据，请上传数据';
				return;
			}*/
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq;
			  	var id = item.id;
				mui("#" + idName)[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openDetails("+id+")">`+dtmgxrq+`</li>`;
			})
			
			//mui("#" + idName)[0].style.display="none";
		}
	})
}
