/**
 * 根据xmid和lxmd获取单位mingd
 * @param xmid 项目id
 * @param lxmd 类型 类型名单：1（顾问单位），2（施工单位），3（其他工作单位名称）
 * @return [{"text":"","value":""},{}]
 */
function getXmdwmdData(xmid,lxmd){
	var arr=[];
	$bj_post_ajax({
		url:baseApiDwmdList,
		data:{"xmid":xmid,"lxmd":lxmd},
		async:false,
		success:function(data){
			if(data&&data.length>0){
				for(i in data){
					var obj={};
					obj["text"]=data[i].chrdwmc||"";
					obj["value"]=data[i].id||"";
					arr.push(obj);
				}
			}
		}
	});
	return arr;
}