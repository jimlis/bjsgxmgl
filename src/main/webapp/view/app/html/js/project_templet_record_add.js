
window.onload = function(){
	dtPicker('#showDateWC');
	upLoadImg('#chbtn','#upbtn');
	relPicker("showLX",getPageData());
}
function getPageData(){
	var data;
	data = [{text:"土建",value:"1"},{text:"其他",value:"2"}]
	return data;
}
function save(){
	bjToast("保存成功");
}