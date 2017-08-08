<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="<c:url value="/resources/js/html5.js"/>"></script>
<script src="<c:url value="/resources/js/css3-mediaqueries.js"/>"></script>
<title>
<c:if test="${title!=null && title!=''}">${title}</c:if>
<c:if test="${title==null || title==''}">珠海智通信息</c:if>
</title>
<style>
/* 当浏览器的可视区域小于980px */
@media screen and (max-width: 980px) {
#wrap {width: 90%; margin:0 auto;}
#content {width: 60%;padding: 5%;}
#sidebar {width: 30%;}
#footer {padding: 8% 5%;margin-bottom: 10px;}
}
/* 当浏览器的可视区域小于650px */
@media screen and (max-width: 650px) {
#header {height: auto;}
#searchform {position: absolute;top: 5px;right: 0;}
#content {width: auto; float: none; margin: 20px 0;}
#sidebar {width: 100%; float: none; margin: 0;}
}
/** 为了更好的显示效果，我们往往还要格式化一些CSS属性的初始值：***/
/* 禁用iPhone中Safari的字号自动调整 */
html {
-webkit-text-size-adjust: none;
}
/* 设置HTML5元素为块 */
article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section {
display: block;
}
/* 设置图片视频等自适应调整 */
img {
max-width: 100%;
height: auto;
width: auto\9; /* ie8 */
}
.video embed, .video object, .video iframe {
width: 100%;
height: auto;
}
</style>
</head>
<body>
 
	<div data-role="page" id="pageone" data-theme="d">
		<div style="border-bottom: 1px #ccc solid;margin:10px;margin-bottm:-10px;">
			<h2 style="text-align: left;margin-top:10px;margin-bottom:15px">${title}</h2>
		</div>
		<div data-role="content" style="margin:10px;margin-bottom:50px;">
		    <p style="text-align: left;color:gray;font-weight: 100;margin:5px 0 20px 0">${author}</p>
		    <article>
		    	${content}
			</article>
			<c:choose>
				<c:when test="${not empty origUrl}">
					<p style="text-align: left;margin:20px 0 20px 0"><a href="${origUrl}" style="text-decoration:none;color:gray;font-weight: 100;">阅读原文</a></p>
				</c:when>
			</c:choose>
		</div>
	</div>
</body>
</html>