showEdit();
dtPicker('#dtmfzrq');
dtPicker('#dtmyxrq');
upLoadFile('#chbtn',{"busType":"bj_xm_xkz"});
$bjAjax({
	url:baseApixkz,
	type:"post",
	data:{
		xmid:getCookie("id"),
		xkzlx:2
	},
	success:function(data){
		var id=data.id||"";
		document.getElementById("chrxkzbh").value = data.chrxkzbh||"";
		document.getElementById("chrffbm").value = data.chrffbm||"";
		document.getElementById("dtmfzrq").value = data.dtmfzrq||"";
		document.getElementById("dtmyxrq").value = data.dtmyxrq||"";
		initFileList("bj_xm_xkz",id,"1","fileIds","file-list",true);
	}
})

function save(){
isSure(function(){

	var data = getFromData("myform");
	data["intxkzlx"] = "2";
	data["intxmid"] = getCookie("id");
	$bjAjax({
		url:baseApixkzSave,
		type:"post",
		data:data,
		success:function(data){
			if(data){
				toUrl("project_basis_constructionPermit.html");
			}
			
		}
	})

})

}
function outPage(){
	toUrl("project_basis_list.html");
}