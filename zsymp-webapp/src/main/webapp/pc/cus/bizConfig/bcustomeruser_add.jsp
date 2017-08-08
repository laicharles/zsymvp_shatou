<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
    
    <meta charset="utf-8" />
    <title>资源新增</title>
    
    
    <!-- 本页调用样式 结束 -->
    <style>
       .wraper #main{ margin-top: 40px; }
       .selectList{
        	border: 1px solid #CCC;
        	width:420px;
        	position: absolute;
        	left:800px;
        	top: 35%;
        	display: none;
        }
        .selIco{padding: 3px;}
        .selectSpan{
        	padding: 10px;
        }
        .editForm{
        	clear:both;
        }
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
	  <h2 class="title_class">新增用户</h2>
	  
	  <form:form calss="editForm" action="${pageContext.request.contextPath}/cus/bcustomeruser_add.do"  modelAttribute="BCustomerUserForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" >
	      	  <input type="hidden" name="BCusotmer.id" id="BCusotmer.id" value="${user.BCusotmer.id }">
	        <div>
		      <label for="loginName"><font color="red">*</font>角色：</label>
		      <select name="role.roleCode" class="general_input_class general_select_class">
		        <c:forEach items="${listForm }" var="from">
		        	<option value="${from.roleCode }">${from.roleName }</option>
		        </c:forEach>
	          </select>
	      	</div>  
            <div>
		      <label for="loginName"><font color="red">*</font>用户名：</label>
		      <input type="text" name="loginName" class="general_input_class" id="loginName" size="64">
	      	</div>
	      		
	      	<div>
		      <label for="mobile"  class="col-sm-2 control-label"><font color="red">*</font>手机号码：</label>
		      <input type="text"  name="mobile" class="general_input_class" id="mobile"  size="11">
	      	</div>
	      	
	      	<div>
		      <label for="userName"  class="col-sm-2 control-label"><font color="red">*</font>真实姓名：</label>
		      <input type="text"  name="userName" class="general_input_class" id="userName"  size="64">
	      	</div>
	      	
	      	<div>
		      <label for="email"  class="col-sm-2 control-label"><font color="red">*</font>email：</label>
		      <input type="text"  name="email" class="general_input_class" id="email"  size="64">
	      	</div>
	      	
	        <div>
	      	    <label for="nickName"><font color="red">*</font>自己微信昵称：</label>
                <input type="text" class="general_input_class" id="nickName" size="64">
	      	</div>
	      	
	      	<div>
	      	     <input type="hidden" name="binOpendId" class="general_input_class" id="binOpendId"/>
	      	</div>
	      	
	      	<div>
	      	    <label for="bossLoginName"><font color="red">*</font>上级登录名：</label>
                <input type="text" class="general_input_class" id="bossLoginName" size="64" placeholder="权限为最高级时可以空">
	      	</div>
	      	<div>
	      	   <input type="hidden" name="higherCustomerUserId" id="higherCustomerUserId"/>
	      	</div>
	      	
	      	<div>
	      	<label for="permission"><font color="red">*</font>权限：</label>
	      	<select name="currentPermissions" class="general_input_class general_select_class">
		        <c:forEach items="${permissionForm }" var="from">
		        	<option value="${from.id }">${from.reviewName }</option>
		        </c:forEach>
	          </select>
	        </div>
	      	
	      <div>
		 	  <label for="remarks" class="col-sm-2 control-label">备注：</label>
		 	  <textarea rows="5" cols="20" name="remarks" id="remarks" class="textArea_class" size="256"></textarea>
	 	  </div>
	      
	      	<input type="button" class="btn btn-default"  id="returnBtn" value="返回"> <input type="button" id="subBtn"  class="btn btn-default" value="保存"> 
	  </form:form>
    </div> 
       <div class="selectList">
		 <!-- <div class="selIco">
		    <input type="radio" value="Recovery" name="nikeName" onclick="userOpenId(this);"/>
		    <img alt="" src="http://wx.qlogo.cn/mmopen/dnE9GK4ykb6vibcTt8dC342ricuIWVEWur7MzAWWSX6JibBRgicfjSKFKOdn0V8WlrUSXDYibXOYicCWxbV0wtVwuHnembHoFn8YkQ/46">
		    <span class="selectSpan">Recovery</span>
		    <span class="selectSpan">R5GjjgEhCMJFyzaVZdrxZ2zRRF4</span>
		 </div> -->
	  </div>
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

