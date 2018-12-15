//初始化必要条件
var xmid=getCookie("id");
var isclick= true;
var vue;
var dtShwoArr=[];
var oldDtIndex=-1;
window.onload=function(){
	//得到数据
	var pageData = getDl();
	if(pageData){
		for(i in pageData){
			dtShwoArr.push(false);
		}
	}
	var qt={};
	var qqbj=[];
	//绑定数据
	 vue = new Vue({
		el: '#app',
		data: {
			qqShow:false,
			jcShow:false,
			ztShow:false,
			ecShow:false,
			dtShow:dtShwoArr,
			dtList:[],
			dls:pageData,
			qts:qt,
			qqbj:qqbj
			
		},
		beforeCreate: function(){
			$bjAjax({
				url:progressJcsgPath+"getXmSgjdJcsgQtListByXmid",
				data:{
					xmid:xmid
				},
				type:"post",
				success:function(result){
					
					qt["jc"]=result;
					console.log(qt);
				}
			});
			$bjAjax({
				url:progressZtjgPath+"getXmSgjdZtjgsgQtListByXmid",
				data:{
					xmid:xmid
				},
				type:"post",
				success:function(result){
					
					qt["zt"]=result;
					console.log(qt);
				}
			});
			$bjAjax({
				url:progressSecQtListByXmidApiPath,
				data:{
					xmid:xmid
				},
				type:"post",
				success:function(result){
					
					qt["ec"]=result;
					console.log(qt);
				}
			});
			$bjAjax({
				url:timenodeListApiPath,
				type:"post",
				data:{
					xmid:xmid,
					jdlx:'qqbj'
				},
				success:function(data){
					if(data){
						vue.$data.qqbj=data||[];
					}
				}
			});
		},
		methods: {
			toggle:function(type){
			    if(isclick){
			        isclick= false;
					switch (type){
						case 'qq':
						this.qqShow = !this.qqShow;
							break;
						case 'jc':
						this.jcShow = !this.jcShow;
							break;
						case 'zt':
						this.ztShow = !this.ztShow;
							break;
						case 'ec':
						this.ecShow = !this.ecShow;
							break;
						case 'dt':
						this.dtShow = !this.dtShow;
							break;
						default:
							break;
					}
			 setTimeout(function(){ 
				            isclick = true;
				        }, 500);
				    }
				
			},
			addQT:function(type){
				console.log(type)
				var address;
				if(type=="JC"){
					address = "project_progress_record_baseAdd.html?intsgwzid=-1&chrsgwzmc=其他";
				}else if(type=="ZT"){
					address = "project_progress_record_bodyAdd.html?intsgwzid=-1&chrsgwzmc=其他";
				}else if(type=="EC"){
					address = "project_progress_record_secqtAdd.html";
				}
				
    				toUrl(address);
			},
			openQqbj: function (gqjdid,chrjdmc) {
				var address = "project_progress_record_qqbjDetail.html?gqjdid="+gqjdid+"&chrjdmc="+chrjdmc;
    				toUrl(address);
			},
			openJC: function (sgwzid,chrsgwzmc) {
				var address = "project_progress_record_baseDetail.html?sgwzid="+sgwzid+"&chrsgwzmc="+chrsgwzmc;
    				toUrl(address);
			},
			openZT: function (sgwzid,chrsgwzmc) {
				var address = "project_progress_record_bodyDetail.html?sgwzid="+sgwzid+"&chrsgwz="+chrsgwzmc;;
    				toUrl(address);
			},
			openEC: function (did,chrdmc) {
				var address = "project_progress_record_secDetail.html?did="+did;
    				toUrl(address);
			},
			openDTbh: function (sgwz,index) {
				if(oldDtIndex==index){
					vue.$data.dtList=[];
					oldDtIndex=-1;
				}else{
					oldDtIndex=index;
					//加载数据
					$bjAjax({
						url:progressElevatorGetListApiPath,
						type:"post",
						data:{
							xmid:xmid,
							sgwz:sgwz
						},
						success:function(data){
							if(data){
								vue.$data.dtList=data||[];
								for(i in vue.$data.dtShow){
									if(i==index){
										vue.$data.dtShow[i]=true;
									}else{
										vue.$data.dtShow[i]=false;
									}
								}
							}
						}
					});
				}
			},
			openDT: function (id,sgwz,dtbh) {
				var address = "project_progress_record_elevatorDetail.html?sgwz="+sgwz+"&dtbh="+dtbh+"&id="+id;
    				toUrl(address);
			},
			openSW: function () {
				var address = "project_progress_record_outdoorDetail.html";
    				toUrl(address);
			},
			openYL: function () {
				var address = "project_progress_record_gardenDetail.html";
    				toUrl(address);
			},
			openQT:function(type,sgwzid,id){
				var address;
				if(type=="jc"){
					address = "project_progress_record_baseDetail.html?sgwzid="+sgwzid+"&jcid="+id;
				}else if(type=="zt"){
					address = "project_progress_record_bodyDetail.html?sgwzid="+sgwzid+"&jcid="+id;
				}else if(type=="ec"){
					address = "project_progress_record_secqtDetail.html?id="+id;
				}
				toUrl(address);
			}
		}
	});
	
	tyclClick("#app");
	mui(".mui-table-view-cell").on('tap','.addbtn',function(){
	  	var type = this.getAttribute("type");
	  	var address;
	  	if(type=="JC"){
			address = "project_progress_record_baseAdd.html?intsgwzid=-1&chrsgwzmc=其他";
		}else if(type=="ZT"){
			address = "project_progress_record_bodyAdd.html?intsgwzid=-1&chrsgwzmc=其他";
		}else if(type=="EC"){
			address = "project_progress_record_secqtAdd.html";
		}else if(type=="DT"){
			var sgwz = this.getAttribute("sgwz");
			var dtbh = this.getAttribute("dtbh");
		    address = "project_progress_record_elevatorAdd.html?sgwz="+sgwz+"&dtbh="+dtbh;
		}
		toUrl(address);
	});
}
//得到【栋楼】数据
function getDl(){
	return getXmjdListByParam(xmid,'jc',1,"");
}
mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}