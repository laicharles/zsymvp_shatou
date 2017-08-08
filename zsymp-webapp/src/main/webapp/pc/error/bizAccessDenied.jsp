<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
   <title>客户管理后台</title>
   <link href="<c:url value="/resources/js/bootstrap/css/twitter-bootstrap/bootstrap.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/plugins/twitter-bootstrap/bootstrap.js"/>"></script>
</head>
<body>

<div class="hero-unit" align="center">
  <h1>权限不足</h1>
  <p>不具备访问本系统权限，请联系管理员！如果浏览器<div id=time style="display: inline-block"></div>秒内不跳转，请点下面按钮</p>
  <p>
    <a class="btn btn-primary btn-large" href="${pageContext.request.contextPath}/biz">
      跳转登录
    </a>
  </p>
</div>


</body>
</html>	
<script type="text/javascript">
var time = 5;
$(function(){
	$("#time").append("<label id=timeOut>5</label>")
	 setInterval(jump,5000);
	 setInterval(timeOut,1000);
}); 

function timeOut(){
	--time;
	if(time>=0){
		$("#timeOut").remove();
		$("#time").append("<label id=timeOut>"+time+"</label>")
	}
}

function jump(){
	window.location.href="${pageContext.request.contextPath}/biz";
}

</script>