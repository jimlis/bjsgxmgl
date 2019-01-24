var xmid = getCookie("id");
window.onload=function(){
	showEdit();
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
    			document.getElementById(type+"GreSpan").innerText=0;
    			document.getElementById(type+"RedSpan").innerText=0;
    			return;
    		}
    		var html="",greNum=0,redNum=0;
    		mui.each(data,function(index,item){
    			var dtmgxrq = item.dtmgxrq||"";
    			var chrbgsqmc = item.chrbgsqmc||"";
    			var chrbgsqbh = item.chrbgsqbh||"";
    			var id = item.id||"";
    			var chrspzt = item.chrspzt;
    			if(chrspzt!='wwc'){
    				greNum++;
			    }else {//未完成就是正在审批
			    	redNum++;
			    }
    			html+=`
    				<li class="mui-table-view-cell mui-collapse" onclick="openDetails(`+id+`)">`+chrbgsqbh+"&nbsp;"+chrbgsqmc+`</li>
    			`;
			})
			document.getElementById(type).innerHTML=html;
    		document.getElementById(type+"Span").innerText=data.length;
    		document.getElementById(type+"GreSpan").innerText=greNum;
			document.getElementById(type+"RedSpan").innerText=redNum;
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

mui.back=function(){
	 outPage();
	 console.log("123");
}
function outPage(){
	toUrl("project_detail_list.html");
}