//初始化必要条件
window.onload=function(){
}
function openNext(id){
	toUrl("project_templet_record_detail.html?id="+id);
}
function openAdd(){
	toUrl("project_templet_record_add.html");
}


function showTempList(temptype,idName){
	
	$bjAjax({
		url:tempRecodeListApiPath,
		type:"post",
		data:{
			xmid:getCookie("id"),
			yblx:temptype
		},
		success:function(array){
			
			/*if(array.length==0){
				mui("#" + idName)[0].innerHTML='没有相关数据，请上传数据';
				return;
			}*/
			mui.each(array,function(index,item){
			  	var dtmgxrq = item.dtmgxrq;
			  	var id = item.id;
				mui("#" + idName)[0].innerHTML +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmgxrq+`</li>`;
			})
			
			//mui("#" + idName)[0].style.display="none";
		}
	})
}

