<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<title>历史记录</title>
	<%@ include file="../base/cssbase.jsp"%>
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
	<style>
		.bill-img {
			position: relative;
			display: inline-block;
			width: 30%;
			height: 125px;
			margin: 1%;
			background-color: #000;
			background-size: cover;
			background-position: center;
			border-radius: 3px;
			box-shadow: 0px 0px 8px rgba(0, 0, 0, .5);
		}		
	</style>
</head>
<body id="page-hdfk" class="page-bill-form">
	<div class="container">
		<form class="bill-form">
			<div class="area">
				<div class="form-row">
					<div class="input-row">
						<label>联系人</label>
						<p>${feedbackManageForm.name}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>联系电话</label>
						<p>${feedbackManageForm.tel}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>地址</label>
						<p>${feedbackManageForm.address}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>反馈内容</label>
						<p>${feedbackManageForm.content}</p>
					</div>
				</div>
				<c:if test="${feedbackManageForm.isReply.logicID=='isReply02'}">
					<div class="form-row">
						<div class="input-row">
							<label>回复日期</label>
							<p>${feedbackManageForm.replyTime}</p>
						</div>
					</div>
					<div class="form-row">
						<div class="input-row">
							<label>回复内容</label>
							<p>${feedbackManageForm.replyContent}</p>
						</div>
					</div>
				</c:if>
				<c:if test="${feedbackManageForm.feedbackType.logicID !='feedBackType03'}">
				  <c:if test="${picNameList != null}">
					<div class="form-row" style="text-align: center;">
						<c:forEach items="${picNameList}" var="picName">
						    <c:if test= "${picName != ''}">
							<a href="${backendPath}/resources/attached/${feedbackManageForm.token}/feedback/${picName}">
								<div class="bill-img" style="background-image: url(${backendPath}/resources/attached/${feedbackManageForm.token}/feedback/${picName});"></div>
							</a>
							</c:if>
						</c:forEach>
					</div>
				 </c:if>
				</c:if>
			</div>
		</form>
	</div>
	<div class="second-bg"></div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
</body>
</html>
