var obj = getRequest();
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
window.onload = function(){
	showEdit();
	dtPicker('#dtmbgrq');
    dtPicker('#dtmtzrq');

    dtPicker('#dtmwczgrq',false,true);
    
    relPicker("chrsgdw",getXmdwmdData(intxmid,"2"),"intsgdw");

    upLoadImg('#chbtn',{"busType":"bj_xm_aqbg"});
    
    upLoadImg('#chbtn_zgwc',{"busType":"bj_xm_aqbg","fileListName":"img-list_zgwc","type":"2"});
    
    //修改
    if(!!obj.id){
    	var id=obj.id||"";
    	$bjAjax({
			url:safeReportByIdApiPath,
			data:{
				xmAqbgId:id
			},
			type:'post',
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				document.getElementById("chraqwtbs").value = data.chraqwtbs||"";
				document.getElementById("dtmbgrq").value = data.dtmbgrq||"";
				document.getElementById("chraqwtwz").value = data.chraqwtwz||"";
				document.getElementById("chrzb").value = data.chrzb||"";
				document.getElementById("dtmgxrq").value = systemdate;
				//document.getElementById("dtmgxrq").value = data.dtmgxrq||"";
				document.getElementById("dtmtzrq").value = data.dtmtzrq||"";
				document.getElementById("dtmwczgrq").value = data.dtmwczgrq||"";
				document.getElementById("id").value = data.id||"";
				document.getElementById("intbgrid").value = chrdlrid;
				document.getElementById("chrbgrmc").value = chrdlrmc;
				document.getElementById("intsgdw").value = data.intsgdw||"";
				document.getElementById("chrsgdw").value = data.chrsgdw||"";
				document.getElementById("intxmid").value = data.intxmid||"";
				
				//加载图片
				initImgList("bj_xm_aqbg",id,"1","fileIds","img-list",true);
				
				//加载图片
				initImgList("bj_xm_aqbg",id,"2","fileIds","img-list_zgwc",true);
			},
		});
    	
    }else{
    		//新增
    		document.getElementById("dtmgxrq").value = systemdate;
    		document.getElementById("intbgrid").value = chrdlrid;
    		document.getElementById("chrbgrmc").value = chrdlrmc;
    		document.getElementById("intxmid").value=intxmid;
    }
}

function save(){
isSure(function(){
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
})
	
}
function outPage(){
	toUrl("project_safe_report.html");
}