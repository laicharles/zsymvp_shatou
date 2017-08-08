
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>${dicName}</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/base.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/list.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<script type="text/javascript">
$(function(){
	$('.article img').each(function(){
        $(this).css('width','100%');
        $(this).css('height','100%');
 	});
})
</script>
</head>
<body>
	<div class="article" style="font-size: 16px;">
		<div style="border-bottom: 1px #ccc solid;margin:10px;margin-bottm:-10px;">
			<h2 style="text-align: left;margin-top:10px;margin-bottom:15px">${cmsForm.title}</h2>
		</div>
		<div style="margin:10px;margin-bottom:50px;">
		    <p style="text-align: left;color:gray;font-weight: 100;margin:5px 0 20px 0">${cmsForm.createdDateTime} ${cmsForm.author}</p>
		    <div>
		    	${cmsForm.pageContent}
			</div>
			<c:choose>
				<c:when test="${not empty cmsForm.origUrl}">
					<p style="text-align: left;margin:20px 0 20px 0"><a href="${cmsForm.origUrl}" style="text-decoration:none;color:gray;font-weight: 100;">阅读原文</a></p>
				</c:when>
			</c:choose>
		</div>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
</body>
</html>