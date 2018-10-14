var xmid=getCookie("id");
/**
 *新增
 */
function openAdd(){
    var address = "project_gov_record_add.html?id=&xmid="+xmid;
    toUrl(address);
}

/**
 * 显示巡查信息
 * @param xcbm 巡查部门
 * @param xclb 巡查类别
 */
function showList(dom,xcbm,xclb) {
    $bj_post_ajax({
    	"url":xmzfxcyzxysApiPath,
    	"data":{
    		"xmid":xmid,
    		"xcbm":xcbm,
    		"xclb":xclb
    	},
    	success:function (data) {
    		bjConsole(data)
    		mui.each(data,function(index,item){
    			var dtmxcrq = item.dtmxcrq
    			var id = item.id
    			document.getElementById(xcbm).innerHTML+=`
    				<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmxcrq+`</li>
    			`;
			})
        }
    });
}
/**
 *详情
 */
function openDetails(id){
    var address = "project_gov_record_details.html?id="+id;
    toUrl(address);
}
