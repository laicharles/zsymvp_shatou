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
    <div class="container-fluid auto-fluid">
        <h2 class="title_class">编辑${dicName}</h2>
		<form:form action="${pageContext.request.contextPath}/cus/ccms_update.do?_csrf=${_csrf.token}" modelAttribute="CCmsAddForm" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="id" id="id" value="${cmsForm.id }">
			<input type="hidden" name="createdDateTime" value="${cmsForm.createdDateTime}">
			<input type="hidden" name="token" value="${cmsForm.token }">
			<input type="hidden" name="cmsType.logicID" value="${cmsType }">
			
			<div>
				<label for="titleId"><font color="red">*</font>标题：</label>
				<input type="text" name="title" id="titleId" value="${cmsForm.title}" class="general_input_class">
			</div>
			<div>
				<label for="authorId">作者：</label>
				<input type="text" name="author" id="authorId" value="${cmsForm.author}" class="general_input_class">
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
				<input type="file" name="picNameFile" id="picNameFile" style="display:none">
				<input type="button" id="picBtn" onclick="document.getElementById('picNameFile').click()" value="选择 " class="btn btn-default btn-chose"/>
				<input type="hidden" name="picName" id="picName">
			</div>
			
			<div class="mtrl_class">
				<button class="btn btn-default" data-toggle="modal"
									data-target="#picMtrlModal" type="button">素材库</button>
			</div>
			
			
			<div>
				<label for="KindEditorId">正文：</label>
				<div class="pageContent_class">
					<textarea name="KindEditor" id="KindEditorId" class="kindeditor_class">${cmsForm.pageContent }</textarea><span style="color:red;"></span>
				</div>
				<input type="hidden" name="pageContent" id="pageContentId">
			</div>
			<div class="mtrl_class">
				<button class="btn btn-default" data-toggle="modal"
								data-target="#pageMtrlModal" type="button">素材库</button>
			</div>
			<div>
				<label for="sortNumberId">排序[微信端]：</label>
				<input type="text" id="sortNumberId"  class="pageUrl_class" name="sortNumber" value="${cmsForm.sortNumber}">
			</div>
			<div>
				<label for="origUrlId">原文链接：</label>
				<input type="text" id="origUrlId" class="pageUrl_class" name="origUrl" value="${cmsForm.origUrl}">
			</div>
			<div>
				<label for="titleId">正文外链：</label>
				<input type="text" id="pageUrlId" class="pageUrl_class" name="pageUrl" value="${cmsForm.pageUrl}">
				<div class="pageUrlTips">（注意：系统默认优先跳转至正文外链，若需要显示正文内容请将正文外链留空！）</div>
			</div>
			<c:if test="${cmsType=='cmsType09' }">
				<div>
					<label for="weixinPath">微信链接：</label>
					<input type="text" id="weixinPath" class="pageUrl_class" value="${weixinPath}" readonly="readonly">
					<input type="button" id="copyUrlBtn" value="复制" style="margin-top:-14px">
				</div>
			</c:if>
			<button type="button" class="btn btn-default" id="returnBtn">返回</button>
			<button type="button" class="btn btn-default" id="saveBtn" onmouseover="getContent()">提交</button>
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
<%-- <script src="<c:url value="/resources/js/bootstrap/plugins/jquery-confirm/1.5.0/jquery-confirm.min.js"/>" ></script> --%>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>
<script src="<c:url value="/resources/js/uploadPreview.min.js"/>"></script>

<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<!-- This is the Javascript file of jqGrid -->   
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<!-- This is the localization file of the grid controlling messages, labels, etc.
<!-- We support more than 40 localizations -->
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/i18n/grid.locale-cn.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/jquery.zclip.min.js"/>"></script>

