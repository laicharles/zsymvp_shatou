<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
	<meta name="description" content=" 汕头自来水微信服务系统 ">
	<meta name="author" content=" 汕头自来水微信服务系统">
	<meta name="format-detection" content="telephone=no">
	<title>${dicName}</title>
	<link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/base.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/list.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
</head>
<body class="page-my-media-list">
	<div class="container">
		<ul id="list" class="my-media-list<c:if test="${cmsType=='cmsType04'}"> big-first-img</c:if>"></ul>
		<button id="loadMore">点击加载更多</button>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/plugins/jquery.pagination/jquery.pagination.js"/>"></script>
	<script>
		var tmp;
		var rows = 20;
    	$(document).ready(function () {
    		$('#list').pagination({
    			url: '${pageContext.request.contextPath}/m/ccmsGrid_wx.do',
    			page: 1,
    			rows: rows,
    			expand: {
    				token: '${token}',
    				cmsType: '${cmsType}'
    			},
    			loadMoreBtn: '#loadMore',
    			jointHTML: function (data) {
    				tmp = data;
    				
   					var html = '';
   					for (var i = 0; i < rows; i++) {
   						if (null != data.formList[i]) {
	   						html += '' +
	   						'<li id="' + data.formList[i].id + '">' +
	    						'<div class="cell">' +
	    							'<div class="cover-wrapper">';
	    					if (null == data.formList[i].image) {
	    						html += '' +
	    								'<div class="cover" style="background-image: url(${pageContext.request.contextPath}/${defaultPicPath}/${defaultPicName});"></div>';
	    					} else {
	    						html += '' +
	    								'<div class="cover" style="background-image: url(${pageContext.request.contextPath}/resources/attached/${token}/' + data.formList[i].image + ');"></div>';
	    					}
	    					html += '' +	
	    							'</div>' +
	    						'</div>' +
	    						'<div class="cell">' +
	    							'<h4 class="title">' + data.formList[i].title + '</h4>' +
	    							'<time>' + data.formList[i].createdDateTime + '</time>' +
	   							'</div>' +
	   						'</li>';
   						}
   					}
   					
   					return html;
    			},
    			doAfterJointing: function () {
    				if (0 === tmp.formList.length) {
    					$('#loadMore').off();
    					$('#loadMore').text('没有数据');
    					return;
    				}
    				
    				if (tmp.page === tmp.total) {
    					$('#loadMore').off();
    					$('#loadMore').text('没有更多数据');
    				}
    			}
    		});
    	});
    	
    	$('#list').on('click', 'li', function () {
    		var $this = $(this);
    		var id = $this.attr('id');
    		window.location.href = '${pageContext.request.contextPath}/m/ccms_wx_view.do?id=' + id;
    	});
		
    </script>
</body>
</html>
