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
	$bj_post_ajax({
	    	"url":xmzfxcyzxysApiPath,
	    	"data":{
	    		"xmid":xmid,
	    	},
	    	success:function (data) {
	    		return data;
	        }
    });
//  if(isBjDebug){
//  		return [{ "dtmgxrq": '2018-8-9',"id":'1',"intxclb":'1'},{ "dtmgxrq": '2018-8-9',"id":'2',"intxclb":'2'},{ "dtmgxrq": '2018-8-9',"id":'3',"intxclb":'3'},{ "dtmgxrq": '2018-8-9',"id":'4',"intxclb":'4'},{ "dtmgxrq": '2018-8-9',"id":'5',"intxclb":'5'}];	
//  }
}

/**
 *详情
 */
function openDetails(id){
    var address = "project_gov_record_details.html?id="+id;
    toUrl(address);
}
