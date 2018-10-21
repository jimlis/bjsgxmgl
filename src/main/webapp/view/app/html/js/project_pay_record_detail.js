var obj = getRequest();
var id=obj.id||"";
//初始化显示数据
window.onload = function(){
	//得到数据
	var pageData = getPageData();
	
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData
	});
	
	if(id){
		initFileList("bj_xm_gckyzfqk",id,"1","fileIds","file-list",false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:payApiDetail,
		type:"post",
		async:false,
		data:{
			xmGckyzfqkId:id
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
}

function edit(){
	toUrl("project_pay_record_add.html?id="+id);
}


function outPage(){
	toUrl("project_pay_record.html");
}