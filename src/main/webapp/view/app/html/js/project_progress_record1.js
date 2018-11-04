//初始化必要条件
var xmid=getCookie("id");
window.onload=function(){
	//得到数据
	var pageData = getDl();
	//绑定数据
	var vue = new Vue({
		el: '#app',
		data: {
			jcShow:false,
			ztShow:false,
			ecShow:false,
			dtShow:false,
			dls:pageData
		},
		methods: {
			toggle:function(type){
				switch (type){
					case 'jc':
					this.jcShow = !this.jcShow;
						break;
					case 'zt':
					this.ztShow = !this.ztShow;
						break;
					case 'ec':
					this.ecShow = !this.ecShow;
						break;
					case 'dt':
					this.dtShow = !this.dtShow;
						break;
					default:
						break;
				}
				
			},
			addQT:function(type){
				console.log(type)
				var address = "project_progress_record_baseAdd1.html?intsgwzid=-1&chrsgwzmc=其他";
    				toUrl(address);
			},
			openJC: function (id) {
				var address = "project_progress_record_baseDetail1.html?id="+id;
    				toUrl(address);
			},
			openZT: function (id) {
				var address = "project_progress_record_bodyDetail.html?id="+id;
    				toUrl(address);
			},
			openEC: function (id) {
				var address = "project_progress_record_secDetail.html?id="+id;
    				toUrl(address);
			},
			openDT: function (id) {
				var address = "project_progress_record_elevatorDetail.html?id="+id;
    				toUrl(address);
			},
			openSW: function () {
				var address = "project_progress_record_outdoorDetail.html";
    				toUrl(address);
			},
			openYL: function () {
				var address = "project_progress_record_gardenDetail.html";
    				toUrl(address);
			},
		}
	});
	
	

}
//得到【栋楼】数据
function getDl(){
	return getXmjdListByParam(xmid,'jc',1,"");
}
function outPage(){
	toUrl("project_detail_list.html");
}