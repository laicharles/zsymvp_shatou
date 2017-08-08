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
    <meta charset="utf-8" />
    <title>jqGrid Loading Data - Million Rows from a REST service</title>
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
    </style>
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/bootstrap/plugins/html5shiv.js"/>"></script>
    <![endif]-->
</head>
    
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
        <!--主体内容-->
        <h2 class="title_class">微信公众号菜单管理</h2>
        <form:form action="${pageContext.request.contextPath}/cus/menuConfigure_update.do?_csrf=${_csrf.token}"
						modelAttribute="CMenuConfigureFormList" method="POST">
        <table class="table" id="Menutable">
   
   <thead>
      <tr>
         <th>一级菜单</th>
         <th>二级菜单</th>
         <th>开启/关闭</th>
      </tr>
   </thead>
   <tbody>
    <c:forEach items="${cMenuConfigureForm}" var="p" varStatus="status">
    <tr>
    	<td><div id="parentName${status.index}" > ${p.parentName}</div>
    	<input name="cMenuConfigureFormList[${status.index}].id" value="${p.id}" type="hidden"/>
    	</td>
    	<td><input name="cMenuConfigureFormList[${status.index}].name" value="${p.name}"  style="width:70px"/></td>
    	<td>
    	<select style="width:80px" name="cMenuConfigureFormList[${status.index}].isDisabled"  id="isDisabled${status.index}">    
           	<c:if test="${p.isDisabled=='0'}">                 
			<option value="0"  selected="selected">已开启</option> 
			<option value="1">关闭</option>
			</c:if>
			<c:if test="${p.isDisabled=='1'}" >                 
			<option value="0" >开启</option> 
			<option value="1" selected="selected" >已关闭</option>
  			</c:if> 
 			</select>
    	</td>
    </tr>
    
    </c:forEach>
    <tr>
    <td></td><td><input type="button" id="subBtn" class="btn-sub" value="保存并生成菜单"/></td><td></td>
    </tr>
   </tbody>
	</table>
        </form:form>
        <div style="text-align:center; color:red;"><h4>温馨提示：微信公众号每次生成菜单将在24小时内生效</h4></div>
  </div>

	<!-- 页脚开始 -->
    <footer id="footer">
        <jsp:include page="../../../base/cus/foot.jsp"/>
    </footer>
    <!-- 页脚结束 -->
    </div>
</div>

<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/jquery.form/3.51/jquery.form.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/twitter-bootstrap/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.slimscroll/jquery.slimscroll.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/js/extents.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/js/sidebar.js"/>"></script>

<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>
<!-- 本页调用JS 开始 -->
<script>
$(function() {
    SideBar.init({
        shortenOnClickOutside: false
    });
});

		$(document).ready(function(){
			
			$("#subBtn").click(function() {
				
				var tsNum = $("tbody tr").length-1;
				var snum = 0;
				var isoutsize = false;
				for(var i=0;i<tsNum;i++)
				{
					
				  if(i==0 && $("#isDisabled"+i).val()==0)
					 {
					  snum++;
					 }
				  
					if(i>0)
					{
						if($("#parentName"+(i-1)).text()==$("#parentName"+i).text())
						  {
							if($("#isDisabled"+i).val()==0)
							 {
							  snum++;
							  if(snum>5)
								  {
								  alert("一级菜单\""+$("#parentName"+i).text()+" \"下不能开启多于5个子菜单");
								  isoutsize = true;
								  break;
								  }
							 }
						  }
						else
						{
							snum = 0;
							if($("#isDisabled"+i).val()==0)
							 {
							  snum++;
							 }
						}
					}
				}
				if(isoutsize)
				{
					return;
				}
				
				
				$("#CMenuConfigureFormList").ajaxSubmit(function(msg) {
					if (msg.result == false) {
						bootbox.alert(msg.warnMsg,function(){});
						//window.location.href='${pageContext.request.contextPath}/cus/menuConfigure_list.do';
					} else if (msg.result == true) {
						bootbox.alert(msg.warnMsg,function(){});
					}
				});
			});
			
			//隐藏重复
			var tNum = $("tbody tr").length-1;
			for(var i=1;i<tNum;i++)
			{
			  
			  if($("#parentName"+(i-1)).text()==$("#parentName"+i).text())
			  {
			    $("#parentName"+i).hide();
			  }
			    
			}
			
			
	});
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>