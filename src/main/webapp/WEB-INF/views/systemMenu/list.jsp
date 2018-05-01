<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="js/plugins/artDialog/jquery.artDialog.js?skin=default"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <title>系统菜单管理</title>
    <style>
        .alt td {
            background: black !important;
        }
    </style>
</head>
<body>
<%--===================================================================--%>
<%@include file="../common/common_msg.jsp" %>
<%--===================================================================--%>
<s:form id="searchForm" namespace="/" action="systemMenu.action" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <s:url var="add" namespace="/" action="systemMenu_input">
                            <s:param name="qo.parentId" value="qo.parentId"></s:param>
                        </s:url>
                        <input type="button" class="ui_input_btn01 btn_add" value="新增"
                               data-url="<s:property value="#add"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            当前位置：&emsp;<s:a namespace="/" action="systemMenu.action" style="color:blue;">根目录</s:a>
            <s:iterator value="#menus">
                --><s:a namespace="/" action="systemMenu.action" style="color:blue;">
                <s:property value="name"/>
                <s:param name="qo.parentId" value="id"/>
            </s:a>
            </s:iterator>
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>菜单编码</th>
                        <th>菜单名称</th>
                        <th>上级菜单</th>
                        <th>URL</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><input type="checkbox" autocomplete="off" name="IDCheck" class="acb"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="name"/></td>
                            <s:if test="parent==null">
                                <td>根目录</td>
                            </s:if><s:else>
                            <td><s:property value="parent.name"/></td>
                        </s:else>
                            <td><s:property value="url"/></td>
                            <td>
                                <s:a namespace="/" action="systemMenu_input">编辑
                                    <s:param name="systemMenu.id" value="id"/>
                                    <s:param name="qo.parentId" value="qo.parentId"/>
                                </s:a>

                                <s:url var="deleteURL" namespace="/" action="systemMenu_delete">
                                    <s:param name="systemMenu.id" value="id"/>
                                </s:url>
                                <a href="#" class="btn_delete" data-url="<s:property value="#deleteURL"/>">删除</a>

                                <s:a namespace="/" action="systemMenu.action"><s:param name="qo.parentId"
                                                                                       value="id"></s:param>查看子菜单</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </div>
                <%--引入分页条--%>
            <%@include file="../common/common_page.jsp" %>
        </div>
    </div>
</s:form>
<s:debug/>
</body>
</html>

