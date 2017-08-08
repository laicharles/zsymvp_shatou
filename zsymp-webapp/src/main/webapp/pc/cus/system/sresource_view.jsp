<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title> 汕头自来水微信服务系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/js/bootstrap/plugins/jquery-ui-1.10.3.custom/css/blitzer/jquery-ui-1.10.3.custom.min.css"/>" rel="stylesheet">
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
    
    <%-- <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/zTreeStyle.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/demo.css"/>" type="text/css"> --%>
    <link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
    <meta charset="utf-8" />
    <title>资源新增</title>
    
    
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
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
    <div class="user"><img class="avatar" width="25" height="25" src="<c:url value="/resources/js/bootstrap/img/avatar.png"/>" alt="Julio Marquez"><span>${user.loginName }</span><i class="icon-user trigger-user-settings"></i></div>
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
    <div class="container-fluid">
        <!--主体内容-->
       <!-- <div style="height: 20px"></div>-->
  		<div >
	  <p class="validateTips">   </p> 
	  <h2 class="title_class">查看资源</h2>
	  <form:form action="${pageContext.request.contextPath}/cus/sresource_update.do"  modelAttribute="SResourceForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" value="${resourceForm.id }">
	      	  <input type="hidden" name="resCode" id="resCode" value="${resourceForm.resCode }">
	          
	          <div>
		      <label for="resName"  ><font color="red">*</font>资源名：</label>
		      <input type="text" name="resName" id="resName" disabled="disabled"   value="${resourceForm.resName }" class="general_input_class" size="64">
	      	  </div>
	      		
	      	<div>
		      <label for="parentResource.resName"  class="col-sm-2 control-label"><font color="red">*</font>上级资源：</label>
		      <input type="text"  name="parentResource.resName" id="parentResource.resName" readonly="readonly" class="text ui-widget-content general_input_class ui-corner-all" value="${resourceForm.parentResource.resName }">
	      	</div>
	      	<input type="hidden"   name="parentResource.id" id="parentResource.id" readonly="readonly" class="text ui-widget-content ui-corner-all" value="${resourceForm.parentResource.id }">
	      	
	      	<div>
		      <label for="resType"  ><font color="red">*</font>资源类型：</label>
		      <div class="input-radio">
		        <input type="hidden" name="resType.typeCode" value="resourceType"> 
		        <input type="radio" name="resType.dicValue" value="0"  disabled="disabled"  <c:if test="${resourceForm.resType.dicValue==0 }">checked="checked"  </c:if>   />目录
		      	<input type="radio" name="resType.dicValue" value="1"  disabled="disabled"  <c:if test="${resourceForm.resType.dicValue==1 }">checked="checked"  </c:if>  />菜单
		      	<input type="radio" name="resType.dicValue" value="2" disabled="disabled"  <c:if test="${resourceForm.resType.dicValue==2 }">checked="checked"  </c:if>  />操作
		      </div>
			</div>
		
		 <div>
	        <label for="isShowMenu" class="col-sm-2 control-label"><font color="red">*</font>是否显示：</label>
	        <div class="input-radio" style="margin-bottom: 10px;">
		        <input type="radio" name="isShowMenu" value="0"  disabled="disabled"  <c:if test="${resourceForm.isShowMenu==0 }">checked="checked"  </c:if> />否
		      	<input type="radio" name="isShowMenu" value="1"  disabled="disabled"  <c:if test="${resourceForm.isShowMenu==1 }">checked="checked"  </c:if> />是
	      	</div>
	      </div>
	      
	      <div>
			<label for="reqUrl" class="col-sm-2 control-label"><font color="red">*</font>资源链接：</label>
	      <input type="text" name="reqUrl" id="reqUrl"  disabled="disabled" size="256" class="pageUrl_class"  value="${resourceForm.reqUrl }" >
	      </div>
	      
	      <div>
	      <label for="authUrl" class="col-sm-2 control-label"><font color="red">*</font>权限链接：</label>
	      <input type="text" name="authUrl" id="authUrl"  disabled="disabled"  size="256" class="pageUrl_class"  value="${resourceForm.authUrl }" >
	      </div>
	      
	      <!-- <label for="picName">资源图片</label>
	      <input type="text" name="picName" id="picName"  class="text ui-widget-content ui-corner-all"> -->
	      <div>
	 	  <label for="remarks" class="col-sm-2 control-label">备注：</label>
	 	  <textarea rows="5" cols="20" name="remarks" id="remarks" disabled="disabled" size="256" class="textArea_class" >${resourceForm.remarks }</textarea>
	 	  </div>
	      <!-- Allow form submission with keyboard without duplicating the dialog button -->
	      
	      	<input type="button" class="btn btn-default"  id="returnBtn" value="返回"> 
	      	
	  </form:form>
    </div> 
       
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

<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/jquery.form/3.51/jquery.form.js"/>"></script>

<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>

<script type="text/javascript" src="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/js/jquery.ztree.all-3.5.min.js"/>"></script>

<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });

    
    $(document).ready(function(){
      	 
      	 //返回按钮
   	     $("#returnBtn").click(function(){
   	    	 window.location.href='${pageContext.request.contextPath}/cus/sresource_list.do';
   			});
      	 
       });
    
		
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>