var prefix = urlPath+"/project/splcZt";
var dictPrefix=urlPath+"/sys/sysDict";
$().ready(function() {
	initSel("chrsplclx",dictPrefix+"/list/splc",{},"name","value");
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
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
			intxh : {
				required : true
			},
			chrsprmc : {
				required : true
			},
			chrsplclx: {
				required : true
			}
		},
		messages : {
			intxh : {
				required : icon + "请输入序号"
			},
			chrsprmc : {
				required : icon + "请输入审批人名称"
			},
			chrsplclx : {
				required : icon + "请选择审批流程类型"
			}
		}
	})
}