var obj = getRequest();
var systemdate = getCookie('sysDate');
window.onload = function(){
    relPicker("intsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}]);

    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq');

    upLoadImg('#chbtn',{"busType":"bj_xm_aqbg"});
    
    //修改
    if(!!obj.id){
    	
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
    	
    }else{
    	//新增
    	document.getElementById("dtmgxrq").innerText = systemdate;
    }
}

function save(){
	var data = getFromData("form");
	$bjAjax({
	url:safeReportSaveApiPath,
	type:"post",
	data:data,
	success:function(data){
		console.log(data);
		bjToast("保存成功！",function(){
			toUrl("project_safe_report.html");
		});
	}
})
	
}
