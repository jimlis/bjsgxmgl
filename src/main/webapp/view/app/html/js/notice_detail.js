
var obj = getRequest(location.search);
mui("title")[0].innerText = obj.chrbt;
mui("header .mui-title")[0].innerHTML = obj.chrbt;
$bjAjax({
	url:noticeApiPath+"details",
	data:{
		id:obj.id
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		var data = data;
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
	},
});

/**
 * 跳转文件详情
 */
function openNext(id){
		toUrl("open_file.html?id="+id);
}