showEdit();
var obj=getRequest();
var id=obj.id||"";
var intxmid = getCookie("id");
window.onload= function(){
	var pageData = getPageData();
	//数据绑定
	var vue = new Vue({
		el: '#app',
		data: {list:pageData},
		methods: {
			
		}
	});
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:targetDataListApiPath,
		type:"post",
		async:false,
		data:{
			xmid:intxmid
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
}
//更新
function edit(){
	toUrl("project_basis_targetData_edit.html?id="+intxmid);
}
function outPage(){
	toUrl("project_basis_list.html");
}