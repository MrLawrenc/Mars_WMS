//加载权限
$(function () {
    $(".btn_permission").click(function () {
        var url=$(".btn_permission").data("url");
        $.dialog({
            title: "操作提示",
            content: "加载权限时间较长，请耐心等待!",
            icon: "face-smile",
            ok:function () {
                $.get(url,function () {
                    $.dialog({
                        title: "操作提示",
                        content: "权限加载成功!",
                        icon: "face-smile",
                        ok:function () {
                            window.location.reload();
                        }
                    });
                });
            },
            cancel:true
        });
    });
});