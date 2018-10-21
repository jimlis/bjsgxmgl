$bjAjax({
	url:fileApiPath+"wdlist",
	data:{
		type:'3'
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
			var array = data;
			if(array.length==0){
				mui(".mui-content .mui-table-view-cell")[0].innerHTML='没有相关数据，请上传数据';
				return;
			}
			mui.each(array,function(index,item){
			  	var fileName = item.fileName;
			 	var id = item.id;
			 	var busType = item.busType;
				mui(".mui-content .mui-table-view")[0].innerHTML +="<li class=\"mui-table-view-cell\" onclick=\"openNext('"+id+"','"+fileName+"');\">"+fileName+"</li>";
			})
			mui(".mui-content .mui-table-view-cell")[0].style.display="none";
	},
});

/**
 * 打开文件详情
 */
function openNext(id,fileName){
    openFileByName(id,fileName);
}
function outPage(){
	toUrl("../main.html");
}