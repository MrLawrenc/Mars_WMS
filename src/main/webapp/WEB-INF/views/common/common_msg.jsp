<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:if test="hasActionMessages()">
    <s:actionmessage/>
</s:if>--%>
<script type="text/javascript">
    <s:if test="hasActionMessages()">
    var msg = '<s:property value="actionMessages[0]"/>';
    $.dialog({
        title: "操作提示",
        content: msg,
        icon: "face-smile",
        ok: true
    });
    </s:if>
    <s:if test="hasActionErrors()">
    var msg = '<s:property value="actionErrors[0]"/>';
    $.dialog({
        title: "操作提示",
        content: msg,
        icon: "face-sad",
        ok: true
    });
    </s:if>
</script>
