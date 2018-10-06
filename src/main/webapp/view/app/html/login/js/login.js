function login(){
	var account=mui("#account")[0].value;
	var password=mui("#password")[0].value;
	if(isEmpty(account)){
		bjToast("账号不能为空");
		return;
	}
	
	if(isEmpty(password)){
        bjToast("密码不能为空");
		return;
	}

	$bj_post_ajax({"url":userApiPath+"login",data:{"mobile":account,"password":password},success:function (result) {
            	var token=result.token;
            	var user=result.user;
            	debugger
            	if(token){
            		try{
                        window.document.setCookie("token",JSON.stringify(token),20*365);
                        window.document.setCookie("user",JSON.stringify(user),20*365);
					}catch (e){

					}

                    localStorage.setItem("token",JSON.stringify(token));
                    localStorage.setItem("user",JSON.stringify(user));
                    window.location.href="../../main.html";
				}

        }});
	
}
