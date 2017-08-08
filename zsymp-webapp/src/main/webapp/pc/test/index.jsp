<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<script type="text/javascript">
	function butClick(){
		console.log("Go beck");
		console.log($('h1').val());
		location.href="";
	}
</script>
</head>
<body>
	<h1>欢迎光临</h1>
	<a href="/zsymp-webapp/u/acquireCode.do?token=225fb8d5394a44e983e7f6354954a21e&redirectUrl=/u/getOpenId.do">
		<butto>点击搞事情</button>
	</a>
</body>
</html>