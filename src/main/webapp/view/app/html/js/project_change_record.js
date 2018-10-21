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
    		"bgsqlx":type
    	},
    	success:function (data) {
    		var html="";
    		mui.each(data,function(index,item){
    			var dtmgxrq = item.dtmgxrq||"";
    			var id = item.id||"";
    			html+=`
    				<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`</li>
    			`;
			})
			document.getElementById(type).innerHTML=html;
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


function outPage(){
	toUrl("project_detail_list.html");
}