var obj = getRequest();
var id=obj.id||"";
//初始化显示数据
window.onload = function(){
	//得到数据
	var pageData = getPageData();
	
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
//			edit: function (id) {
//				var address = "project_gov_record_add.html?id="+id;
//  			toUrl(address);
//			}
		}
	});
	
	if(id){
		//加载图片
		initImgList("bj_xm_zfxcyzxys",id,"1","fileIds","img-list",false);
	}
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

function edit(){
	toUrl("project_gov_record_add.html?id="+id);
}


