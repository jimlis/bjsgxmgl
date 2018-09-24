



/**
 * 跳转项目具体信息
 */
function openNext(id){
	var address = null;
	switch (id){
		case 1:
		address="project_basis_list.html"
			break;
		default:
			break;
	}
	window.location.href=address;
}