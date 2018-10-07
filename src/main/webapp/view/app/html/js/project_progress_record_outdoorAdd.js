
window.onload = function(){
	dtPicker('#showDateSP');
	relPicker("showLX",getPageData());
}
function getPageData(){
	var data;
	data = [{text:"雨水管网",value:"1"}]
	return data;
}
function save(){
	bjToast("保存成功");
}