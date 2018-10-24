var obj=getRequest();
var intxmid = getCookie("id");
var gqjdbjid=obj.gqjdbjid||"";
var chrjdlx=obj.chrjdlx||"";
var pageData ={};
window.onload= function(){
    pageData =getPageData();
	//数据绑定
	var vue = new Vue({
		el: '#app',
		data: {list:pageData,gqjdbjid:gqjdbjid},
		methods: {
			updateNode:function(nodeid){
				toUrl("project_timenode_comparison_yjedit.html?chrjdlx="+chrjdlx+"&gqjdbjid="+nodeid);
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
			}
		}
	});
}

//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:timenodeListApiPath,
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