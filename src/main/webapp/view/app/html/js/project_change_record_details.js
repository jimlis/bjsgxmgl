var obj = getRequest("id");
$bjAjax({
		url:changeApiDetail,
		type:"post",
		data:{
			xmZfxcyzxysId:obj.id
		},
		success:function(data){
			document.getElementById("dtmgxrq").innerText=data.dtmgxrq;
			document.getElementById("intbgsqlx").innerText=data.intbgsqlx;
			document.getElementById("chrbgsqbh").innerText=data.chrbgsqbh;
			document.getElementById("chrbgsqmc").innerText=data.chrbgsqmc;
			document.getElementById("intsfqd").innerText=data.intsfqd;
			document.getElementById("").innerText=data.;
			document.getElementById("chrbgxq").innerText=data.chrbgxq;
			document.getElementById("intgqyx").innerText=data.intgqyx;
			document.getElementById("intbggs").innerText=data.intbggs;
			document.getElementById("").innerText=data.;
			document.getElementById("inthtzb").innerText=data.inthtzb;
			document.getElementById("chrbz").innerText=data.chrbz;
		}
});

/**
 *编辑
 */
function edit(){
    var address = "project_change_record_add.html?id="+obj.id;
    toUrl(address);
}
