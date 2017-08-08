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
    
    <%--  <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/zTreeStyle.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/demo.css"/>" type="text/css">--%>
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
	  <h2 class="title_class">新增角色</h2>
	  
	  <form:form action="${pageContext.request.contextPath}/cus/srole_add.do"  modelAttribute="SRoleForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" >
	          
	          <div>
		      <label for="roleCode"  ><font color="red">*</font>角色代码：</label>
		      <input type="text" name="roleCode" id="roleCode" class="general_input_class" size="32">
	      	  </div>
	      		
	      	<div>
		      <label for="roleName"  class="col-sm-2 control-label"><font color="red">*</font>角色名：</label>
		      <input type="text"  name="roleName" id="roleName" class="general_input_class" size="64">
	      	</div>
	      	
	      <div>
		 	  <label for="remarks" class="col-sm-2 control-label">备注：</label>
		 	  <textarea rows="5" cols="20" name="remarks" id="remarks" class="textArea_class" size="256"></textarea>
	 	  </div>
	      
	      	<input type="button" class="btn btn-default"  id="returnBtn" value="返回"> <input type="button" id="subBtn"  class="btn btn-default" value="保存"> 
	  </form:form>
       
    </div>
    
    
    
    <div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:260px; height: 300px;"></ul>
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
<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });

    
     $(document).ready(function(){
    	 
    	 //保存按钮
    		$("#subBtn").click(function(){
    			saveRecord();
    			//防止刷新，返回false
    			return false;
    		});
     
    	 //返回按钮
	     $("#returnBtn").click(function(){
	    	 window.location.href='${pageContext.request.contextPath}/cus/srole_list.do?pageNo=1';
			});
     
       //失去焦点检查唯一性
       $("#roleCode").blur(function(){
    	   this.value = $.trim(this.value);    //删除前后空格
    	   if(this.value != ""){
    		   
    		   if($(this).attr("size")!=undefined && $.trim($(this).val())!=''){
     				if( chklength($(this),$(this).attr("size"))==false){
     					
     					text = $(this).parent().children().filter("label").text(); 
     			    	
     			    	$(this).after("<font id="+idValue+"color color='red'>"+text+"超出最大字符数，最大只允许输入"+$("#"+idValue).attr("size")+"字符（一个中文占两个字节）！</font>");
     					
         				return false; 
     				}
   				}
    		   
		    	   $.get(
		          			"${pageContext.request.contextPath}/cus/srole_checkRoleCode.do",
		          			{"roleCode":this.value,"id":""},
		          			function(msg){
		          				$("#roleCodecolor").remove();
		          				if(msg.result==true){
		          					$("#roleCode").after("<font id=roleCodecolor color='green'>"+msg.warnMsg+"</font>");
		      		            }else{
		   							$("#roleCode").after("<font id=roleCodecolor color='red'>"+msg.warnMsg+"</font>");
		      		            }
		   	            	
		          			}
		       		); 
       			}
      		 });
     
     }); 
    
    
    function checkForm() { 
    	pass = true; 
    	
    	$("#SRoleForm").children().filter("div:contains('*')").children().filter("input").each(function(){
    		
    		
    			var idValue = this.id.replace("\.",""); 
    		
    			this.value = $.trim(this.value);    //删除前后空格
    		
    			$("#"+idValue+"color").remove();
    			
		    	if(this.value == '') { 
			    	text = $(this).parent().children().filter("label").text(); 
			    	
			    	$(this).after("<font id="+idValue+"color color='red'>"+text+"是必填项！</font>");
			    	
			    	this.focus(); 
			    	pass = false; 
			    	
		    	} 
    	});
    	
    	
    	 $("#SRoleForm").children().filter("div").children().filter("input:text,textarea").each(function(){
      		
      		var idValue = this.id.replace("\.","\\\."); 
      		var lengthTips = this.id.replace("\.",""); 
      		
      		if($("#"+idValue).attr("size")!=undefined && $.trim($("#"+idValue).val())!=''){
      			
      			$("#"+lengthTips+"color").remove();
      				if( chklength($("#"+idValue),$("#"+idValue).attr("size"))==false){
      					
      					text = $(this).parent().children().filter("label").text(); 
      			    	
      			    	$(this).after("<font id="+lengthTips+"color color='red'>"+text+"超出最大字符数，最大只允许输入"+$("#"+idValue).attr("size")+"字符（一个中文占两个字节）！</font>");
      					
      					this.focus(); 
          				pass = false; 
      				}
      		}
      	});
    	
    	return pass;  
    } 
    
    
	function saveRecord(){
		if(checkForm()==true){
			bootbox.confirm("确认保存？",function(result){
				if(result==true){
						  $("#SRoleForm").ajaxSubmit(
								  function(msg){
									if(msg.result==false){
										bootbox.alert(msg.warnMsg,function(){});
									}else{
										bootbox.alert(msg.warnMsg,function(){
											window.location.href='${pageContext.request.contextPath}/cus/srole_list.do?pageNo=1';
										});
									}
						   }); 
				}
			});
		}
		
	}
    
    
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>