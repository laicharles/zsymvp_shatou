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
    
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/zTreeStyle.css"/>" type="text/css">
    <!-- 本页调用样式 开始 -->
    
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/zTreeStyle.css"/>" type="text/css">
    
    
    <meta charset="utf-8" />
    <title>编辑温馨提示</title>
    
    
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
	  <h2 class="title_class">编辑温馨提示</h2>
	  <form:form action="${pageContext.request.contextPath}/cus/reminderTemp_update.do"  modelAttribute="CReminderTempForm"  method="POST" >
	      
	      	<input type="hidden" name="id" id="id" value="${reminderTempForm.id}">
	      	  	      	  
	      	<div>
		      <label for="first" class="col-sm-2 control-label"><font color="red">*</font>抬头：</label>
		      <input type="text" name="first" id="first" class="general_input_class" size="128" value="${reminderTempForm.first}">
	      	</div>
	      	  
	      	<div>
		      <label for="reminderType" class="col-sm-2 control-label"><font color="red">*</font>服务单位：</label>
		      <input type="text" name="serviceUnits" id="serviceUnits" class="general_input_class" size="1024" value="${reminderTempForm.serviceUnits}">
	      	</div>
	      	  
	      	<div>
		      <label for="reminderContent" class="col-sm-2 control-label"><font color="red">*</font>联系电话：</label>
		    
		      <input type="text" name="tel" id="tel" class="general_input_class" size="512" value="${reminderTempForm.tel}">
	      	</div>
	      	  
	      	<div>
		      <label for="remark" class="col-sm-2 control-label"><font color="red">*</font>其他说明：</label>
		      <textarea rows="5" cols="20" name="tempRemark" id="tempRemark" size="1024">${reminderTempForm.tempRemark}</textarea>
	      	</div>
	      	  
	     	<input type="button" class="btn btn-default"  id="returnBtn" value="返回">  
	     	<input type="button" id="subBtn"  class="btn btn-default" value="保存"> 
	     	 
	     	
	     	<br>
	     	<br>
	      	<div>
		      <label for="sample" class="col-sm-2 control-label">内容示例：</label>
		      <textarea rows="10" cols="20" name="sample" id="sample" readonly="readonly">
温馨提示
服务单位：XXXXXX单位
联系电话：12345678901
因为唐家湾镇水管检修，停水期间给市民带来不便敬请谅解！</textarea>
	      	  </div>
	      	
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
<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });

    $(document).ready(function(){
		$("#subBtn").click(function(){
			updateRecord()
		});
		
		 $("#returnBtn").click(function(){
			 window.location.href='${pageContext.request.contextPath}/cus/reminderTemp_list.do?pageNo=${page }';
			});
	  }); 
    
    
    function checkForm() { 
    	pass = true; 
    	
    	$("#CReminderTempForm").children().filter("div:contains('*')").children().filter("input:text,textarea").each(function(){
    			
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
		
		$("#CReminderTempForm").children().filter("div").children().filter("input:text,textarea").each(function(){
      		
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
    
    function updateRecord(){
		if(checkForm()==true){
	    	bootbox.confirm("确认保存？",function(result){
	    		if(result==true){
				  $("#CReminderTempForm").ajaxSubmit(function(msg){
							if(msg.result==false){
								bootbox.alert(msg.warnMsg,function(){});
							}else{
								
								bootbox.alert(msg.warnMsg,function(){
									window.location.href='${pageContext.request.contextPath}/cus/reminderTemp_list.do?pageNo=${page }';
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