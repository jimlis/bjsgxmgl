var fileUploadSize=1024*20;
/**
 * 公共js
 */
//公共参数
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName=window.document.location.pathname;
//获取带"/"的项目名，如：/uimcardprj
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

var urlPath  = location.origin+projectName+"/";
//处理URL参数（在跟服务器在同一项目下时用）
if (!window.location.origin) {
    urlPath = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ':' + window.location.port: '')+projectName+"/";
}else{
    urlPath = location.origin+projectName+"/";
}

/**
 * 通用的下拉框初始方法
 * @param domid 下拉框dom节点id
 * @param url 请求url
 * @param data  参数
 * @param keyName  返回key名称
 * @param valueName 返回value名称
 */
function  initSel(domid,url,data,keyName,valueName) {
    $.ajax({
        url:url,
        type:"POST",
        dataType:"json",
        data:data||{},
        async : false,
        success:function(result){
            if(result&&result.length>0){
                var $dom=$("#"+domid);
                for(i in result){
                    var obj=result[i];
                    keyName=keyName||"key";
                    valueName=valueName||"value";
                    $dom.append("<option value='"+(obj[valueName]||"")+"'>"+(obj[keyName]||"")+"</option>");
                }
                $dom.selectpicker('refresh');
            }
        }
    });
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

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile = /^1[345789]\d{9}$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");


