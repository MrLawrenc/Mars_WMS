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
    <title>供应商管理</title>
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
<s:form id="searchForm" namespace="/" action="supplier.action" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_bottom">
                        <s:url var="add" namespace="/" action="supplier_input">
                        </s:url>
                        <input type="button" class="ui_input_btn01 btn_add" value="新增"
                               data-url="<s:property value="#add"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>id</th>
                        <th>供应商地址</th>
                        <th>供应商名字</th>
                        <th>供应商电话</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><input type="checkbox" autocomplete="off" name="IDCheck" class="acb"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="address"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="phone"/></td>
                            <td>
                                <s:url var="editURL" namespace="/" action="supplier_input">
                                    <s:param name="supplier.id" value="id"/>
                                </s:url>
                                <a href="<s:property value="#editURL"/>">编辑</a>

                                <s:url var="deleteURL" namespace="/" action="supplier_delete">
                                    <s:param name="supplier.id" value="id"/>
                                </s:url>
                                <a href="#" class="btn_delete" data-url="<s:property value="#deleteURL"/>">删除</a>
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
</body>
</html>

