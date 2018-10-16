var obj = getRequest();

//初始化显示数据
window.onload = function(){
	//得到数据
	var pageData = getPageData();
	//显示图片
	var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			edit: function (id) {
				var address = "project_gov_record_add.html?id="+id;
    			toUrl(address);
			}
		}
	});
}
//得到显示数据
function getPageData(){
	$bjAjax({
		url:xmzfxcyzxysApiDetail,
		type:"post",
		data:{
			xmZfxcyzxysId:obj.id
		},
		success:function(data){
			return data;
		}
	});
//	if(isBjDebug){
//		return{dtmgxrq:'2018-8-9',dtmxcrq:'2016-9-8',intxcbm:'发改委',chrxcry:'李思思',chrzb:'Bad Request 请求出现语法错误,一般是请求参数不对',chrbgrmc:'李思'};
//	}
}


