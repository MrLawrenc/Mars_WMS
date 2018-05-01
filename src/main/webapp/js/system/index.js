//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "." + myDay;
}

/**
 * 隐藏或者显示侧边栏
 *
 **/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) {	// flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({width: '280px'});
        $('#top_nav').css({width: '77%', left: '304px'});
        $('#main').css({left: '280px'});
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({width: '60px'});
            $('#top_nav').css({width: '100%', left: '60px', 'padding-left': '28px'});
            $('#main').css({left: '60px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({width: '280px'});
            $('#top_nav').css({width: '77%', left: '304px', 'padding-left': '0px'});
            $('#main').css({left: '280px'});
            $("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
    }
}

$(function () {
    //文档加载完毕，默认加载第一个菜单树
    loadMenu('business');

    //为菜单设置点击事件
    $("#TabPage2 li").click(function () {
        //在点击会后先将所有的class设置为空
        $.each($("#TabPage2 li"), function (index, item) {
            $(item).removeClass("selected");
            $(item).children("img").prop("src", "../../images/common/" + (index + 1) + ".jpg");
        });

        //选中谁就设置class样式和src
        $(this).addClass("selected").children("img").prop("src", "../../images/common/" + ($(this).index() + 1) + "_hover.jpg");
        //修改模块的标题
        $("#nav_module img").prop("src", "../../images/common/module_" + ($(this).index() + 1) + ".png");

        //切换菜单时加载相应的菜单树:sn点击不同的菜单使用不同的菜单树
        var sn = $(this).data("rootmenu");
        var liId=$(this).id;
        loadMenu(sn,liId);
    });
    //======================================================
    //加载时间
    loadDate();
    // 显示侧边栏
    switchSysBar(true);
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});
//=======================================zTree=============================================
var setting = {
    data: {
        simpleData: {
            enable: true//启用简单json数据格式
            /*,
            idKey: "id",
            pIdKey: "pId",
            rootPId: ""*/
        }
    },
    callback: {
        onClick: function (event, treeId, treeNode) {
            if (treeNode.action) {
                $("#here_area").html("当前位置：系统管理&nbsp;>&nbsp;" + treeNode.name);
                var url = treeNode.action + ".action";
                $("#main iframe").prop("src", url);
            }
        }
    },
    async: {
        enable: true,
        url: "/systemMenu_loadMenusByParentSn.action",
        autoParam: ['sn']
    }
};

//静态菜单树
var zNodes = {
    'business': [
        {id: 1, pId: 0, name: "业务管理", isParent: true, sn: "business"}
    ],
    'system': [
        {id: 2, pId: 0, name: "系统管理", isParent: true, sn: "system"}
    ],
    'chart': [
        {id: 3, pId: 0, name: "报表管理", isParent: true, sn: "chart"}
    ]
}

//加载菜单
function loadMenu(sn) {
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[sn]);
}