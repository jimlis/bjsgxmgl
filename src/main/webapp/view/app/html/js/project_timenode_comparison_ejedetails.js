var obj=getRequest();
var intxmid = getCookie("id");
var gqjdbjid=obj.gqjdbjid||"";
var chrjdlx=obj.chrjdlx||"";
var pageData ={};
var parentMap={};
var title=chrjdlx=="jc"?"基础":(chrjdlx=="zt"?"主体":"");
window.onload= function(){
	//document.getElementsByTagName("title")[0].innerText=title+"详情";
    pageData =getPageData();
	//数据绑定
	var vue = new Vue({
		el: '#app',
		data: {map:pageData,gqjdbjid:gqjdbjid,parentMap:parentMap},
		methods: {
			updateNode:function(nodeid){
				toUrl("project_timenode_comparison_ejedit.html?chrjdlx="+chrjdlx+"&gqjdbjid="+nodeid);
			},
			deleteNode:function(nodeid,lx,event){
				if(nodeid){
					$bjAjax({
						url:timenodeDelPath+"/"+nodeid,
						data:{},
						type:"post",
						success:function(data){
							
							bjToast("删除成功！",function(){
								event.target.parentNode.parentNode.remove();
								if(lx==1){//删除子类型
									var nodes=document.getElementsByName(nodeid);
									for(i in nodes){
										nodes[0].remove();
									}
								}
							});
						}
					});
				}else{
					bjToast("删除成功！",function(){
						event.target.parentNode.parentNode.remove();
						if(lx==1){//删除子类型
							var nodes=document.getElementsByName(nodeid);
							for(i in nodes){
								nodes[i].remove();
							}
						}
					});
				}
			}
		}
	});
}

//得到显示数据
function getPageData(){
	var o=[];
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
	for(i in o){
		if(!o[i].intfjdid){//父节点
			parentMap[o[i].id]=o[i];
		}
	}
	
	var arr={};
	for(key in parentMap){
		var list=arr[key]||[];
		for(i in o){
			if(o[i].intfjdid==key){//父节点
				    list.push(o[i]);
			}
		}
		arr[key]=list;
	}
	return arr;
}

function outPage(){
	toUrl("project_timenode_comparison.html");
}