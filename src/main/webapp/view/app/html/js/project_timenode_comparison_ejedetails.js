var obj=getRequest();
var intxmid = getCookie("id");
var gqjdbjid=obj.gqjdbjid||"";
var chrjdlx=obj.chrjdlx||"";
var pageData ={};
var parentMap={};
var title=chrjdlx=="jc"?"基础":(chrjdlx=="zt"?"主体":"");
var sysDate=bjGetSysDate();
window.onload= function(){
	//document.getElementsByTagName("title")[0].innerText=title+"详情";
    pageData =getPageData();
	//数据绑定
	var vue = new Vue({
		el: '#app',
		data: {list:pageData,gqjdbjid:gqjdbjid,parentMap:parentMap},
		methods: {
			updateNode:function(nodeid,lx){
				toUrl("project_timenode_comparison_ejedit.html?chrjdlx="+chrjdlx+"&gqjdbjid="+nodeid+"&lx="+lx);
			},
			deleteNode:function(nodeid,event){
				if(nodeid){
					$bjAjax({
						url:timenodeDelPath+"/"+nodeid,
						data:{},
						type:"post",
						success:function(data){
							
							bjToast("删除成功！",function(){
								event.target.parentNode.parentNode.remove();
							});
						}
					});
				}else{
					bjToast("删除成功！",function(){
						event.target.parentNode.parentNode.remove();
					});
				}
			},
			computedDay:function(key,index){
				var row=this.list[key]["childList"][index];
				var dtmjhwcsj=row.dtmjhwcsj;//计划完成时间
				if(!dtmjhwcsj){
					return "";
				}
				var dtmsjwcsj=row.dtmsjwcsj||"";//时间完成时间
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
}

//得到显示数据
function getPageData(){
	var o=[];
	$bjAjax({
		url:timenodeZtListApiPath,
		type:"post",
		async:false,
		data:{
			gqjdbjid:gqjdbjid,
			xmid:intxmid,
			jdlx:chrjdlx
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
}

function outPage(){
	toUrl("project_timenode_comparison.html");
}