var xmid=getCookie("id");
//初始化显示数据
var zxysArr=[];
var jgysArr=[];
window.onload = function(){
	var pageData = getPageData();
	initZxjgData();
	var sghj=getDataByXcbm(pageData,'sghj');
	var qghj=getDataByXcbm(pageData,'qghj');
	var zjzxc=getDataByXcbm(pageData,'zjzxc');
	var ajzxc=getDataByXcbm(pageData,'ajzxc');
	var yzfxc=getDataByXcbm(pageData,'yzfxc');
	
	//var zxys=getDataByXclb(pageData,'zxys');
	//var jgys=getDataByXclb(pageData,'jgys');
	var zxys=zxysArr;
	var jgys=jgysArr;
	var vue = new Vue({
		el: '#app',
		data: {sghj:sghj,qghj:qghj,zjzxc:zjzxc,ajzxc:ajzxc,yzfxc:yzfxc,
			zxys:zxys,jgys:jgys},
		methods: {
			openDetail: function (id,xclb) {
				var address="";
				if(xclb=="zxys"||xclb=="jgys"){
					address = "project_gov_record_details.html?xclb="+xclb+"&gqjdid="+id;
				}else{
					address = "project_gov_record_details.html?id="+id+"&xclb="+xclb;
				}
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

//初始化专项竣工数据
function initZxjgData(){
	var result={};
	$bjAjax({
		url:timenodeZxjgMapListByXmidApiPath,
		type:"post",
		async:false,
		data:{
			xmid:xmid
		},
		success:function(data){
			if(data){
				zxysArr=data.zxys||[];
				jgysArr=data.jgys||[];
				result = data;
			}
		}
	});
	return result;
}

/**
 *详情
 */
function openDetails(id){
    var address = "project_gov_record_details.html?id="+id;
    toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}