var xmid=getCookie("id");
//初始化显示数据
window.onload = function(){
	var pageData = getPageData();
	var gw=getDataByDwlx(pageData,"1");
	var sg=getDataByDwlx(pageData,"2");
	var qt=getDataByDwlx(pageData,"3");
	var vue = new Vue({
		el: '#app',
		data: {gw:gw,sg:sg,qt:qt},
		methods: {
			openDetail: function (id) {
				var address = "project_pay_record_detail.html?id="+id;
    				toUrl(address);
			}
		}
	});
	tyclClick("#app");
}

function getDataByDwlx(data,dwlx){
	var arr=[];
	if(!data||data.length==0){
		return arr;
	}
	for(i in data){
		if(data[i].intdwlx==dwlx){
			arr.push(data[i]);
		}
	}
	return arr;
}

/**
 *新增
 */
function openAdd(){
    var address = "project_pay_record_add.html?id=&xmid="+xmid;
    toUrl(address);
}
//得到巡查信息数据
function getPageData(){
	var obj={};
	$bj_post_ajax({
	    	"url":payApiList,
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
    var address = "project_pay_record_details.html?id="+id;
    toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}