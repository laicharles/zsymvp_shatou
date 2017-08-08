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
    <link href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqvmap/jqvmap.css"/>" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/css/ui.jqgrid.css"/>" />
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
    </style>
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/bootstrap/plugins/html5shiv.js"/>"></script>
    <![endif]-->
	<link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
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
        <h2 class="title_class">增加人员</h2>
		<form:form action="${pageContext.request.contextPath}/cus/cFeedBackPerson_update.do" modelAttribute="cFeedBackPersonForm" method="POST">
			<div>
				<input type="hidden" name="id" id="id" value="${cFeedBackPersonForm.id }">
				<label for="openIDId"><font color="red">*</font>openID：</label>
				<input type="text" name="openID" id="openIDId" class="general_input_class" value="${cFeedBackPersonForm.openID}">
			</div>
			<div>
				<label for="nameId"><font color="red">*</font>备注：</label>
				<input type="text" name="name" id="nameId" class="general_input_class" value="${cFeedBackPersonForm.name}">
			</div>
			<div>
				<label for="nameId"><font color="red">*</font>类型：</label>
				<input type="radio" name="type" value="0"  checked="checked"/>全部接收
				<input type="radio" name="type" value="1"  />仅接收保修
				<input type="radio" name="type" value="2"  />仅接收咨询
	
			</div>
			<button type="button" class="btn btn-default" id="returnBtn">返回</button>
			<button type="button" class="btn btn-default" id="saveBtn">保存</button>
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
<!-- This is the localization file of the grid controlling messages, labels, etc.
<!-- We support more than 40 localizations -->
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/i18n/grid.locale-cn.js"/>"></script>

<jsp:include page="../cPageMtrl_picklist.jsp"/>
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });
    
    $(function(){
    	//返回列表
    	$("#returnBtn").click(function(){
    		window.location.href="${pageContext.request.contextPath}/cus/cfan_list.do?pageNo=${page}";
    	});
    	//保存
    	$("#saveBtn").click(function(){
    		if(checkSubmit()){
    			bootbox.confirm("确定保存吗？",function(result){
    				if(result==true){
    					$("#cFeedBackPersonForm").ajaxSubmit(function(msg){
               				if(msg.result==false){
               					bootbox.alert(msg.warnMsg,function(){
               						window.location.href="${pageContext.request.contextPath}/cus/cfan_list.do?pageNo=${page}";
               					});
            				}else{
            					bootbox.alert(msg.warnMsg,function(){
            						window.location.href="${pageContext.request.contextPath}/cus/cfan_list.do?pageNo=${page}";
            					});
            				}
            			});
    				}
        		});
    		}
    	});
    });
    
    //提交前校验
    function checkSubmit(){
    	//openID
    	var openID = $("#openIDId");
		$("#openIDWarn").remove();
		if(isNotNull(openID)==false){
			openID.after("<font id='openIDWarn' color='red'>请填写openID！</font>");
			openID.focus();
			return false;
    	}
		//作者
		var name = $("#nameId");
		$("#nameWarn").remove();
		if(isNotNull(name)==false){
			name.after("<font id='nameWarn' color='red'>请填写姓名！</font>");
			name.focus();
			return false;
    	}
    	return true;
    }
    
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>