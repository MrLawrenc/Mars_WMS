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
    <script type="text/javascript">
        $(function () {
            $(":input").prop("readonly", true);
        });
    </script>
</head>
<body>
<%--===================================================================--%>
<%@include file="../common/common_msg.jsp" %>
<%--===================================================================--%>
<s:form name="editForm" action="orderBill_saveOrUpdate.action" method="post" id="editForm">
    <s:hidden name="orderBill.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">采购订单的编辑</span>
            <div id="page_close">
                <a>
                    <img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">订单编号</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">供应商</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <s:if test="orderBill.id==null">
                            <div style="color: #ff0507" align="center">注意：请按行 逐一添加明细，并填写好数量及单价！</div>
                        </s:if>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                                <%--编辑的页面--%>
                            <s:iterator value="orderBill.items">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02"
                                                     tag="name" name="product.name"/>
                                        <s:hidden name="product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"><s:property value="product.brand.name"/></span></td>
                                    <td><s:textfield tag="costPrice" name="product.costPrice"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><s:textfield tag="number" name="number"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td><span tag="amount"><s:property value="amount"/></span></td>
                                    <td><s:textfield tag="remark" name="remark"
                                                     cssClass="ui_input_txt02"/></td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                        <div align="right" style="color:red">审核人：<s:property value="orderBill.auditor.name"/>&nbsp;&nbsp;&nbsp;审核时间：<s:property
                                value="orderBill.auditTime"/> &nbsp;&nbsp;制单人：<s:property
                                value="orderBill.inputUser.name"/>&nbsp;&nbsp;&nbsp;商品总金额：<s:property
                                value="orderBill.totalAmount"/></div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="返回列表" class="ui_input_btn01 btn_submit"
                                     onclick="window.history.back()"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>