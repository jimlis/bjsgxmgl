var obj = getRequest();
var id=obj.id||"";
$bjAjax({
	url:materialApiDetail,
	type:"post",
	data:{
		xmClybspjlId:id
	},
	success:function(data){
		document.getElementById("dtmgxrq").innerText=data.dtmgxrq||"";
		document.getElementById("dtmbgrq").innerText=data.dtmbgrq||"";
		document.getElementById("intclyblx").innerText=data.chrclyblx||"";
		document.getElementById("intsgdw").innerText=data.chrsgdw||"";
		var intsfdtp=data.intsfdtp||"";
		document.getElementById("intsfdtp").innerText=data.chrsfdtp||"";
		document.getElementById("chrybmc").innerText=data.chrybmc||"";
		document.getElementById("chrybwz").innerText=data.chrybwz||"";
		document.getElementById("chrppmc").innerText=data.chrppmc||"";
		document.getElementById("chrgfbz").innerText=data.chrgfbz||"";
		document.getElementById("chrbz").innerText=data.chrbz||"";
		document.getElementById("chrsplczt").innerText=data.chrsplczt||"";
		document.getElementById("dtmspztrq").innerText=data.dtmspztrq||"";
		initFileList("bj_xm_clybspjl",id,"1","fileIds","file-list",false);
		var xmClybspjlJszlList=data.xmClybspjlJszlList||[];
		var html="";
		var tempOne="";
		var tempTwo="";
		var index=0;
		 ;
		for(i in xmClybspjlJszlList){
			var jszlObj=xmClybspjlJszlList[i];
			var chrpp=jszlObj.chrpp||"";
			var chrjscl=jszlObj.chrjscl||"";
//			if(i%3==0){
//				tempOne="<tr style=\"height: 20px\"><td>"+chrpp+"</td>";
//				tempTwo="<tr style=\"height: 20px\"><td>"+chrjscl+"</td>";
//				index=0;
//			}else if(i%3==1){
//				tempOne+="<td>"+chrpp+"</td>";
//				tempTwo+="<td>"+chrjscl+"</td>";
//				index=1;
//			}if(i%3==2){
//				tempOne+="<td>"+chrpp+"</td></tr>";
//				tempTwo+="<td>"+chrjscl+"</td></tr>";
//				index=2;
//				html+=tempOne+tempTwo;
//			}
			html+=` <tr>
				      <td data-label="品牌">`+chrpp+`</td>
				      <td data-label="技术资料">`+chrjscl+`</td>
					</tr>`
		}
//		if(index==0){
//			tempOne+="<td></td>";
//			tempTwo+="<td></td>";
//			index=1;
//		}
//		if(index==1){
//			tempOne+="<td></td></tr>";
//			tempTwo+="<td></td></tr>";
//			html+=tempOne+tempTwo;
//		}
		document.getElementById("jszlNr").innerHTML=html;
	}
});
/*
*编辑
 */
function  edit() {
    var address = "project_material_record_add.html?id="+id;
    toUrl(address);
}
function outPage(){
	toUrl("project_material_record.html");
}