<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../../base/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="no-js">
<head>
<meta http-equiv="Content-Type" content="textml; charset=utf-8">
<title>缴纳水费</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no" />
<meta name="format-detection" content="email=no" />
<link href="<c:url value="/resources/css/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/form.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<link
	href="<c:url value="/resources/js/plugins/jquery.mydialog/mydialog.css"/>"
	rel="stylesheet" type="text/css" media="screen">
<style>
.mydialog-content {
	max-height: 500px;
	overflow-y: auto;
}
</style>
</head>
<body id="page-jnsf" class="page-bill-form">
	<div class="container">
		<div class="mytooltip-container"></div>

		<form id="waterbill" class="bill-form">
			<div class="bill-status" style="background-color: #009ff0;">
				<p class="datetime">${chargeform.cdate}</p>
				<p class="fee"><strong>${chargeform.total}</strong> 元</p>
				<p class="status" id="payStatus">未缴纳</p>
			</div>
			<div class="area">
				<div class="form-row">
					<div class="input-row">
						<label>户号</label>
						<p>${chargeform.accountno}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>用户名</label>
						<p>${chargeform.name}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>新地址</label>
						<p>${chargeform.newAddr}</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>水费</label>
						<p class="highlight">${chargeform.price}元</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>污水费</label>
						<p class="highlight">${chargeform.pwf}元</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>违约金</label>
						<p class="highlight">${chargeform.cnj}元</p>
					</div>
				</div>
				<div class="form-row">
					<div class="input-row">
						<label>垃圾费</label>
						<p class="highlight">${chargeform.ljprice}元</p>
					</div>
				</div>
 				<div class="form-row">
					<div class="input-row">
						<label>总计</label>
						<p class="highlight">${chargeform.total}元</p>
					</div>
				</div>
		</form>
	</div>
	<div class="padding-handler" style="margin-top: 10px;">
		<button id="payButton" class="btn btn-lg btn-base col-xs-12 f-cb">缴纳水费</button>
	</div>
	<!-- footer -->
	<%-- <%@ include file="../base/footer.jsp"%> --%>

	<!-- JavaScript -->
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.mydialog/jquery.mydialog.js"/>"></script>
    <script type="text/javascript" charset="UTF-8" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
		var payStatus = '${size}';
		var flag = '${flag}';
