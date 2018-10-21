var obj = getRequest();
if(obj.id){
	$bjAjax({
		url:baseApiAddress,
		type:"post",
		data:{
			xmid:getCookie("id"),
			id:obj.id
		},
		success:function(data){
			var chrqy = data.chrqy||"",
			chrwz = data.chrwz||"",
			chrgdjt = data.chrgdjt||"",
			chrzwzk = data.chrzwzk||"",
			fileIds = data.fileIds||"",
			id = data.id||"";
			document.getElementById("chrqy").value = chrqy;
			document.getElementById("chrwz").value = chrwz;
			document.getElementById("chrgdjt").value = chrgdjt;
			document.getElementById("chrzwzk").value = chrzwzk;
			 ;
			initImgList("bj_xm_qyjwz",id,"1","fileIds","img-list",true);
		}
	})
}

function save(){
	var data = getFromData("myform");
	data["intxmid"] = getCookie("id");
	data["id"] = obj.id||"";
	$bjAjax({
		url:baseApiAddressSave,
		data:data,
		type:"post",
		success:function(data){
			bjToast("保存成功！",function(){
				toUrl("project_basis_address.html");
			});
		}
	});
}
upLoadImg('#chbtn',{"busType":"bj_xm_qyjwz"});