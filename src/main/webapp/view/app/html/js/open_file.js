
function reinitIframe(){
	var iframe = document.getElementById("fileIframe");
	try{
		var bHeight = window.screen.height;
		//var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
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