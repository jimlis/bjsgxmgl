var prefix =urlPath+ "sys/user";
var filePrefix=urlPath+"sys/sysFile";

$(function () {
    laydate({
        elem : '#birth'
    });

    //加载文件列表
    initFileShow("sys_user",$("#id").val());
});
/**
 * 基本信息提交
 */
$("#base_save").click(function () {
    var hobbyStr = getHobbyStr();
    //$("#hobby").val(hobbyStr);
    $("#fileIds").val(fileIds.join(","));
    if($("#basicInfoForm").valid()){
            $.ajax({
                cache : true,
                type : "POST",
                url :prefix+"/updatePeronal",
                data : $('#basicInfoForm').serialize(),
                async : false,
                dataType:"json",
                error : function(request) {
                    laryer.alert("未知错误");
                },
                success : function(data) {
                    if (data.code == 0) {
                        parent.layer.msg("更新成功");
                    } else {
                        parent.layer.alert(data.msg)
                    }
                }
            });
        }

});
$("#pwd_save").click(function () {
    if($("#modifyPwd").valid()){
        $.ajax({
            cache : true,
            type : "POST",
            url :prefix+"/resetPwd",
            data : $('#modifyPwd').serialize(),
            async : false,
            dataType:"json",
            error : function(request) {
                parent.laryer.alert("未知错误");
            },
            success : function(data) {
                if (data.code == 0) {
                    parent.layer.alert("更新密码成功");
                    $("#photo_info").click();
                } else {
                    parent.layer.alert(data.msg)
                }
            }
        });
    }
});
function getHobbyStr(){
    var hobbyStr ="";
    $(".hobby").each(function () {
        if($(this).is(":checked")){
            hobbyStr+=$(this).val()+";";
        }
    });
   return hobbyStr;
}


var fileIds=[];

layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#test1', //绑定元素
        url:filePrefix+ '/upload', //上传接口
        size: fileUploadSize,
        accept: 'images',
        data:{"busType":"sys_user"},
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
            url : filePrefix+"/remove",
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
 * 加载文件显示列表
 * @param busType  业务表名称
 * @param busId 业务id
 */
function initFileShow(busType,busId) {
    $.ajax({
        cache : true,
        type : "POST",
        url : filePrefix+"/listAll",
        data : {"busType":busType,"busId":busId},// 你的formid
        dataType:"json",
        async : false,
        error : function(request) {
            parent.layer.alert("未知错误");
        },
        success : function(r) {
            if(r.code==0){
                var $fileList=$("#fileList");
                if(r.data.length>0){
                    for (i  in r.data){
                        var file=r.data[i];
                        var fileId=file.id;
                        var fileName=file.fileName||"";
                        $fileList.append("<div id=\"" + fileId + "\" fileId=\""+fileId+"\" title=\""+fileName+"\" class=\"file-item\"><a href=\"javascript:void(0);\" style='color:blue;' onclick=\"downFile("+fileId+")\">"+ fileName + "</a>" +
                            "<a href=\"javascript:void(0);\" class=\"glyphicon glyphicon-remove\" style=\"color: red\" aria-hidden=\"false\" onclick=\"removeFile("+fileId+",this)\"></a></div>");
                    }
                }

            }else{
                layer.msg(r.msg);
            }

        }
    });

}