<%@ page  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include  file="../base.jsp"  %>


     <div class="accordion" id="accordionMain">
     
     
        <div class="accordion-group active">
            <div class="accordion-heading">
                <a class="accordion-toggle opened" href="${pageContext.request.contextPath}/cus/main.do">
                    <img src="<c:url value="/resources/images/icon/home.png"/>" alt="Layouts">
                    <span>首页</span>
                    <span class="badge">10</span>
                </a>
            </div>
        </div>
        
        
        <%-- 目前只支持二级菜单显示 --%>
        <c:choose>
        <c:when test="${sessionScope.menus.isShowMenu eq 1 }">
        	<div class="accordion-group">
	            <div class="accordion-heading">
	                <a class="accordion-toggle " data-toggle="collapse" data-parent="#accordionMain" href="#collapse-layouts">
	                    <img src="<c:url value="/resources/js/bootstrap/img/icons/stuttgart-icon-pack/32x32/archives.png"/>" alt="Layouts">
	                    <span>${sessionScope.menus.resName }</span><span class="arrow"></span>
	                </a>
	            </div>
	            <ul id="collapse-layouts" class="accordion-body nav nav-list collapse ">
	            	<c:forEach items="${sessionScope.menus.subResources }" var="subMenus">
		                <li><a href="${pageContext.request.contextPath}/${subMenus.reqUrl }">${subMenus.resName }</a></li>
	                </c:forEach>
	            </ul>
        	</div>
        </c:when>
        <c:otherwise>
        	<c:forEach items="${sessionScope.menus.subResources }" var="menu" varStatus="status">
        		<c:choose>
        		<c:when test="${menu.isShowMenu eq 1 }">
			        <div class="accordion-group">
			            <div class="accordion-heading">
			                <c:if test="${!empty menu.subResources }">
				                <a class="accordion-toggle " data-toggle="collapse" data-parent="#accordionMain" href="#collapse-layouts${status.index }">
				                    <c:choose>
				                    	<c:when test="${menu.resName eq '消息通知'}">
				                    		<img src="<c:url value="/resources/images/icon/icon01.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '素材管理'}">
				                    		<img src="<c:url value="/resources/images/icon/icon02.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '微官网'}">
				                    		<img src="<c:url value="/resources/images/icon/icon03.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '水信息管理'}">
				                    		<img src="<c:url value="/resources/images/icon/icon04.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '水服务管理'}">
				                    		<img src="<c:url value="/resources/images/icon/icon05.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '水公告管理'}">
				                    		<img src="<c:url value="/resources/images/icon/icon06.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '智能回复'}">
				                    		<img src="<c:url value="/resources/images/icon/icon07.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '系统配置'}">
				                    		<img src="<c:url value="/resources/images/icon/icon08.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '微信支付'}">
				                    		<img src="<c:url value="/resources/images/icon/icon09.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '统计分析'}">
				                    		<img src="<c:url value="/resources/images/icon/icon10.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '操作中心'}">
				                    		<img src="<c:url value="/resources/images/icon/icon11.png"/>" alt="Layouts">
				                    	</c:when>
				                    	<c:when test="${menu.resName eq '日志中心'}">
				                    		<img src="<c:url value="/resources/images/icon/icon12.png"/>" alt="Layouts">
				                    	</c:when>
				                    </c:choose>
				                    <span>${menu.resName }</span><span class="arrow"></span>
				                </a>
			                </c:if>
			                <c:if test="${empty menu.subResources }">
				                <a class="accordion-toggle " data-toggle="collapse" data-parent="#accordionMain" >
				                    <img src="<c:url value="/resources/js/bootstrap/img/icons/stuttgart-icon-pack/32x32/archives.png"/>" alt="Layouts">
				                    <span>${menu.resName }</span>
				                </a>
			                </c:if>
			            </div>
			            <ul id="collapse-layouts${status.index }" class="accordion-body nav nav-list collapse ">
			            	<c:forEach items="${menu.subResources }" var="subMenus">
			            		<c:if test="${subMenus.isShowMenu eq 1 }">
				                	<li><a href="${pageContext.request.contextPath}/${subMenus.reqUrl }">${subMenus.resName }</a></li>
				                </c:if>
			                </c:forEach>
			            </ul>
			        </div>
	        	</c:when>
	        	</c:choose>
	         </c:forEach>
        </c:otherwise>
        </c:choose>
    </div>
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script>
		var url = window.location.href;
		var project = '${pageContext.request.contextPath}';
		
		if (-1 != url.indexOf('cmsType0')) {
			var idx1 = url.indexOf('cmsType0');
			url = url.substring(idx1, idx1 + 9);
			$('li').each(function () {
				var targetUrl = $(this).children().attr('href');
				if (-1 != targetUrl.indexOf('cmsType0')) {
					var idx2 = targetUrl.indexOf('cmsType0');
					targetUrl = targetUrl.substring(idx2, idx2 + 9);
					if (targetUrl == url) {
						$(this).parent().siblings('.accordion-heading').children().attr('class', 'accordion-toggle opened'); 
						$(this).parent().attr('class', 'accordion-body nav nav-list in collapse'); 
						$(this).parent().attr('height', 'auto'); 
						$(this).children().css('background', '#6d84b4');
					}
				}
			});
		} else {
			url = url.substring(7);
			var firstIndex = url.indexOf('/');
			url = url.substring(firstIndex);
			var lastIndex = url.lastIndexOf('.do');
			if (lastIndex != -1) {
				url = url.substring(0, lastIndex);
			}
			$('li').each(function () {
				var targetUrl = $(this).children().attr('href');
				var lastIndex = targetUrl.lastIndexOf('.do');
				if (lastIndex != -1) {
					targetUrl = targetUrl.substring(0, lastIndex);
				}
				
				if (-1 != url.indexOf('_')) {
					url = url.split('_')[0];
				}
				
				if (-1 != targetUrl.indexOf('_')) {
					targetUrl = targetUrl.split('_')[0];
				}
				
				if (targetUrl == url) {
					$(this).parent().siblings('.accordion-heading').children().attr('class', 'accordion-toggle opened'); 
					$(this).parent().attr('class', 'accordion-body nav nav-list in collapse'); 
					$(this).parent().attr('height', 'auto'); 
					$(this).children().css('background', '#6d84b4');
				}
	   		});
		}
	</script>