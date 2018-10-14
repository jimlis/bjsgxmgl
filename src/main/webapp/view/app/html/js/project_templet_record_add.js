var obj = getRequest();
var systemdate = getCookie('sysDate');
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
window.onload = function(){
	dtPicker('#dtmwcrq');//样板施工完成日期
	dtPicker('#dtmsprq');//样板施工完成日期
	upLoadImg('#chbtn','#upbtn');
	relPicker("intyblx",getPageData());
	//修改
	if(!!obj.id){
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
					document.getElementById("intyblx").innerText = "土建";
				}else if(data.intyblx=='2'){
					document.getElementById("intyblx").innerText = "机电";
				}else if(data.intyblx=='3'){
					document.getElementById("intyblx").innerText = "装修";
				}else if(data.intyblx=='4'){
					document.getElementById("intyblx").innerText = "园林";				
				}else{
					document.getElementById("intyblx").innerText = "其他";
				}
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