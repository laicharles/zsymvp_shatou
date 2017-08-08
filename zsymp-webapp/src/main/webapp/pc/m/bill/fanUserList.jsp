<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>账户列表</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no" />  
<meta name="format-detection" content="email=no" />  
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike,  sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed,  figure, figcaption, footer, header, hgroup,  menu, nav, output, ruby, section, summary, time, mark, audio, video{margin:0; padding:0; border:0; font-size:100%; font:inherit; vertical-align:baseline}
body{ background:#f0f0f0;}
#wx-wrap-list{
	background-color:#f0f0f0;
}

#wx-wrap-list .module{
	position:relative;
	border:2px solid lightgray;
	width:90%;
	margin:20px auto;
	border-radius:15px;  
	font-size:16px;
	background-color:white;
	max-width:600px;
	overflow:hidden;
}

#wx-wrap-list .module .div-rows{
	float:left;
	width:61%;
	padding:10px 0 15px 10px;
}
#wx-wrap-list .module .div-col-content{
	clear:both;
}

#wx-wrap-list .module .div-col-content .div-col-content01{
	float:left;
	width:50px;
	padding-top:10px;
	font-weight:bold;
	}
#wx-wrap-list .module .div-col-content .div-col-content02{
	float:left;
	overflow:hidden;
	width:71%;
	padding-top:10px;
}

#wx-wrap-list .module img{
	position:absolute;
	bottom:10%;
	right:0;
	padding:10px 0 0;
	width:100px;
	height:100px;
	overflow:hidden;
	}

#wx-wrap-list .module h2{
	background-color:#009ff0;
	color:white;
	border-radius:13px 13px 0 0;
	padding-left:20px;
	height:40px;
	line-height:40px;
	font-size:20px;
}

#wx-wrap-list .module span{
	color:#d60000;
	}

#wx-wrap-list .module h3{
	clear:both;
	height:12px;
	background-color:#009ff0;
	border-radius: 0 0 13px 13px;
}

#wx-wrap-list input{
	width:60%;
	border-radius:10px;
	background-color:#009ff0;
	color:white;
	font-size:20px;
	padding:10px 0;
	max-width:400px;
	border:2px solid lightgray;
	-webkit-appearance: none;
}

#wx-wrap-list .btn-div{
	text-align:center;
}


</style>
<script type="text/javascript">
	function search(account, openID){
		window.location.href="${pageContext.request.contextPath}/m/getWXPayPrepayid.do?accountNo=" + account + "&token=${token}&openID=${openID}";
	}
</script>

</head>
<body>
 
	<div id="wx-wrap-list">
		<c:forEach items="${fanUserFormList}" var="fanUserForm">
			<div class="module" onclick="search('${fanUserForm.userAccount}', '${openID }')">
				<h2 style="height: 15px;"></h2>
				<div class="div-rows">
					<div style="font-size: 18px;font-weight: bold;">${fanUserForm.remarks }</div>
		            <div class="div-col-content">
		                <div class="div-col-content01">户号:</div>
		                <div class="div-col-content02">${fanUserForm.userAccount }</div>
		            </div>
		            <div class="div-col-content">
		                <div class="div-col-content01">电话:</div>
		                <div class="div-col-content02">${fanUserForm.mobile }</div>
		            </div>
		            <%-- <div class="div-col-content">
		                <div class="div-col-content01">地址:</div>
		                <div class="div-col-content02">${fanUserForm.contactAddr }</div>
		            </div> --%>
		        </div>
				<img src="<c:url value="/resources/images/m/house.png"/>"/>
				<h3>&nbsp;</h3>
			</div>
		</c:forEach>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
</body>
</html>