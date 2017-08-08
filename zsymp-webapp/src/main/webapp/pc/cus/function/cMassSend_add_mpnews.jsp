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
    <title>新建${materialTypeName}群发</title>
    
    <!-- jqgrid  The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/css/ui.jqgrid.css"/>" />
    <meta charset="utf-8" />
    <title>jqGrid Loading Data - Million Rows from a REST service</title>
    
    
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
    </style>
    <style>
    html,body,h1,h2,h3,h4,h5,*{ font-family: "微软雅黑","宋体",sans-serif; margin: 0; padding: 0;}
.font-size-normal{ font-size: 16px !important;}
.ml-normal{ margin-left: 10px;}
.wraper #main{ margin-top: 40px;}

.MassSend_form,.preview_box{ display:inline-block; vertical-align:top;}

.MassSend_form{ width:49%;}
.preview_box{ width: 300px; border: 1px solid #ccc; border-radius: 5px;}

.first_item{ position: relative; width: 96%; height: 200px; margin: 0 auto; padding: 5px 0 0 0; overflow: hidden;}
.first_item h4{ position: absolute; z-index: 998; bottom: 0; display: block; width: 100%; height: 30px; line-height: 30px; background: rgba(0,0,0,0.7); color: #fff; font-size: 14px;}
.first_item img{ position: absolute; z-index: 997; width: 100%;}

.normal_item{ position: relative; width: 96%; height: 86px; margin: 0 auto; border-bottom: 1px solid #ccc;}
.normal_item:last-child{ border-bottom: none;}
.normal_item span{ display: inline-block; vertical-align: middle; width: 68%; height: 86px; line-height: 86px;}
.normal_item img{ vertical-align: middle; width: 30%; height: 100%;}
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
	  <h2 class="title_class">新建${materialTypeName}群发</h2>
	  
	  <div class="MassSend_form">
	  	<form:form action="${pageContext.request.contextPath}/cus/cMassSend_add.do"  modelAttribute="CMassSendForm"  method="POST" >
	      
	      	  <input type="hidden" name="id" id="id" >
	      	  <input type="hidden" name="materialType" id="materialType" value="${materialType}">
	          
	          <div>
		      <label for="title" class="col-sm-2 control-label"><font color="red">*</font>标题：</label>
		      <input type="text" name="title" id="title"  class="general_input_class" size="64">
	      	  </div>
	      	  
	      	  <div class="mtrl_class">
	      	  <input class="btn btn-default" data-toggle="modal"
									data-target="#articleMtrlModal" onClick="reload()" id="articleMtrlModalId" type="button" value="素材库"> <input type="button" id="deselectAllMatrial" value="清空重选">
		      <input type="text"  id="materialID"  name="materialID">
	      	  </div>
	      	  
	      	<input type="button" class="btn btn-default"  id="returnBtn" value="返回"> <input type="button" id="subBtn"  class="btn btn-default" value="保存"> <input type="button" id="subNSendBtn"  class="btn btn-default" value="保存并发送"> 
	  </form:form>
	  </div>
	  <div class="preview_box" id="preview_box">
	</div>
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

<!-- This is the Javascript file of jqGrid -->   
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<!-- This is the localization file of the grid controlling messages, labels, etc.
<!-- We support more than 40 localizations -->
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/i18n/grid.locale-cn.js"/>"></script>

<jsp:include page="../cArticleMtrl_picklist.jsp"/>
<%-- <jsp:include page="../cTextMtrl_picklist.jsp"/> --%>
<%-- <jsp:include page="../cPicMtrl_picklist.jsp"/> --%>
<script src="<c:url value="/resources/js/valField.js"/>" ></script>

<!-- 本页调用JS 开始 -->
<script>
    function reload(){
    	//alert("a");
    	$("#datagridArticle").trigger("reloadGrid");
    } 

    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
        
        document.getElementById("subNSendBtn").style.display="none";
        document.getElementById("materialID").style.display="none";
        
    });
    
    
    
    var martrialNum = 0;
    var finalPicPath="${cusPicPathPickList}";
    function setValueArticleMtrl(obj){
    	
    	if(obj.token=="system"){
    		finalPicPath="${bizPicPathPickList}";
       }else{
    	    finalPicPath="${cusPicPathPickList}"
       }
    	
       if("${materialType}"=="mpnews"){
    	   var materialID = $.trim($("#materialID").val());
    	   if(materialID==""){
    		   $("#materialID").val(obj.id);
    		   $("#preview_box").append( '<div class="first_item">	<h4>'+obj.title+'</h4>	<img src="'+"${pageContext.request.contextPath}"+finalPicPath+obj.picName+'" /> </div>');
    		   ++martrialNum;
    	   }else{
    		   if(martrialNum<8){
    			   $("#materialID").val(materialID+","+obj.id);
    			   $("#preview_box").append( '<div class="normal_item">  <span>'+obj.title+'</span>  <img src="'+"${pageContext.request.contextPath}"+finalPicPath+obj.picName+'" /> </div>');
    			   ++martrialNum;
    		   }else{
    			   bootbox.alert("最多只有添加八个图文！");
    		   }
    		  
    	   }
       }
    	
   	   
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
	    	 window.location.href='${pageContext.request.contextPath}/cus/cMassSend_list.do?pageNo=1';
			});
    	 
	   //保存并发送按钮
	     $("#subNSendBtn").click(function(){
	    	 //TODO tmh
			});
	   
     }); 
     
     
       //清空素材按钮
	   $("#deselectAllMatrial").click(function(){
		  $("#materialID").val("");
		  document.getElementById("preview_box").innerHTML="";
		  martrialNum=0;
	   });
    
    function checkForm() { 
    	pass = true; 
    	
    	$("#CMassSendForm").children().filter("div:contains('*')").children().filter("input:text,textarea").each(function(){
    		
    		
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
		
		$("#CMassSendForm").children().filter("div").children().filter("input:text,textarea").each(function(){
      		
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
		if($("#materialID").val()=="" ||$("#materialID").val()==null){
			bootbox.alert("请选择素材！");
			return false;
		}
		
		if(checkForm()==true){
		bootbox.confirm("确认保存？",function(result){
			if(result==true){
 				   
					  $("#CMassSendForm").ajaxSubmit(
							  function(msg){
								if(msg.result==false){
									bootbox.alert(msg.warnMsg,function(){});
								}else{
									bootbox.alert(msg.warnMsg,function(){
										window.location.href='${pageContext.request.contextPath}/cus/cMassSend_list.do?pageNo=1';
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