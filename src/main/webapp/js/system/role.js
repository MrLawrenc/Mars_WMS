//角色编辑界面的权限全选
$(function () {
    //左右移动
    $("#selectAll").click(function () {
        $(".left_permissions option").appendTo($(".right_permissions"));
    });
    $("#select").click(function () {
        $(".left_permissions option:selected").appendTo($(".right_permissions"));
    });

    $("#deselectAll").click(function () {
        $(".right_permissions option").appendTo($(".left_permissions"));
    });
    $("#deselect").click(function () {
        $(".right_permissions option:selected").appendTo($(".left_permissions"));
    });


    //从权限列表中去除已经分配的权限
    var ids = $.map($(".right_permissions option"), function (item) {
        return item.value;
    });
    //迭代出所有权限列表中的所有选项option
    $.each($(".left_permissions option"), function (index, item) {
        var id = item.value;//左边每一个选项的id值
        //判断是否和右边id数组中的值相等
        if ($.inArray(id, ids) >= 0) {
            $(item).remove();
        }
    });
});
//=======================================================================================


//角色编辑界面的菜单全选
$(function () {
    //左右移动
    $("#mselectAll").click(function () {
        $(".left_menus_permissions option").appendTo($(".right_menus_permissions"));
    });
    $("#mselect").click(function () {
        $(".left_menus_permissions option:selected").appendTo($(".right_menus_permissions"));
    });

    $("#mdeselectAll").click(function () {
        $(".right_menus_permissions option").appendTo($(".left_menus_permissions"));
    });
    $("#mdeselect").click(function () {
        $(".right_menus_permissions option:selected").appendTo($(".left_menus_permissions"));
    });

    var ids = $.map($(".right_menus_permissions option"), function (item) {
        return item.value;
    });
    $.each($(".left_menus_permissions option"), function (index, item) {
        var id = item.value;//左边每一个选项的id值
        //判断是否和右边id数组中的值相等
        if ($.inArray(id, ids) >= 0) {
            $(item).remove();
        }
    });
});
$(function () {
    $("#editForm").submit(function () {
        //在提交表单之前，把有右边所有的都设置为选中状态，这样才能提交
        $(".right_permissions option").prop("selected", true);
        $(".right_menus_permissions option").prop("selected", true);
    });
});