<script src="<c:url value="/resources/js/valField.js"/>" ></script>
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });
    
    function userOpenId(obj){
		 console.log($(obj).next().next().next().html());
		 var openId = $(obj).next().next().next().html();
		 $('#binOpendId').val(openId);
	 }
    
     $(document).ready(function(){
    	 
    	 //保存按钮
    	 $("#subBtn").click(function(){
    		saveRecord();
    		//防止刷新，返回false
    		return false;
    	});
     
    	 //返回按钮
    	 $("#returnBtn").click(function(){
	    	 window.location.href='${pageContext.request.contextPath}/cus/bcustomeruser_list.do?pageNo=1';
		 });
    	 
    	 //失去焦点检查唯一性，昵称
	     $('#nickName').blur(function(){
	    	 var nickName = this.value;
	    	 if(this.value != ""){
	    	   $("#nickName").nextAll().remove();
	    	   $.get(
	          			"${pageContext.request.contextPath}/cus/findNickname.do",
	          			{"nickname":this.value},
	          			function(msg){
	          				console.log(msg.length);
	          				if(msg.length > 0){
	          					if(msg.length==1){
	          						$('.selectList').css({"display":"none"});
	          						$('#nickName').after("<font id="+nickName+" color='green'>昵称可用！</font>");
	 	   	            	        $('#binOpendId').val(msg.openID);
	          					}else{
	          						$('.selectList').css({"display":"block"});
	          						$('.selectList').html("");
	          						$(msg).each(function(i,o){
	          							var html = "<div class='selIco'>"
          							 		+"<input type='radio' name='nikeName' onclick='userOpenId(this);'/>"
			          					    +"<img alt='' src='"+o.headimgurl+"'>"
			          					    +"<span class='selectSpan'>"+o.nickName+"</span>"
			          					    +"<span class='selectSpan'>"+o.openID+"</span>"
			          					    +"</div>";
			          					
	          							$('.selectList').append(html);
	          						});
	          					}
	          				}else{
	          					$('.selectList').css({"display":"none"});
	          					$('#nickName').after("<font id="+nickName+" color='red'>昵称不存在！</font>");
	   	            	    	$('#binOpendId').val("");
	          				}
	          			}
	       		); 
	    	 }
	   	 });
	   //失去焦点检查唯一性，上级登录名
	     $('#bossLoginName').blur(function(){
	    	 var bossLoginName = this.value;
	    	 if(this.value != ""){
 	    	   $("#bossLoginName").next().remove(); 
	    	   $.get(
	          			"${pageContext.request.contextPath}/cus/findBossLoginName.do",
	          			{"bossName":this.value},
	          			function(msg){
	          				if(msg.loginName == bossLoginName){
	   	            	        $('#bossLoginName').after("<font id="+bossLoginName+"a color='green'>上级登录名可用！</font>");
	   	            	        $('#higherCustomerUserId').val(msg.id);
	   	            	    }else{
	   	            	    	$('#bossLoginName').after("<font id="+bossLoginName+"a color='red'>上级登录名不可用！</font>");
	   	            	    	$('#higherCustomerUserId').val("");
	   	            	    }
	          			}
	       		); 
	    	 }
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
		          			{"loginName":this.value,"id":""},
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
		          			{"mobile":this.value,"id":""},
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
		          			{"email":this.value,"id":""},
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
     	console.log($("#BCustomerUserForm").children().filter("div:contains('*')").children().filter("input"));
     	$("#BCustomerUserForm").children().filter("div:contains('*')").children().filter("input").each(function(){
     		
     		
     			var idValue = this.id.replace("\.",""); 
     		
     			this.value = $.trim(this.value);    //删除前后空格
     		
     			$("#"+idValue+"color").remove();
     			var currentPerminssions = $('select[name="currentPermissions"]').val();
    			var count = 0;
    			if(currentPerminssions=='de914f62-7755-4fe7-852f-0cb9d4fcb67e'){
    				if(this.value == '') { 
    			    	text = $(this).parent().children().filter("label").text(); 
    			    	console.log(text);
    			    	if(text!='*上级登录名：'){
    			    		$(this).after("<font id="+idValue+"color color='red'>"+text+"是必填项！</font>");
        			    	this.focus(); 
        			    	pass = false;
        			    	count++;
    			    	}
    		    	} 
    				console.log(count);
    				if(count==0){
    					pass=true;
    				}
    			}else{
    				if(this.value == '') { 
    			    	text = $(this).parent().children().filter("label").text(); 
    			    	
    			    	$(this).after("<font id="+idValue+"color color='red'>"+text+"是必填项！</font>");
    			    	this.focus(); 
    			    	pass = false;

    		    	} 
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
			
			//校验上级登陆名
			var nickName = $('#nickName').val();
			$("#nickName").nextAll().remove();
			if($('#binOpendId').val() == null  || $('#binOpendId').val() == ""){
				$('#nickName').after("<font id="+nickName+"color color='red'>请选择昵称或者昵称不存在</font>");
				return false;
			}

			
			//校验上级登陆名
			var currentPerminssions = $('select[name="currentPermissions"]').val();
			if(currentPerminssions!='de914f62-7755-4fe7-852f-0cb9d4fcb67e'){
				var bossLoginName = $('#bossLoginName').val();
				var blage = true;
				$.ajax({
					url:"${pageContext.request.contextPath}/cus/findBossLoginName.do",
					data:{"bossName":bossLoginName},
					type:"GET",
					dataType:"JSON",
					async:false, 
					success:function(msg){
          				if(msg.loginName != bossLoginName){
          					blage = false;
   	            	    }
          			}
				})
				if (!blage) {
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
										bootbox.alert(msg.warnMsg,function(){
											window.location.href='${pageContext.request.contextPath}/cus/bcustomeruser_list.do?pageNo=1';
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