var obj = getRequest();
window.onload = function(){
    var list = document.getElementById('img-list');
    list.appendChild(createFragment(10,'../images/ly.png'));
    funLazyLoad('#img-list').refresh(true);
	
  	/*relPicker("chrqxlx",[{"text":"土地","value":""},{"text":"机电","value":""},{"text":"装修","value":""},{"text":"园林","value":""}
        ,{"text":"其他","value":""}],"chrqxlx");
    relPicker("chrsgdw",[{"text":"地勘单位","value":""},{"text":"总包单位","value":""}],"intsgdw");
    dtPicker('#dtmtzrq');
    dtPicker('#dtmwczgrq');*/
    //upLoadImg('#chbtn',{"busType":"bj_xm_zlqxbg"});
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
   
   
}

/*
*编辑
 */
function  edit() {
    var address = "project_quality_report_add.html?id=" + obj.id;
    toUrl(address);
}
