var obj = getRequest();
window.onload = function(){
    var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);

   /* relPicker("intsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}]);

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');*/
   	var id = obj.id;
   	$bjAjax({
		url:safeReportByIdApiPath,
		data:{
			xmAqbgId:id
		},
		type:'post',
		success:function(data){
			//服务器返回响应，根据响应结果，分析是否登录成功；
			document.getElementById("chraqwtbs").innerText = data.chraqwtbs;
			document.getElementById("chraqwtwz").innerText = data.chraqwtwz;
			document.getElementById("chrbgrmc").innerText = data.chrbgrmc;
			document.getElementById("chrzb").innerText = data.chrzb;
			document.getElementById("dtmgxrq").innerText = data.dtmgxrq;
			document.getElementById("dtmtzrq").innerText = data.dtmtzrq;
			document.getElementById("dtmwczgrq").innerText = data.dtmwczgrq;
			document.getElementById("fcbz").innerText = data.fcbz;
			document.getElementById("gxsj").innerText = data.gxsj;
			document.getElementById("id").innerText = data.id;
			document.getElementById("intbgrid").innerText = data.intbgrid;
			document.getElementById("intsgdw").innerText = data.intsgdw;
			document.getElementById("intxh").innerText = data.intxh;
			document.getElementById("intxmid").innerText = data.intxmid;
		},
	});
   
   
}

/*
*编辑
 */
function  openAdd() {
    var address = "project_saft_report_add.html?id=" + obj.id;
    toUrl(address);
}
