var obj = getRequest();
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');
window.onload = function(){
	dtPicker('#dtmwcrq');//样板施工完成日期
	dtPicker('#dtmsprq');//样板施工完成日期
	upLoadImg('#chbtn','#upbtn');
	relPicker("chryblx",getPageData(),"intyblx");
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
				document.getElementById("chrbgrmc").value = data.chrbgrmc;
				document.getElementById("chrbz").value = data.chrbz;
				document.getElementById("chrybms").value = data.chrybms;
				document.getElementById("chrybwz").value = data.chrybwz;
				document.getElementById("dtmgxrq").value = data.dtmgxrq;
				document.getElementById("dtmsprq").value = data.dtmsprq;
				document.getElementById("dtmwcrq").value = data.dtmwcrq;
				document.getElementById("id").value = data.id;
				document.getElementById("intbgrid").value = data.intbgrid;
				if(data.intyblx=='1'){
					document.getElementById("chryblx").value = "土建";
				}else if(data.intyblx=='2'){
					document.getElementById("chryblx").value = "机电";
				}else if(data.intyblx=='3'){
					document.getElementById("chryblx").value = "装修";
				}else if(data.intyblx=='4'){
					document.getElementById("chryblx").value = "园林";				
				}else{
					document.getElementById("chryblx").value = "其他";
				}
				document.getElementById("intyblx").value = data.intyblx;
				document.getElementById("intxmid").value = data.intxmid;
			},
		});
	}else{
		//新增初始化 数据
		document.getElementById("dtmgxrq").value = systemdate;
		document.getElementById("chrbgrid").value = chrdlrid;
		document.getElementById("chrbgrmc").value = chrdlrmc;
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
	data["id"] = obj.id;
	data["intxmid"] = getCookie("id");
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