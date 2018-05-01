<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/jquery-validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/plugins/artDialog/jquery.artDialog.js?skin=default"></script>
</head>
<body>
<%--===================================================================--%>
<%@include file="../common/common_msg.jsp" %>
<%--===================================================================--%>
<s:form name="editForm" action="${objName}_saveOrUpdate.action" method="post" id="editForm">
    <s:hidden name="${objName}.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">${className}编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <#list propNmaes as propName>
                <tr>
                    <td class="ui_text_rt" width="140">${className}${propName}</td>
                    <td class="ui_text_lt">
                        <s:textfield name="${objName}.${propName}" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                </#list>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>