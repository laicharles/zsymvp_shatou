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
    <title>个人信息</title>
    
    
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
    <div class="user"><img class="avatar" width="25" height="25" src="<c:url value="/resources/js/bootstrap/img/avatar.png"/>" alt="${user.loginName }"><span>${user.loginName }</span><i class="icon-user trigger-user-settings"></i></div>
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
	  <h2 class="title_class">个人信息修改</h2>
	  
	  <form:form action="${pageContext.request.contextPath}/cus/customeruser_x_updateInfo.do"  modelAttribute="BCustomerUserForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" value="${userForm.id }">
	      	  <input type="hidden" name="role.roleCode" id="role.roleCode" value="${userForm.role.roleCode }">
	      	  <input type="hidden" name="BCusotmer.id" id="BCusotmer.id" value="${userForm.BCusotmer.id }">
	      	  <textarea rows="5" cols="20" name="remarks" id="remarks"  style="display:none">${userForm.remarks }</textarea>
	          
	          <div>
		      <label for="loginName" ><font color="red">*</font>用户名：</label>
		      <input type="text" name="loginName" id="loginName" class="general_input_class" readonly="readonly" size="64" value="${userForm.loginName }">
	      	  </div>
	      		
	      	<div>
		      <label for="mobile"  class="col-sm-2 control-label"><font color="red">*</font>手机号码：</label>
		      <input type="text"  name="mobile" id="mobile" class="general_input_class" size="11" value="${userForm.mobile }">
	      	</div>
	      	
	      	<div>
		      <label for="userName"  class="col-sm-2 control-label"><font color="red">*</font>真实姓名：</label>
		      <input type="text"  name="userName" id="userName" class="general_input_class" size="64" value="${userForm.userName }">
	      	</div>
	      	
	      	<div>
		      <label for="email"  class="col-sm-2 control-label"><font color="red">*</font>email：</label>
		      <input type="text"  name="email" id="email"  class="general_input_class" size="64" value="${userForm.email }">
	      	</div>
	      	
	      		<div>
                  <label for="oldPassword">旧密码：</label>
                <input id=oldPassword  name=oldPassword  type="password" class="general_input_class" size="64"  placeholder="密码" title="密码"/>
                </div>
	      	
	      		<div>
                  <label for="password">密码：</label>
                <input id=password  name=password  type="password" class="general_input_class" size="64"  placeholder="密码" title="密码"/>
                </div>
                 <div>
                  <label for="repassword">确认密码：</label>
                  <input id=repassword  name=repassword  type="password"  class="general_input_class"  placeholder="确认密码" title="确认密码"/><br>
                </div>  
	      
	      	   <input type="button" id="subBtn"  class="btn btn-default" value="保存" style="margin-left:127px;"> 
	      	   <br><h4><font color="red">温馨提示：需要修改密码时，旧密，密码，确认密必须填；不需要修改密码时，则不填！</font></h4>
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
     
       //失去焦点检查唯一性，用户名
       $("#loginName").blur(function(){
    	   this.value = $.trim(this.value);    //删除前后空格
    	   var idValue = this.id.replace("\.","");   //id名字如果带有.号，jquery会有问题，需要去除
    	   
    	   if(this.value != ""){
    		   
    		   $("#"+idValue+"color").remove();
    		    var loginName = $("#loginName").val();
    		    
    		    
    		    if($(this).attr("size")!=undefined && $.trim($(this).val())!=''){
        				if( chklength($(this),$(this).attr("size"))==false){
        					
        					text = $(this).parent().children().filter("label").text(); 
        			    	
        			    	$(this).after("<font id="+idValue+"color color='red'>"+text+"超出最大字符数，最大只允许输入"+$("#"+idValue).attr("size")+"字符（一个中文占两个字节）！</font>");
        					
            				return false; 
        				}
        		}
    		    
    		    
	   			var loginNameReg = /^[a-zA-Z0-9]+$/;
	   			if(!loginNameReg.test(loginName)){
	   				$("#loginName").after("<font id="+idValue+"color color='red'>用户名只能由数字和字母组成！</font>");
	   				return false;
	   			}
   			
		    	   $.get(
		          			"${pageContext.request.contextPath}/cus/bcustomeruser_checkByLoginName.do",
		          			{"loginName":this.value,"id":$("#id").val()},
		          			function(msg){
		          				if(msg.result==true){
		          					$("#loginName").after("<font id="+idValue+"color color='green'>"+msg.warnMsg+"</font>");
		      		            }else{
		   							$("#loginName").after("<font id="+idValue+"color color='red'>"+msg.warnMsg+"</font>");
		      		            }
		   	            	
		          			}
		       		); 
       			}
      		 });
       
       
     	//失去焦点检查唯一性，手机号码
       $("#mobile").blur(function(){
    	   this.value = $.trim(this.value);    //删除前后空格
    	   var idValue = this.id.replace("\.","");   //id名字如果带有.号，jquery会有问题，需要去除
    	   
    	   if(this.value != ""){
    		   
    		   $("#"+idValue+"color").remove();
    		    var mobile = $("#mobile").val();
    		    
    		    
    		    
    		    if($(this).attr("size")!=undefined && $.trim($(this).val())!=''){
    				if( chklength($(this),$(this).attr("size"))==false){
    					
    					text = $(this).parent().children().filter("label").text(); 
    			    	
    			    	$(this).after("<font id="+idValue+"color color='red'>"+text+"超出最大字符数，最大只允许输入"+$("#"+idValue).attr("size")+"字符（一个中文占两个字节）！</font>");
    					
        				return false; 
    				}
    		}
		    
    		    
	   			var mobileReg = /^(1[3-9]\d{9}$)/;
	   			if(!mobileReg.test(mobile)){
	   				$("#mobile").after("<font id="+idValue+"color color='red'>手机号码格式不正确！</font>");
	   				return false;
	   			}
    		   
		    	   $.get(
		          			"${pageContext.request.contextPath}/cus/bcustomeruser_checkByMobile.do",
		          			{"mobile":this.value,"id":$("#id").val()},
		          			function(msg){
		          				if(msg.result==true){
		          					$("#mobile").after("<font id="+idValue+"color color='green'>"+msg.warnMsg+"</font>");
		      		            }else{
		   							$("#mobile").after("<font id="+idValue+"color color='red'>"+msg.warnMsg+"</font>");
		      		            }
		   	            	
		          			}
		       		); 
       			}
      		 });
       
   		//失去焦点检查唯一性，email
       $("#email").blur(function(){
    	   this.value = $.trim(this.value);    //删除前后空格
    	   var idValue = this.id.replace("\.","");   //id名字如果带有.号，jquery会有问题，需要去除
    	   
    	   if(this.value != ""){
    		   
    		       $("#"+idValue+"color").remove();
	    		   var email = $("#email").val();
	    		   
	    		   if($(this).attr("size")!=undefined && $.trim($(this).val())!=''){
	       				if( chklength($(this),$(this).attr("size"))==false){
	       					
	       					text = $(this).parent().children().filter("label").text(); 
	       			    	
	       			    	$(this).after("<font id="+idValue+"color color='red'>"+text+"超出最大字符数，最大只允许输入"+$("#"+idValue).attr("size")+"字符（一个中文占两个字节）！</font>");
	       					
	           				return false; 
	       				}
       				}
	    		   
	    		   
		   		   var emailReg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		   		   if(!emailReg.test(email)){
		   				$("#email").after("<font id="+idValue+"color color='red'>email格式不正确！</font>");
		   				return false;
		   		   }
    		   
		    	   $.get(
		          			"${pageContext.request.contextPath}/cus/bcustomeruser_checkByEmail.do",
		          			{"email":this.value,"id":$("#id").val()},
		          			function(msg){
		          				if(msg.result==true){
		          					$("#email").after("<font id="+idValue+"color color='green'>"+msg.warnMsg+"</font>");
		      		            }else{
		   							$("#email").after("<font id="+idValue+"color color='red'>"+msg.warnMsg+"</font>");
		      		            }
		   	            	
		          			}
		       		); 
       			}
      		 });
       
   		
     
     }); 
    
    
    function checkForm() { 
    	pass = true; 
    	
    	$("#BCustomerUserForm").children().filter("div:contains('*')").children().filter("input").each(function(){
    		
    		
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
    	
    	
		$("#BCustomerUserForm").children().filter("div").children().filter("input:text,textarea").each(function(){
    		
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
		
		if(checkForm()){
			
			var loginName = $("#loginName").val();
   			var loginNameReg = /^[a-zA-Z0-9]+$/;
   			var loginNameidValue = $("#loginName").id;
			$("#"+loginNameidValue+"color").remove();
   			if(!loginNameReg.test(loginName)){
   				$("#loginName").after("<font id="+loginNameidValue+"color color='red'>用户名只能由数字和字母组成！</font>");
   				return false;
   			}
			
			var mobile = $("#mobile").val();
			var mobileReg = /^(1[3-9]\d{9}$)/;
			var mobileidValue = $("#mobile").id;
			$("#"+mobileidValue+"color").remove();
			if(!mobileReg.test(mobile)){
				$("#mobile").after("<font id="+mobileidValue+"color color='red'>手机号码格式不正确！</font>");
				return false;
			}
			
			var email = $("#email").val();
			var emailReg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
			var emailidValue = $("#email").id;
			$("#"+emailidValue+"color").remove();
			if(!emailReg.test(email)){
				$("#email").after("<font id="+emailidValue+"color color='red'>email格式不正确！</font>");
				return false;
			}
			
			var password = $.trim($("#password").val());
			var repassword = $.trim($("#repassword").val());
			var oldPassword = $.trim($("#oldPassword").val());
			var repidValue = $("#repassword").id;
			$("#"+repidValue+"color").remove();
			if(password!=repassword){
				$("#repassword").after("<font id="+repidValue+"color color='red'>确认密码不一致！</font>");
				return false;
			}
			
			$("#passwordColor").remove();
			$("#oldPasswordColor").remove();
			if((oldPassword!="" &&password=="")
				||(oldPassword=="" &&password!="")	){
				if(oldPassword==""){
					$("#oldPassword").after("<font id=oldPasswordColor color='red'>如需修改密码，则必须填写旧密码，密码，确认密码，否则为空！</font>");
					return false;
				}
				if(password==""){
					$("#password").after("<font id=passwordColor color='red'>如需修改密码，则必须填写旧密码，密码，确认密码，否则为空！</font>");
					return false;
				}
				
			}
			
			
			bootbox.confirm("确认保存？",function(result){
				if(result==true){
						  $("#BCustomerUserForm").ajaxSubmit(
								  function(msg){
									if(msg.result==false){
										bootbox.alert(msg.warnMsg,function(){});
									}else{
										bootbox.alert(msg.warnMsg);
										$("#oldPassword").val("");
										$("#password").val("");
										$("#repassword").val("");
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