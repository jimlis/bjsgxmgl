
var xmid = getCookie("id");
function showList(type,typeID){
	$bj_post_ajax({
    	"url":materialApiList,
    	"data":{
    		"xmid":xmid,
    		"xcbm":type
    	},
    	success:function (data) {
    		bjConsole(data)
    		mui.each(data,function(index,item){
    			var dtmgxrq = item.dtmgxrq
    			var id = item.id
    			document.getElementById(xcbm).innerHTML+=`
    				<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`</li>
    			`;
			})
        }
    });
}
/**
 *详情
 */
function openDetails(id){
    var address = "project_material_record_details.html?id="+id;
    toUrl(address);
}

/**
 *新增
 */
function add(){
    var address = "project_material_record_add.html";
    toUrl(address);
}
