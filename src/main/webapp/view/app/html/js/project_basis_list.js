



/**
 * 跳转项目具体信息
 */
function openNext(id){
	var address = null;
	switch (id){
		case 1:
		address="project_basis_address.html"
			break;
		case 2:
		address="project_basis_buildUnits.html"
			break;
		case 3:
		address="project_basis_planPermit.html"
			break;
		case 4:
		address="project_basis_constructionPermit.html"
			break;
		case 5:
		address="project_basis_targetData.html"
			break;
		case 6:
			address="project_basis_gytdsyz.html"
				break;
		case 7:
			address="project_basis_jsgcydghz.html"
				break;
		default:
			break;
	}
	toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}