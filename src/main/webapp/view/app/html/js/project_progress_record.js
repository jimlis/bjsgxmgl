//初始化必要条件
window.onload=function(){
	//初始化新增跳转页面
	init();
	//得到数据
	var PageData = getPageData();
	//绑定数据
	var vue = new Vue({
		el: '#app',
		data: {jcs:pageData.jc,
		zts:pageData.zt,
		ers:pageData.ec,
		dts:pageData.dt,
		sws:pageData.sw,
		yls:pageData.yl},
		methods: {
			openJC: function (id) {
				var address = "project_progress_record_baseDetail.html?id="+id;
    				toUrl(address);
			},
			openZT: function (id) {
				var address = "project_progress_record_BodyDetail.html?id="+id;
    				toUrl(address);
			},
			openEC: function (id) {
				var address = "project_progress_record_SecDetail.html?id="+id;
    				toUrl(address);
			},
			openDT: function (id) {
				var address = "project_progress_record_ElevatorDetail.html?id="+id;
    				toUrl(address);
			},
			openSW: function (id) {
				var address = "project_progress_record_outdoorDetail.html?id="+id;
    				toUrl(address);
			},
			openYL: function (id) {
				var address = "project_progress_record_gardenDetail.html?id="+id;
    				toUrl(address);
			},
		}
	});
	
}
//初始化新增跳转页面
function init(){
	var btnArray = [{
			value: '0',
			text: '基础施工'
		}, {
			value: '1',
			text: '主体结构施工'
		}, {
			value: '2',
			text: '二次结构、装修等施工'
		}, {
			value: '3',
			text: '电梯设备安装施工'
		}, {
			value: '4',
			text: '室外管网施工'
		}, {
			value: '5',
			text: '园林施工'
	}];
	relPicker('confirmBtn',btnArray,null,function(obj){
		switch (obj.value){
			case "0":
			toUrl("project_progress_record_baseAdd.html");
				break;
			case "1":
			toUrl("project_progress_record_bodyAdd.html");
				break;
			case "2":
			toUrl("project_progress_record_SecDetail.html");
				break;
			case "3":
			toUrl("project_progress_record_elevatorAdd.html");
				break;
			case "4":
			toUrl("project_progress_record_outdoorAdd.html");
				break;
			case "5":
			toUrl("project_progress_record_gardenAdd.html");
				break;
			default:
				break;
		}
	});
}
//得到基础、主体、二次、电梯、室外、园林信息数据
function getPageData(){
	$bj_post_ajax({
	    	"url":xmzfxcyzxysApiPath,
	    	"data":{
	    		"xmid":xmid,
	    	},
	    	success:function (data) {
	    		return data;
	        }
    });
}
