
window.onload = function(){
	relPicker("showLX",getPageData());
}
function getPageData(){
	var data;
	data = [{text:"绿化施工",value:"1"}]
	return data;
}
function save(){
	bjToast("保存成功");
}