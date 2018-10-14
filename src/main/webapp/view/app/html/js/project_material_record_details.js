var obj = getRequest();

$bjAjax({
	url:materialApiDetail,
	type:"post",
	data:{
		xmClybspjlId:obj.id
	},
	success:function(data){
		document.getElementById("dtmgxrq").innerText=data.dtmgxrq;
		document.getElementById("intclyblx").innerText=data.intclyblx;
		document.getElementById("intsgdw").innerText=data.intsgdw;
		document.getElementById("intsfdtp").innerText=data.intsfdtp;
		document.getElementById("chrybmc").innerText=data.chrybmc;
		document.getElementById("chrybwz").innerText=data.chrybwz;
		document.getElementById("chrgfbz").innerText=data.chrgfbz;
		document.getElementById("chrbz").innerText=data.chrbz;
	}
});
/*
*编辑
 */
function  edit() {
    var address = "project_material_record_add.html?id="+obj.id;
    toUrl(address);
}
