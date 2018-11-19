//初始化必要条件
var xmid=getCookie("id");
var sysDate=bjGetSysDate();
var vue;
var zxys=[];
var jgys=[];
var isclick= true;
window.onload=function(){
	//初始化新增跳转页面
	init();
	//得到数据
	var pageData = {};
//	initZxjgData();
//	pageData["zxys"]=zxys;
//	pageData["jgys"]=jgys;
	//绑定数据
	vue = new Vue({
		el: '#app',
		data: {
			qqbj:[],
			jc:[],
			zt:[],
			zxys:[],
			jgys:[],
			ztShow:false,
			
		},
		beforeCreate: function(){
			$bjAjax({
				url:timenodeZtListApiPath,
				type:"post",
				//async:false,
				data:{
					gqjdbjid:"",
					xmid:xmid,
					jdlx:'zt'
				},
				success:function(data){
					if(data){
						vue.$data.zt = data;
					}
				}
			});
			$bjAjax({
				url:timenodeZxjgMapListByXmidApiPath,
				type:"post",
				//async:false,
				data:{
					xmid:xmid
				},
				success:function(data){
					if(data){
						vue.$data.zxys =data.zxys||[];
						vue.$data.jgys =data.jgys||[];
					}
				}
			});
		},
		methods: {
			toggle:function(type){
			    if(isclick){
			        isclick= false;
					switch (type){
						case 'zt':
						console.log(this.ztShow);
						this.ztShow = !this.ztShow;
							break;
						default:
							break;
					}
			 setTimeout(function(){ 
				            isclick = true;
				        }, 500);
				    }
				
			},
			add:function(nodeid){
				toUrl("project_timenode_comparison_ztedit.html?gqjdbjid="+nodeid);
			},
			computedDay:function(dtmjhwcsj,dtmsjwcsj){
				var dtmjhwcsj=dtmjhwcsj;//计划完成时间
				if(!dtmjhwcsj){
					return "";
				}
				var dtmsjwcsj=dtmsjwcsj||"";//时间完成时间
				var wcsj=dtmsjwcsj||sysDate;
				var jhwcsj=new Date(dtmjhwcsj.replace(/-/g, "/"));
					wcsj=new Date(wcsj.replace(/-/g, "/"));
					var days = jhwcsj.getTime() - wcsj.getTime();
					var day = parseInt(days / (1000 * 60 * 60 * 24));
				if(day==0){
					if(dtmsjwcsj){
						return "0";
					}else{
						return "";
					}
				}else if(day<0){
					return ""+String(day)+"天";
				}else{
					if(dtmsjwcsj){
						return "+"+String(day)+"天";
					}else{
						return "";
					}
					
				}
			},
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
				toUrl("project_timenode_comparison_zxysedit.html");
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