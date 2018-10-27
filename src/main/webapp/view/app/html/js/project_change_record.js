var xmid = getCookie("id");
window.onload=function(){
	showList("1");
	showList("2");
	showList("3");
	tyclClick("#list");
}

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
    		if(!data||data.length==0){
    			document.getElementById(type).innerHTML +=`<li class="mui-table-view-cell mui-collapse"  >没有相关数据，请上传数据</li>`;
    			document.getElementById(type+"Span").innerText=0;
    			return;
    		}
    		var html="";
    		mui.each(data,function(index,item){
    			var dtmgxrq = item.dtmgxrq||"";
    			var id = item.id||"";
    			html+=`
    				<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+dtmgxrq+`</li>
    			`;
			})
			document.getElementById(type).innerHTML=html;
    		document.getElementById(type+"Span").innerText=data.length;
    		//tyclClick("#list");
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