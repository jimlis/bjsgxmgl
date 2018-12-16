var obj = getRequest();
var intxmlx=obj.intxmlx||"";
setCookie("xmlx",intxmlx);
var chrxmlx;
if(intxmlx=='1'){
	chrxmlx = "PMC项目";
}else{
	chrxmlx = "EPC项目";
}
document.getElementById("xmlx").innerText=chrxmlx;
var xmlxmc='';

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
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("../main.html");
}