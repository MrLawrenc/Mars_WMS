<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>进销存项目（演示版）</title>
<link href="/style/error_css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<s:debug></s:debug>
	<div id="login_center">
		<div id="login_area">
			<div id="login_box">
				<div id="login_form">
					<H2>出错啦！</H2>
					<span>执行过程中发生了异常：</span>
					<span class="error"><s:property value="exception.message" /></span>
					<span>请联系管理员刘明瑶解决！</span>
					<span>联系电话：17709033745</span>
					<span>联系邮件：mrliu943903861@163.com</span>
					<span>&copy;西南科技大学光电信息科学与工程有限公司</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>