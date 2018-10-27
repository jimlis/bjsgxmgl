var xmid=getCookie("id");
//初始化显示数据
window.onload = function(){
	var pageData = getPageData();
	var sghj=getDataByXcbm(pageData,'sghj');
	var qghj=getDataByXcbm(pageData,'qghj');
	var zjzxc=getDataByXcbm(pageData,'zjzxc');
	var ajzxc=getDataByXcbm(pageData,'ajzxc');
	var yzfxc=getDataByXcbm(pageData,'yzfxc');
	
	var zxys=getDataByXclb(pageData,'zxys');
	var jgys=getDataByXclb(pageData,'jgys');
	var vue = new Vue({
		el: '#app',
		data: {sghj:sghj,qghj:qghj,zjzxc:zjzxc,ajzxc:ajzxc,yzfxc:yzfxc,
			zxys:zxys,jgys:jgys},
		methods: {
			openDetail: function (id) {
				var address = "project_gov_record_details.html?id="+id;
    				toUrl(address);
			}
		}
	});
	tyclClick("#app");
}

function getDataByXcbm(data,xcbm){
	var arr=[];
	if(!data||data.length==0){
		return arr;
	}
	for(i in data){
		if(data[i].intxcbm==xcbm){
			arr.push(data[i]);
		}
	}
	return arr;
}

function getDataByXclb(data,xclb){
	var arr=[];
	if(!data||data.length==0){
		return arr;
	}
	for(i in data){
		if(data[i].intxclb==xclb){
			arr.push(data[i]);
		}
	}
	return arr;
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