/* 		var canPay = ${canPay};
		var bill_can_pay = '${waterBill.bill_can_pay}';
		var return_desc = '${waterBill.return_desc}';
		var isrepeat = '${isrepeat}'; */
		var reClick = true;
		
		$(document).ready(function () {
			
			if('1' == payStatus) {
				if('0' == flag){
					$('#payButton').attr('disabled', true);	
				}
/* 				if (!canPay) {
					$('#billRemark').html('暂时无法缴费，请稍后再试！'); 
					$('#payButton').attr('disabled', true);
				} else {
					if ('FAIL' == bill_can_pay) { 
						$('#billRemark').html(return_desc); 
						$('#payButton').attr('disabled',true);
					} else {
						if(isrepeat == 'repeat') {
							$('#billRemark').html('您已经支付了，请不要重复缴费！'); 
							$('#payButton').attr('disabled', true);
						}
					}
				} */
				$('#payStatus').html('未缴纳').css({'color': '#f00'});
			} else if ('0' == payStatus) {
				$('#payStatus').html('已缴纳'); 
				$('#payButton').attr('disabled', true);
			}
			
			/* 预支付按钮 */
			$('#payButton').click(function () {
				if(!reClick) {
					alert("error");
					return;
				}
				reClick = false;
				
				$.ajax({
					url: '${pageContext.request.getContextPath()}/wxpay/getWXPayPrepayid.do',
					dataType: 'json',
					async: false,
					data: {
						'accountno': '${chargeform.accountno}',
						'token': '${token}',
						'openId': '${openId}'
						//'totalFee': '${waterBill.total_charge}'
					},
				    type: 'POST', // 请求方式
				    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				    beforeSend: function () {
				        // 请求前的处理
				    },
				    success: function (data) {
/*  				    alert(data.finalsign);
				    	alert(data.packages);
				    	alert(data.nonceStr);
				    	alert(data.timestamp);
				    	alert(data.appId);  */
				    	if (data.result == 'success') {
				    		var appId = data.appId;
							var timestamp = data.timestamp;
							var nonceStr = data.nonceStr;
							var packages = data.packages;
							var finalsign = data.finalsign;
							var out_trade_no = data.out_trade_no;
							
  				        if(!inserOrder(out_trade_no)) {
								reClick = true;
								return ;
							}
							// 弹出支付窗口
							WeixinJSBridge.invoke('getBrandWCPayRequest',
									{
							 			'appId': appId,
							 			'timeStamp': timestamp,
							 			'nonceStr': nonceStr,
										'package': packages,
										'signType': 'MD5',
										'paySign': finalsign
									}, function (res) {
								WeixinJSBridge.log(res.err_msg);
								if (res.err_msg == 'get_brand_wcpay_request:ok') {
									alert('支付成功！感谢使用，祝您生活愉快！');
									$('#payStatus').html('已缴纳');
									$('#payButton').attr('disabled', true);
								} else if (res.err_msg == 'get_brand_wcpay_request:cancel') {
									reClick = true;
								} else {
									alert(res.err_msg);
									alert(JSON.stringify(res));
									alert('系统繁忙，正在升级，请稍后再试！');
									reClick = true;
								}
							});
				    	} else {
				    		alert('系统繁忙，请稍后再试！');
				    		reClick = true;
				    	}
				    }, 
				    complete: function () {
				        // 请求完成的处理
				    },
				    error: function (s) {   
				        // 请求出错处理
				    }
				});
			});
			
			/* 插入订单 */
			function inserOrder(orderNo) {
				var result = false;
				
				$.ajax({
					url: '${pageContext.request.getContextPath()}/wxpay/inserOrder.do',
					dataType: 'json',
					async: false,
					data: {
						'openId': '${openId}',
						'token': '${token}',
						'accountNo': '${chargeform.accountno}',
						'accountName': '${chargeform.name}',
						'address': '${chargeform.addr}',
						'orderNo': orderNo,
						'totalCharge': '${chargeform.total}'
					},
				    type: 'POST', // 请求方式
				    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				    beforeSend: function () {
				        // 请求前的处理
				    },
				    success: function (data) {
				    	if (data.msg == 'SECCESS') {  
				    		result = true;
				    	}
				    }, 
				    complete: function () {
				        // 请求完成的处理
				    },
				    error: function (s) {   
				        // 请求出错处理
				    }
				});
				return result;
			}
		});
		function comptime(beginTime,endTime) {
		    var beginTimes = beginTime.substring(0, 10).split('-');
		    var endTimes = endTime.substring(0, 10).split('-');

		    beginTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0] + ' ' + beginTime.substring(10, 19);
		    endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);

		    var a = (Date.parse(endTime) - Date.parse(beginTime)) / 3600 / 1000;
		    if (a < 0) {
		        return false;
		    } else if (a > 0) {
		        return true;
		    } 
		}
		/* 帮助提示窗口 */
/* 		var html = '';
		html +=	'<div><p style="font-size: 16px;">${helpContent} </p></div>';
		
		$('#helpBtn').click(function () {
			$(this).mydialog({
				overlay: true,
				winType: 'alert',      //对话框类型 
				skinClass: 'responsive',     //对话框皮肤
				title: '${helpTitle }',         //对话框标题 
				content: html,       //对话框内容 
				hasCloseBtn: false,             //是否显示右上角交叉按钮 
				text4AlertBtn: '知道了',         //弹出窗中按钮的文本 
				handler4AlertBtn: function () {       //点击Alert框“确定”时执行的方法 
					console.log('alert');
				}
			});
		});  */  //说明窗口方法结束 
	
	</script>
</body>
</html>