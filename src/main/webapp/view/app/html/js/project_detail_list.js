mui("title")[0].innerText = getCookie("chrxmmc");
(function setNavbar(){
	try{
		var intxmlx = getCookie("xmlx")||"";
		var xmlxmc='';
		if(intxmlx==1){
			xmlxmc = "PMC项目";
		}else{
			xmlxmc = "EPC项目";
		}
		var nav = document.getElementById("title-scrollT");
		nav.innerHTML = `
			<a href="../main.html">博建施工项目管理&nbsp;/</a>
			<a href="project_list.html">`+xmlxmc+`列表&nbsp;/</a>
			<a href="#" style="color: #fff;">`+mui("title")[0].innerText+`</a>
		`;
		mui('.mui-scroll-wrapper').scroll().scrollTo(-1000,0,500);//100毫秒滚动到顶
	}catch(e){
		//TODO handle the exception
	}
})();
/**
 * 跳转项目具体信息
 */
function openNext(id){
	var address = null;
	switch (id){
		case 1:
		address="project_basis_list.html"
			break;
		case 2:
	    address="project_timenode_comparison.html"
			break;
		case 3:
		address="project_photo_record_list.html"
			break;
		case 4:
		address="project_progress_record1.html"
			break;
		case 5:
		address="project_templet_record.html"
			break;
		case 6:
		address="project_quality_report.html"
			break;
		case 7:
		address="project_material_record.html"
			break;
		case 8:
		address="project_change_record.html"
			break;
		case 9:
		address="project_pay_record.html"
			break;
		case 10:
		address="project_safe_report.html"
			break;
		case 11:
		address="project_gov_record.html"
			break;
		default:
			break;
	}
	toUrl(address);
}
function outPage(){
	toUrl("project_list.html");
}