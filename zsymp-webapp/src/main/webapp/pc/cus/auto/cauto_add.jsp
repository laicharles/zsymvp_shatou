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
					<p class="validateTips"></p>
					<h2 class="title_class">新增自动回复</h2>
					<form:form
						action="${pageContext.request.contextPath}/cus/cauto_add.do?_csrf=${_csrf.token}"
						modelAttribute="CAutoForm" method="POST"
						enctype="multipart/form-data">

						<!-- 	      	  <input type="hidden" name="id" id="id" > -->
						
						<div id="tag_Div">
							<label for="tag"><font class="need_font">*</font>标签：</label>
							<div id="tagContent">
								<button class="btn btn-default" data-toggle="modal"
									data-target="#tagModal" type="button">添加</button>
							</div>
						</div>
						
						<div id="keyWord_Div">
							<label for="keyWord"><font class="need_font">*</font>关键词：</label>
							<div id="kwContent">
								<button class="btn btn-default" data-toggle="modal"
									data-target="#kwModal" type="button">添加</button>
							</div>
						</div>

						<div id="question_Div">
							<label for="question"><font class="need_font">*</font>问题名：</label> <input
								type="text" name="question" id="question" class="general_input_class" size="100">
						</div>

						<div id="answerType_Div">
							<label for="answerType"><font class="need_font">*</font>回复类型：</label>
							<div id="answerType"></div>
						</div>

						<div class="form-group" id="textContent_Div">
							<label for="textContent"><font class="need_font">*</font>文本内容：</label>
							<textarea class="textArea_class" name="textContent" size="300"
								id="textContent"></textarea>
						</div>

						<div id="title_Div">
							<label for="title"><font class="need_font">*</font>图文标题：</label> <input
								type="text" name="title" id="title" class="general_input_class" size="64">
						</div>

						<div id="picName_Div" class="form-group">
							<label for="picName"><font class="need_font">*</font>图文图片：</label>
							<div id="imgdiv">
								<img id="imgShow" class="imgShow_class" />
							</div>
							<input type="file" id="picName" name="picName" />
							<input type="hidden" name="picUrl" id="picUrl">
						</div>

						<div class="form-group" id="description_Div">
							<label for="description"><font class="need_font">*</font>图文简介：</label>
							<textarea class="textArea_class"  name="description" size="300"
								id="description"></textarea>
						</div>

						<div id="pageUrl_Div">
							<label for="pageUrl">图文外链地址：</label>
							<input type="text" name="pageUrl" id="pageUrl" class="pageUrl_class" size="256">
						</div>
						
						<div id="pageContent_Div">
							<label for="KindEditorId">正文：</label>
							<textarea name="KindEditor" id="KindEditorId" class="kindeditor_class"></textarea>
							<input type="hidden" name="pageContent" id="pageContent">
						</div>
						
						<div id="author_Div">
							<label for="author">作者：</label>
							<input type="text" name="author" id="author" class="general_input_class" size="32">
						</div>
						
						<div id="origUrl_Div">
							<label for="origUrl">原文链接：</label>
							<input type="text" name="origUrl" id="origUrl" class="pageUrl_class" size="256">
						</div>
						
						<input type="button" id="returnBtn" class="btn btn-default"
							value="返回">

						<input type="button" id="subBtn" class="btn btn-default"
							value="保存">
						<span class="errortip"> <c:if
								test="${not empty cAutoForm.tips}">
								<font color="red"> ${cAutoForm.tips} </font>
							</c:if>
						</span>
					</form:form>
				</div>
				<!-- 页脚开始 -->
				<footer id="footer">
					<jsp:include page="../../../base/cus/foot.jsp" />
				</footer>
				<!-- 页脚结束 -->
			</div>

			<div class="modal fade" id="tagModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="tagModalLabel">添加标签</h4>
						</div>
						<div class="modal-body">
							<input type="text" class="form-control" id="tagAdd"
								placeholder="请输入标签"
								style='border-left: 0px; border-top: 0px; border-right: 0px; border-bottom: 0' />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" id="btn-tagAdd">确认添加</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
			
			<div class="modal fade" id="kwModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="kwModalLabel">添加关键词</h4>
						</div>
						<div class="modal-body">
							<input type="text" class="form-control" id="kwAdd"
								placeholder="请输入关键词"
								style='border-left: 0px; border-top: 0px; border-right: 0px; border-bottom: 0' />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" id="btn-kwAdd">确认添加</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
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
	<script src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>

	<!-- 本页调用JS 开始 -->
	<script>
		var kwNum = 0;
		var tagNum = 0;
		$(function() {
			SideBar.init({
				shortenOnClickOutside : false
			});
		});

		$(document).ready(function() {
			init();

			//返回按钮
	    	$("#returnBtn").click(function(){
	    		window.location.href="${pageContext.request.contextPath}/cus/cauto_list.do?pageNo=1";
	    	});
			
			//提交按钮
			$("#subBtn").click(function() {
				getContent();
				saveRecord();
			});
			
			$("#btn-tagAdd").click(function() {
				if($.trim($("#tagAdd").val())!="" && $("#tagAdd").val().length<=16)
				$('#tagContent').prepend('<span id="tagSpan_'+tagNum+'"><span  class="label label-success">'
										+ $("#tagAdd").val()
										+ '</span><span id="'+tagNum+'" class="tagDelete" style="cursor:pointer">&times;</span>&nbsp;&nbsp;'
										+ '<input type="hidden" name="CSelfTags['
										+ tagNum
										+ '].name" value="'
										+ $("#tagAdd").val()
										+ '"></span>');
				$("#tagAdd").val('');
				tagNum++;
			});

			$("#btn-kwAdd").click(function() {
				if($.trim($("#kwAdd").val())!="" && $("#kwAdd").val().length<=16)
				$('#kwContent').prepend('<span id="kwSpan_'+kwNum+'"><span  class="label label-info">'
										+ $("#kwAdd").val()
										+ '</span><span id="'+kwNum+'" class="kwDelete" style="cursor:pointer">&times;</span>&nbsp;&nbsp;'
										+ '<input type="hidden" name="CAutoKeywords['
										+ kwNum
										+ '].keyName" value="'
										+ $("#kwAdd").val()
										+ '"></span>');
				$("#kwAdd").val('');
				kwNum++;
			});

			$(document).on("click", ".kwDelete", function() {
				$("#kwSpan_" + $(this).attr("id")).remove();
			});
			
			$(document).on("click", ".tagDelete", function() {
				$("#tagSpan_" + $(this).attr("id")).remove();
			});

		});

		function init() {
			new uploadPreview({
				UpBtn : "picName",
				DivShow : "imgdiv",
				ImgShow : "imgShow"
			});
			
			$.ajax({
						url : '${pageContext.request.contextPath}/system/dictionary_list.do',
						type : 'POST',
						data : {
							'typeCode' : "answerType"
						},
						dataType : 'json',
						timeout : 1000,
						error : function() {
							alert('Error');
						},
						success : function(result) {
							var radioHtml = "";
							$.each(
									result,
									function(index, callback) {
										if (index == 0) {
											radioHtml += "<input type='radio' name='answerType' id='"+result[index].logicID+"' value='"+result[index].logicID+"' checked>"
													+ result[index].dicName
													+ "&nbsp;&nbsp;";
										} else {
											radioHtml += "<input type='radio' name='answerType' id='"+result[index].logicID+"' value='"+result[index].logicID+"'>"
													+ result[index].dicName
													+ "&nbsp;&nbsp;";
										}

									});
							$("#answerType").html(radioHtml + "<label></label>");
							$("#answerType1").on("click",function() {
												$("#textContent_Div").css('display', 'block');
												$('#textContent').attr("disabled", false);
												$("#title_Div,#description_Div,#picName_Div,#pageUrl_Div,#pageContent_Div,#author_Div,#origUrl_Div").css('display', 'none');
												$('#title,#description,#picName,#pageUrl,#KindEditorId,#pageContent,#author,#origUrl').attr("disabled", true);
											});

							$("#answerType2").on("click",function() {
												$("#textContent_Div").css('display', 'none');
												$('#textContent').attr("disabled", true);
												$("#title_Div,#description_Div,#picName_Div,#pageUrl_Div,#pageContent_Div,#author_Div,#origUrl_Div").css('display', 'block');
												$('#title,#description,#picName,#pageUrl,#KindEditorId,#pageContent,#author,#origUrl').attr("disabled", false);
											});

							$("#answerType1").click();
						}
					});
		}


		function saveRecord() {

			if (checkForm("CAutoForm")) {
				bootbox.confirm("确定保存吗？",function(result){
					if(result==true){
						$('#subBtn').attr("disabled", true);
						$("#CAutoForm").ajaxSubmit(function(message) {
							if (message == "false") {
								$('#subBtn').attr("disabled", false);
								return false;
							} else if (message == "true") {
								bootbox.alert("保存成功",function(){
								window.location.href='${pageContext.request.contextPath}/cus/cauto_list.do?pageNo=1';
								});
							}
						});
					}});
			}
		}
		
		function getContent(){
			var contentdata = zsy_editor.html();
			$("#pageContent").val(contentdata);
		}
	</script>
	<!-- 本页调用JS 结束 -->
</body>

</html>