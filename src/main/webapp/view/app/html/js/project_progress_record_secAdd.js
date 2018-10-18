var obj=getRequest()
var id = obj.id||"";
var xmid=getCookie("id");
var chrdlrid = getCookie('chrdlrid');//chrbgrmc
var chrdlrmc = getCookie('chrdlrmc');//chrbgrmc
var sysdate=bjGetSysDate();
var pageData;
var vue;
var dlhData;
window.onload = function(){
	
	dlhData=getXmdlListByXmid(xmid);
	
	//判断是否更新；
	pageData = isUpdata()||'';
	if(pageData==''){
		//创建数据Model；
		pageData = buildModel();
	}
	
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
				var html=' <tr >'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].id"  class="bj-input bj-p-black-font" type="hidden" />'+
                         '<input id="xmSgjdEcjgzxWclList['+lcnum+'].chrlc"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intmc"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intfs"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intnbw"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intqt"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><input id="xmSgjdEcjgzxWclList['+lcnum+'].intzx"  class="bj-input bj-p-black-font" type="text" /></td>'+
                         ' <td><button type="button" class="mui-btn mui-btn-danger" style="margin-top: 3px;" onclick="deletelc(\'\',this)">删除</button></td>'+
                         '</tr>';
				wclTableTbody.innerHTML+=html;
				document.getElementById("lcnum").value=parseInt(lcnum)+1;
			},
			v_deletelc:function(id,obj){
				debugger;
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
	if(id){
		var result={};
		$bjAjax({
			url:progressSecByIdApiPath,
			type:"post",
			async:false,
			data:{
				xmSgjdEcjgzxId:id
			},
			success:function(data){
				result = data;
			}
		});
		return result;
	}
	return '';
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
}
