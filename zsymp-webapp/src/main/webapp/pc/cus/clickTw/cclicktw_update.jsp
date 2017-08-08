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
    <title>信息登记图文配置</title>
    
    
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
	  <h2 class="title_class">${dicName}图文配置</h2>
	  
	  <form:form action="${pageContext.request.contextPath}/cus/clickTw_update.do?_csrf=${_csrf.token}"  modelAttribute="clickTwForm"  
	  	method="POST" enctype="multipart/form-data">
	      
	      	  <input type="hidden" name="id" id="id" value="${clickTwForm.id }">
	      	  <input type="hidden" name="token" id="token" value="${clickTwForm.token }">
	      	  <input type="hidden"  name="picName"  id="picName" value="${clickTwForm.picName }">
	          <input type="hidden"  name="twType.logicID" value="${twType}">
	          <div>
		      <label for="title" ><font color="red">*</font>标题：</label>
		      <input type="text" name="title" id="title" class="general_input_class"  value="${clickTwForm.title }" size="64">
	      	  </div>
	      		
	      	<div>
		      <label for="description"  class="col-sm-2 control-label"><font color="red">*</font>简介：</label>
		      <textarea rows="5" cols="20"  name="description" class="textArea_class"  id="description"  size="256">${clickTwForm.description}</textarea>
	      	</div>
	      	<div>
		      <label for="imgFile" class="col-sm-2 control-label"><font color="red">*</font>图片：</label>
				<div id="imgdiv">
					<img id="imgShow" src="${pageContext.request.contextPath}${picPath}" style="width:264px;height: 150px"/>
				</div>
	      	</div>
	      	<input type="file" id="imgFile" name="imgFile" style="display: none"/><input type="button" id="fileBtn" value="选择" class="btn btn-default btn-check" />
	      <div>
		 	  <label for="remarks" class="col-sm-2 control-label">备注：</label>
		 	  <textarea rows="5" cols="20" name="remarks" class="textArea_class"  id="remarks"  size="256">${clickTwForm.remarks }</textarea>
	 	  </div>
	      
	      	<input type="button" id="subBtn"  class="btn btn-default" value="保存" style="margin-left:127px;"> 
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
<script src="<c:url value="/resources/js/uploadPreview.min.js"/>"></script>

<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false       /*单击选择事件的时候左边的列表不会收缩*/
        });
    });

    
     $(document).ready(function(){
    	 
    	 //保存按钮
    		$("#subBtn").click(function(){
    			saveRecord();
    			//防止刷新，返回false
    			return false;
    		});
     		
    	    $("#fileBtn").click(function(){
    	    	$("#imgFile").click();
    	    });
    	 
    		new uploadPreview({
    			UpBtn : "imgFile",
    			DivShow : "imgdiv",
    			ImgShow : "imgShow"
    		});
     
     }); 
    
    
    function checkForm() { 
    	pass = true; 
    	
    	$("#clickTwForm").children().filter("div:contains('*')").children().filter("input").each(function(){
    		
    		
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
    	
    	
	$("#clickTwForm").children().filter("div").children().filter("input:text,textarea").each(function(){
    		
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
			
			var description = $.trim($("#description").val());
			$("#descriptioncolor").remove();
			if(description==""){
				$("#descriptioncolor").after("<font id=descriptioncolor color='red'>请填写简介！</font>");
				return false;
			}
			
			var filePath = $("#imgFile").val();
			$("#imgFilecolor").remove();
			if(filePath!=""){
				var arr = filePath.split("\\");
				var fileName = 	arr[arr.length-1];
				$("#picName").val(fileName);
			}else{
				if($("#picName").val()==""){
					$("#imgFile").after("<font id=imgFilecolor color='red'>请选择上传的图片！</font>");
					return false;
				}
			}
			
			bootbox.confirm("确认保存？",function(result){
				if(result==true){
					if($("#imgFile").val()==""){
						$("#imgFile").attr("disabled",true);
					}
					  $("#clickTwForm").ajaxSubmit(
							  function(msg){
								if(msg.result==false){
									bootbox.alert(msg.warnMsg);
								}else{
									bootbox.alert(msg.warnMsg,function(){
										$("#id").val(msg.tips);
									});
								}
								$("#imgFile").attr("disabled",false);
					   }); 
				}
			});
		}
	}
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>