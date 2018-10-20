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
		url:xmzfxcyzxysApiDetail,
		type:"post",
		async:false,
		data:{
			xmZfxcyzxysId:obj.id
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