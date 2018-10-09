var request=getRequest();
var xmid=request.xmid||"";

/**
 *详情
 */
function openDetails(id){
    var address = "project_gov_record_details.html?id="+id+"&xmid="+xmid;
    toUrl(address);
}

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
    $bj_post_ajax({"url":xmzfxcyzxysApiPath+"getXmZfxcyzxys","data":{"xmid":xmid,"xcbm":xcbm,"xclb":xclb},success:function (result) {
                    if(result&&result.length>0){
                        var html="";
                         for (i in result){
                             var obj=result[i];
                             var dtmxcrq=obj.dtmxcrq||"";
                                    dtmxcrq=dtmxcrq.length>10?dtmxcrq.substring(0,10):dtmxcrq;
                             var id=obj.id;
                             html+=" <li class=\"mui-table-view-cell mui-collapse\" onclick=\"openDetails('"+id+"')\">"+dtmxcrq+"</li>";
                         }
                        dom.nextSibling.nextSibling.innerHTML=html;
                    }else{
                        dom.nextSibling.nextSibling.innerHTML="";
                    }

        }});
}