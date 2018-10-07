

document.getElementById("confirmBtn").addEventListener('tap', function() {
	var btnArray = ['基础施工', '否', '否', '否', '否'];
	mui.confirm('MUI是个好框架，确认？', 'Hello MUI', btnArray, function(e) {
		switch (e.index){
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			default:
				break;
		}
		bjToast("提升错误信息");
	})
});
function openNextAdd(id){
		toUrl("project_photo_record_add.html?id="+id);
}
function openNext(){
	
}
