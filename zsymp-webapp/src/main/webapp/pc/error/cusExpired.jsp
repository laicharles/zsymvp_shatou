<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../base/base.jsp"  %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge" />
   <title> 汕头自来水微信服务系统</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<c:url value="/resources/js/bootstrap/img/shantou.ico"/>" />
   <link href="<c:url value="/resources/js/bootstrap/css/twitter-bootstrap/bootstrap.css"/>" rel="stylesheet">
   <script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/plugins/twitter-bootstrap/bootstrap.js"/>"></script>
</head>
<body>

<div class="hero-unit" align="center">
  <h1>过期提示</h1>
  <p>长时间没有操作，请重新登录！如果浏览器<div id=time style="display: inline-block"></div>秒内不跳转，请点下面按钮</p>
  <p>
  <a class="btn btn-primary btn-large" href="${pageContext.request.contextPath}">
    	跳转登录
  </a>
  </p>
</div>

<script>
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
	alert("${pageContext.request.contextPath}");
	window.location.href="${pageContext.request.contextPath}";
}

</script>
</body>
</html>		

  
