<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../base/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta name="description" content=" 汕头自来水微信服务系统 ">
<meta name="author" content=" 汕头自来水微信服务系统">
<meta name="format-detection" content="telephone=no">
<title>微官网</title>
<link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/base.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/list.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
</head>
<body id="page-wgw">
	<div class="container">
		<!-- 幻灯片 -->
		<div class="slider-wrapper">
			<div class="main_visual">
				<div class="main_image">
					<ul>
						<li>
							<span style="background-image: url(/zsymp-webapp/resources/images/website/website1.jpg);background-size:100% 100%;"></span>
						</li>
						<li>
							<span style="background-image: url(/zsymp-webapp/resources/images/website/website2.jpg);background-size:100% 100%;"></span>
						</li>
						<li>
							<span style="background-image: url(/zsymp-webapp/resources/images/website/website3.jpg);background-size:100% 100%;"></span>
						</li>
						<li>
							<span style="background-image: url(/zsymp-webapp/resources/images/website/website4.jpg);background-size:100% 100%;"></span>
						</li>
						<li>
							<span style="background-image: url(/zsymp-webapp/resources/images/website/website5.jpg);background-size:100% 100%;"></span>
						</li>
						<li>
							<span style="background-image: url(/zsymp-webapp/resources/images/website/website6.jpg);background-size:100% 100%;"></span>
						</li>
					</ul>
					<a href="javascript:;" id="btn_prev"></a> <a href="javascript:;"
						id="btn_next"></a>
				</div>
			</div>
		</div>
		<!-- 官网模块 -->
		<div class="main-menu-wrapper">
			<table class="main-menu">
				<tbody>
					<c:forEach items="${configMap}" var="colsMap">
						<tr>
							<c:forEach items="${colsMap.value}" var="colslist">
								<td class="menu-cell" id="${colslist.id}" data-url="${colslist.url }"><img
									class="menu-icons"
									src="${pageContext.request.contextPath}/resources/images/m/${colslist.ico }" alt="">
									<label>${colslist.name}</label></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- footer -->
	<%--<%@ include file="../base/footer.jsp"%> --%>

	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.pagination/jquery.pagination.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.touchslider/jquery.event.drag.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.touchslider/jquery.touchslider.js"/>"></script>
	<script>
		$(document).ready(function () {
			/* 大屏轮播 */
			$('.main_visual').hover(function () {
				$('#btn_prev, #btn_next').fadeIn();
			}, function () {
				$('#btn_prev, #btn_next').fadeOut();
			});
			
			$dragBln = false;
			
			$('.main_image').touchSlider({
				flexible: true,
				speed: 200,
				btn_prev: $('#btn_prev'),
				btn_next: $('#btn_next')
			});
			
			$('.main_image').on('mousedown', function () {
				$dragBln = false;
			});
			
			$('.main_image').on('dragstart', function () {
				$dragBln = true;
			});
			
			$('.main_image a').click(function () {
				if ($dragBln) {
					return false;
				}
			});
			
			timer = setInterval(function () {
				$('#btn_next').click();
			}, 3000);
			
			$('.main_visual').hover(function () {
				clearInterval(timer);
			}, function () {
				timer = setInterval(function () {
					$('#btn_next').click();
				}, 3000);
			});
			
			$('.main_image').on('touchstart', function () {
				clearInterval(timer);
			}).on('touchend', function () {
				timer = setInterval(function () {
					$('#btn_next').click();
				}, 3000);
			});
			
			var wInnerH = window.innerHeight; // 设备物理高度
			$('.slider-wrapper .main_image, .slider-wrapper .main_image li span').css({
				'height': wInnerH * 0.333 + 'px' 
			});
		});
		
		
		/* 显示微官网模块内容 */
		$('.main-menu').on('click', '.menu-cell', function () {
			var id = $(this).attr('id');
			var url = $(this).data('url');
			window.location.href = '/zsymp-webapp'+url;
		});
		
	</script>
</body>
</html>
