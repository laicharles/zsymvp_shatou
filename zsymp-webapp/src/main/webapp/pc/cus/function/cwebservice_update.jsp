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
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/css/ui.jqgrid.css"/>" />
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
	  <h2 class="title_class">接口配置</h2>
	  
	  <form:form action="${pageContext.request.contextPath}/cus/cwebservice_update.do"  modelAttribute="CWebServiceListForm"  method="POST" >
	      
	      	 <!-- <table id="datagrid"></table> -->

			
			<table class="table table-hover">
			   <thead>
			      <tr>
			         <th style="display: none">id</th>
			         <th style="width: 20%">接口名</th>
			         <th style="width: 70%">接口链接</th>
			         <th style="width: 10%">测试状态</th>
			      </tr>
			   </thead>
			   <tbody>
			    <c:choose>
        			<c:when test="${!empty serviceForms}">
        			<c:forEach items="${serviceForms }" var="service" varStatus="status">
					      <tr>
					         <td style="display: none">
						         <input type="hidden"  value="${service.id }"  name="serviceForms[${status.index }].id" id="serviceForms[${status.index }].id" >
						         <input type="hidden"  value="${service.token }"  name="serviceForms[${status.index }].token" id="serviceForms[${status.index }].token" >
					         	 <input type="hidden"   value="${service.webCode }"  name="serviceForms[${status.index }].webCode" id="serviceForms[${status.index }].webCode">
					         </td>
					         <td><input type="text" class="form-control" style="width:95%" value="${service.wsName }"  name="serviceForms[${status.index }].wsName" id="serviceForms[${status.index }].wsName"  readonly="readonly">
					         </td>
					         <td><input type="text" class="form-control" style="width:80%" value="${service.wsUrl }" name="serviceForms[${status.index }].wsUrl" id="serviceForms[${status.index }].wsUrl"></td>
					         <td>
					         <c:if test="${service.testOk==1 }" >
					         	<input type="checkbox"  value="${service.testOk }" checked="checked"  name="serviceForms[${status.index }].testOk" onclick="return false;" id="serviceForms[${status.index }].testOk" readonly="readonly">
					         </c:if>
				          	 <c:if test="${service.testOk==0 }" >
					         	<input type="checkbox"  value="${service.testOk }"  name="serviceForms[${status.index }].testOk" onclick="return false;" id="serviceForms[${status.index }].testOk" readonly="readonly">
					         </c:if>
					         
					         </td>
					      </tr>
					 </c:forEach>
			      </c:when>
			      </c:choose>
			      
			   </tbody>
			</table>
	      
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
<script src="<c:url value="/resources/js/valField.js"/>" ></script>

<!-- 本页调用JS 开始 -->
<script>

