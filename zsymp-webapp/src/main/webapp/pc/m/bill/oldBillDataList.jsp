<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>历史账单周期列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no" />  
	<meta name="format-detection" content="email=no" /> 
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/function.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/base.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/list.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/article.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/js/plugins/jquery.mydialog/mydialog.css"/>" rel="stylesheet" type="text/css" media="screen">
</head>
<body class="page-base-list">
	<div class="container">
		<ul id="list" class="base-list">
			<c:forEach items="${billFormMap}" var="item">
				<li id="${item.key}">
					<p>${item.value}</p>
					<div class="arrow-wrapper"><img src="<c:url value="/resources/images/icon-go.png"/>"/></div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="second-bg"></div>
	<!-- footer -->
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	
	<!-- JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.mydialog/jquery.mydialog.js"/>"></script>
	<script>
		// 跳到修改绑定信息页面 
		$('#list').on('click', 'li', function () {
			var id = $(this).attr('id');
			window.location.href = '${pageContext.request.getContextPath()}/m/oldWaterBill.do?id=' + id + '&accountNo=${accountNo}';			
		});

	</script>
</body>
</html>
