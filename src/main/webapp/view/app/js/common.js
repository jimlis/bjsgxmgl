
/**服务端地址*/
var serverPath="http://127.0.0.1:8080/bjsgxmgl/";
var userApiPath=serverPath+"api/user/";
var deptApiPath=serverPath+"api/dept/";
var fileApiPath=serverPath+"api/file/";
var noticeApiPath=serverPath+"api/gsgg/";
var imgApiPath = serverPath+"api/file/down/";
var dictApiPath = serverPath+"api/dict/";
var commonApiPath = serverPath+"api/common/";
var xmzfxcyzxysApiPath = serverPath+"api/xmzfxcyzxys/";

function bjToast(data){
	 mui.toast(data);
}
/**
 * 将null undefined  "null" 转换为 ""
 * @param {Object} str 字符串
 */ 
function nullToEmpty(str){
	return (str==null||typeof(str)=="undefined"||str=="null")?"":str;

}

/**
 * 判断字符是否为空
 * @param {Object} str 字符串
 * @return true-空 false-不为空
 */
function isEmpty(str){
	return nullToEmpty(str)=="";
}

/**
 * 判断字符是否不为空
 * @param {Object} str
 *  @return true-不为空 false-空
 */
function isNotEmpty(str){
	return !isEmpty(str);
}
/**
 * 打开链接，进行跳转，传入跳转地址
 * */
function toUrl(url){
	var enUrl = encodeURI(url);
	window.location.href=enUrl;
}

function getRequest(){
	var url=window.location.search;
	      url = decodeURI(url);
	var theRequest = new Object();
	if ( url.indexOf( "?" ) != -1 ) {
	  var str = url.substr( 1 ); //substr()方法返回从参数值开始到结束的字符串；
	  var strs = str.split( "&" );
	  for ( var i = 0; i < strs.length; i++ ) {
	    theRequest[ strs[ i ].split( "=" )[ 0 ] ] = ( strs[ i ].split( "=" )[ 1 ] );
	  }
	  //console.log( theRequest ); //此时的theRequest就是我们需要的参数；
	}
	return theRequest;
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/**
 *根据文件名称选择打开方式
 * @param fileId 文件id
 * @param fileName 文件名称
 */
function openFileByName(fileId,fileName) {
	if(fileName.slice(-".pdf".length)==".pdf"){//pdf文件
		var url = "open_file.html?id="+fileId+"&fileName="+fileName;
        toUrl(url);
	}else{
		window.open(fileApiPath+"/down/"+fileId);
	}
}
/**
 *封装ajax访问。
 */
$bjAjax = function(obj){
	var headers={'Content-Type':'application/x-www-form-urlencoded'};
	var token=getLocalTokenValue("token");
	if(token){
        headers["Authorization"]=token;
	}
	mui.ajax(obj.url,{
	data:obj.data,
	dataType:'json',//服务器返回json格式数据
	type:obj.type,//HTTP请求类型
	timeout:10000,//超时时间设置为10秒；
	headers:headers,
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		var code=data.code;
		var msg=data.msg;
		if(code==0){
            obj.success(data.data);
		}else if(code==2){//重新登录
		localStorage.removeItem("token");
		localStorage.removeItem("user");
		window.location.href="login/login.html";
		}else{
            bjToast(msg);
		}

	},
	error:function(xhr,type,errorThrown){
		//异常处理；
        bjToast(xhr)
	}
	})
}

$bj_get_ajax=function (obj) {
    obj.type="GET";
    $bjAjax(obj);
}

$bj_post_ajax=function (obj) {
    obj.type="POST";
    $bjAjax(obj);
}

/**
 *日期选择器
 */