var listSize = '${fn:length(serviceForms)}';

    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });

    
     $(document).ready(function(){
    	 
    	 //保存按钮
    		$("#subBtn").click(function(){
    			updateRecord();
    			//防止刷新，返回false
    			return false;
    		});
    	 
    	 
    		for(var i=0;i<listSize;i++){
		    	var wsUrl = $("#serviceForms\\["+i+"\\]\\.wsUrl").val();
		    	$("#serviceForms\\["+i+"\\]\\.wsUrl").blur(function(){
		    		
		    		var thisId = this.id;
		    		var prefix = thisId.split(".")[0];
		    		
		    		thisId = thisId.replace("[","\\[");
		    		thisId = thisId.replace("]","\\]");
		    		thisId = thisId.replace(".","\\.");
		    		
		    		var oldPrefix = prefix;
		    		
		    		prefix = prefix.replace("[","\\[");
		    		prefix = prefix.replace("]","\\]")
		    		var webCode = $("#"+prefix+"\\.webCode").val();
		    		var val = $.trim(this.value);
		    		
		    		if(chklength($("#"+thisId),256)==false){
		       		 $("#"+thisId).after("<font id="+prefix+"color color='red'>接口链接最大只能允许256字符！</font>");
		      			 $("#"+thisId).css("background-color","#FFFFCC");
		      			 return false;
		       	 	}
		    		
		    		
		    		
		    		if(val != ""){
		    		
		    			$("#"+prefix+"color").remove();
		    			
		    			 if(val.indexOf("token")==-1){
		    		    		$("#"+thisId).after("<font id="+oldPrefix+"color color='red'>链接地址必须包括token参数！</font>");
		    		  			$("#"+thisId).css("background-color","#FFFFCC");
		    		  			return false;
		    		  		}
		    		    	 
		    		    	 
		    		    	var webCodeReg = /^(http|https):\/\/([\w-]+\.)+[\w-]+(:\d+)?(\/[\w- .\/?%&=]*)?$/;
		    		 		if(!webCodeReg.test(val)){
		    		 			$("#"+thisId).after("<font id="+oldPrefix+"color color='red'>链接地址格式不符合！</font>");
		    		 			$("#"+thisId).css("background-color","#FFFFCC");
		    		 			return false;
		    		 		}
		    			
		    			
		    			
			    		 $.get(
				          			"${pageContext.request.contextPath}/cus/cwebservice_checkUserURL.do",
				          			{"bindCode":webCode,"wsUrl":this.value},
				          			function(msg){
				          				if(msg.result==true){
				          					
				          					$("#"+prefix+"\\.testOk").attr("checked","checked"); 
				      		            	$("#"+prefix+"\\.testOk").val("1"); 
				      		            	
				          					$("#"+thisId).after("<font id="+oldPrefix+"color color='green'>"+msg.warnMsg+"</font>");
				      		            }else{
				      		            	$("#"+prefix+"\\.testOk").attr("checked",false); 
				      		            	$("#"+prefix+"\\.testOk").val("0"); 
				   							$("#"+thisId).after("<font id="+oldPrefix+"color color='red'>"+msg.warnMsg+"</font>");
				      		           }
				   	            	
				          			}
				       		); 
		    		}else{
		    			$("#"+prefix+"color").remove();
		    			$("#"+prefix+"\\.testOk").attr("checked",false); 
  		            	$("#"+prefix+"\\.testOk").val("0"); 
		    		}
		    	});
		    }  
     
     }); 
     
     function checkWsUrl(wsUrl,objectID,prefix){
    	 wsUrl = $.trim(wsUrl);
    	 if(wsUrl==""){
    		 prefix = prefix.replace("[","\\[");
			 prefix = prefix.replace("]","\\]");
				
    		$("#"+prefix+"\\.testOk").attr("checked",false); 
           	$("#"+prefix+"\\.testOk").val("0"); 
    		 return true;
    	 }
    	 
    	 if(chklength($("#"+objectID),256)==false){
    		 $("#"+objectID).after("<font id="+prefix+"color color='red'>接口链接最大只能允许256字符！</font>");
   			 $("#"+objectID).focus();
   			 $("#"+objectID).css("background-color","#FFFFCC");
   			 return false;
    	 }
    	 
    	 if(wsUrl.indexOf("token")==-1){
    		$("#"+objectID).after("<font id="+prefix+"color color='red'>链接地址必须包括token参数！</font>");
  			$("#"+objectID).focus();
  			$("#"+objectID).css("background-color","#FFFFCC");
  			return false;
  		}
    	 
    	 
    	var webCodeReg = /^(http|https):\/\/([\w-]+\.)+[\w-]+(:\d+)?(\/[\w- .\/?%&=]*)?$/;
 		if(webCodeReg.test(wsUrl)){
 			return true;
 		}else{
 			$("#"+objectID).after("<font id="+prefix+"color color='red'>链接地址格式不符合！</font>");
 			$("#"+objectID).focus();
 			$("#"+objectID).css("background-color","#FFFFCC");
 			return false;
 		}
		
		
     }
     
    
    
	function updateRecord(){
		
		     for(var i=0;i<listSize;i++){
		    	var wsUrl = $("#serviceForms\\["+i+"\\]\\.wsUrl").val();
		    	
		    	var objectID = "serviceForms["+i+"].wsUrl";
		    	var prefix = objectID.split(".")[0];
		    	var oldPrefix = prefix;
				prefix = prefix.replace("[","\\[");
				prefix = prefix.replace("]","\\]");
				
				objectID = objectID.replace("[","\\[");
				objectID = objectID.replace("]","\\]");
				objectID = objectID.replace(".","\\.");
				
				
		    	
				$("#"+prefix+"color").remove();
		    	if(checkWsUrl(wsUrl,objectID,oldPrefix)==false){
		    		return false;
		    		}
		    }
		
		
			bootbox.confirm("确认保存？",function(result){
				if(result==true){
						  $("#CWebServiceListForm").ajaxSubmit(
								  function(msg){
									if(msg.result==false){
										bootbox.alert(msg.warnMsg,function(){});
									}else{
										bootbox.alert(msg.warnMsg,function(){});
									}
						   }); 
				}
			});
	}
    
    
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>