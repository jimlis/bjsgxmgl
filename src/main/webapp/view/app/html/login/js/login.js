function login(){
//	var account=mui("#account")[0].value;
//	var password=mui("#password")[0].value;
	var account="admin";
	var password="1";
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
            	if(token){
            		try{
            				setCookie("chrdlrmc",user.name);
            				setCookie("chrdlrid",user.id);
                        setCookie("token",JSON.stringify(token));
					}catch (e){

					}
                    window.location.href="../../main.html";
				}

        }});
	
}
function mlogin1(){
	
			bjToast("正在免登录中...");
				$bj_get_ajax({
					url:serverPath+"getToken",
					success:function(data){
						try{
							dd.ready(function() {
								dd.runtime.permission.requestAuthCode({
									corpId: data.corpId,
									onSuccess: function(info) {
											mlogin2(data,info);
									},
									onFail : function(err) {}
										
								});
							});
						}catch(e){
							
						}
						try{
							DingTalkPC.config({
								agentId: data.agentId,
								corpId: data.corpId,
								timeStamp: data.timeStamp,
								nonceStr: data.nonceStr,
								signature: data.signature,
								jsApiList: [
								   'runtime.info',          
									'biz.contact.choose',              //选择用户接口
									'device.notification.confirm',     //confirm,alert,prompt都是弹出小窗口的接口   
									'device.notification.alert',
									'device.notification.prompt',
									'biz.util.openLink'
								]
							});
							DingTalkPC.runtime.permission.requestAuthCode({
									corpId : data.corpId,
									onSuccess : function(info) {
											mlogin2(data,info);
										
									}
									
								});
						}catch(e){
							
						}
					}
				})
			
}
function mlogin2(data,info){
	var code=info.code;
	var token = data.access_token;
	$bj_get_ajax({
		data:{
			access_token:token,
			code:code
		},
		url:serverPath+"getDDUser",
		success:function(user){
			$bj_get_ajax({
				data:{
					access_token:token,
					userid:user.userid
				},
				url:serverPath+"getDDUserInfo",
				success:function(userInfo){
					var info = JSON.parse(userInfo);
					setCookie("tel",info.mobile);
					$bj_post_ajax({
						url:userApiPath+"logonFree",
						data:{"tel":info.mobile,"token":getToken()},
						success:function (result) {
								var token=result.token;
								var user=result.user;
								if(token){
									try{
										setCookie("chrdlrmc",user.name);
										setCookie("chrdlrid",user.id);
										setCookie("token",JSON.stringify(token));
									}catch (e){
				
									}
									window.location.href="../../main.html";
								}
			
					}});	
				}
			});	
		}
	});
}
