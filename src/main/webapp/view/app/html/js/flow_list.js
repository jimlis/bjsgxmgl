$bjAjax({
	url:fileApiPath+"wdlist",
	data:{
		type:'4'
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		var array = data;
		console.log(array);
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