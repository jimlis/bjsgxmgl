$bjAjax({
	url:noticeApiPath+"list",
	data:{
		
	},
	type:'post',
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		if(data.code==0){
			var array = data.data;
				console.log(array);
			mui.each(array,function(index,item){
				var id = item.id;
				var chrbt = item.chrbt;
				var dtmfbsj = item.dtmfbsj;
				var chrdjrbmmc = item.chrdjrbmmc;
				var chrdjrmc = item.chrdjrmc;
				mui(".mui-content")[0].innerHTML +=
                    '<div class="mui-card" onclick="openNext('+id+',\''+chrbt+'\')"><div class="mui-card-header" id="title">'+chrbt+'</div>'+
					<!--页脚，放置补充信息或支持的操作-->
					'<div class="mui-card-footer"><span id="time">'+dtmfbsj+'</span><span id="name">'+chrdjrbmmc+'/'+chrdjrmc+'</span></div></div>';
			})
		}else{
			
			mui(".mui-content .mui-table-view-cell")[0].innerHTML=data.msg;
		}
	},
	err:function(xhr,type,errorThrown){
		//异常处理；
		console.log(type);
		mui(".mui-content .mui-table-view-cell")[0].innerHTML='获取数据失败！错误码:'+type;

	}
});


/**
 * 跳转公告详情
 */
function openNext(id,chrbt){
		toUrl("notice_detail.html?id="+id+"&chrbt="+chrbt);
}