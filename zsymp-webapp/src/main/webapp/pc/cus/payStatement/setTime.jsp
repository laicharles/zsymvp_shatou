<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="edge" />
    <title> 汕头自来水微信服务系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    
    <!-- jqgrid  The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/css/ui.jqgrid.css"/>" />
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
        .mar{
        	margin-top:-4px !important;
        	margin-left:5px;
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
    <div class="user"><img class="avatar" width="25" height="25" src="<c:url value="/resources/js/bootstrap/img/avatar.png"/>" alt="${user.loginName }"><span>${user.loginName }</span><i class="icon-user trigger-user-settings"></i></div>
    <div class="navigation-sidebar"><i class="switch-sidebar-icon icon-align-justify"></i></div>
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
    <div class="container-fluid">
        <!--主体内容-->
        <h2 class="title_class">缴费规则设置</h2>
        <p class="title_class" style="font-size:20px">日结时间设置</p>
        <div class="search_field_class">
                                小时1：<input type="text" class="input-txt" id="time1" name="time1" readonly="readonly"  style="height: 25px;"
				onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});" value="${timeSet.firstHour}"/>
		        小时2：<input type="text" class="input-txt" id="time2" name="time2" readonly="readonly"  style="height: 25px;"
				onclick="WdatePicker({dateFmt:'HH:mm:ss',isShowClear:false});" value="${timeSet.secondHour}"/>
   				<input type="checkbox" style="margin:0px;zoom:300%;" id="button1"  data-off="default">
   				<font color="red">*</font>勾选表示生效
   				<input type="button" id="button11" class="btn btn-default mar" value="保存">
		</div>
		<div class="search_field_class">
		   <p class="title_class" style="font-size:20px">月结时间限制</p>
		     <p class="title_class" style="font-size:15px">1.居民月结时间限制</p>
                                            日期1：<input type="text" class="input-txt" id="month1" readonly="readonly"  style="height: 25px;"
				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});" value="${timeSet.firstDay1}"/>
		                    日期2：<input type="text" class="input-txt" id="month2" readonly="readonly"  style="height: 25px;"
				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});" value="${timeSet.secondDay1}"/>
		            <input type="checkbox" style="margin:0px;zoom:300%;" id="button2"  data-off="default">
		            <font color="red">*</font>勾选表示生效   
		            <input type="button" id="button22" class="btn btn-default mar" value="保存">
		     <p class="title_class" style="font-size:15px">2.企业月结时间限制</p>
                                            日期1：<input type="text" class="input-txt" id="months1" readonly="readonly"  style="height: 25px;"
				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});" value="${timeSet.firstDay2}"/>
		                    日期2：<input type="text" class="input-txt" id="months2" readonly="readonly"  style="height: 25px;"
				     onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});" value="${timeSet.secondDay2}"/>
		            <input type="checkbox" style="margin:0px;zoom:300%;" id="button3"  data-off="default">
		            <font color="red">*</font>勾选表示生效
		            <input type="button" id="button33" class="btn btn-default mar" value="保存">
      	</div>
         
        <table id="datagrid"></table>
        <div id="pagegrid"></div>
    </div>

	<!-- 页脚开始 -->
    <footer id="footer">
        <jsp:include page="../../../base/cus/foot.jsp"/>
    </footer>
    <!-- 页脚结束 -->
    </div>
</div>
<script src="<c:url value="/resources/js/plugins/My97DatePicker/WdatePicker.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/twitter-bootstrap/bootstrap.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery.slimscroll/jquery.slimscroll.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/js/extents.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap/js/sidebar.js"/>"></script>

<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>

<!-- This is the Javascript file of jqGrid -->   
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<!-- This is the localization file of the grid controlling messages, labels, etc.
<!-- We support more than 40 localizations -->
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/i18n/grid.locale-cn.js"/>"></script>

