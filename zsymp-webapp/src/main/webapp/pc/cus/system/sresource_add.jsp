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
  		<div >
	  <p class="validateTips">   </p> 
	  <h2 class="title_class">新增资源</h2>
	  <form:form action="${pageContext.request.contextPath}/cus/sresource_add.do"  modelAttribute="SResourceForm"  method="POST">
	      <!-- <label for="resCode">资源代码</label>
	      <input type="text" name="resCode" id="resCode"  class="text ui-widget-content ui-corner-all"> -->
	          
	          <div>
		      <label for="resName"  ><font color="red">*</font>资源名：</label>
		      <input type="text" name="resName" id="resName" class="general_input_class" size="64" value="${resourceForm.resName }">
	      	  </div>
	      		
	      	<div>
		      <label for="parentResource.resName"  class="col-sm-2 control-label"><font color="red">*</font>上级资源：</label>
		      <input type="text"  name="parentResource.resName" id="parentResource.resName"  class="general_input_class"  readonly="readonly" value="${resourceForm.parentResource.resName }">
		      <input type="button" id="selectMenus" class="btn btn-default"  style="margin-top:-12px" value="选择" onclick="showMenu();return false;"/> 
	      	</div>
	      	<input type="hidden"   name="parentResource.id" id="parentResource.id" readonly="readonly" value="${resourceForm.parentResource.id }">
	      	
	      	<div>
	      <label for="resType"  ><font color="red">*</font>资源类型：</label>
	      <div class="input-radio">
	        <input type="hidden" name="resType.typeCode" value="resourceType"> 
	         <c:if test="${not empty resourceForm }">
		        <input type="radio" name="resType.dicValue" value="0"  <c:if test="${resourceForm.resType.dicValue==0 }">checked="checked" </c:if> />目录
		      	<input type="radio" name="resType.dicValue" value="1" <c:if test="${resourceForm.resType.dicValue==1 }">checked="checked"  </c:if> />菜单
		      	<input type="radio" name="resType.dicValue" value="2" <c:if test="${resourceForm.resType.dicValue==2 }">checked="checked"  </c:if> />操作
	      	 </c:if>
	      	 <c:if test="${empty resourceForm }">
		      	  <input type="radio" name="resType.dicValue" value="0"  checked="checked"/>目录
		      	  <input type="radio" name="resType.dicValue" value="1"  />菜单
		      	  <input type="radio" name="resType.dicValue" value="2"  />操作
	      	 
	      	 </c:if>
	      	 </div>
			</div>
		
		<div>
	        <label for="isShowMenu" class="col-sm-2 control-label"><font color="red">*</font>是否显示：</label>
	        <div class="input-radio" style="margin-bottom: 10px;">
	        <c:if test="${empty resourceForm }">
		        <input type="radio" name="isShowMenu" value="0" />否
		      	<input type="radio" name="isShowMenu" value="1" checked="checked"/>是
	      	</c:if>
	      	<c:if test="${not empty resourceForm }">
	      		<input type="radio" name="isShowMenu" value="0" <c:if test="${resourceForm.isShowMenu==0 }">checked="checked" </c:if>/>否
		      	<input type="radio" name="isShowMenu" value="1" <c:if test="${resourceForm.isShowMenu==1 }">checked="checked" </c:if>/>是
	      	</c:if>
	      	</div>
	      </div>
	      
	      <div>
			<label for="reqUrl" class="col-sm-2 control-label"><font color="red">*</font>资源链接：</label>
	      <input type="text" name="reqUrl" id="reqUrl" value="${resourceForm.authUrl }" size="256" class="pageUrl_class" >
	      </div>
	      
	      <div>
	      <label for="authUrl" class="col-sm-2 control-label"><font color="red">*</font>权限链接：</label>
	      <input type="text" name="authUrl" id="authUrl"  value="${resourceForm.authUrl }" size="256" class="pageUrl_class" >
	      </div>
	      
	      <!-- <label for="picName">资源图片</label>
	      <input type="text" name="picName" id="picName"  class="text ui-widget-content ui-corner-all"> -->
	      <div>
	 	  <label for="remarks" class="col-sm-2 control-label">备注：</label>
	 	  <textarea rows="5" cols="20" name="remarks" id="remarks"  size="256"   class="textArea_class" >${resourceForm.authUrl }</textarea>
	 	  </div>
	      <!-- Allow form submission with keyboard without duplicating the dialog button -->
	      
	      	<input type="button" class="btn btn-default"  id="returnBtn" value="返回"> <input type="button" id="subBtn"  class="btn btn-default" value="保存">
	  </form:form>
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
	    	 window.location.href='${pageContext.request.contextPath}/cus/sresource_list.do';
			});
   	 
    });
    
    
    
    function checkForm() { 
    	pass = true; 
    	$("#SResourceForm").children().filter("div:contains('*')").children().filter("input").each(function(){
    		
    		var idValue = this.id.replace("\.","");   //id名字如果带有.号，jquery会有问题，需要去除
    		
    		this.value = $.trim(this.value);    //删除前后空格
			$("#"+idValue+"color").remove();    //清除已有样式提示
	    	if(this.value == '') { 
		    	text = $(this).parent().children().filter("label").text(); 
		    	$(this).after("<font id="+idValue+"color color='red'>"+text+"是必填项！</font>");
		    	this.focus(); 
		    	pass = false; 
	    	} 
    	});
    	
    	
	   $("#SResourceForm").children().filter("div").children().filter("input:text,textarea").each(function(){
    		
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
			 	   
			 		  $("#SResourceForm").ajaxSubmit(
			 				  function(msg){
			 					if(msg.result==false){
			 						bootbox.alert(msg.warnMsg,function(){});
			 					}else{
			 						bootbox.confirm(msg.warnMsg,function(){
			 							window.location.href='${pageContext.request.contextPath}/cus/sresource_list.do';
			 						});
			 					}
			 		   }); 
			       		
    		}
    	});
    	}
 	   
     }
    
    
    
    
    //树状资源选选择
    var setting = {
    		check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onCheck: onCheck
			}
		};

		$(document).ready(function(){
			$.ajax( {  
		        url : "${pageContext.request.contextPath}/cus/sresource_menu_json.do",  
		        type : "get",  
		        dataType : "json",  
		        success : initZtree  
		    });  
		});
    
		
		function initZtree(json) {  
		    zTreeObj = $.fn.zTree.init($('#treeDemo'), setting, json);  
		}  
		
    
		
		
		function showMenu() {
			var cityObj = $("#parentResource.id");
			var cityOffset = $("#selectMenus").offset();
			
			$("#menuContent").css({"left":cityOffset.left + "px", "top":cityOffset.top + cityObj.outerHeight() +30+ "px"}).slideDown("fast");
			
			$("body").bind("mousedown", onBodyDown);
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "parentResource.id" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}

		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			id = "";
			name = "";
			for (var i=0, l=nodes.length; i<l; i++) {
				name += nodes[i].name + ",";
				id += nodes[i].id + ",";
			}
			if (id.length > 0 ) id = id.substring(0, id.length-1);
			if (name.length > 0 ) name = name.substring(0, name.length-1);
			var parentIdObj = $("#parentResource\\.id");
			var parentNameObj = $("#parentResource\\.resName");
			
			parentNameObj.attr("value", name);
			parentIdObj.attr("value", id);
		}
		
		
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>