<% //jsp中引入一些上下文对象 %>
<%@page import="com.eyeieye.melody.web.url.URLBroker"%>
<%
	URLBroker appServer = (URLBroker)request.getAttribute("appServer");
	URLBroker imageServer = (URLBroker)request.getAttribute("imageServer");
%>
