
window.onload = function(){
	dtPicker('#showDateJZS');
	upLoadImg('#chbtn','#upbtn');
	relPicker("showAddress",getPageData("sgwz"));
}
function getPageData(type){
	var data;
	switch (type){
		case "sgwz":
		data = [{text:"#1栋",value:"1"},{text:"其他",value:"2"}]
			break;
		case "pslx":
		data = [{text:"独立基础",value:"1"},{text:"筏板",value:"2"}]
			break;
		default:
			break;
	}
	return data;
}
function save(){
	console.log("baoc");
}