
var obj = getRequest(location.search);
mui("title")[0].innerText = obj.name;
mui("header .mui-title")[0].innerHTML = obj.name;
$bjAjax({
	url:userApiPath+"getUserById",
	data:{
		userId:obj.userId
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		if(data.code==0){
			console.log(data);
			var user = data.data;
			if(user.fileId!=null){
				mui(".mui-content")[0].innerHTML =`
					<img class="bj-img-center" src="`+imgApiPath+user.fileId+`"/>
				`;
				return;
			}
			var userId=user.id;//用户id
			var deptId=user.deptId;//部门id
			var deptName=user.deptName||"";//部门名称
			var name=user.name||"";//用户名称
			var mobile=user.mobile||"";//手机
			var email=user.email||""//邮箱
			mui(".mui-content .mui-table-view")[0].innerHTML +=`
				<li class="mui-table-view-cell">
					<span>姓名：`+name+`</span>
				</li>
				<li class="mui-table-view-cell"><span>电话：`+mobile+`</span></li>
				<li class="mui-table-view-cell" ><span>邮箱：`+email+`</span></li>
				<li class="mui-table-view-cell"><span>部门：`+deptName+`</span></li>
			`;
		}
	},
	err:function(xhr,type,errorThrown){
		//异常处理；
		console.log(type);
	}
});






