var xmid = getCookie("id");
var dlxxObj={};
var ycgcObj={};
var dlData=getXmdlListByXmid(xmid);
$bjAjax({
	url:photoApiList,
	type:"post",
	data:{
		xmid:xmid
	},
	success:function(data){
		var ztnum=0;var dlnum=0; var ysnum=0;
		if(data){
			var ztxx=data["1"];
			var ztxxDom=document.getElementById("ztxx");
			if(ztxx&&ztxx.length>0){
				var html="";
				for(i in ztxx){
					ztnum++;
					var dtmbgrq = ztxx[i].dtmbgrq||"";
				  	var id = ztxx[i].id||"";
				  	html +=`<li class="mui-table-view-cell mui-collapse" onclick="openNext(`+id+`)">`+dtmbgrq+`</li>`;
				}
				ztxxDom.innerHTML=html;
			}else{
				ztxxDom.innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
			}
			var dlxx=data["2"];
			var dlxxDom=document.getElementById("dlxx");
			if(dlxx){
				var html="";
				dlxxObj=dlxx;
				for(key in dlxx){
					dlnum++;
					var dlMc=getDlMc(dlData,key);
				  	html +=`<li class="mui-table-view-cell mui-collapse" onclick="selectDate('`+key+`','1')">`+dlMc+`</li>`;
				}
				dlxxDom.innerHTML=html;
			}else{
				dlxxDom.innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
			}
			
			var gcys=data["3"];
			var gcysDom=document.getElementById("gcys");
			if(gcys){
				var html="";
				ycgcObj=gcys;
				for(key in gcys){
					ysnum++;
				  	html +=`<li class="mui-table-view-cell mui-collapse" onclick="selectDate('`+key+`','2')">`+key+`</li>`;
				}
				gcysDom.innerHTML=html;
			}else{
				gcysDom.innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
			}
		}
		mui("#zttempspan")[0].innerText=ztnum;
		mui("#dltempspan")[0].innerText=dlnum;
		mui("#ystempspan")[0].innerText=ysnum;
	}
});


tyclClick("#list");

function getDlMc(data,dlid){
	if(!data||data.length==0){
		return "";
	}
	for( i in data){
		if(data[i].value==dlid){
			return data[i].text||"";
		}
	}
	return "";
}


function selectDate(key,lx){
	var nowData=[];
		if(lx==1){
			nowData=dlxxObj[key];
			for(i in nowData){
				nowData[i].text=nowData[i].dtmbgrq||"";
				nowData[i].value=nowData[i].id||"";
			}
		}else if(lx==2){
			nowData=ycgcObj[key];
			for(i in nowData){
				var intbglb=nowData[i].intbglb||"";
				var chrpswz=nowData[i].chrpswz||"";
				var chrqtms=nowData[i].chrqtms||"";
				if(intbglb==3&&chrpswz==3){
					nowData[i].text=nowData[i].dtmbgrq||""+"&nbsp;&nbsp;"+chrqtms;
				}else{
					nowData[i].text=nowData[i].dtmbgrq||"";
				}
				nowData[i].value=nowData[i].id||"";
			}
		}
		
	var userPicker = new mui.PopPicker();
	userPicker.setData(nowData);
	userPicker.show(function(items) {
		openNext(items[0].value||"");
	});
}


/**
 * 跳转项目详情列表
 */
function openNext(id){
		toUrl("project_photo_record_detail.html?id="+id);
}

function add(){
		toUrl("project_photo_record_add.html");
}
function outPage(){
	toUrl("project_detail_list.html");
}