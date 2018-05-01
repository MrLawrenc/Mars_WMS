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

    <link href="/js/plugins/fancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/plugins/fancyBox/jquery.fancybox.pack.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".fancybox").fancybox();

            //选择货品的点击事件
            $(".selectProduct").click(function () {
                //设置回传的数据,关闭当前窗口
                var productJson = $(this).data("product");//得到当前页面的json数据
                window.opener.window.$(".searchproduct:last").closest("tr").find("[tag=name]").val(productJson.name);
                window.opener.window.$(".searchproduct:last").closest("tr").find("[tag=pid]").val(productJson.id);
                window.opener.window.$(".searchproduct:last").closest("tr").find("[tag=brand]").text(productJson.brandName);
                window.opener.window.$(".searchproduct:last").closest("tr").find("[tag=costPrice]").val(productJson.costPrice);
                self.close();
            });
        });
    </script>
    <title>货品管理</title>
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
<s:form id="searchForm" namespace="/" action="product_selectProductList.action" method="post">
<div id="container">
    <div class="ui_content">
        <div class="ui_text_indent">
            <div id="box_border">
                <div id="box_top">搜索</div>
                <div id="box_center">
                    货品名称/编号
                    <s:textfield name="qo.keyword" cssClass="ui_input_txt02"/>
                    货品品牌
                    <s:select list="#brands" name="qo.brandId" listKey="id" listValue="name" headerKey="-1"
                              headerValue="所有品牌"
                              cssClass="ui_select02"></s:select>
                </div>
                <div id="box_bottom">
                    <input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>货品图片</th>
                        <th>货品名称</th>
                        <th>货品编码</th>
                        <th>货品品牌</th>
                        <th>成本价格</th>
                        <th>销售价格</th>
                        <th></th>
                    </tr>
                    <tbody>
                    <s:iterator value="#pageResult.result">
                        <tr>
                            <td>
                                <a class="fancybox" href="<s:property value='imagePath'/>"
                                   title="<s:property value="name"/>">
                                    <img width="60" src="<s:property value='smallImagePath'/>"/>
                                </a>
                            </td>
                            <td><s:property value="name"/></td>
                            <td><s:property value="sn"/></td>
                            <td><s:property value="brand.name"/></td>
                            <td><s:property value="costPrice"/></td>
                            <td><s:property value="salePrice"/></td>
                            <td><s:property value="productJson"/>
                                <input type="button" value="选择该商品" class="left2right selectProduct"
                                       data-product="<s:property value="productJson"/>"/>
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

