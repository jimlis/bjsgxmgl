var obj = getRequest();
var id=obj.id||"";
var dwData=[];
var bgbhData=[];
var intxmid = getCookie('id');//intxmid
//初始化显示数据
var pageData={};
var isCk=hasPermission("bj:ckje");
var bjje=hasPermission("bj:bjje");
window.onload = function(){
	showEdit();
	//得到数据
	pageData = getPageData();
	pageData["isCk"] = isCk;
	dwData=getXmdwmdData(intxmid,pageData.intdwlx);
	
	getBgbhList(pageData.intdwlx,pageData.intdwmcid,id);
	
	getHtje();
	
	getQsAndJe(pageData.intdwlx,pageData.intdwmcid,id);
	
	getGgjeSum();//设置潜在变更总金额
	
    //数据绑定
	var vue = new Vue({
		el: '#app',
		data: pageData,
		computed: {
			    BA: function () {
			    	var B=this.intqzbgzje;
			    	var A=this.inthtje;
			      return A?new Number((new Number(B)/new Number(A))).toFixed(2):0;
			    },
			    DE:function(){
			    	var D=this.intbqsqje;
			    	var E=this.intbqhsffje;
			    	return E?new Number((new Number(D)/new Number(E))).toFixed(2):0;
			    },
			    CA:function(){
			    	var C=this.yfje;
			    	var A=this.inthtje;
			    	return A?new Number((new Number(C)/new Number(A))).toFixed(2):0;
			    },
			    CEA:function(){
			    	var C=this.yfje;
			    	var E=this.intbqhsffje;
			    	var A=this.inthtje;
			    	return A?new Number(((new Number(C)+new Number(E))/new Number(A))).toFixed(2):0;
			    },
			    CAB:function(){
			    	var C=this.yfje;
			    	var A=this.inthtje;
			    	var B=this.intqzbgzje;
			    	if(!A)return 0;
			    	if(!B)return 0;
			    	return new Number((new Number(C)/(new Number(A)+new Number(B)))).toFixed(2);
			    },
			    CEAB:function(){
			    	var C=this.yfje;
			    	var E=this.intbqhsffje;
			    	var A=this.inthtje;
			    	var B=this.intqzbgzje;
			    	if(!A)return 0;
			    	if(!B)return 0;
			    	return new Number(((new Number(C)+new Number(E))/(new Number(A)+new Number(B)))).toFixed(2);
			    }
			 }
	});
	
	if(id){
		initFileList("bj_xm_gckyzfqk",id,"1","fileIds","file-list",false);
	}

}
//是否具有编辑金额权限
function showEdit(){
	var editBtn=document.getElementById("editBtn");
	if(editBtn){
		if(bjje){
			editBtn.style.display="inline-block";
		}else{
			editBtn.style.display="none";
		}
	}
	return bjje;
}
//获取变更情况
function getBgbhList(bgsqlx,dwmcid,nowId){
	if(bgsqlx){
		$bjAjax({
			url:changeApiList,
			type:"post",
			async:false,
			data:{
				xmid:intxmid,
				bgsqlx:bgsqlx,
				dwmcid:dwmcid,
				bgthid:-2,
				nowBgsqjlId:(nowId||"")
			},
			success:function(data){
				if(data){
					bgbhData=data;
					for(i in bgbhData){
						bgbhData[i].text=bgbhData[i].chrbgsqbh||"";
						bgbhData[i].value=bgbhData[i].id||"";
					}
				}
			}
		});
	}else{
		bgbhData=[];
	}
}

function getHtje(){
	if(id){
		if(dwData){
			for(i in dwData){
				if(dwData[i].value==pageData.intdwmcid){
					pageData.inthtje=dwData[i].inthtje||0;
					break;
				}
			}
		}
	}else{
		pageData.inthtje=0
	}
}

//获取发放期数和金额
function getQsAndJe(dwlx,dwmcid,xmgczfid){
	var map={};
	if(dwlx&&dwmcid){
		$bjAjax({
			url:payApiGetQsAndJe,
			type:"post",
			async:false,
			data:{
				xmid:intxmid,
				dwlx:dwlx,
				dwmcid:dwmcid,
				xmgczfid:(xmgczfid||"")
			},
			success:function(data){
				if(data){
					map=data;
				}
			}
		});
	}
	pageData.yfqs=(map.yfqs||0);
	pageData.yfje=(map.yfje||0);
}

/**
 * 设置潜在变更金额
 * @returns
 */
function getGgjeSum(){
	if(bgbhData){
		var intbggs=0;
		for(i in bgbhData){
			var intsfqd=pageData.intsfqd||"";
			//if((!(intsfqd==1&&intbgthid==bgbhData[i].value))){
			//if(bgbhData[i].intsfqd==0){
				intbggs+=Number(bgbhData[i].intbggs||0);
			//}
		}
		pageData.intqzbgzje=intbggs;
		return;
	}
	pageData.intqzbgzje=0;
}

//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:payApiDetail,
		type:"post",
		async:false,
		data:{
			xmGckyzfqkId:id
		},
		success:function(data){
			if(data){
				o=data;
			}
		}
	});
	return o;
}

function edit(){
	toUrl("project_pay_record_add.html?id="+id);
}

mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_pay_record.html");
}