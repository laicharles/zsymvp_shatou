<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-CN" class="no-js">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>水费账单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no" />  
	<meta name="format-detection" content="email=no" /> 
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/js/plugins/jquery.mydialog/mydialog.css"/>" rel="stylesheet" type="text/css" media="screen">
	<style>
		.mydialog-content {
			max-height: 500px;
			overflow-y: auto;
		}
	</style>
</head>
<body id="page-sfzd" class="page-bill-form">
	<div class="container">
		<div class="mytooltip-container"></div>
		<form id="waterbill" class="bill-form">
			<div class="bill-status" style="background-color: #6DBCE7;">
				<p class="datetime">${billForm.lcdate} — ${billForm.cdate}</p>
				<p class="fee"><strong>${billForm.totalMoney}</strong> 元</p>
				<p class="status" id="payStatus">缴费月应缴纳</p>
			</div>
			<div class="area">
				<div class="form-row">
					<div class="input-row">
						<label>户号</label>
						<p>${billForm.hno}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>用户名</label>
						<p>${billForm.name}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>用水地址</label>
						<p>${billForm.newaddr}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>用水性质</label>
						<p>${billForm.usort}</p>
					</div>
				</div>
			</div>
			<div class="area">
				<div class="form-row">
					<div class="input-row">
						<label>水费</label>
						<p class="highlight">${billForm.price}元</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>水费明细</label>
						<p>
							上期抄码:${billForm.lnum}<br/>
							本期抄码:${billForm.pnum}<br/>
							水量(立方):${billForm.quanty }<br/>
						</p>
					</div>
				</div>
			</div>
			<div class="area">
				<%-- <div class="form-row">
					<div class="input-row">
						<label>其他费用</label>
						<p class="highlight">${billForm.pwf+billForm.cnj+billForm.pwcnj+billForm.ljprice}元</p>
					</div>
				</div> --%>
				<div class="form-row">
					<div class="input-row">
						<label>其他费用<br/>明细</label>
						<p>
							污水费:${billForm.pwf }<br/>
							违约金:${billForm.cnj }<br/>
							污水费滞纳金:${billForm.pwcnj }<br/>
							垃圾费:${billForm.ljprice }
						</p>
					</div>
				</div>
			</div>
			<div class="area">
				<div class="form-row">
					<div class="input-row">
						<label>账单总额</label>
						<p class="highlight">${billForm.totalMoney}元</p>
					</div>
				</div>
			</div>
			<%-- <div class="area">
				<div class="form-row">
					<div class="input-row">
						<label>备注</label>
						<p class="highlight">${waterBill.remark}</p>
					</div>
				</div>
			</div> --%>
		</form>
		<c:if test="${bill=='newBill' }">
			<div class="padding-handler" <c:if test="${billFormStatus=='FAIL' }">style="display: none;"</c:if>>
				<button id="oldBillDate" class="btn btn-lg btn-base col-xs-12 f-cb">历史账单</button>
			</div>
		</c:if>
		<c:if test="${bill=='oldBill' }">
			<div class="padding-handler">
				<button id="returnBillDate" class="btn btn-lg btn-base col-xs-12 f-cb">返回</button>
			</div>
		</c:if>
		
		<!-- 帮助提示 -->
		<%-- <c:if test="${helpContent != '' && helpContent != null}">
			<div class="warm-tips-card">
				<div class="cell">
					<span>帮助提示：</span>
				</div>
				<div class="cell">
					<a href="javascript:;" id="helpBtn">${helpTitle }</a><br>
				</div>
			</div>
		</c:if> --%>
	</div>
	<div class="second-bg"></div>
	<!-- footer -->
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	
	<!-- JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.mydialog/jquery.mydialog.js"/>"></script>
	
	<script>
		var payStatus = '${billForm.sdate}';
		var reClick = true;
		
		$(document).ready(function () {
			console.log("payStatus:"+payStatus);
			if("" == payStatus) {
				$('#payStatus').html('缴费月应缴纳').css({'color': '#f00'}); 
			} else{
				$('#payStatus').html('已缴纳'); 
			}
			
			/* 订单历史账单按钮 */
			$('#oldBillDate').click(function () {
				window.location.href = '${pageContext.request.contextPath}/m/openOldBillDateList.do?accountNo=${billForm.hno}';
			});
			
			$('#returnBillDate').click(function(){
				window.location.href = '${pageContext.request.contextPath}/m/openOldBillDateList.do?accountNo=${billForm.hno}';
			});
		});
		
		
		
		/* 帮助提示窗口 */
		var html = '';
		html +=	'<div><p style="font-size: 16px;">${helpContent} </p></div>';
		
		$('#helpBtn').click(function () {
			$(this).mydialog({
				overlay: true,
				winType: 'alert',     //对话框类型 
				skinClass: 'responsive',     //对话框皮肤
				title: '${helpTitle }',         //对话框标题 
				content: html,       //对话框内容 
				hasCloseBtn: false,             //是否显示右上角交叉按钮 
				text4AlertBtn: '知道了',         //弹出窗中按钮的文本 
				handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
					console.log('alert');
				}
			});
		});   //说明窗口方法结束
	
	</script>
</body>
</html>
