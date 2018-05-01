/*
* 禁用将表单元素数组或着对象序列化
* */
$.ajaxSettings.traditional = true;
/** table鼠标悬停换色* */
$(function () {
    // 如果鼠标移到行上时，执行函数
    $(".table tr").mouseover(function () {
        $(this).css({background: "#CDDAEB"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#1D1E21"});
        });
    }).mouseout(function () {
        $(this).css({background: "#FFF"});
        $(this).children('td').each(function (index, ele) {
            $(ele).css({color: "#909090"});
        });
    });
});
/*
* 点击按钮，打开新增界面
* */
$(function () {
    $(".btn_add").click(function () {
        var url = $(".btn_add").data("url");
        window.location.href = url;
        alert(1)
    });
});
/*
* 高级查询和分页
* */
$(function () {
    //限定跳转框的那个页码限制
    $(":input[name='qo.currentPage']").prop("type", "number").css("width", "50").prop("max", $(".totalPage").data("page")).prop("min", 1);
    //翻页
    $(".btn_page").click(function () {
        var pageNo = $(this).data("page");
        //将值设置到跳转的文本框(qo.currentPage文本框)
        $(":input[name='qo.currentPage']").val(pageNo);
        //提交表单
        $("#searchForm").submit();
    });
    //分页条数
    $(":input[name='qo.pageSize']").change(function () {
        $("#searchForm").submit();
    });
    //手动跳转到的页数
    $(".ui_input_btn01").click(function () {
        $("#searchForm").submit();
    });
});
/*
* 删除操作
* */
$(function () {
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        $.dialog({
            title: "操作提示",
            content: "你确定要删除吗？",
            icon: "face-smile",
            ok: function () {
                $.get(url, function (data) {
                    $.dialog({
                        title: "操作提示",
                        content: data,
                        icon: "face-smile",
                        ok: function () {
                            window.location.reload();
                        }
                    });
                });
            },
            cancel: true
        });
    });
});
$(function () {
    $("#all").click(function () {
        $(".acb").prop("checked", $("#all").prop("checked"));
    });
    //点击批量删除按钮
    $(".btn_deleteAll").click(function () {
        //被选中需要删除的id
        /*var ids = [];
        $.each($(".acb:checked"), function (index, item) {
            ids[index] = $(item).data("eid");
        })*/
        var ids = $.map($(".acb:checked"), function (item) {
            return $(item).data("eid");
        })
        var url = $(".btn_deleteAll").data("url");
        if (ids.length == 0) {
            $.dialog({
                title: "操作提示",
                icon: "face-smile",
                content: "请选择需要删除的数据!",
                ok: true
            });
            return;
        }
        $.dialog({
            title: "操作提示",
            icon: "face-smile",
            content: "确认需要删除这些数据吗?",
            cancel: true,
            ok: function () {
                $.get(url, {ids: ids}, function () {
                    window.location.reload();//重新加载当前文档
                });
            }
        });

    });
});

