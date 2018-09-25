var prefix = urlPath+"sys/user";
var deptPrefix=urlPath+"sys/dept";
var filePrefix=urlPath+"sys/sysFile";
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
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
function save() {
    $("#fileIds").val(fileIds.join(","));
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix+"/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		dataType: 'json',
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
			name : {
				required : true
			},
            mobile : {
				required : true,
                mobile : true,
				remote : {
					url : prefix+"/exit", // 后台处理程序
					type : "post", // 数据发送方式
					dataType : "json", // 接受数据格式
					data : { // 要传递的数据
                        mobile : function() {
							return $("#mobile").val();
						}
					}
				}
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
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
            email:{
                email:true
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
                mobile  : icon + "请输入正确的手机号",
				remote : icon + "手机号已经存在"
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
        url: filePrefix+'/upload', //上传接口
        size: fileUploadSize,
        accept: 'file',
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
