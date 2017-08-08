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
    
    <meta charset="utf-8" />
    <title>公众号信息维护</title>
    <!-- XXX tmh the usage of the above two lines -->
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
        #msg {
  margin-left: 10px;
  color: green;
  border: 1px solid #3c3;
  background: url(<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/checkmark.png"/>) no-repeat 2px 3px;
  padding: 3px 6px 3px 20px;
}
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
        <!--主体内容-->
        <div>
        <p class="validateTips">   </p><!-- XXX tmh the usage of this line -->
        <h2 class="title_class">公众号信息维护</h2>
		<form:form action="${pageContext.request.contextPath}/cus/cmp_add.do" modelAttribute="CMpForm" method="post">
				<input type="hidden" id="id" name="id" value="${cmpForm.id}">
				<div>
					<label for="name"  class="col-sm-2 control-label"><font color="red">*</font>公众号名称：</label>
					<input type="text" id="name" name="name" value="${cmpForm.name}" class="general_input_class" size="32">
				</div>	
				<div>
					<label for="origID"  class="col-sm-2 control-label"><font color="red">*</font>公众号原始ID：</label>
					<input type="text" id="origID" name="origID" value="${cmpForm.origID}" class="general_input_class" size="32">
				</div>	
				<div>
					<label for="wcAccount"  class="col-sm-2 control-label"><font color="red">*</font>微信号：</label>
					<input type="text" id="wcAccount" name="wcAccount" value="${cmpForm.wcAccount}" class="general_input_class" size="32">
				</div>	
				<div>
					<label for="appID"  class="col-sm-2 control-label"><font color="red">*</font>AppID(公众号)：</label>
					<input type="text" id="appID" name="appID" value="${cmpForm.appID}" class="general_input_class" size="64">
				</div>	
				<div>
					<label for="appSecret"  class="col-sm-2 control-label"><font color="red">*</font>AppSecret：</label>
					<input type="text" id="appSecret" name="appSecret" value="${cmpForm.appSecret}" class="general_input_class" size="64">
				</div>	
				<div>
					<label for="url"  class="col-sm-2 control-label">Url：</label>
					<input type="text" readonly="readonly" style="width: 44%" id="url" name="url" value="${webSitePath }/weixinServlet?token=${cmpForm.accessToken.token}">
		  			<input type="button" id="copyUrlBtn" value="复制" style="margin-top:-14px">
				</div>
				
				<div>
					<label for="accessToken.token"  class="col-sm-2 control-label">Token：</label>
					<input type="text" readonly="readonly" style="width: 44%" id="accessToken.token" name="accessToken.token" value="${cmpForm.accessToken.token}">
		  			<input type="button" id="copyTokenBtn" value="复制" style="margin-top:-14px">
				</div>
						
				<input type="submit" id="subBtn" class="btn btn-default" value="保存"  style="margin-left:127px;"/>
			
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
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/jquery.zclip.min.js"/>"></script>
<script src="<c:url value="/resources/js/valField.js"/>" ></script>

<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
        
    });
    
	$(function(){
	    	
	    	$('#copyUrlBtn').zclip({ 
	            path: '<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/ZeroClipboard.swf"/>', 
	            copy: function(){//复制内容 
	                return $('#url').val(); 
	            }, 
	            afterCopy: function(){//复制成功 
	            	$("#msg").remove();
	                $("<span id='msg'/>").insertAfter($('#copyUrlBtn')).text('复制成功').fadeOut(4000); 
	            } 
	        }); 
	    	
	    	$('#copyTokenBtn').zclip({ 
	            path: '<c:url value="/resources/js/bootstrap/plugins/jquery.zeroClipboard/1.1/ZeroClipboard.swf"/>', 
	            copy: function(){//复制内容 
	                return $('#accessToken\\.token').val(); 
	            }, 
	            afterCopy: function(){//复制成功 
	            	$("#msg").remove();
	                $("<span id='msg'/>").insertAfter($('#copyTokenBtn')).text('复制成功').fadeOut(4000); 
	            } 
	        }); 
	    	
	});
    
    $(document).ready(function(){
		$("#subBtn").click(function(){
			chkAppAndSave();
			//防止刷新，返回false
			return false;
		});
		
	}		
   ); 
   
   function chkAppAndSave(){
	   
	   if(checkForm()==true){
	   
	   $.get(
		          			"${pageContext.request.contextPath}/cus/cmp_chkApp.do",
		          			{"appID":$("#appID").val(),"appSecret":$("#appSecret").val()},
		          			function(msg){	          				
		          				if(msg.result==true){
		          					saveRecord();
		      		            }else{
		   							bootbox.alert(msg.warnMsg,function(){});
									return false;
		      		            }
		   	            	
		          			}
		       		); 
	   
	   }
					
   
   }
   
   
  
    function saveRecord(){

	
		bootbox.confirm("确认保存？",function(result){
			if(result==true){
 				   
					  $("#CMpForm").ajaxSubmit(
							  function(msg){
								if(msg.result==false){
									bootbox.alert(msg.warnMsg,function(){});
								}else{
									bootbox.alert(msg.warnMsg,function(){
										window.location.href='${pageContext.request.contextPath}/cus/cmp_toAdd.do';
									});
								}
					   }); 
 				   
			}
		});
		
	}
        
    function checkForm() { 
    	pass = true; 
    	
    	$("#CMpForm").children().filter("div:contains('*')").children().filter("input:text,textarea").each(function(){
    		
    		
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
		
		$("#CMpForm").children().filter("div").children().filter("input:text,textarea").each(function(){
      		
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
		
		var email = $("#email").val();
		var emailReg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		if((email!=null && email!="")&&!emailReg.test(email)){
			var idValue = $("#email").id;
			$("#"+idValue+"color").remove();
			$("#email").after("<font id="+idValue+"color color='red'><br>email格式不正确！</font>");
			pass = false;
		}
		
    	return pass;  
    }
    
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>