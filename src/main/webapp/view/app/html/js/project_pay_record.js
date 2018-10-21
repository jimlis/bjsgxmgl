var xmid=getCookie("id");
//初始化显示数据
window.onload = function(){
	var pageData = getPageData();
	var vue = new Vue({
		el: '#app',
		data: {list:pageData},
		methods: {
			openDetail: function (id) {
				var address = "project_gov_record_details.html?id="+id;
    				toUrl(address);
			}
		}
	});
}
/**
 *新增
 */
function openAdd(){
    var address = "project_gov_record_add.html?id=&xmid="+xmid;
    toUrl(address);
}
//得到巡查信息数据
function getPageData(){
	var obj={};
	$bj_post_ajax({
	    	"url":xmzfxcyzxysApiPath,
	    	"data":{
	    		"xmid":xmid,
	    	},
	    	async:false,
	    	success:function (data) {
	    		if(data){
	    			obj=data;
	    		}
	        }
    });
	return obj;
}

/**
 *详情
 */
function openDetails(id){
    var address = "project_gov_record_details.html?id="+id;
    toUrl(address);
}
function outPage(){
	toUrl("project_detail_list.html");
}