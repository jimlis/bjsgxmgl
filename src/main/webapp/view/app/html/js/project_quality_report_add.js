var obj = getRequest();
var systemdate = bjGetSysDate();
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var intxmid = getCookie('id');//intxmid
window.onload = function(){
	dtPicker('#dtmtzrq');
    dtPicker('#dtmwczgrq');
    dtPicker('#dtmzgwcrq');
    dtPicker('#dtmzlqxfxrq');
	upLoadImg('#chbtn',{"busType":"bj_xm_zlqxbg"});
	upLoadImg('#chbtn_zgwc',{"busType":"bj_xm_zlqxbg","fileListName":"img-list_zgwc","type":"2"});
	relPicker("chrqxlx",getPageData(),"intqxlx");
	relPicker("chrsgdw",getXmdwmdData(intxmid,"2"),"intsgdw");
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
				document.getElementById("chrbgrmc").value = data.chrbgrmc||"";
				document.getElementById("chrbz").value = data.chrbz||"";
				document.getElementById("chrqxms").value = data.chrqxms||"";
				document.getElementById("chrqxwz").value = data.chrqxwz||"";
				document.getElementById("dtmzlqxfxrq").value = data.dtmzlqxfxrq||"";
				document.getElementById("dtmgxrq").value = data.dtmgxrq||"";
				document.getElementById("dtmtzrq").value = data.dtmtzrq||"";
				document.getElementById("dtmzgwcrq").value = data.dtmzgwcrq||"";
				document.getElementById("id").value = data.id||"";
				document.getElementById("intbgrid").value = data.intbgrid||"";
				document.getElementById("intqxlx").value = data.intqxlx||"";
				if(data.intqxlx=='1'){
					document.getElementById("chrqxlx").value = "土建";
				}else if(data.intyblx=='2'){
					document.getElementById("chrqxlx").value = "机电";
				}else if(data.intyblx=='3'){
					document.getElementById("chrqxlx").value = "装修";
				}else if(data.intyblx=='4'){
					document.getElementById("chrqxlx").value = "园林";				
				}else{
					document.getElementById("chrqxlx").value = "其他";
				}
				document.getElementById("intsgdw").value = data.intsgdw||"";
				document.getElementById("chrsgdw").value = data.chrsgdw||"";
				document.getElementById("intxmid").value = data.intxmid||"";
				//加载图片
				initImgList("bj_xm_zlqxbg",id,"1","fileIds","img-list",true);
				initImgList("bj_xm_zlqxbg",id,"2","fileIds","img-list_zgwc",true);
			},
		});
	}else{
		//新增初始化 数据
		document.getElementById("dtmgxrq").value = systemdate;
		document.getElementById("intbgrid").value = chrdlrid;
		document.getElementById("chrbgrmc").value = chrdlrmc;
		document.getElementById("intxmid").value=intxmid;
	}
}
function getPageData(){
	var data;
	data = [{text:"土建",value:"1"},{text:"机电",value:"2"},{text:"装修",value:"3"},{text:"园林",value:"4"},{text:"其他",value:"5"}]
	return data;
}


function save(){
	//bjToast("保存成功");
	isSure(function(){
	var data = getFromData("form");
	$bjAjax({
		url:quaRecodeSaveApiPath,
		type:"post",
		data:data,
		success:function(data){
			console.log(data);
			bjToast("保存成功！",function(){
				toUrl("project_quality_report.html");
			});
		}
	})
})
}
function outPage(){
	toUrl("project_quality_report.html");
}