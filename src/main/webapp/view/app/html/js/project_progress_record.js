//初始化必要条件
window.onload=function(){
	init();
}
function init(){
	var btnArray = [{
			value: '0',
			text: '基础施工'
		}, {
			value: '1',
			text: '主体结构施工'
		}, {
			value: '2',
			text: '二次结构、装修等施工'
		}, {
			value: '3',
			text: '电梯设备安装施工'
		}, {
			value: '4',
			text: '室外管网施工'
		}, {
			value: '5',
			text: '园林施工'
	}];
	relPicker('confirmBtn',btnArray,null,function(obj){
		switch (obj.value){
			case "0":
			toUrl("project_progress_record_baseDetail.html");
				break;
			case "1":
			toUrl("project_progress_record_BodyDetail.html");
				break;
			case "2":
			toUrl("project_progress_record_SecDetail.html");
				break;
			case "3":
			toUrl("project_progress_record_ElevatorDetail.html");
				break;
			case "4":
			toUrl("project_progress_record_outdoorDetail.html");
				break;
			case "5":
			toUrl("project_progress_record_gardenDetail.html");
				break;
			default:
				break;
		}
		bjToast("您选择了："+obj.text);
	});
}
function openNext(type,id){
	switch (type){
			case "0":
			toUrl("project_progress_record_baseDetail.html?id="+id);
				break;
			case "1":
			toUrl("project_progress_record_BodyDetail.html?id="+id);
				break;
			case "2":
			toUrl("project_progress_record_SecDetail.html?id="+id);
				break;
			case "3":
			toUrl("project_progress_record_ElevatorDetail.html?id="+id);
				break;
			case "4":
			toUrl("project_progress_record_outdoorDetail.html?id="+id);
				break;
			case "5":
			toUrl("project_progress_record_gardenDetail.html?id="+id);
				break;
			default:
				break;
		}
}
