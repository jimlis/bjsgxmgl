showEdit();
var obj=getRequest();
var id = obj.id||"";
var did = obj.did||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var dlhData;
window.onload = function(){
	
	//dlhData=getXmjdListByParam(xmid,'jc',1,"");
	
	//判断是否更新；
	pageData = isUpdata();
	
	//数据绑定
	vue = new Vue({
		el: '#app',
		data: pageData,
		methods: {
			dlhPicker:function(event){
				vuePicker(pageData,"chrdmc",dlhData,"intdid",null);
			},
			addlc:function(event){
				var wclTableTbody=document.getElementById("wclTableTbody");
				var lcnum=document.getElementById("lcnum").value;
				var tr=document.createElement("tr");
				tr.innerHTML=''+
                         ' <td><input  class="bj-input bj-p-black-font" type="hidden" />'+
                         '<input id="xmSgjdEcjgzxWclList['+lcnum+'].chrlc"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intmc"  class="bj-input bj-p-black-font" type="number" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intfs"  class="bj-input bj-p-black-font" type="number" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intnbw"  class="bj-input bj-p-black-font" type="number" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intqt"  class="bj-input bj-p-black-font" type="number" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intzx"  class="bj-input bj-p-black-font" type="number" /></td>'+
                         ' <td><button type="button" class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="deletelc(\'\',this)">-</button></td>'+
                         '';
				wclTableTbody.appendChild(tr);
				document.getElementById("lcnum").value=parseInt(lcnum)+1;
			},
			v_deletelc:function(id,obj){
				 ;
				deletelc(id,obj.currentTarget);
			}
		}
	});
	
}

var deleteIds="";
function deletelc(id,obj){
	if(id){
		deleteIds+=id+",";
	}
	obj.parentNode.parentNode.remove();
}

//判断是否更新
function isUpdata(){
		var result={};
		$bjAjax({
			url:progressSecByParamApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdEcjgzxId:id,
				xmid:xmid,
				did:did,
				fwlx:"xz"
			},
			success:function(data){
				data["intbgrid"]=chrdlrid;
				data["chrbgrmc"]=chrdlrmc;
				result = data;
				result["dtmgxrq"]=sysdate;
			}
		});
		return result;
}
//创建数据Model
function buildModel(){
	var model = {
		id:id,
		intxmid:xmid,
		dtmgxrq:sysdate,
		intdid:'',
		chrdmc:'',
		chrbz:'',
		xmSgjdEcjgzxWclList:[],
		intbgrid:chrdlrid,
		chrbgrmc:chrdlrmc
	}
	return model;
}

//保存数据
function save(){
isSure(function(){
	var data = getFromData("myform");
	data.deleteWclIds=(deleteIds.length>0?deleteIds.substring(0,deleteIds.length-1):deleteIds);
	$bjAjax({
		url:progressSecSaveApiPath,
		data:data,
		type:"post",
		success:function(result){
			bjToast("保存成功！",function(){
				toUrl("project_progress_record_secDetail.html?id="+result.id);
			});
		}
	});
})
}
function outPage(){
	toUrl("project_progress_record_secDetail.html?id="+id);
}