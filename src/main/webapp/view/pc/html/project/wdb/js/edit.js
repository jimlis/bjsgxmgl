var prefix=urlPath+"project/wdb";
var dictPrefix=urlPath+"sys/sysDict";
var xmjbPrefix=urlPath+"project/xmjb";
var filePrefix=urlPath+"sys/sysFile";
$().ready(function() {

	//初始化类型下拉框
    initSel("type",dictPrefix+"/list/wjlx",{},"name","value");
    $('#type').selectpicker('val', $("#typeValue").val());

    //初始化项目下拉框
    initSel("xmid",xmjbPrefix+"/listAll",{},"chrxmmc","id");
    $('#xmid').selectpicker('val', $("#xmidValue").val());

    //加载文件列表
    initFileShow("bj_wdb",$("#id").val());

	validateRule();

});

function typeOnchang(obj){
    var text = obj.options[obj.selectedIndex].innerText;
    $("#typeName").val(text);
}

function xmOnchang(obj){
    var text ="";
    if(obj.options[obj.selectedIndex]!=null){
        text=obj.options[obj.selectedIndex].innerText;
    }
    $("#xmmc").val(text);
}

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    $("#fileIds").val(fileIds.join(","));

	$.ajax({
		cache : true,
		type : "POST",
		url : prefix+"/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
        dataType:"json",
		error : function(request) {
			parent.layer.alert("未知错误");
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
            fileName : {
				required : true
			}
		},
		messages : {
            fileName : {
				required : icon + "请输入文件名称"
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
        url: '/common/sysFile/upload', //上传接口
        size: fileUploadSize,
        accept: 'file',
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
            async : false,
            dataType:"json",
            error : function(request) {
                parent.layer.alert("未知错误");
            },
            success : function(data) {
                if (data.code == 0) {
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
        async : false,
        dataType:"json",
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
