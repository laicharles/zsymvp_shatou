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
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/jquery-confirm/1.5.0/jquery-confirm.css"/>" type="text/css">
    <link href="<c:url value="/resources/js/bootstrap/css/demo.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/bootstrap/css/themes/social.theme-blue.css"/>" rel="stylesheet" id="theme">
    <!-- 本页调用样式 开始 -->
    <link href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqvmap/jqvmap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
 
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
    </style>
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/bootstrap/plugins/html5shiv.js"/>"></script>
    <![endif]-->
    <link href="<c:url value="/resources/js/editor/themes/default/default.css"/>" rel="stylesheet">
     <script src="<c:url value="/resources/js/editor/kindeditor-min.js"/>"></script>
	<script src="<c:url value="/resources/js/editor/lang/zh_CN.js"/>"></script>
	<script src="<c:url value="/resources/js/editor/zsy_editor.js"/>"></script>
	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
	    var csrf_token = "${_csrf.token}";
		function getContent(){
			var contentdata = zsy_editor.html();
			var pageContent = document.getElementById("pageContent");
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
        <h2 class="title_class">${nameDictionary.dicName}回复</h2>
		<form:form action="${pageContext.request.contextPath}/cus/updateFeedback.do?_csrf=${_csrf.token}&openId=${feedbackForm.openId}" modelAttribute="CFeedBackManageForm" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${feedbackForm.id }">
			<input type="hidden" name="token" value="${feedbackForm.token }">
			<div>
				<label for="telId">反馈人姓名：</label>
				<input type="text" name="feedbackOption" id="feedbackOptionId" value="${feedbackForm.name }" class="general_input_class" readonly>
			</div>
			<div>
				<label for="telId">电话：</label>
				<input type="text" id="telId" value="${feedbackForm.tel }" class="general_input_class" readonly>
			</div>
			<div>
				<label for="addrId">地址：</label>
				<input type="text" id="addressId" value="${feedbackForm.address }" class="pageUrl_class" readonly>
			</div>
			<div>
				<label for="descriptionId">用户备注：</label>
				<textarea id="contentId" class="textArea_class pageUrl_class" readonly> ${feedbackForm.content }</textarea>
			</div>
			<div>
			    <c:if test="${nameDictionary.dicName != '用水申请管理'}">
			    <label for="imgListId">附件：</label>
			    <c:if test="${picNameList != null}">
				<c:forEach items="${picNameList}" var="picName">
				   <c:if test= "${picName != ''}">
					<a class="example-image-link" href="${backendPath}/resources/attached/${token}/feedback/${picName}" data-lightbox="example-set" >
						<img class="example-image" src="${backendPath}/resources/attached/${token}/feedback/${picName}" alt="" style="width:100px;"/>
					</a>
					<c:if test= "${picName == ''}">
					</c:if>
				   </c:if>
				</c:forEach>
				</c:if>
				<c:if test="${picNameList == null}">
					无图片！
				</c:if>
				</c:if>
			</div>
			<br/>
			<div>
				<label for="replyById"><font color="red">*</font>回复人：</label>
				<input type="text" name="replyBy" id="replyById" value="${feedbackForm.replyBy}" class="general_input_class">
			</div>
			 <div>
				<label for="KindEditorId" class="col-sm-2 control-label"><font color="red">*</font>回复正文：</label>
				<div class="pageContent_class">
				<textarea name="KindEditor" id="KindEditorId" class="kindeditor_class">${feedbackForm.replyContent }</textarea>
				</div>
				<input type="hidden" id="pageContent">
				
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
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });
    
    $(function(){
    	//名称校验
    	$("#nameId").blur(function(){
    		var name = $("#nameId");
    		$("#nameWarn").remove();
    		if(isNotNull(name)==false){
    			name.after("<font id='nameWarn' color='red'>请填写标题！</font>");
    			name.focus();
        	}else if(chklength(name,64)==false){
        		name.after("<font id='nameWarn'  color='red'>请输入小于32个字的名称！</font>");
        		name.focus();
        	}
    	});
    	//电话校验
    	$("#telId").blur(function(){
    		var tel = $("#telId");
    		$("#telWarn").remove();
    		if(isNotNull(tel)==false){
    			tel.after("<font id='telWarn' color='red'>请填写电话！</font>");
    			tel.focus();
        	}else if(chklength(tel,32)==false){
        		tel.after("<font id='telWarn'  color='red'>请输入小于16位的电话号码！</font>");
        		tel.focus();
        	}
    	});
    	//地址校验
    	$("#addrId").blur(function(){
    		var addr = $("#addrId");
    		$("#addrWarn").remove();
    		if(isNotNull(addr)==false){
    			addr.after("<font id='addrWarn' color='red'>请填写地址！</font>");
    			addr.focus();
        	}else if(chklength(addr,128)==false){
        		addr.after("<font id='addrWarn'  color='red'>请输入小于64个字的地址！</font>");
        		addr.focus();
        	}
    	});
    	//简介校验
    	$("#descriptionId").blur(function(){
    		var description = $("#descriptionId");
    		$("#descriptionWarn").remove();
    		if(isNotNull(description)==false){
    			description.after("<font id='descriptionWarn' color='red'>请填写简介！</font>");
    			description.focus();
        	}else if(chklength(description,256)==false){
        		description.after("<font id='descriptionWarn'  color='red'>请输入小于128个字的简介！</font>");
        		description.focus();
        	}
    	});
    	//url校验
    	$("#mapUrlId").blur(function(){
    		var mapUrl = $("#mapUrlId");
    		$("#mapUrlWarn").remove();
    		if(isNotNull(mapUrl)==false){
    			mapUrl.after("<font id='mapUrlWarn' color='red'>请填写url！</font>");
    			mapUrl.focus();
        	}else if(chklength(mapUrl,256)==false){
        		mapUrl.after("<font id='mapUrlWarn'  color='red'>请输入小于256位的url！</font>");
        		mapUrl.focus();
        	}else if(!IsURL(mapUrl)){
        		mapUrl.after("<font id='mapUrlWarn' color='red'>请输入正确的url！</font>");
        		mapUrl.focus();
        		return false;
        	}
    	});
    	//返回列表
    	$("#returnBtn").click(function(){
    		window.location.href="${pageContext.request.contextPath}/cus/${feedBackType}/feedbackList.do?pageNo=1";
    	});
    	//保存
    	$("#saveBtn").click(function(){
    		if(true){
    			bootbox.confirm("确定保存吗？",function(result){
    				if(result==true){
    					$("#CFeedBackManageForm").ajaxSubmit(function(msg){
               				if(msg.result==false){
               					bootbox.alert(msg.warnMsg,function(){
               						window.location.href="${pageContext.request.contextPath}/cus/${feedBackType}/feedbackList.do?pageNo=1";
               					});
            				}else{
            					bootbox.alert(msg.warnMsg,function(){
            						window.location.href="${pageContext.request.contextPath}/cus/${feedBackType}/feedbackList.do?pageNo=1";
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
    	//名称
    	var name = $("#nameId");
		$("#nameWarn").remove();
		if(isNotNull(name)==false){
			name.after("<font id='nameWarn' color='red'>请填写标题！</font>");
			name.focus();
			return false;
    	}else if(chklength(name,64)==false){
    		name.after("<font id='nameWarn'  color='red'>请输入小于32个字的名称！</font>");
    		name.focus();
    		return false;
    	}
		//电话
		var tel = $("#telId");
		$("#telWarn").remove();
		if(isNotNull(tel)==false){
			tel.after("<font id='telWarn' color='red'>请填写电话！</font>");
			tel.focus();
			return false;
    	}else if(chklength(tel,32)==false){
    		tel.after("<font id='telWarn'  color='red'>请输入小于16位的电话号码！</font>");
    		tel.focus();
    		return false;
    	}
		//简介
		var description = $("#descriptionId");
		$("#descriptionWarn").remove();
		if(isNotNull(description)==false){
			description.after("<font id='descriptionWarn' color='red'>请填写简介！</font>");
			description.focus();
			return false;
    	}else if(chklength(description,256)==false){
    		description.after("<font id='descriptionWarn'  color='red'>请输入小于128个字的简介！</font>");
    		description.focus();
    		return false;
    	}
		//地址
		var addr = $("#addrId");
		$("#addrWarn").remove();
		if(isNotNull(addr)==false){
			addr.after("<font id='addrWarn' color='red'>请填写地址！</font>");
			addr.focus();
			return false;
    	}else if(chklength(addr,128)==false){
    		addr.after("<font id='addrWarn'  color='red'>请输入小于64个字的地址！</font>");
    		addr.focus();
    		return false;
    	}
		//url
		var mapUrl = $("#mapUrlId");
		$("#mapUrlWarn").remove();
		if(isNotNull(mapUrl)==false){
			mapUrl.after("<font id='mapUrlWarn' color='red'>请填写url！</font>");
			mapUrl.focus();
			return false;
    	}else if(chklength(mapUrl,256)==false){
    		mapUrl.after("<font id='mapUrlWarn'  color='red'>请输入小于256位的url！</font>");
    		mapUrl.focus();
    		return false;
    	}else if(!IsURL(mapUrl.val())){
    		mapUrl.after("<font id='mapUrlWarn' color='red'>请输入正确的url！</font>");
    		mapUrl.focus();
    		return false;
    	}
		return true;
    }
    
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>