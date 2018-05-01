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
    <script type="text/javascript" src="/js/system/employee.js"></script>
    <title>PSS-账户管理</title>
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
<s:form id="searchForm" namespace="/" action="employee.action" method="post">
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        姓名/邮箱
                        <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                        所属部门
                        <s:select list="#depts" name="qo.deptId" listKey="id" listValue="name" cssClass="ui_select01"
                                  headerKey="-1" headerValue="所有部门">
                        </s:select>
                    </div>
                    <div id="box_bottom">
                        <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>

                        <s:url var="add" namespace="/" action="employee_input">
                        </s:url>
                        <input type="button" class="ui_input_btn01 btn_add" value="新增"
                               data-url="<s:property value="#add"/>"/>

                        <s:url var="deleteAllURL" namespace="/" action="employee_batchDelete">
                        </s:url>
                        <input type="button" class="btn_deleteAll" value="批量删除"
                               data-url="<s:property value="#deleteAllURL"/>"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th width="30"><input type="checkbox" id="all"/></th>
                        <th>编号</th>
                        <th>用户名</th>
                        <th>EMAIL</th>
                        <th>年龄</th>
                        <th>所属部门</th>
                        <th>角色</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td><input type="checkbox" autocomplete="off" name="IDCheck" class="acb"
                                       data-eid="<s:property value="id"/>"/>
                            </td>
                            <td><s:property value="id"/></td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="email"/></td>
                            <td><s:property value="age"/></td>
                            <td><s:property value="dept.name"/></td>
                            <td><s:property value="roleNames"/></td>
                            <td>
                                <s:url var="editURL" namespace="/" action="employee_input">
                                    <s:param name="employee.id" value="id"/>
                                </s:url>
                                <a href="<s:property value="#editURL"/>">编辑</a>

                                <s:url var="deleteURL" namespace="/" action="employee_delete">
                                    <s:param name="employee.id" value="id"/>
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

