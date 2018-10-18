var obj=getRequest()
var id = obj.id||"";
var vue;
window.onload = function(){
	
	
	//判断是否更新；
	pageData = isUpdata()||'';
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData
	});
	
}

//判断是否更新
function isUpdata(){
	if(id){
		var result={};
		$bjAjax({
			url:progressSecByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdEcjgzxId:id
			},
			success:function(data){
				result = data;
			}
		});
		return result;
	}
	return '';
}

function edit(){
	toUrl("project_progress_record_secAdd.html?id="+id);
}