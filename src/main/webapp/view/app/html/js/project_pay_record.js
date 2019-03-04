var xmid=getCookie("id");
var gwShowArr={};
var sgShowArr={};
var qtShowArr={};
var oldtimestamp=0;
//初始化显示数据
window.onload = function(){
	showEdit();
	var pageData = getPageData();
	var gw=getDataByDwlx(pageData,"1");
	var sg=getDataByDwlx(pageData,"2");
	var qt=getDataByDwlx(pageData,"3");
	var vue = new Vue({
		el: '#app',
		data: {gw:gw,gwMap:dwMap(gw,'gw'),gwShow:gwShowArr,
			sg:sg,sgMap:dwMap(sg,'sg'),sgShow:sgShowArr,
			qt:qt,qtMap:dwMap(qt,'qt'),qtShow:qtShowArr},
		methods: {
			openDetail: function (id) {
				var address = "project_pay_record_detail.html?id="+id;
    				toUrl(address);
			},
			changeSwShow:function(key){
				var newTime = new Date().getTime();
				if((newTime - oldtimestamp)>400){
					
					this.gwShow[key]=!this.gwShow[key];
					oldtimestamp = new Date().getTime();
				}
			},
			changeSgShow:function(key){
				var newTime = new Date().getTime();
				if((newTime - oldtimestamp)>400){
					this.sgShow[key]=!this.sgShow[key];
					oldtimestamp = new Date().getTime();
				}
			},
			changeQtShow:function(key){
				var newTime = new Date().getTime();
				if((newTime - oldtimestamp)>400){
					this.qtShow[key]=!this.qtShow[key];
					oldtimestamp = new Date().getTime();
				}
			}
		},
		computed: {
		    gwGre: function () {
		      return getSpztNum(this.gw,"gre");
		    },
		    gwRed: function () {
			      return getSpztNum(this.gw,"red");
			    },
		    sgGre: function () {
		      return getSpztNum(this.sg,"gre");
		    },
		    sgRed: function () {
		      return getSpztNum(this.sg,"red");
		    },
		    qtGre: function () {
			      return getSpztNum(this.qt,"gre");
			},
		    qtRed: function () {
		      return getSpztNum(this.qt,"red");
		    }
		}
	});
	tyclClick("#app");
}

function getSpztNum(arr,type){
	var num=0;
	try{
		if(arr){
			for(var i=0;i<arr.length;i++){
				var chrspzt = arr[i].chrspzt;
				if(chrspzt){
					if(type=="gre"&&chrspzt!='wwc'){
						num++;
					}else if(type=="red"&&chrspzt=='wwc'){
						num++;
					}
				}
			}
		}
	}catch(e){
		
	}
		return num;
}

function getDataByDwlx(data,dwlx){
	var arr=[];
	if(!data||data.length==0){
		return arr;
	}
	for(i in data){
		if(data[i].intdwlx==dwlx){
			arr.push(data[i]);
		}
	}
	return arr;
}

/**
 *新增
 */
function openAdd(){
    var address = "project_pay_record_add.html?id=&xmid="+xmid;
    toUrl(address);
}
//得到巡查信息数据
function getPageData(){
	var obj={};
	$bj_post_ajax({
	    	"url":payApiList,
	    	"data":{
	    		"xmid":xmid,
	    	},
	    	async:false,
	    	success:function (data) {
	    		if(data){
	    			obj=data;
	    		}
	        }
    });
	return obj;
}

/**
 *详情
 */
function openDetails(id){
    var address = "project_pay_record_details.html?id="+id;
    toUrl(address);
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}

function dwMap(list,type){
	var map={};
	if(list&&list.length>0){
		for(var i=0;i<list.length;i++){
			var o=list[i];
			var intdwmcid=o["intdwmcid"]||"";
			var chrdwmc=o["chrdwmc"]||"";
			if(map[""+intdwmcid]){
				var dwArr=map[intdwmcid];
				    dwArr.push(o);
				map[""+intdwmcid]=dwArr;
			}else{
				map[""+intdwmcid]=[o];
			}
			if(type=="gw"){
				gwShowArr[intdwmcid]=false;
			}else if(type=="sg"){
				sgShowArr[intdwmcid]=false;
			}else if(type=="qt"){
				qtShowArr[intdwmcid]=false;
			}
		}
	}
	return map;
}