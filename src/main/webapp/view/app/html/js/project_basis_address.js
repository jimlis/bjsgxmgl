showEdit1();
$bjAjax({
	url:baseApiAddress,
	type:"post",
	data:{
		xmid:getCookie("id")
	},
	success:function(data){
		var chrqy = data.chrqy||"",
		chrwz = data.chrwz||"",
		chrgdjt = data.chrgdjt||"",
		chrzwzk = data.chrzwzk||"",
		fileIds = data.fileIds||"",
		id = data.id||"";
		document.getElementById("id").value=id;
		mui(".mui-content .mui-content-padded")[0].innerHTML +=`
			<h5 class="bj-title2-font"><span class="bj-title-p-font">区域：</span><span class="bj-p-gray-font">`+chrqy+`</span></h5>
			<h5 class="bj-title2-font"><span class="bj-title-p-font">位置：</span><span class="bj-p-gray-font">`+chrwz+`</span></h5>
			<h5 class="bj-title2-font"><span class="bj-title-p-font">轨道交通：</span><span class="bj-p-gray-font">`+chrgdjt+`</span></h5>
			<h5 class="bj-title2-font"><span class="bj-title-p-font">周围状况：</span><span class="bj-p-gray-font">`+chrzwzk+`</span></h5>
			<h5 class="bj-title2-font"><span class="bj-title-p-font">项目效果图：</span></h5>
			<div id="uploader" class="wu-example">
				<div id="img-list" class="mui-table-view mui-table-view-chevron mui-grid-view">
				</div>
			</div>
			`;
		initImgList("bj_xm_qyjwz",id,"1","fileIds","img-list",false);
	}
})


function edit(){
	var id=document.getElementById("id").value;
	if(id){
		toUrl("project_basis_address_edit.html?id="+id);
		return;
	}
	toUrl("project_basis_address_edit.html");
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_basis_list.html");
}
