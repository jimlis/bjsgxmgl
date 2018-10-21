
var fileName = getRequest(location.search).fileName;
mui("title")[0].innerText = fileName;
mui("header .mui-title")[0].innerHTML = fileName;
function reinitIframe(){
	var iframe = document.getElementById("fileIframe");
	try{
		var bHeight = window.screen.height;
		iframe.height = bHeight;
		console.log(bHeight);
	}catch (ex){
		
	}
}
reinitIframe();
var id=getQueryString("id");
openFile(id);
function openFile(id) {
	document.getElementById("fileIframe").setAttribute("src",serverPath+"plugins/PDFViewer/web/viewer.html?file="+serverPath+"api/file/down/"+id);
}
function outPage(){
	toUrl("../main.html");
}