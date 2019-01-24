var xmid=getCookie("id");

//初始化显示数据
window.onload = function(){
	showEdit();
	var pageData = getPageData();
	var gw=getDataByDwlx(pageData,"1");
	var sg=getDataByDwlx(pageData,"2");
	var qt=getDataByDwlx(pageData,"3");
	var vue = new Vue({
		el: '#app',
		data: {gw:gw,sg:sg,qt:qt},
		methods: {
			openDetail: function (id) {
				var address = "project_pay_record_detail.html?id="+id;
    				toUrl(address);
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