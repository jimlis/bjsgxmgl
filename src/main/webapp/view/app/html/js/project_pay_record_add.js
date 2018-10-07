
window.onload = function(){
	relPicker("showLX",getPageData("lx"));
	relPicker("showMC",getPageData("mc"));
	relPicker("showSPZT",getPageData("spzt"));
}
function getPageData(type){
	var data;
	switch (type){
		case "lx":
		data = [{text:"顾问单位",value:"1"}];
			break;
		case "mc":
		data = [{text:"顾问单位",value:"1"}];
			break;
		case "spzt":
		data = [{text:"待审批",value:"1"}];
			break;
		default:
			break;
	}
	
	return data;
}
function save(){
	bjToast("保存成功");
}