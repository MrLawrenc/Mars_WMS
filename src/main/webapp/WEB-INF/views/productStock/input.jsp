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
    <script type="javascript" type="text/javascript" src="../../../js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        //清空一行的明细
        function clearTrData(tr) {
            tr.find("[tag=pid]").val("");
            tr.find("[tag=name]").val("");
            tr.find("[tag=costPrice]").val("");
            tr.find("[tag=number]").val("");
            tr.find("[tag=remark]").val("");
            tr.find("[tag=brand]").text("");
            tr.find("[tag=amount]").text("");
        }

        $(function () {
            $("#edit_table_body").on("click", ".searchproduct", function () {
                //使用on来统一绑定事件-----------弹出产品选择窗口
                window.open("product_selectProductList.action", null, " height=600,width=750");
            }).on("change", "[tag=costPrice],[tag=number]", function () {//支持链式编程
                //使用on来统一绑定事件-----------计算金额小计的改变事件
                var tr = $(this).closest("tr");
                var costPrice = tr.find("[tag=costPrice]").val();
                var number = tr.find("[tag=number]").val();
                //上面注释的和下面两行等价
                if (costPrice && number) {
                    tr.find("[tag=amount]").text((number * costPrice).toFixed(2));//保留两位小数
                }
            }).on("click", ".removeItem", function () {
                var tr = $(this).closest("tr");
                if ($("#edit_table_body tr").size() == 1) {//清空最后一行数据
                    clearTrData(tr);
                } else { //删除其他数据
                    tr.remove();
                }
            });


            //添加明细
            $(".appendRow").click(function () {
                var copyTr = $("#edit_table_body tr:first").clone();//克隆第一行 clone中参数true代表拷贝时候会拷贝事件
                //清除数据
                clearTrData(copyTr);
                //追加edit_table_body后面
                copyTr.appendTo($("#edit_table_body"));
            });
            //提交表单
            $(".btn_submit").click(function () {
                //重新设置 每一条明细的name
                $.each($("#edit_table_body tr"), function (index, item) {
                    var copyTr = $(item);
                    copyTr.find("[tag=pid]").prop("name", "orderBill.items[" + index + "].product.id");
                    copyTr.find("[tag=costPrice]").prop("name", "orderBill.items[" + index + "].costPrice");
                    copyTr.find("[tag=number]").prop("name", "orderBill.items[" + index + "].number");
                    copyTr.find("[tag=remark]").prop("name", "orderBill.items[" + index + "].remark ");
                });
                //提交表单
                $("#editForm").submit();
            });
        });
    </script>
    <script type="text/javascript">
        $(function () {
            $("input[name='orderBill.vdate']").addClass("Wdate").click(function () {
                WdatePicker({
                    maxDate: new Date()
                });
            });
        })
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
                        <s:select list="#suppliers" name="orderBill.supplier.id" listKey="id" listValue="name"
                                  cssClass="ui_select01"></s:select>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">业务时间</td>
                    <td class="ui_text_lt">
                        <s:date name="orderBill.vdate" format="yyyy-MM-dd" var="vd"/>
                        <s:textfield name="orderBill.vdate" cssClass="ui_input_txt02" value="%{vd}"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
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
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                                <%--新增的页面--%>
                            <s:if test="orderBill.id==null">
                                <tr>
                                    <td></td>
                                    <td>
                                        <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04"
                                                     tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <s:hidden name="orderBill.items.product.id" tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><s:textfield tag="costPrice" name="100"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><s:textfield tag="number" name="1"
                                                     cssClass="ui_input_txt04"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><s:textfield tag="remark" name="orderBill.items.remark"
                                                     cssClass="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </s:if>
                                <%--编辑的页面--%>
                            <s:else>
                                <s:iterator value="orderBill.items">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <s:textfield disabled="true" readonly="true" cssClass="ui_input_txt02"
                                                         tag="name" name="product.name"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
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
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </s:else>

                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>