<!-- 本页调用JS 开始 -->
<script>
    var hourSatus = '${timeSet.hourSatus}';
    var daySatus1 = '${timeSet.daySatus1}';
    var daySatus2 = '${timeSet.daySatus2}';
    if(hourSatus == '1'){
    	$('#button1').attr("checked",'true');
    }
    if(daySatus1 == '1'){
    	$('#button2').attr("checked",'true');
    }
    if(daySatus2 == '1'){
    	$('#button3').attr("checked",'true');
    }
	
    $("#button11").click(function(){
		var time1 = $('#time1').val();
		var time2 = $('#time2').val();
		var status = "";
		if($('#button1').prop("checked")){
			status = "1";
		}else{
			status = "0";
		}
		$.get(
       			"${pageContext.request.contextPath}/cus/saveDay.do",
       			{"time1":time1,"time2":time2,'status':status},
       			function(msg){
       				if(msg.tips == '0'){
       					alert("日结时间设置成功");
   		            }else if(msg.tips == '1'){
   		            	alert("日结时间设置失败");
   		            	alert(msg.warnMsg);
   		            }
	            	
       			}
    		); 
	});
    $('#button22').click(function(){
    	var month1 = $('#month1').val();
		var month2 = $('#month2').val();
		if($('#button2').prop("checked")){
			status = "1";
		}else{
			status = "0";
		}
		var start=new Date(month1.replace("-", "/").replace("-", "/")); 
		var end=new Date(month2.replace("-", "/").replace("-", "/")); 
 		if(end < start){  
			alert("起始的时间大于末尾的时间");
	        return false;  
	    }
 		$.get(
       			"${pageContext.request.contextPath}/cus/saveMonth.do",
       			{"month1":month1,"month2":month2,"userType":"1",'status':status},
       			function(msg){
       				if(msg.tips == '0'){
       					alert("居民月结时间设定成功");
   		            }else if(msg.tips == '1'){
   		            	alert("居民月结时间设定失败");
   		            	alert(msg.warnMsg);
   		            }
       			}
    		);
    });
    $('#button33').click(function(){
		var months1 = $('#months1').val();
		var months2 = $('#months2').val();
		if($('#button3').prop("checked")){
			status = "1";
		}else{
			status = "0";
		}
		var start=new Date(months1.replace("-", "/").replace("-", "/")); 
		var end=new Date(months2.replace("-", "/").replace("-", "/")); 
 		if(end < start){  
			alert("起始的时间大于末尾的时间");
	        return false;  
	    }
 		$.get(
       			"${pageContext.request.contextPath}/cus/saveMonth.do",
       			{"months1":months1,"months2":months2,"userType":"2",'status':status},
       			function(msg){
       				if(msg.tips == '0'){
       					alert("企业月结时间设置成功");
   		            }else if(msg.tips == '1'){
   		            	alert("企业月结时间设置失败");
   		            	alert(msg.warnMsg);
   		            }
       			}
    		);
    });
/* 	$("#hour").click(function(){
		var time1 = $('#time1').val();
		var time2 = $('#time2').val();
		$.get(
       			"${pageContext.request.contextPath}/cus/saveDay.do",
       			{"time1":time1,"time2":time2},
       			function(msg){
       				if(msg.tips == '0'){
       					alert("成功");
   		            }else if(msg.tips == '1'){
   		            	alert("失败");
   		            }
	            	
       			}
    		); 
	});
	$("#date1").click(function(){
		var month1 = $('#month1').val();
		var month2 = $('#month2').val();
		var userType = '1';
		$.get(
       			"${pageContext.request.contextPath}/cus/saveMonth.do",
       			{"month1":month1,"month2":month2,"userType":userType},
       			function(msg){
       				if(msg.tips == '0'){
       					alert("成功");
   		            }else if(msg.tips == '1'){
   		            	alert("失败");
   		            }
       			}
    		); 
	});
	$("#date2").click(function(){
		var months1 = $('#months1').val();
		var months2 = $('#months2').val();
		var userType = '2';
		$.get(
       			"${pageContext.request.contextPath}/cus/saveMonth.do",
       			{"months1":months1,"months2":months2,"userType":userType},
       			function(msg){
       				if(msg.tips == '0'){
       					alert("成功");
   		            }else if(msg.tips == '1'){
   		            	alert("失败");
   		            }
	            	
       			}
    		); 
	}); */
</script>
<!-- 本页调用JS 结束 -->
</body>

</html>