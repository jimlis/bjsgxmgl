
/**服务端地址*/
var serverPath="http://127.0.0.1:8080/bjsgxmgl/";
var userApiPath=serverPath+"api/user/";
var deptApiPath=serverPath+"api/dept/";
var fileApiPath=serverPath+"api/file/";
var noticeApiPath=serverPath+"api/gsgg/"


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

function getRequest(url){
	var url = decodeURI(url);
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
	mui.ajax(obj.url,{
	data:obj.data,
	dataType:'json',//服务器返回json格式数据
	type:obj.type,//HTTP请求类型
	timeout:10000,//超时时间设置为10秒；
	headers:{'Content-Type':'application/x-www-form-urlencoded'},	              
	success:function(data){
		//服务器返回响应，根据响应结果，分析是否登录成功；
		obj.success(data);
	},
	error:function(xhr,type,errorThrown){
		//异常处理；
		obj.err(xhr,type,errorThrown);
	}
	})
}