<jsp:include page="../cPageMtrl_picklist.jsp"/>
<jsp:include page="../cPicMtrl_picklist.jsp"/>
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
        new uploadPreview({
			UpBtn : "picNameFile",
			DivShow : "imgdiv",
			ImgShow : "imgShow",
			ImgType: ["jpg"],
			ErrMsg: "选择文件错误,图片类型必须是jpg",
			MaxSize: 1
		});  
        $('#copyUrlBtn').zclip({ 
            path: '<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/ZeroClipboard.swf"/>', 
            copy: function(){//复制内容 
                return $('#weixinPath').val(); 
            }, 
            afterCopy: function(){//复制成功 
            	$("#msg").remove();
                $("<span id='msg'/>").insertAfter($('#copyUrlBtn')).text('复制成功').fadeOut(4000); 
            } 
        });
    });
    
    function setValuePageMtrl(obj){
//     	$("#titleId").val(obj.title);
    	zsy_editor.html(obj.pageContent);
    }
    
    function setValuePicMtrl(obj){
        
      	//use the same name of the derived one
    	$("#picName").val(obj.picName);
    	$("#inheritedToken").val(obj.token);
    	$("#imgShow").attr("src","${pageContext.request.contextPath}/resources/attached/"+obj.token+"/"+obj.picName);   	
    	$("#picNameFile").val("");
    	
    }
    
    $(function(){
    	var flag = {"title":false,"pageContent":false,"pageUrl":false};
    	//标题校验
    	$("#titleId").blur(function(){
    		var title = $(this);
    		$("#titleWarn").remove();
    		if(isNotNull(title)==false){
    			title.after("<font id='titleWarn' color='red'>请填写标题！</font>");
    			title.focus();
        	}else if(chklength(title,64)==false){
        		title.after("<font id='titleWarn'  color='red'>请输入小于32个字的标题！</font>");
        		title.focus();
        	}
    	});
    	//返回列表
    	$("#returnBtn").click(function(){
    		window.location.href="${pageContext.request.contextPath}/cus/${cmsType}/ccms_list.do?pageNo=${page }";
    	});
    	//保存
    	$("#saveBtn").click(function(){
    		if(checkSubmit()){
    			bootbox.confirm("确定保存吗？",function(result){
    				if(result==true){

    					 if(($("#picNameFile").val()=="" && ($("#picName").val()=="" || $("#picName").val()==null)) ||
    							 ($("#picNameFile").val()=="" && ($("#picName").val()!="" && $("#picName").val()!=null))){
    	 						$("#picNameFile").attr("disabled",true);
    					 }
    					$("#CCmsAddForm").ajaxSubmit(function(msg){
               				if(msg.result==false){
               					bootbox.alert(msg.warnMsg,function(){
               						window.location.href="${pageContext.request.contextPath}/cus/"+msg.tips+"/ccms_list.do?pageNo=${page }";
               					});
            				}else{
            					bootbox.alert(msg.warnMsg,function(){
            						window.location.href="${pageContext.request.contextPath}/cus/"+msg.tips+"/ccms_list.do?pageNo=${page }";
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
    	//标题
    	var title = $("#titleId");
		$("#titleWarn").remove();
		if(isNotNull(title)==false){
			title.after("<font id='titleWarn' color='red'>请填写标题！</font>");
			title.focus();
			return false;
    	}else if(chklength(title,64)==false){
    		title.after("<font id='titleWarn'  color='red'>请输入小于32个字的标题！</font>");
    		title.focus();
    		return false;
    	}
		//作者
		var author = $("#authorId");
		$("#authorWarn").remove();
		if(chklength(author,64)==false){
			author.after("<font id='authorWarn'  color='red'>请输入小于32个字的作者！</font>");
			author.focus();
    		return false;
    	}
		//排序编号
		var sortNumber = $("#sortNumberId");
		$("#sortNumberWarn").remove();
		if(isNotNull(sortNumber)==false){
			sortNumber.after("<font id='sortNumberWarn' color='red'>请填写排序编号，值小排在前面</font>");
			sortNumber.focus();
			return false;
		}else if(isNotNumber(sortNumber)){
			sortNumber.after("<font id='sortNumberWarn' color='red'>请填入数字</font>");
			sortNumber.focus();
			return false;
		}
		
    	//正文
    	var pageContent = $("#pageContentId");
    	var pageContentVal = pageContent.val();
    	//外链
    	var pageUrl = $("#pageUrlId");
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
    	return chklengthCms(pageContent,$("#KindEditorId"),'pageContentWarn');
    	//原文链接
    	$("#origUrlWarn").remove();
    	var origUrl = $("#origUrlId");
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
    	return true;
    }
    
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>