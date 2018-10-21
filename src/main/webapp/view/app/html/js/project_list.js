var intxmlx = getCookie("xmlx")||"";
var xmlxmc='';
if(intxmlx==1){
	xmlxmc = "PMC项目";
}else{
	xmlxmc = "EPC项目";
}
window.onload=function(){
	var nav = document.getElementById("title-scrollT");
	nav.innerHTML = `
		<a href="../main.html">博建施工项目管理&nbsp;/</a>
		<a href="#" style="color: #fff;">`+xmlxmc+`列表</a>
	`;
}
$bjAjax({
	url:projectApiBase,
	data:{xmlx:intxmlx},
	type:"post",
	success:function(data){
		if(data.length==0){
			bjToast("没有项目数据");
			return;
		}
		mui.each(data,function(index,item){
		  	var chrxmmc = item.chrxmmc;
		 	var id = item.id;
			mui(".mui-content .mui-table-view")[0].innerHTML +=`
				<li class="mui-table-view-cell" onclick="openNext(`+id+`,'`+chrxmmc+`')">`+chrxmmc+`</li>
			`;
		})
	}
});


/**
 * 跳转项目详情列表
 */
var allcookies="";
function openNext(id,chrxmmc){
	 allcookies = document.cookie;
		setCookie("chrxmmc",chrxmmc);
		setCookie("id",id);
		toUrl("project_detail_list.html");
}
function outPage(){
	toUrl("../main.html");
}