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
    
    <link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
    <!-- 本页调用样式 开始 -->
    
    <meta charset="utf-8" />
    <title>查看粉丝</title>
    
    
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
	  <h2 class="title_class">查看粉丝</h2>
	  
	  <form:form action="${pageContext.request.contextPath}/cus/cfan_list.do?pageNo=${pageNo }"  modelAttribute="CFanForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" value="${fanForm.id }">
	          
	          <div>
		      <label for="nickName" ><font color="red">*</font>昵称：</label>
		      <input type="text" name="nickName" id="nickName" class="general_input_class" disabled="disabled" value="${fanForm.nickName }">
	      	  </div>
	      		
	      	<div>
		      <label for="openID"  class="col-sm-2 control-label"><font color="red">*</font>openID：</label>
		      <input type="text"  name="openID" id="openID" class="general_input_class" disabled="disabled"  value="${fanForm.openID }">
	      	</div>
	      	
	      	<div>
		      <label for="isSubscribe"  class="col-sm-2 control-label"><font color="red">*</font>是否关注：</label>
		      <input type="checkbox"  name="isSubscribe" id="isSubscribe" style="margin-top: 10px; margin-bottom: 20px;" disabled="disabled" <c:if test="${fanForm.isSubscribe==1 }">checked="checked"</c:if>>
	      	</div>
	      	
	      	<div>
		      <label for="gender.dicName"  class="col-sm-2 control-label"><font color="red">*</font>性别：</label>
		      <input type="text"  name="gender.dicName" id="gender.dicName" class="general_input_class" disabled="disabled"   value="${fanForm.gender.dicName }">
	      	</div>
	      	
	      	<div>
		      <label for="country"  class="col-sm-2 control-label"><font color="red">*</font>国家：</label>
		      <input type="text"  name="country" id="country" disabled="disabled" class="general_input_class"   value="${fanForm.country }">
	      	</div>
	      	
	      	<div>
		      <label for="provinceName"  class="col-sm-2 control-label"><font color="red">*</font>省份：</label>
		      <input type="text"  name="provinceName" id="provinceName" disabled="disabled" class="general_input_class"  value="${fanForm.provinceName }">
	      	</div>
	      	
	      	<div>
		      <label for="cityName"  class="col-sm-2 control-label"><font color="red">*</font>城市：</label>
		      <input type="text"  name="cityName" id="cityName" disabled="disabled"  class="general_input_class" value="${fanForm.cityName }">
	      	</div>
	      	
	      	<div>
		      <label for="subscribeDate"  class="col-sm-2 control-label"><font color="red">*</font>关注时间：</label>
		      <input type="text"  name="subscribeDate" id="subscribeDate" disabled="disabled" class="general_input_class"  value="${fanForm.subscribeDate }">
	      	</div>
	      	
	      	<div>
		      <label for="unsubscribeDate"  class="col-sm-2 control-label"><font color="red">*</font>退订时间：</label>
		      <input type="text"  name="unsubscribeDate" id="unsubscribeDate" disabled="disabled" class="general_input_class"  value="${fanForm.unsubscribeDate }">
	      	</div>
	      	
	      	<div>
		      <label for="hasBind"  class="col-sm-2 control-label"><font color="red">*</font>有无绑定：</label>
		      <input type="text"  name="hasBind" id="hasBind" disabled="disabled" class="general_input_class"  value="${fanForm.hasBind}">
	      	</div>
	      	
	      	
	      	<%-- <div>
		      <label for="fanUser.bindStatus.dicName"  class="col-sm-2 control-label"><font color="red">*</font>绑定状态：</label>
		      <c:if test="${!empty fanForm.fanUser }">
		      
		      <select id="bindStatus" disabled="disabled">
		        <option value="">请选择</option>
		        	<c:choose>
		        	<c:when test="${! empty bindStatus }">
			        	<c:forEach items="${bindStatus }" var="status" >
			         		<option value="${status.dicValue }"   <c:if test="${fanForm.fanUser.bindStatus.dicValue ==status.dicValue }">selected="selected"</c:if> >${status.dicName }</option>
			         	</c:forEach>
		         	</c:when>
		         	</c:choose>
		         </select>
		      
		      <!-- <input type="text"  name="fanUser.bindStatus.dicName" id="fanUser.bindStatus.dicName" disabled="disabled"   value="${fanForm.fanUser.bindStatus.dicName }"> -->
		      </c:if>
		      <c:if test="${empty fanForm.fanUser }">
		           <select id="bindStatus" disabled="disabled">
		        <option value="">请选择</option>
		        	<c:choose>
		        	<c:when test="${! empty bindStatus }">
			        	<c:forEach items="${bindStatus }" var="status" >
			         		<option value="${status.dicValue }"   <c:if test="${status.dicValue ==01 }">selected="selected"</c:if> >${status.dicName }</option>
			         	</c:forEach>
		         	</c:when>
		         	</c:choose>
		         </select>
		      
		      <!-- <input type="text"  name="fanUser.bindDateTime" id="fanUser.bindDateTime" disabled="disabled"   value="未绑定"> -->
		      </c:if>
	      	</div> --%>
	      	
	      	<%-- <div>
		      <label for="fanUser.bindDateTime"  class="col-sm-2 control-label"><font color="red">*</font>绑定时间：</label>
		      <c:if test="${!empty fanForm.fanUser }">
		      <input type="text"  name="fanUser.bindDateTime" id="fanUser.bindDateTime" class="general_input_class" disabled="disabled"   value="${fanForm.fanUser.bindDateTime }">
		      </c:if>
		      <c:if test="${empty fanForm.fanUser }">
		      <input type="text"  name="fanUser.bindDateTime" id="fanUser.bindDateTime" class="general_input_class" disabled="disabled"   value="">
		      </c:if>
	      	</div> --%>
	      	
	      
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
	    	 window.location.href='${pageContext.request.contextPath}/cus/cfan_list.do?pageNo=${pageNo}';
			});
     
     }); 
    
    
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>