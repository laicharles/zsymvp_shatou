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
    
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/zTreeStyle.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/resources/js/bootstrap/plugins/zTree_v3/css/demo.css"/>" type="text/css">
    
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

    <!-- 主体 -->
    <div id="main">
    <div class="container-fluid">
        <!--主体内容-->
       <!-- <div style="height: 20px"></div>-->
  		<div >
	  <p class="validateTips">   </p> 
	  <h2 class="title_class">个人信息修改</h2>
	  
	  <form:form action="${pageContext.request.contextPath}/cus/bcustomeruser_resetPassword.do"  modelAttribute="BCustomerUserForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" value="${userForm.id }">
	          
	          <div>
		      <label for="loginName" >用户名：</label>
		      <input type="text" name="loginName" id="loginName" readonly="readonly"  class="general_input_class" value="${userForm.loginName }">
	      	  </div>
	      		
	      	<div>
		      <label for="userName"  class="col-sm-2 control-label">真实姓名：</label>
		      <input type="text"  name="userName" id="userName" readonly="readonly"  class="general_input_class" value="${userForm.userName }">
	      	</div>
	      	
	      	<div>
		      <label for="email"  class="col-sm-2 control-label">email：</label>
		      <input type="text"  name="email" id="email" readonly="readonly"   class="general_input_class" value="${userForm.email }">
	      	</div>
	      	
	      		<div>
                  <label for="password"><font color="red">*</font>密码：</label>
                <input id=password  name=password  type="password"  class="general_input_class" size=64 placeholder="密码" title="密码"/>
                </div>
                 <div>
                  <label for="repassword"><font color="red">*</font>确认密码：</label>
                  <input id=repassword  name=repassword  type="password"  class="general_input_class" placeholder="确认密码" title="确认密码"/><br>
                </div>  
	      
	      	   <input type="button" id="subBtn"  class="btn btn-default" value="保存"> 
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
    	 
    	 //保存按钮
    		$("#subBtn").click(function(){
    			saveRecord();
    			//防止刷新，返回false
    			return false;
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
    	return pass;  
    } 
    
    
	function saveRecord(){
		
		if(checkForm()){
			
			var password = $.trim($("#password").val());
			var repassword = $.trim($("#repassword").val());
			var repidValue = $("#repassword").id;
			$("#"+repidValue+"color").remove();
			
			 if(chklength($("#password"),64) == false){
				 $("#password").after("<font id="+repidValue+"color color='red'>密码超出最大字符数，最大只允许输入64个字符（一个中文占两个字节）！</font>");
					return false;
			 }
			if(password!=repassword){
				$("#repassword").after("<font id="+repidValue+"color color='red'>确认密码不一致！</font>");
				return false;
			}
			
			bootbox.confirm("确认修改吗？",function(result){
				if(result==true){
						  $("#BCustomerUserForm").ajaxSubmit(
								  function(msg){
									if(msg.result==false){
										bootbox.alert(msg.warnMsg,function(){});
									}else{
										bootbox.alert(msg.warnMsg);
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