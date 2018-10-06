function login(){
	var account=$("#account").val();
	var password=$("#password").val();
	if(isEmpty(account)){
		alert("账号不能为空");
		return;
	}
	
	if(isEmpty(password)){
		alert("密码不能为空");
		return;
	}

	$bj_post_ajax({"url":userApiPath+"login",data:{"mobile":account,"password":password},success:function (result) {
            var code=result.code;
            var msg=result.msg;
            if(code==0){
            	var data=result.data;
            	var token=data.token;
            	var user=data.user;
            	if(token){
                    localStorage.setItem("token",token);
                    localStorage.setItem("user",user);
                    window.location.href="../main.html";
				}
			}else{
                alert(msg);
			}
        }});
	
}
