<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>营业网点</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike,  sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed,  figure, figcaption, footer, header, hgroup,  menu, nav, output, ruby, section, summary, time, mark, audio, video{margin:0; padding:0; border:0; font-size:100%; font:inherit; vertical-align:baseline}
body{ background:#f0f0f0;}
#wx-wrap-bind{
	background-color:#f0f0f0;
	padding:10px 0 10px 0;
	-webkit-appearance: none;
}

#wx-wrap-bind .subimt-div{
	background-color:white;
	border-top:2px solid lightgray;
	border-bottom:2px solid lightgray;
	margin:0 auto 10px auto;
	max-width:600px; 
}

#wx-wrap-bind .div-rows{
	width:90%;
	border-bottom:1px dashed lightgray;
	margin:10px auto;
	font-size:16px;
	/*line-height: 40px;*/
	vertical-align: middle;
	color:#333;
}

#wx-wrap-bind .div-rows:last-child{
	border:none;
}

#wx-wrap-bind .div-col-label{
	line-height:30px;
	margin:4px;
}

#wx-wrap-bind .title{
	border-bottom:2px solid #999;
}

#wx-wrap-bind .title .div-col-label p{
	font-size:24px;
	color:#141414;
}

#wx-wrap-bind .div-col-label label{
	line-height:30px;
	/*margin-bottom:3px;*/
}

#wx-wrap-bind .div-col-label p.add{
	background:url(../../../resources/images/m/icon_add.png) no-repeat left center;
	padding-left:30px;
	}

#wx-wrap-bind .div-col-label p.phone{
	background:url(../../../resources/images/m/icon_phone.png) no-repeat left center;
	padding-left:30px;
	}
	
#wx-wrap-bind .div-col-label p.business{
	background:url(../../../resources/images/m/icon_info.png) no-repeat left center;
	padding-left:30px;
	}
	
#wx-wrap-bind .div-col-label p.business_info{
	padding-left:30px;
	line-height:24px;
	color:#868686;
	}

#wx-wrap-bind .btn-div input{
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

#wx-wrap-bind .btn-div{
	text-align:center;
}
</style>
</head>
<body>
 
	<div id="wx-wrap-bind">
		<div class="subimt-div">
			<div class="div-rows title">
				<div class="div-col-label" >
					<p>${yywdForm.name}</p>
				</div>
			</div>
			<div class="div-rows">
				<div class="div-col-label" >
					<p class="phone"><a href="tel:${yywdForm.tel}">${yywdForm.tel}</a></p>
				</div>
			</div>
			<div class="div-rows">
				<div class="div-col-label" >
	            	<p class="add">${yywdForm.addr}</p>
				</div>
			</div>
	        <div class="div-rows">
				<div class="div-col-label" >
					<p class="business">营业时间</p>
	                <p class="business_info">${yywdForm.description}</p>
				</div>
			</div>
		</div>
		<div class="btn-div">
			<input type="button" value="查看地图" onclick="location='${yywdForm.mapUrl}'"/>
		</div>
	</div>
	
	<%-- <%@ include file="../base/footer.jsp"%> --%>
</body>
</html>