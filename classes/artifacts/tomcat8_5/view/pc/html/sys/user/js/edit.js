var prefix = urlPath+"sys/user";
var deptPrefix=urlPath+"sys/dept";
var filePrefix=urlPath+"sys/sysFile";
// 以下为官方示例
$().ready(function() {
	validateRule();

    //加载文件列表
    initFileShow("sys_user",$("#id").val());
	// $("#signupForm").validate();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
    $("#fileIds").val(fileIds.join(","));
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix+"/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		dataType: 'json',
		error : function(request) {
			alert("未知错误");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function setCheckedRoles() {
	var roleIds = $("#roleIds").val();
	alert(roleIds);
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
            mobile : {
				required : true,
                mobile : true
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			email:{
                email:true
            },
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required",
            orderNum:{digits:true}
		},
		messages : {

			name : {
				required : icon + "请输入姓名"
			},
            mobile : {
				required : icon + "请输入您的手机号",
                mobile : icon + "请输入正确的手机号"
			},
			password : {
				required : icon + "请输入您的密码",
				minlength : icon + "密码必须6个字符以上"
			},
			confirm_password : {
				required : icon + "请再次输入密码",
				minlength : icon + "密码必须6个字符以上",
				equalTo : icon + "两次输入的密码不一致"
			},
            email:{
                email: icon + "请输入正确的邮箱"
            },
            orderNum:{digits:icon+"排序号必须为整数"}
		}
	})
}
var openDept = function(){
	layer.open({
		type:2,
		title:"选择部门",
		area : [ '300px', '450px' ],
		content:deptPrefix+"/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
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