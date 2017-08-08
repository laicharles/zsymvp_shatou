<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<title>模板审核</title>
	<%@ include file="../base/cssbase.jsp"%>
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/js/plugins/jquery.mydialog/mydialog.css"/>" rel="stylesheet">
	<style>
	#selectPic {
		position: relative;
	}

	#chooseImage {
	    position: absolute;
	    top: 50%;
	    right: 10px;
	    width: 30px;
	    height: 30px;
	    margin-top: -15px;
	    padding: 0;
	    border: none;
	}
	
	#chooseImage .glyphicon {
		font-size: 24px;
		line-height: 30px;
	}
	
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
	
	.bill-img .delete {
		position: absolute;
		top: -8px;
		right: -9px;
		display: block;
		width: 20px;
		height: 20px;
		line-height: 18px;
		font-size: 1.8rem;
		color: #fff;
		text-align: center;
		text-decoration: none;
		border-radius: 10px;
		background: #f00;
		z-index: 10;
	}
	.test {
		width:100%;
		background0-color: yellow;
	}
	</style>
</head>
<body id="page-xzhdfk" class="page-color-card-form">
	<div class="container">
		<div class="mytooltip-container"></div>
		<form id="reviewForm" class="color-card-form" data-parsley-trigger="focusout">
			<input type="hidden" name="token" value="${token }"/>
			<input type="hidden" name="contentId" value="${contentId }"/>
			<input type="hidden" name="reviewCustomerUserId" value="${higherCustomerUserId }"/>
			<input type="hidden" name="submitBinOpendId" value="${submitBinOpendId }"/>
			<input type="hidden" name="auditorOpenId" value="${auditorOpenId }"/>
			<input type="hidden" name="tempGenre" value="${tempGenre }"/>
			
			<%-- <p>token : ${token }</p><br/>
			<p>contentId : ${contentId }</p><br/>
			<p>reviewCustomerUserId : ${higherCustomerUserId }</p><br/>
			<p>submitBinOpendId : ${submitBinOpendId }</p><br/>
			<p>auditorOpenId : ${auditorOpenId }</p><br/>
			<p>tempGenre : ${tempGenre }</p><br/> --%>
			
			
			<div class="form-row">
				<div class="input-row">
					<textarea cols="8" placeholder="备注" id="remark" style="width:100%;"></textarea>
				</div>
			</div>
		</form>
		<div class="handler-group">
			<div class="cell">
				<button id="consentBtn" class="btn btn-lg btn-base col-xs-10 f-cb">同意</button>
			</div>
			<div class="cell">
				<button id="disagreeBtn" class="btn btn-lg btn-base col-xs-10 f-cb">不同意</button>
			</div>
		</div>
	</div>
	
	<!-- JavaScript -->
	<%@ include file="../base/jsbase.jsp"%>
	<script src="<c:url value="/resources/js/plugins/jquery.mydialog/jquery.mydialog.js"/>"></script>
	<script type="text/javascript">
		$(function(){
			$('#consentBtn').click(function(){
				var data = $('#reviewForm').serialize();
				var remark = $.trim($('#remark').val());
				
				data = data+"&isThrough=SUCCESS&remark="+remark;
				console.log(data);
				
				$('#consentBtn').attr("disabled","disabled");
				$('#disagreeBtn').attr("disabled","disabled");
				$.ajax({
					url:"${pageContext.request.getContextPath()}/m/audit.do",
					data:data,
					dataType:"JSON",
					type:"POST",
					success:function(rs){
						$.MyToolTip.popupMessage(rs.msg, rs.status);
						if(rs.status=="danger"){
							$('#consentBtn').removeAttr("disabled");
							$('#disagreeBtn').removeAttr("disabled");
						}
					},
					error:function(rs){
						$.MyToolTip.popupMessage('审核失败！', 'danger');
						$('#consentBtn').removeAttr("disabled");
						$('#disagreeBtn').removeAttr("disabled");
					}
				})
			});
			
			$('#disagreeBtn').click(function(){
				$('#consentBtn').attr("disabled","disabled");
				$('#disagreeBtn').attr("disabled","disabled");
				
				var data = $('#reviewForm').serialize();
				var remark = $.trim($('#remark').val());
				data = data+"&isThrough=FAIL&remark="+remark;
				
				if(remark == ""){
					$.MyToolTip.popupMessage('备注不能为空', 'danger');
					$('#consentBtn').removeAttr("disabled");
					$('#disagreeBtn').removeAttr("disabled");
					return;
				}
				
				$.ajax({
					url:"${pageContext.request.getContextPath()}/m/audit.do",
					data:data,
					dataType:"JSON",
					type:"POST",
					success:function(rs){
						$.MyToolTip.popupMessage(rs.msg, rs.status);
						if(rs.status=="danger"){
							$('#consentBtn').removeAttr("disabled");
							$('#disagreeBtn').removeAttr("disabled");
						}
					},
					error:function(rs){
						$.MyToolTip.popupMessage('审核失败！', 'danger');
						$('#consentBtn').removeAttr("disabled");
						$('#disagreeBtn').removeAttr("disabled");
					}
				})
			});
				
		});
	</script>
</body>
</html>