function dtPicker(selecter){
	var result = mui(selecter);
	result.each(function(i, btn) {
		btn.addEventListener('tap', function() {
			var _self = this;
			if(_self.picker) {
				_self.picker.show(function (rs) {
					_self.value = rs.text;
					_self.picker.dispose();
					_self.picker = null;
				});
			} else {
				var optionsJson = this.getAttribute('data-options') || '{}';
				var options = JSON.parse(optionsJson);
				var id = this.getAttribute('id');
				/*
				 * 首次显示时实例化组件
				 * 示例为了简洁，将 options 放在了按钮的 dom 上
				 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
				 */
				_self.picker = new mui.DtPicker(options);
				_self.picker.show(function(rs) {
					/*
					 * rs.value 拼合后的 value
					 * rs.text 拼合后的 text
					 * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
					 * rs.m 月，用法同年
					 * rs.d 日，用法同年
					 * rs.h 时，用法同年
					 * rs.i 分（minutes 的第二个字母），用法同年
					 */
					_self.value = rs.text;
					/* 
					 * 返回 false 可以阻止选择框的关闭
					 * return false;
					 */
					/*
					 * 释放组件资源，释放后将将不能再操作组件
					 * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
					 * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
					 * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
					 */
					_self.picker.dispose();
					_self.picker = null;
				});
			}
			
		}, false);
	});
}
/*
*选择框
*@param  textSelecter 文本显示domid
*@param  data  点击文本显示的下拉框数据
*@param  valueSelecter  选择文本存放隐藏至的domid
* @param  funResult  自定义返回函数
**/
function relPicker(textSelecter,data,valueSelecter,funResult){
	var userPicker = new mui.PopPicker();
	userPicker.setData(data);
	var showUserPickerButton = document.getElementById(textSelecter);
	showUserPickerButton.addEventListener('tap', function(event) {
		userPicker.show(function(items) {
			showUserPickerButton.value = items[0].text.replace(/^\"|\"$/g,'');
			if(valueSelecter){
                var valueInput = document.getElementById(valueSelecter);
                      valueInput.value=items[0].value.replace(/^\"|\"$/g,'');
			}
			if(funResult){
				funResult(items[0]);
			}
			//返回 false 可以阻止选择框的关闭
			//return false;
		});
	}, false);
}

/*照片懒加载1*/
var createFragment = function(count,src) {
    var fragment = document.createDocumentFragment();
    var ul = document.createElement('ul');
    ul.className = 'mui-table-view mui-table-view-chevron mui-grid-view';
    var li;
    for(var i = 0; i < count; i++) {
        li = document.createElement('li');
        li.className = 'mui-table-view-cell mui-media mui-col-xs-4';
        li.innerHTML = `<a>
							<img class="mui-media-object mui-pull-left" data-lazyload="`+src+`">
						</a>`;
        ul.appendChild(li);
    }
    fragment.appendChild(ul);
    return fragment;
};

/*照片懒加载1*/
var createFragment111 = function(files) {
	var fragment = document.createDocumentFragment();
	var ul = document.createElement('ul');
	ul.className = 'mui-table-view mui-table-view-chevron mui-grid-view';
	var li;
	for(var i = 0; i < files; i++) {
		var obj=files[i];
		var src=fileApiPath+"down/"+obj.id;
		li = document.createElement('li');
		li.className = 'mui-table-view-cell mui-media mui-col-xs-4';
		li.innerHTML ='<a><img class="mui-media-object mui-pull-left" data-lazyload="'+src+'"></a>';
		ul.appendChild(li);
	}
	fragment.appendChild(ul);
	return fragment;
};

/*照片懒加载2*/
function funLazyLoad(select){
	var lazyLoad = mui(select).imageLazyload({
    placeholder: '../images/bj_building.png',
    destroy: false
	});
	return lazyLoad;
}
/*
*图片上传
*@param  elem “选择文件”按钮的ID
* @param  bind “上传”按钮的ID
* @param  data 自定义提交数据
* @param  done 上传完成后函数
**/
function upLoadImg(elem,bind,data,done){
	layui.use('upload', function(){
	var upload = layui.upload;
	//选完文件后不自动上传（js代码，将文件传到后台）
	upload.render({
		elem: elem				//“选择文件”按钮的ID
		,url: fileApiPath+"upload"	//后台接收地址
		,data: data		//传递到后台的数据
		,auto: false				//不自动上传设置
		,accept: 'file'				 //允许上传的文件类型
		,exts: 'png|jpg|bmp' 			//设置智能上传图片格式文件
		,size: 5000 				//最大允许上传的文件大小
		,multiple: true				//设置是否多个文件上传
		,bindAction: bind		//“上传”按钮的ID
		,choose: function(obj){
			//将每次选择的文件追加到文件队列
    			var files = obj.pushFile();
		    //预读本地文件示例，不支持ie8
			obj.preview(function(index, file, result){		//在当前ID为“demo2”的区域显示图片
				console.log(index); //得到文件索引
      			console.log(file); //得到文件对象
				//$('#img-list').append(`<img class="bj-img-temp" src="`+ result +`" alt="`+ file.name +`">`)
				document.getElementById("img-list").innerHTML+=`<img class="bj-img-temp" src="`+ result +`" alt="`+ file.name +`">`;
			});
		}
		,done: done||function(res) {
            	uploadDone(res,"fileIds","img-list")
       		 }
		});
	});
}

/**
 * 图片上传后回调
 * @param result 上传成功返回reslut对象
 * @param fileIdsDomId 文件id串存放的domid
 * @param showListDomId 图片列表显示domid
 */
function  uploadDone(result,fileIdsDomId,showListDomId) {
    var code=result.code;
    var data=result.data;
    var msg=result.msg;
    if(code==0){//成功
        var $fileIds=mui("#"+fileIdsDomId);
        var fileId=data.id;
        var fileName=data.fileName||"";
        if($fileIds.length>0){
            var fileIds=$fileIds[0].value;
            if(isEmpty(fileIds)){
                fileIds=fileId;
            }else{
                fileIds+=","+fileId;
            }
            $fileIds[0].value=fileIds;
        }
        var src=fileApiPath+"down/"+fileId;
        var div = document.createElement('div');
        	   div.innerHTML='<img class="bj-img-temp" src="'+ src +'" alt="'+fileName +'">' +
            '<a href=\"javascript:void(0);\" class=\"glyphicon glyphicon-remove\" style=\"color: red\" aria-hidden=\"false\" onclick=\"removeFile(\''+fileId+'\',\''+fileIdsDomId+'\',this)\">x</a>';
        mui('#'+showListDomId)[0].appendChild(div);
    }else{
    	bjToast(msg);
	}
}

/**
 * 删除文件
 * @param id
 */
function  removeFile(id,fileIdsDomId,obj) {
	mui.confirm('删除不可以恢复，确定删除文件？',"提示",['确定','取消'],function(data){
		var index=data.index;
		if(index==0){//确定
            $bj_post_ajax({"url":fileApiPath+ "/del/"+id,success:function (result) {
                    var $fileIds=mui("#"+fileIdsDomId);
                    if($fileIds.length>0){
                        var fileIds=$fileIds[0].value;
                               fileIds=fileIds.split(",");
                        var index = $.inArray(id,fileIds);
                        if(index>=0){//存在 就删除
                            fileIds.splice(index,1);
                            $fileIds[0].value=fileIds.join(",");
                        }
                        obj.parentNode.remove();
                    }
                }});
		}

	});
}

/**
 * 根据key获取token的属性值
 * @param key
 */
function getLocalTokenValue(key) {
	var token=JSON.parse(localStorage.getItem("token"));
	return (token&&token[key])||"";
}

/**
 * 根据key获取user的属性值
 * @param key
 */
function getLocalUserValue(key) {
    var token=JSON.parse(localStorage.getItem("user"));
    return (token&&token[key])||"";
}

/**
 * 根据key获取token的属性值
 * @param key
 */
function geCookieTokenValue(key) {
    var token=JSON.parse(window.getCookie("token"));
    return (token&&token[key])||"";
}

/**
 * 根据key获取user的属性值
 * @param key
 */
function getCookieUserValue(key) {
    var token=JSON.parse(window.getCookie("user"));
    return (token&&token[key])||"";
}

function getFromData(form){
    var form=document.getElementById(form);
    var data={};
    for (var i = 0; i < form.elements.length; i++) {
        var element=form.elements[i];
        switch(element.type) {
            case 'text':
                data[element.name]=element.value;
            	break;
            case 'checkbox':
                if (element.checked) {
                    if (data[element.name]) {
                        data[element.name]=data[element.name]+','+element.value;
                    }else{
                        data[element.name]=element.value;
                    }
				}
                break;
            case 'radio':
                if (element.checked) {
                    data[element.name]=element.value;
                }
                break;
			case 'textarea':
                data[element.name]=element.innerHTML;
            default:
                data[element.name]=element.value;
        }
    }
    return data;
}

/**
 * 是否拥有该权限
 * @param bs 权限标识
 * @returns {boolean} true 拥有 false 不拥有
 */
function hasPermission(bs){
	return true;
}