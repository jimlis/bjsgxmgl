var id =null;
$bjAjax({
	url:baseApiAddress,
	type:"post",
	data:{
		xmid:getCookie("id")
	},
	success:function(data){
		var chrqy = data.chrqy,
		chrwz = data.chrwz,
		chrgdjt = data.chrgdjt,
		chrzwzk = data.chrzwzk,
		fileIds = data.fileIds,
		id = data.id;
		mui(".mui-content .mui-content-padded")[0].innerHTML +=`
			<h5 class="bj-title2-font">区域：<span class="bj-p-gray-font">`+chrqy+`</span></h5>
			<h5 class="bj-title2-font">位置：<span class="bj-p-gray-font">`+chrwz+`</span></h5>
			<h5 class="bj-title2-font">轨道交通：<span class="bj-p-gray-font">`+chrgdjt+`</span></h5>
			<h5 class="bj-title2-font">周围状况：<span class="bj-p-gray-font">`+chrzwzk+`</span></h5>
			<h5 class="bj-title2-font">项目效果图：</h5>
			`;
		if(fileIds.length!=0){
			 setimg(fileIds);
		}
	}
})
function setimg(url){
	var list = document.getElementById('list');
	 list.appendChild(createFragments(url));
	 funLazyLoad('#list').refresh(true);
}

function edit(){
	if(id){
		toUrl("project_basis_address_edit.html?id="+id);
		return;
	}
	toUrl("project_basis_address_edit.html");
}
