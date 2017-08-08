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
    <link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
        button{
        	margin-top:20px;
        	margin-left: 5px;
        }
        .butDiv{
        	margin-left: 100px;
        }
    </style>
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/bootstrap/plugins/html5shiv.js"/>"></script>
    <![endif]-->
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
    <!-- 搜索表单 -->
    <!--<div class="search-sidebar">
        <img src="assets/img/icons/stuttgart-icon-pack/32x32/search.png" alt="Search">
        <form class="search-sidebar-form">
            <input type="text" class="search-query input-block-level" placeholder="Search">
        </form>
    </div>-->
    <!-- 主菜单 开始 -->

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
    <div class="container-fluid">
        <h2 class="title_class">审核</h2>
		<form id="reviewForm" method="POST">
			<input type="hidden" name="contentId" value="${contentId }">	
			
			<div>
				<label for="nameId"><font color="red">*</font>审核意见：</label>
				<textarea rows="10" cols="30" id="remark" class="general_textarea_class"></textarea>
			</div>
			
			<div class="butDiv">
				<button type="button" class="btn btn-default" id="retBtn">返回</button>
				<button type="button" class="btn btn-default" id="consentBtn">同意</button>
				<button type="button" class="btn btn-default" id="disagreeBtn">不同意</button>
			</div>
		</form>
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
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });
    
    var data = $('#reviewForm').serialize();
    
    $('#consentBtn').click(function(){
    	var remark = $('#remark').val();
    	data = data+"&isThrough=SUCCESS&remark="+remark;
    	console.log(data);
    	$.ajax({
			url:"${pageContext.request.contextPath}/cus/ccms_reviewCmss.do",
			data:data,
			type:"GET",
			success:function(rs){
				if(rs=="SUCCESS"){
					location.href="${pageContext.request.contextPath}/cus/${cmsType}/ccms_list.do?pageNo=${pageNo }";
				}else{
					bootbox.alert('审核失败!');
				}
			},
			error:function(rs){
				bootbox.alert('审核失败!');
			}
		})
    });
    
    $('#disagreeBtn').click(function(){
    	var remark = $('#remark').val();
    	if(remark=="" || remark == null){
    		bootbox.alert('请填写审核意见');
    		return;
    	}
    	data = data+"&isThrough=FAIL&remark="+remark;
    	$.ajax({
    		url:"${pageContext.request.contextPath}/cus/ccms_reviewCmss.do",
    		data:data,
    		type:"GET",
	    	success:function(rs){
	    		if(rs=="SUCCESS"){
					location.href="${pageContext.request.contextPath}/cus/${cmsType}/ccms_list.do?pageNo=${pageNo }";
				}else{
					bootbox.alert('审核失败!');
				}
    		},
    		error:function(rs){
    			bootbox.alert('审核失败!');
    		}
    		
    	})
    });
    
 	//返回列表
	$("#retBtn").click(function(){
		window.location.href="${pageContext.request.contextPath}/cus/${cmsType}/ccms_list.do?pageNo=${pageNo }";
	});
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>