//初始化必要条件
var xmid=getCookie("id");
window.onload=function(){
	//初始化新增跳转页面
	init();
	//得到数据
	var pageData = {};
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
				var address = "project_timenode_comparison_ejedetails.html?chrjdlx=zt";
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
				toUrl("project_timenode_comparison_yjedit.html?chrjdlx=zxjq");
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