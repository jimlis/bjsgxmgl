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
				  	html +=`<li class="mui-table-view-cell mui-collapse details" zjid="`+id+`" >`+dtmbgrq+`</li>`;
				}
				ztxxDom.innerHTML=html;
			}else{
				ztxxDom.innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
			}
			var dlxx=data["2"];
			var dlxxDom=document.getElementById("dlxx");
			var dlArr=getXmjdListByParam(xmid, "jc", "1", "");
			if(dlArr&&dlArr.length>0){
				var html="";
				 for(i in dlArr){
					 var dlObj=dlArr[i];
					 var dlMc=dlObj.text||"";
					 var dlId=dlObj.value||"";
					 var num=0;
					 if(dlxx){
						 for(key in dlxx){
							 if(key==dlId){
								 num+=dlxx[key].length;
							 }
						 }
					 }
					 html +=`<li dlId="`+dlId+`" class="mui-table-view-cell mui-collapse mleft10 dlli" >`+dlMc+`<span  class="mui-badge mui-badge-primary" style="right:35px">`+num+`</span></li>`;
					 html +=`<ul name="dlul" id="`+dlId+`" class="mui-table-view  mleft30 " style="display:none">`;
					 if(dlxx){
						 for(key in dlxx){
								if(key==dlId){
									for(j in dlxx[key]){
										dlnum++;
										var dtmbgrq=dlxx[key][j]["dtmbgrq"];
										var zpjdid=dlxx[key][j]["id"];
									  	html +=`<li class="mui-table-view-cell details" zjid="`+zpjdid+`" >`+dtmbgrq+`</li>`;
									}
								}
							}
					 }
					 html +=`</ul>`;
				 }
				 dlxxDom.innerHTML=html;
			}else{
				dlxxDom.innerHTML=`<li class="mui-table-view-cell mui-collapse" >没有相关数据，请上传数据</li>`;
			}
			/*if(dlxx){
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
			}*/
			
			var gcys=data["3"];
			var gcysDom=document.getElementById("gcys");
			if(gcys){
				var html="";
				ycgcObj=gcys;
				for(key in gcys){
					 html +=`<li gcysKey="`+key+`"  class="mui-table-view-cell mui-collapse mleft10 gcysli" >`+key+`</li>`;
					 html +=`<ul name="ycgcul" id="`+key+`" class="mui-table-view  mleft30 " style="display:none">`;
					 for(j in gcys[key]){
						 ysnum++;
						 var dtmbgrq=gcys[key][j]["dtmbgrq"];
					     var zpjdid=gcys[key][j]["id"];
						 html +=`<li class="mui-table-view-cell mui-collapse details" zjid="`+zpjdid+`" >`+dtmbgrq+`</li>`;
					 }
					 html +=`</ul>`;
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


mui("#list").on('tap',".details",function (event) {
	var zjid=event.target.getAttribute("zjid");
	openNext(zjid);
});

mui("#list").on('tap',".dlli",function (event) {
	var dlid=event.target.getAttribute("dlid");
	showNext(dlid);
});

mui("#list").on('tap',".gcysli",function (event) {
	var gcysKey=event.target.getAttribute("gcysKey");
	showGcysNext(gcysKey);
});

var temp=0;
function showNext(dlid){
	if(temp==dlid){
		document.getElementById(dlid).style.display="none";
		temp=0;
		return;
	}else{
		temp=dlid;
	}
	var dlul=document.getElementsByName("dlul");
	if(dlul&&dlul.length>0){
		for(var i=0;i<dlul.length;i++){
			dlul[i].style.display="none";
		}
		document.getElementById(dlid).style.display="block";
	}
}

var temp1=0;
function showGcysNext(key){
	if(temp1==key){
		document.getElementById(key).style.display="none";
		temp1=0;
		return;
	}else{
		temp1=key;
	}
	var ycgcul=document.getElementsByName("ycgcul");
	if(ycgcul&&ycgcul.length>0){
		for(var i=0;i<ycgcul.length;i++){
			ycgcul[i].style.display="none";
		}
		document.getElementById(key).style.display="block";
	}
}



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
			openNext(key);
			return;
		}else if(lx==2){
			openNext(key);
			return;
		}
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