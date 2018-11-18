//初始化必要条件
var xmid=getCookie("id");
var zxys=[];
var jgys=[];
window.onload=function(){
	//初始化新增跳转页面
	init();
	//得到数据
	var pageData = {};
	initZxjgData();
	pageData["zxys"]=zxys;
	pageData["jgys"]=jgys;
	//绑定数据
	var vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			openQqbj: function (id) {
				var address = "project_timenode_comparison_yjedetails.html?chrjdlx=qqbj";
    				toUrl(address);
			},
			openJc: function (id) {
				var address = "project_timenode_comparison_jcejedetails.html?chrjdlx=jc";
    				toUrl(address);
			},
			openZt: function (id) {
				var address = "project_timenode_comparison_ztdetail.html?chrjdlx=zt";
    				toUrl(address);
			},
			openZxjg: function (id) {
				var address = "project_timenode_comparison_yjedetails.html?chrjdlx=zxjq";
    				toUrl(address);
			}
		}
	});

	tyclClick("#app");
}

//初始化新增跳转页面
function init(){
	var btnArray = [{
			value: '0',
			text: '前期报建'
		}, {
			value: '1',
			text: '基础'
		}, {
			value: '2',
			text: '主体'
		}, {
			value: '3',
			text: '专项及竣工验收'
		}];
	relPicker('confirmBtn',btnArray,null,function(obj){
		switch (obj.value){
			case "0":
				toUrl("project_timenode_comparison_yjedit.html?chrjdlx=qqbj");
				break;
			case "1":
				toUrl("project_timenode_comparison_jcejedit.html?chrjdlx=jc");
				break;
			case "2":
				if(getJcList().length==0){
					bjToast("请先新增基础然后再新增主体");
				}else{
					toUrl("project_timenode_comparison_ejedit.html?chrjdlx=zt");
				}
				break;
			case "3":
				toUrl("project_project_timenode_comparison_zxysedit.html");
					break;
			default:
				break;
		}
	});
}

function outPage(){
	toUrl("project_detail_list.html");
}

function getJcList(){
	var o=[];
	$bjAjax({
		url:timenodeListApiPath,
		type:"post",
		async:false,
		data:{
			xmid:xmid,
			jdlx:"jc"
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
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
				zxys=data.zxys||[];
				jgys=data.jgys||[];
				result = data;
			}
		}
	});
	return result;
}