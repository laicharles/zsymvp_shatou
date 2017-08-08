<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title> 汕头自来水微信服务系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="<c:url value="/resources/js/bootstrap/img/shantou.ico"/>" />
    <link href="<c:url value="/resources/js/bootstrap/css/twitter-bootstrap/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/social-jquery-ui-1.10.0.custom.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/social.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/social.plugins.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/font-awesome.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/social-coloredicons-buttons.css"/>" rel="stylesheet">
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/bootstrap/css/social-jquery.ui.1.10.0.ie.css"/>"/>
    <![endif]-->
    <link href="<c:url value="/resources/js/bootstrap/css/demo.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/themes/social.theme-blue.css"/>" rel="stylesheet" id="theme">
    <!-- 本页调用样式 开始 -->
    <%--  <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/jquery-confirm/1.5.0/jquery-confirm.css"/>" type="text/css"> --%>
    <%-- <link href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqvmap/jqvmap.css"/>" rel="stylesheet"> --%>
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/css/ui.jqgrid.css"/>" />
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
        textarea {  display: block;  } 
    </style>
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/bootstrap/plugins/html5shiv.js"/>"></script>
    <![endif]-->
    <link href="<c:url value="/resources/js/editor/themes/default/default.css"/>" rel="stylesheet">
	<link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/editor/kindeditor-min.js"/>"></script>
	<script src="<c:url value="/resources/js/editor/lang/zh_CN.js"/>"></script>
	<script src="<c:url value="/resources/js/editor/zsy_editor.js"/>"></script>
	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
	    var csrf_token = "${_csrf.token}";
		function getContent(){
			var contentdata = zsy_editor.html();
			var pageContent = document.getElementById("pageContentId");
			pageContent.value = "";
			pageContent.value = contentdata;
		}
	</script>
</head>

<body>
<!-- 外框架 开始 -->
<div class="wraper sidebar-full">
    <!-- 侧栏 -->
    <aside class="social-sidebar sidebar-full">
    <!-- 用户设置 -->
    <div class="user-settings">
       <jsp:include page="../../../base/cus/userSetting.jsp"/>
    </div>
    <!-- 内容 -->
    <div class="social-sidebar-content">
    <div class="scrollable">
    <!-- 用户信息 -->
    <div class="user"><img class="avatar" width="25" height="25" src="<c:url value="/resources/js/bootstrap/img/avatar.png"/>" alt="Julio Marquez"><span>${user.loginName}</span><i class="icon-user trigger-user-settings"></i></div>
    <div class="navigation-sidebar"><i class="switch-sidebar-icon icon-align-justify"></i></div>

	<section class="menu">
    <jsp:include page="../../../base/cus/menu.jsp"/>
    </section>
    <!-- 主菜单 结束 -->
    </div>
    </div>
    </aside>
    <!-- 页头-->
    <header>
    <!-- 导航 -->
        <nav class="navbar navbar-fixed-top social-navbar social-sm">
            <jsp:include page="../../../base/cus/head.jsp"/>
        </nav>
    </header>

    <!-- 主体 -->
    <div id="main">
    <div class="container-fluid auto-fluid">
        <h2 class="title_class">查看${dicName}</h2>
		<form:form action="${pageContext.request.contextPath}/cus/ccms_update.do?_csrf=${_csrf.token}" modelAttribute="CCmsAddForm" method="POST" enctype="multipart/form-data">
			<div>
				<label for="titleId"><font color="red">*</font>标题：</label>
				<input type="text" name="title" id="titleId" value="${cmsForm.title}" class="general_input_class" disabled="disabled">
			</div>
			<div>
				<label for="authorId">作者：</label>
				<input type="text" name="author" id="authorId" value="${cmsForm.author}" class="general_input_class" disabled="disabled">
			</div>
			
			<div class="form-group">
				<label for="picNameFile" class="col-sm-2 control-label"><font color="red">*</font>封面图片：<font color="orange">(图片类型只能为.jpg, 大小不能超过1M)</font></label>
				<div id="imgdiv">
					<c:if test="${cmsForm.image!=null}">
						<img id="imgShow" src="${pageContext.request.contextPath}${picPath}${cmsForm.image}" style="width:100px;height: 100px"/>
					</c:if>
					<c:if test="${cmsForm.image==null}">
						<img id="imgShow" src="${pageContext.request.contextPath}${defaultPicPath}${defaultPicName}" style="width:100px;height: 100px"/>
					</c:if>
				</div>
			</div>
			
			<br/>
			
			<div>
				<label for="KindEditorId">正文：</label>
				<div class="pageContent_class">
					<textarea name="KindEditor" id="KindEditorId" class="kindeditor_class">${cmsForm.pageContent }</textarea>
				</div>
			</div>

			<div>
				<label for="sortNumberId">排序[微信端]：</label>
				<input type="text" id="sortNumberId"  class="pageUrl_class" name="sortNumber" value="${cmsForm.sortNumber}" disabled="disabled">
			</div>
			<div>
				<label for="origUrlId">原文链接：</label>
				<input type="text" id="origUrlId" class="pageUrl_class" name="origUrl" value="${cmsForm.origUrl}" disabled="disabled">
			</div>
			<div>
				<label for="titleId">正文外链：</label>
				<input type="text" id="pageUrlId" class="pageUrl_class" name="pageUrl" value="${cmsForm.pageUrl}" disabled="disabled">
				<div class="pageUrlTips">（注意：系统默认优先跳转至正文外链，若需要显示正文内容请将正文外链留空！）</div>
			</div>
			<c:if test="${cmsType=='cmsType09' }">
				<div>
					<label for="weixinPath">微信链接：</label>
					<input type="text" id="weixinPath" class="pageUrl_class" value="${weixinPath}" readonly="readonly" disabled="disabled">
				</div>
			</c:if>
			<button type="button" class="btn btn-default" id="returnBtn">返回</button>
		</form:form>
    </div>

	<!-- 页脚开始 -->
    <footer id="footer">
        <jsp:include page="../../../base/cus/foot.jsp"/>
    </footer>
    <!-- 页脚结束 -->
    </div>
</div>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/twitter-bootstrap/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.slimscroll/jquery.slimscroll.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/js/extents.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/js/sidebar.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.form/3.51/jquery.form.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>

<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<!-- This is the Javascript file of jqGrid -->   
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/i18n/grid.locale-cn.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/jquery.zclip.min.js"/>"></script>
<!-- 本页调用JS 开始 -->
<script>
    $(function(){
    	
    	//返回列表
    	$("#returnBtn").click(function(){
    		window.location.href="${pageContext.request.contextPath}/cus/${cmsType}/ccms_list.do?pageNo=${page }";
    	});
    });
    
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>