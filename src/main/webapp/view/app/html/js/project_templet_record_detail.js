var obj = getRequest();
window.onload = function(){
	
//  var list = document.getElementById('img-list');
//  list.appendChild(createFragment(10,'../images/ly.png'));
//  funLazyLoad('#img-list').refresh(true);
	
   /* relPicker("intsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}]);

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');*/
   	var id = obj.id;
   	$bjAjax({
		url:tempRecodeByIdApiPath,
		data:{
			xmYbsgjlId:id
		},
		type:'post',
		success:function(data){
			//服务器返回响应，根据响应结果，分析是否登录成功；
			document.getElementById("chrbgrmc").innerText = data.chrbgrmc;
			document.getElementById("chrbz").innerText = data.chrbz;
			document.getElementById("chrybms").innerText = data.chrybms;
			document.getElementById("chrybwz").innerText = data.chrybwz;
			document.getElementById("dtmgxrq").innerText = data.dtmgxrq;
			document.getElementById("dtmsprq").innerText = data.dtmsprq;
			document.getElementById("dtmwcrq").innerText = data.dtmwcrq;
			document.getElementById("id").innerText = data.id;
			document.getElementById("intbgrid").innerText = data.intbgrid;
			if(data.intyblx=='1'){
				document.getElementById("chryblx").innerText = "土建";
			}else if(data.intyblx=='2'){
				document.getElementById("chryblx").innerText = "机电";
			}else if(data.intyblx=='3'){
				document.getElementById("chryblx").innerText = "装修";
			}else if(data.intyblx=='4'){
				document.getElementById("chryblx").innerText = "园林";				
			}else{
				document.getElementById("chryblx").innerText = "其他";
			}
			document.getElementById("intxmid").innerText = data.intxmid;
		},
	});
   
   
}

/*
*编辑
 */
function  openNext() {
    var address = "project_templet_record_add.html?id=" + obj.id;
    toUrl(address);
}
