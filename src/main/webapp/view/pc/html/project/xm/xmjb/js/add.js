var prefix =urlPath+"project/xmjb";
var dictPrefix=urlPath+"/sys/sysDict";
var filePrefix=urlPath+"sys/sysFile";
$().ready(function() {
    initSel("intxmlx",dictPrefix+"/list/xmlx",{},"name","value");

	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
    if(!validataLd()){
        return;
    }
    $("#fileIds").val(fileIds);
    $("#ldJson").val(getLdJson());
    $("#delLdIds").val(delLdIds.length>0?delLdIds.substring(0,delLdIds.length-1):delLdIds);
    $.ajax({
		cache : true,
		type : "POST",
		url : prefix+"/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
        dataType:"json",
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
            chrxmmc : {
				required : true
			}
		},
		messages : {
            chrxmmc : {
				required : icon + "项目名称不能为空"
			}
		}
	})
}

var fileIds=[];
layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test1', //绑定元素
        url: filePrefix+'/upload', //上传接口
        size: fileUploadSize,
        accept: 'file',
        data:{"busType":"bj_xmjb"},
        done: function (r) {
            if(r.code==0){
                var file=r.data;
                var fileId=file.id;
                var fileName=file.fileName||"";
                fileIds.push(fileId);
                var $fileList=$("#fileList");
                $fileList.append("<div id=\"" + fileId + "\" fileId=\""+fileId+"\" title=\""+fileName+"\" class=\"file-item\"><a href=\"javascript:void(0);\" style='color:blue;' onclick=\"downFile("+fileId+")\">"+ fileName + "</a>" +
                    "<a href=\"javascript:void(0);\" class=\"glyphicon glyphicon-remove\" style=\"color: red\" aria-hidden=\"false\" onclick=\"removeFile("+fileId+",this)\"></a></div>");
                $fileList.append("");
            }else{
                layer.msg(r.msg);
            }
        },
        error: function (r) {
            layer.msg(r.msg);
        }
    });
});

function  downFile(id) {
    if($("#downForm").length>0){
        $("#downForm").remove();
    }
    var $form=$('<form id="downForm" method="post" action="'+filePrefix+'/downFile/'+id+'"></form>');
    $(document.body).append($form);
    $form.submit();
}

/**
 * 删除文件
 * @param id
 */
function  removeFile(id,obj) {
    layer.confirm('删除不可以恢复，确定删除文件？', {
        btn : [ '确定', '取消' ],title: "提示"//按钮
    }, function(index) {
        $.ajax({
            cache : true,
            type : "POST",
            url :filePrefix+ "/remove",
            data : {"id":id},// 你的formid
            dataType:"json",
            async : false,
            error : function(request) {
                parent.layer.alert("未知错误");
            },
            success : function(data) {
                if (data.code == 0) {
                    debugger;
                    var index = $.inArray(id,fileIds);
                    if(index>=0){//存在 就删除
                        fileIds.splice(index,1);
                    }
                    $(obj).parent().remove();
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
        layer.close(index);
    });

}

/**
 * 新增楼
 */
function addLd() {
    var html=$("<div style=\"margin-bottom: 5px\"><input type=\"hidden\" name=\"xmldid\"></div>");
    html.append("排序<em class=\"gg-star\">*</em>：<input name=\"intxh\" type=\"number\"   style=\"width: 45px\"/>").
            append("&nbsp;栋楼名称<em class=\"gg-star\">*</em>：<input  name=\"chrdlmc\" type=\"text\"  maxlength=\"50\" style=\"width: 110px\"/>").
            append("&nbsp;栋楼层数<em class=\"gg-star\">*</em>：<input name=\"intcs\" type=\"number\"  style=\"width: 45px\"/>层").
            append("&nbsp;&nbsp;&nbsp; <a class=\"a-hand\" onclick=\"delLd(this,'')\">删除</a>");
    $("#ldDiv").append(html);
}

/**
 * 获取楼栋的json字符串
 * @returns {string}
 */
function getLdJson() {
    var arr=[];
    $("#ldDiv").find("div").each(function () {
                var id=$(this).find("input[name='xmldid']").val();
                var intxh=$(this).find("input[name='intxh']").val();
                var chrdlmc=$(this).find("input[name='chrdlmc']").val();
                var intcs=$(this).find("input[name='intcs']").val();
                var obj={};
                obj.id=id||null;
                obj.intxh=intxh;
                obj.chrdlmc=chrdlmc;
                obj.intcs=intcs;
                arr.push(obj);
    });
    return  JSON.stringify(arr);
}

/**
 * 校验楼栋属性不能为空
 */
function validataLd() {
    var flag=true;
    $("#ldDiv").find("div").each(function () {
        if(!flag){
            return;
        }

        var intxh=$(this).find("input[name='intxh']").val();
        if(isEmpty(intxh)){
            flag=false;
            $(this).find("input[name='intxh']").focus();
            layer.msg("排序不能空");
           return;
        }
        var chrdlmc=$(this).find("input[name='chrdlmc']").val();
        if(isEmpty(chrdlmc)){
            flag=false;
            $(this).find("input[name='chrdlmc']").focus();
            layer.msg("楼栋名称不能空");
            return;
        }
        var intcs=$(this).find("input[name='intcs']").val();
        if(isEmpty(intcs)){
            flag=false;
            $(this).find("input[name='intcs']").focus();
            layer.msg("层数不能空");
            return;
        }
    });

    return flag;
}

var delLdIds="";

/**
 * 删除楼栋
 * @param obj dom对象
 * @param id xmldid
 */
function  delLd(obj,id) {
        $(obj).parent().remove();
        if(isNotEmpty(id)){
            delLdIds+=id+",";
        }
}