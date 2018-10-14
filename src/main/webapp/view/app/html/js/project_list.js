var obj = getRequest();
$bjAjax({
	url:projectApiBase,
	data:{xmlx:obj.xmlx},
	type:"post",
	success:function(data){
		if(data.length==0){
			bjToast("没有项目数据");
			return;
		}
		mui.each(data,function(index,item){
		  	var chrxmmc = item.chrxmmc;
		 	var id = item.id;
		 	var intdjrbm = item.intdjrbm;
		 	var intdjrid = item.intdjrid;
		 	var chrdjrmc = item.chrdjrmc;
		 	var intds = item.intds;
		 	setCookie("chrxmmc",chrxmmc);
		 	setCookie("id",id);
		 	setCookie("intdjrbm",intdjrbm);
		 	setCookie("intdjrid",intdjrid);
		 	setCookie("chrdjrmc",chrdjrmc);
		 	setCookie("intds",intds);
			mui(".mui-content .mui-table-view")[0].innerHTML +=`
				<li class="mui-table-view-cell" onclick="openNext(`+id+`)">`+chrxmmc+`</li>
			`;
		})
	}
});


/**
 * 跳转项目详情列表
 */
function openNext(id){
		toUrl("project_detail_list.html");
}