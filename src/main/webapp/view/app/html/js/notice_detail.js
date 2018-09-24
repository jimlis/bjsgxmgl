
var id = getRequest(location.search).id;
$bjAjax({
	url:noticeApiPath+"details",
	data:{
		id:id
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		if(data.code==0){
			var data = data.data;
			console.log(data);
			var id = data.id;
			var chrbt = data.chrbt;
			var dtmfbsj = data.dtmfbsj;
			var chrdjrbmmc = data.chrdjrbmmc;
			var chrdjrmc = data.chrdjrmc;
			var chrggnr = data.chrggnr;
			var fileList = data.fileList;
			mui(".mui-content .mui-content-padded")[0].innerHTML +=`
				<h3>`+chrbt+`</h3>
				<h6>
					<span>`+dtmfbsj+`</span>
					&nbsp;
					<span>`+chrdjrbmmc+`/`+chrdjrmc+`</span>
				</h6>
				`+chrggnr
			mui.each(fileList,function(index,item){
				mui(".mui-content .filelist")[0].innerHTML +=`
				<a onclick="openNext(`+item.id+`)">`+item.fileName+`</a>
				`
			})
		}else{
			//mui(".mui-content .mui-table-view-cell")[0].innerHTML=data.msg;
		}
	},
	err:function(xhr,type,errorThrown){
		//异常处理；
		console.log(type);
		mui(".mui-content .mui-table-view-cell")[0].innerHTML='获取数据失败！错误码:'+type;

	}
});

/**
 * 跳转文件详情
 */
function openNext(id){
		window.location.href="open_file.html?id="+id;
}