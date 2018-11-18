mui("title")[0].innerText = getCookie("chrxmmc");
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
		address="project_progress_record.html"
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
