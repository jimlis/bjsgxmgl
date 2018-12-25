var obj = getRequest();
var id=obj.id||"";
var xmid = getCookie('id');//intxmid
var xclb=obj.xclb||"";
var gqjdid=obj.gqjdid||"";
//初始化显示数据
window.onload = function(){
	showEdit();
	
	//得到数据
	var pageData = getPageData();
	if(xclb=='zxys' || xclb=='jgys'){
		pageData["chrlbmc"]="验收";
	}else{
		pageData["chrlbmc"]="巡查";
	}
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			edit: function (id) {
				var address = "project_gov_record_add.html?id="+id;
    			toUrl(address);
			}
		}
	});
	
	if(id){
		//加载图片
		initImgList("bj_xm_zfxcyzxys",id,"1","fileIds","img-list",false);
	}
}
//得到显示数据
function getPageData(){
	var o={};
	if(xclb=="zxys"||xclb=="jgys"){
		$bjAjax({
			url:xmzxyszfxcyzxysApi,
			type:"post",
			async:false,
			data:{
				xmid:xmid,
				xclb:xclb,
				gqjdid:gqjdid,
				fwlx:"cx"
			},
			success:function(data){
				if(data){
					id=data.id||"";
					o=data;
				}
			}
		});
	}else{
		$bjAjax({
			url:xmzfxcyzxysApiDetail,
			type:"post",
			async:false,
			data:{
				xmZfxcyzxysId:obj.id
			},
			success:function(data){
				if(data){
					o=data;
				}
			}
		});
	}
	return o;
}

function edit(){
	toUrl("project_gov_record_add.html?id="+id+"&xclb="+xclb+"&gqjdid="+gqjdid);
}


function outPage(){
	toUrl("project_gov_record.html");
}