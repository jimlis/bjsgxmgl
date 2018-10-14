var xmid = getCookie("id");
/**
 *新增
 */
function add(){
    var address = "project_change_record_add.html";
    toUrl(address);
}

function showList(type){
	$bj_post_ajax({
    	"url":changeApiList,
    	"data":{
    		"xmid":xmid,
    		"bgsqlx":bgsqlx
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
    var address = "project_change_record_details.html?id="+id;
    toUrl(address);
}


