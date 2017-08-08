<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../../base/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<title> 汕头自来水微信服务系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="shortcut icon"
	href="<c:url value="/resources/js/bootstrap/img/shantou.ico"/>" />
<link
	href="<c:url value="/resources/js/bootstrap/css/twitter-bootstrap/bootstrap.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/js/bootstrap/css/social-jquery-ui-1.10.0.custom.css"/>"
	rel="stylesheet">
<link href="<c:url value="/resources/js/bootstrap/css/social.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/js/bootstrap/css/social.plugins.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/js/bootstrap/css/font-awesome.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/js/bootstrap/css/social-coloredicons-buttons.css"/>"
	rel="stylesheet">
<!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/bootstrap/css/social-jquery.ui.1.10.0.ie.css"/>"/>
    <![endif]-->
<link href="<c:url value="/resources/js/bootstrap/css/demo.css"/>"
	rel="stylesheet">
<link
	href="<c:url value="/resources/js/bootstrap/css/themes/social.theme-blue.css"/>"
	rel="stylesheet" id="theme">
<!-- 本页调用样式 开始 -->
<link
	href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqvmap/jqvmap.css"/>"
	rel="stylesheet">
	<link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
<!-- 本页调用样式 结束 -->
<style>
	.wraper #main {
		margin-top: 40px;
	}
	
	/* .autoPlace {
		margin: 0px 10px 10px;
	} */
	
	h4 {
		margin: 0px 35px 0px;
	}
	
	.imgShow_class {
		width: 264px;
		height: 150px;
</style>
<!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/bootstrap/plugins/html5shiv.js"/>"></script>
    <![endif]-->
    
     <link href="<c:url value="/resources/js/editor/themes/default/default.css"/>" rel="stylesheet">
	<script src="<c:url value="/resources/js/editor/kindeditor-min.js"/>"></script>
	<script src="<c:url value="/resources/js/editor/lang/zh_CN.js"/>"></script>
    <script src="<c:url value="/resources/js/editor/zsy_editor.js"/>"></script>
    <script>
	    var contextPath = "${pageContext.request.contextPath}";
	    var csrf_token = "${_csrf.token}";
    </script>
</head>

<body>
	<!-- 外框架 开始 -->
	<div class="wraper sidebar-full">
		<!-- 侧栏 -->
		<aside class="social-sidebar sidebar-full">
			<!-- 用户设置 -->
			<div class="user-settings">
				<jsp:include page="../../../base/cus/userSetting.jsp" />
			</div>
			<!-- 内容 -->
			<div class="social-sidebar-content">
				<div class="scrollable">
					<!-- 用户信息 -->
					<div class="user">
						<img class="avatar" width="25" height="25"
							src="<c:url value="/resources/js/bootstrap/img/avatar.png"/>"
							alt="Julio Marquez"><span>${user.loginName }</span><i
							class="icon-user trigger-user-settings"></i>
					</div>
					<div class="navigation-sidebar">
						<i class="switch-sidebar-icon icon-align-justify"></i>
					</div>

					<section class="menu">
						<jsp:include page="../../../base/cus/menu.jsp" />
					</section>
					<!-- 主菜单 结束 -->
				</div>
			</div>
		</aside>
		<!-- 页头-->
		<header>
			<!-- 导航 -->
			<nav class="navbar navbar-fixed-top social-navbar social-sm">
				<jsp:include page="../../../base/cus/head.jsp" />
			</nav>
		</header>

		<!-- 主体 -->
		<div id="main">
			<div class="container-fluid auto-fluid">
					<h2 class="title_class">默认回复设置</h2>
					<h4 style=" margin:0px; color:#3b5998;">默认无匹配和无客服时回复</h4><br/>
					<form:form action="${pageContext.request.contextPath}/cus/cautoDefault_update.do?_csrf=${_csrf.token}"
						modelAttribute="form1" method="POST"
						enctype="multipart/form-data">
						<div>
							<input type="hidden" name="id" value="${cautoDefault.id}"> 
							<input type="hidden" name="picName" id="picName" value="${cautoDefault.picName }">
							<div id="question_Div">
								<label for="autoReply"><font class="need_font">*</font>文本内容：</label> 
								<textarea class="textArea_class autoPlace" name="autoReply" style="width: 650px; height: 300px;"
									id="autoReply" >${cautoDefault.autoReply}</textarea>
							</div>
						</div>
						<br/>
						<div style="border-top: dotted black 1px"> </div>
						<br/>
						<h4 style=" margin:0px; color:#3b5998;">默认关注时回复</h4><br/>
						<div id="answerType_Div">
							<label for="answerType"><font class="need_font">*</font>回复类型：</label>
							<select name="sdictionary.logicID" id="logicID" class="general_select_class autoPlace" onchange="funType()">
			                    <c:forEach var='replyType' items='${sDictionaryFormList}'>
				                    <c:choose>
				                     <c:when test="${replyType.logicID==cautoDefault.sdictionary.logicID}">
				                     	<option value='${replyType.logicID}' selected="selected">${replyType.dicName}</option>
				                     </c:when>
				                     <c:otherwise>
				                     	<option value='${replyType.logicID}'>${replyType.dicName}</option>
				                     </c:otherwise>
				                   	</c:choose>
			                    </c:forEach>
							</select>
						</div>

						<div class="form-group" id="textContent_Div">
							<label for="textContent"><font class="need_font">*</font>文本内容：</label>
							<textarea class="textArea_class autoPlace" name="textContent" style="width: 650px; height: 300px"
								id="textContent" >${cautoDefault.textContent}</textarea>
						</div>
						
						<div id="twContent_Div">
							<div id="title_Div">
								<label for="title"><font class="need_font">*</font>图文标题：</label> 
								<input type="text" name="title" id="title" value="${cautoDefault.title}" class="general_input_class autoPlace" size="64">
							</div>
	
							<div id="author_Div">
								<label for="author">作者：</label>
								<input type="text" name="author" id="author" value="${cautoDefault.author}" class="general_input_class autoPlace" size="32">
							</div>
	
							<div id="picName_Div" class="form-group">
								<label for="picNameFile">图文图片：</label>
								<div id="imgdiv">
									<img id="imgShow" src="${pageContext.request.contextPath}${picPath}" class="imgShow_class autoPlace"/>
								</div>
								<input type="file" name="picNameFile" id="picNameFileId" style="display:none">
								<input type="button" id="picBtn" onclick="document.getElementById('picNameFileId').click()" value="选择 " class="btn btn-default" />
							</div> 
	
							<div class="form-group" id="description_Div">
								<label for="description"><font class="need_font">*</font>图文简介：</label>
								<textarea class="textArea_class autoPlace"  name="description"
									id="description">${cautoDefault.description}</textarea>
							</div>
							
							<div id="pageContent_Div">
								<label for="KindEditorId">正文：</label>
								<textarea name="KindEditor" id="KindEditorId" class="kindeditor_class">${cautoDefault.pageContent}</textarea>
								<input type="hidden" name="pageContent" id="pageContent">
							</div>
							
							<div id="pageUrl_Div">
								<label for="pageUrl">图文外链地址：</label>
								<input type="text" name="pageUrl" id="pageUrl" value="${cautoDefault.pageUrl}" class="pageUrl_class" size="256">
							</div>
							
							<div id="origUrl_Div">
								<label for="origUrl">原文链接：</label>
								<input type="text" name="origUrl" id="origUrl" value="${cautoDefault.origUrl}" class="pageUrl_class" size="256">
							</div>
						</div>
						
						<input type="button" id="subBtn" class="btn btn-default" value="保存" style="margin-left:127px">
					</form:form>
				</div>
			<!-- 页脚开始 -->
			<footer id="footer">
				<jsp:include page="../../../base/cus/foot.jsp" />
			</footer>
			<!-- 页脚结束 -->
			</div>
			
		</div>
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/plugins/twitter-bootstrap/bootstrap.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.slimscroll/jquery.slimscroll.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/js/extents.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/js/sidebar.js"/>"></script>
	<script src="<c:url value="/resources/js/uploadPreview.min.js"/>"></script>
	<script src="<c:url value="/resources/js/autoValfield.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.form/3.51/jquery.form.js"/>"></script>
	<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>
	<script src="<c:url value="/resources/js/valField.js"/>" ></script>
	<!-- 本页调用JS 开始 -->
	<script>
		$(document).ready(function() {
			SideBar.init({
				shortenOnClickOutside : false
			});
			new uploadPreview({
				UpBtn : "picNameFileId",
				DivShow : "imgdiv",
				ImgShow : "imgShow"
			});
			//提交按钮
			$("#subBtn").click(function() {
				getContent();
				saveRecord();
			});
			funType();
		});
		
		function funType(){
			var logicID = $("#logicID").val();
			if(logicID=="answerType1"){
				$("#textContent_Div").css('display', 'block');
				$("#twContent_Div").css('display', 'none');
			}else if(logicID=="answerType2"){
				$("#textContent_Div").css('display', 'none');
				$("#twContent_Div").css('display', 'block');
			}
		}

		function saveRecord() {
			if(checkJs()){
				bootbox.confirm("确定保存吗？",function(result){
					if(result==true){
						if($("#picNameFileId").val()==""){
							$("#picNameFileId").attr("disabled",true);
						}
						$("#form1").ajaxSubmit(function(msg){
	           				if(msg.result==false){
	           					bootbox.alert(msg.warnMsg,function(){
	           						window.location.href="${pageContext.request.contextPath}/cus/cautoDefault_toUpdate.do";
	           					});
	        				}else{
	        					bootbox.alert(msg.warnMsg,function(){
	        						window.location.href="${pageContext.request.contextPath}/cus/cautoDefault_toUpdate.do";
	        					});
	        				}
							$('#picNameFileId').attr("disabled", false);
	        			});
					}
	    		});
			}
		}
		
		function checkJs(){
			//名称
	    	var autoReply = $("#autoReply");
			$("#autoReplyWarn").remove();
			if(isNotNull(autoReply)==false){
				autoReply.after("<font id='autoReplyWarn' color='red'>请填写标题！</font>");
				autoReply.focus();
				return false;
	    	}else if(chklength(autoReply,512)==false){
	    		autoReply.after("<font id='autoReplyWarn'  color='red'>请输入小于256个字的名称！</font>");
	    		autoReply.focus();
	    		return false;
	    	}
			var type = $("#logicID").val();
			if(type=="answerType1"){
				//文本内容
		    	var textContent = $("#textContent");
				$("#textContentWarn").remove();
				if(isNotNull(textContent)==false){
					textContent.after("<font id='textContentWarn' color='red'>请填写标题！</font>");
					textContent.focus();
					return false;
		    	}else if(chklength(textContent,512)==false){
		    		textContent.after("<font id='nameWarn'  color='red'>请输入小于256个字的名称！</font>");
		    		textContent.focus();
		    		return false;
		    	}
			}else if(type=="answerType2"){
				//标题
		    	var title = $("#title");
				$("#titleWarn").remove();
				if(isNotNull(title)==false){
					title.after("<font id='titleWarn' color='red'>请填写标题！</font>");
					title.focus();
					return false;
		    	}else if(chklength(title,64)==false){
		    		title.after("<font id='titleWarn'  color='red'>请输入小于32个字的名称！</font>");
		    		title.focus();
		    		return false;
		    	}
				//简介
		    	var description = $("#description");
				$("#descriptionWarn").remove();
				if(isNotNull(description)==false){
					description.after("<font id='descriptionWarn' color='red'>请填写标题！</font>");
					description.focus();
					return false;
		    	}else if(chklength(description,256)==false){
		    		description.after("<font id='descriptionWarn'  color='red'>请输入小于128个字的名称！</font>");
		    		description.focus();
		    		return false;
		    	}
				//正文
		    	var pageContent = $("#pageContent");
		    	var pageContentVal = pageContent.val();
		    	//外链
		    	var pageUrl = $("#pageUrl");
		    	var pageUrlVal = pageUrl.val();
		    	$("#pageContentWarn").remove();
		    	$("#pageUrlWarn").remove();
		    	if(pageContentVal==""&&pageUrlVal==""){
		    		pageUrl.after("<font id='pageContentWarn' color='red'>正文和正文外链至少必须填写其一！</font>");
		    		pageContent.after("<font id='pageUrlWarn' color='red'>正文和正文外链至少必须填写其一！</font>");
		    		return false;
		    	}
		    	if(!IsURL(pageUrlVal)&&pageUrlVal!=""){
		    		pageUrl.after("<font id='pageContentWarn' color='red'>请输入正确的网址！</font>");
		    		pageUrl.focus();
		    		return false;
		    	}
		    	//网址字数校验
		    	if(pageUrlVal!=""&&chklength(pageUrl,255)==false){
		    		pageUrl.after("<font id='pageContentWarn' color='red'>网址太长！</font>");
		    		pageUrl.focus();
		    		return false;
		    	}
		    	//内容字数校验
		    	if(pageContentVal!=""&&chklength(pageContent,20000)==false){
		    		pageContentVal.after("<font id='pageContentWarn' color='red'>正文内容太长！</font>");
		    		pageContentVal.focus();
		    		return false;
		    	}
		    	//原文链接
		    	$("#origUrlWarn").remove();
		    	var origUrl = $("#origUrl");
		    	var origUrlVal = origUrl.val();
		    	if(!IsURL(origUrlVal)&&origUrlVal!=""){
		    		origUrl.after("<font id='origUrlWarn' color='red'>请输入正确的网址！</font>");
		    		origUrl.focus();
		    		return false;
		    	}
		    	//原文链接字数校验
		    	if(origUrlVal!=""&&chklength(origUrl,255)==false){
		    		origUrl.after("<font id='origUrlWarn' color='red'>网址太长！</font>");
		    		origUrl.focus();
		    		return false;
		    	}
		    	var filePath = $("#picNameFileId").val();
				$("#imgFilecolor").remove();
				if(filePath!=""){
					var arr = filePath.split("\\");
					var fileName = 	arr[arr.length-1];
					$("#picName").val(fileName);
				}else{
					if($("#picName").val()==""){
						$("#picBtn").after("<font id=imgFilecolor color='red'>请选择上传的图片！</font>");
						$("#picBtn").focus();
						return false;
					}
				}
			}
			return true;
		}
		
		function getContent(){
			var contentdata = zsy_editor.html();
			$("#pageContent").val(contentdata);
		}
	</script>
	<!-- 本页调用JS 结束 -->
</body>

</html>