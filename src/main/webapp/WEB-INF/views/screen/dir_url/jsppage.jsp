<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="GB18030"%>
<%@include file="../contain/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>这是一个jsp页面</title>
</head>
<body>
  这是一个jsp页面，也能调用URLBroker:  <%= appServer.get("index.htm")%><br/>
  但是jsp页面没有layout,没有contain。本框架不鼓励使用jsp，请尽可能使用velocity
</body>
</html>