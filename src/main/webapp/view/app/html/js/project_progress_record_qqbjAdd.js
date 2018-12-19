showEdit();
var obj=getRequest();
var gqjdid=obj.gqjdid||"";
var pageData;
var vue;
var xmid=getCookie("id");
window.onload = function(){
	upLoadFile('#chbtn',{"busType":"bj_xm_sgjd_qqbj"});
	//数据绑定
	pageData=getPageData();
	vue = new Vue({
		el: '#app',
		data:pageData,
		methods: {
			dataPicker:function(domid){
				vueDtPicker(pageData,domid,false,true);
			}
		}
	});
	
	if(pageData.id){
		//加载
		initFileList("bj_xm_sgjd_qqbj",pageData.id,"1","fileIds","file-list",true);
	}
}


//得到显示数据
function getPageData(){
	var o={};
	$bjAjax({
		url:progressGetQqbjApiPath,
		type:"post",
		async:false,
		data:{
			xmid:xmid,
			gqjdid:gqjdid,
			fwlx:"xz",
		},
		success:function(data){
			if(data){
				o=data;
			}else{
				bjToast("无数据，请更新数据");
			}
		}
	});
	
	return o;
}

//保存数据
function save(){
	mui.confirm("将新增一条新的报告记录，\n是否确定更新？","提示",['是','否'],function(seletitem){
		console.log(seletitem);
		if(seletitem.index==0){
			var data = getFromData("form");
			data["id"]="";
			$bjAjax({
				url:progressQqbjSaveApiPath,
				data:data,
				type:"post",
				success:function(result){
					bjToast("保存成功！",function(){
						outPage();
					});
				}
			});
		}
	});
	
}
function outPage(){
	toUrl("project_progress_record_qqbjDetail.html?gqjdid="+gqjdid);
}


