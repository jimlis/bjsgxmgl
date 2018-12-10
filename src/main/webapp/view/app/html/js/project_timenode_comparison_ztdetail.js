var obj=getRequest();
var intxmid=getCookie("id");
var pageData = [];
var vue ;
var sysDate=bjGetSysDate();
window.onload= function(){
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: {
			pageData:pageData
		},
		beforeCreate: function(){
			$bjAjax({
				url:timenodeZtListApiPath,
				type:"post",
				//async:false,
				data:{
					gqjdbjid:"",
					xmid:intxmid,
					jdlx:'zt'
				},
				success:function(data){
					if(data){
						vue.$data.pageData = data;
					}
				}
			});
		},
		methods: {
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
			}
		}
	});
	tyclClick("#app");
}

//得到显示数据
function getPageData(){
	var o=[];
	
	return o;
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_timenode_comparison.html");
}