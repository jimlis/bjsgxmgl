
var obj = getRequest(location.search);
var deptId = obj.deptId||0;
if(isNotEmpty(obj.name)){
	mui("header .mui-title")[0].innerHTML = obj.name;
	mui("title")[0].innerText = obj.name;
}

$bjAjax({
	url:deptApiPath+"getNextDeptAndUser",
	data:{
		deptId:deptId
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		if(data.code==0){
			var array = data.data;
			if(array.length==0){
				mui(".mui-content .mui-table-view-cell")[0].innerHTML='没有相关数据，请上传数据';
				return;
			}
			mui.each(array,function(index,item){
			  	var name = item.name;
			  	var lx = item.lx;
			 	var id = item.id;
				mui(".mui-content .mui-table-view")[0].innerHTML +="<li class=\"mui-table-view-cell\" onclick=\"openNext('"+lx+"','"+id+"','"+name+"');\">"+name+"</li>";
			})
			mui(".mui-content .mui-table-view-cell")[0].style.display="none";
		}else{
			mui(".mui-content .mui-table-view-cell")[0].innerHTML=data.msg;
		}
	},
	err:function(xhr,type,errorThrown){
		//异常处理；
		console.log(type);
		mui(".mui-content .mui-table-view-cell")[0].innerHTML='获取数据失败！错误码:'+type;

	}
});

/**
 * 跳转组织机构人员列表页面
 */
function openNext(lx,id,name){
	if(lx=='unit'){
		var url = "address_dept.html?deptId="+id+"&name="+name;
	}else{
		var url = "address_user.html?userId="+id+"&name="+name;
	}
	toUrl(url);
}

