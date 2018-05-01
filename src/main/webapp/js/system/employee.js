//表单校验
$(function () {
    if ($("#editForm").size() > 0) {
        $("#editForm").validate({
            //编写规则
            rules: {
                'employee.name': {
                    required: true,
                    rangelength: [2, 8]
                },
                'employee.password':
                    {
                        required: true,
                        minlength: 4,
                        maxlength: 12
                    }
            },

            //编写对应规则错误时提示的错误信息
            messages: {
                'employee.name': {
                    required: "必须填写你的账号",
                    rangelength: "账号长度必须在{0}-{1}位之间"
                }
            }, 'employee.password':
                {
                    required: "必须填写你的密码",
                    minlength: "密码长度必须大于{0}位",
                    maxlength: "密码长度必须小于{1}位"
                }

        });
    }
});
