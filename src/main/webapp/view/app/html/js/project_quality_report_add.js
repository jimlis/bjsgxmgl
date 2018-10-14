var obj = getRequest();
var systemdate = getCookie('sysDate');
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
window.onload = function(){
	dtPicker('#dtmtzrq');
    dtPicker('#dtmwczgrq');
	upLoadImg('#chbtn',{"busType":"bj_xm_zlqxbg"});
	relPicker("chrqxlx",getPageData());
	//修改
	if(!!obj.id){
		var id = obj.id;
	   	$bjAjax({
			url:quaRecodeByIdApiPath,
			data:{
				xmZlqxbgId:id
			},
			type:'post',
			success:function(data){
				//服务器返回响应，根据响应结果，分析是否登录成功；
				document.getElementById("chrbgrmc").innerText = data.chrbgrmc;
				document.getElementById("chrbz").innerText = data.chrbz;
				document.getElementById("chrqxms").innerText = data.chrqxms;
				document.getElementById("chrqxwz").innerText = data.chrqxwz;
				document.getElementById("dtmgxrq").innerText = data.dtmgxrq;
				document.getElementById("dtmtzrq").innerText = data.dtmtzrq;
				document.getElementById("dtmzgwcrq").innerText = data.dtmzgwcrq;
				document.getElementById("id").innerText = data.id;
				document.getElementById("intbgrid").innerText = data.intbgrid;
				document.getElementById("intqxlx").innerText = data.intqxlx;
				if(data.intqxlx=='1'){
					document.getElementById("chrqxlx").innerText = "土建";
				}else if(data.intyblx=='2'){
					document.getElementById("chrqxlx").innerText = "机电";
				}else if(data.intyblx=='3'){
					document.getElementById("chrqxlx").innerText = "装修";
				}else if(data.intyblx=='4'){
					document.getElementById("chrqxlx").innerText = "园林";				
				}else{
					document.getElementById("chrqxlx").innerText = "其他";
				}
				document.getElementById("intsgdw").innerText = data.intsgdw;
				document.getElementById("intxmid").innerText = data.intxmid;
			},
		});
	}else{
		//新增初始化 数据
		document.getElementById("dtmgxrq").innerText = systemdate;
		document.getElementById("chrbgrid").innerText = chrdlrid;
		document.getElementById("chrbgrmc").innerText = chrdlrmc;
	}
}
function getPageData(){
	var data;
	data = [{text:"土建",value:"1"},{text:"机电",value:"2"},{text:"装修",value:"3"},{text:"园林",value:"4"},{text:"其他",value:"5"}]
	return data;
}
function save(){
	//bjToast("保存成功");
	
	var data = getFromData("form");
	$bjAjax({
		url:tempRecodeSaveApiPath,
		type:"post",
		data:data,
		success:function(data){
			console.log(data);
			bjToast("保存成功！",function(){
				toUrl("project_templet_record.html");
			});
		}
	})
	
	
}