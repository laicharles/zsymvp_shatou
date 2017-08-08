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
    <meta charset="utf-8" />
    <title>jqGrid Loading Data - Million Rows from a REST service</title>
    
    
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
    <div class="container-fluid">
        <!--主体内容-->
        <h2 class="title_class">用户管理</h2>
        <div class="list_btn_div_class">
        <input type="button" id="addRecord" class="btn btn-default"  onclick="location='${pageContext.request.contextPath}/cus/bcustomeruser_toAdd.do'" value="新增">
        <input type="button" id="updateRecord" class="btn btn-default" onclick="updateRecord()" value="编辑"> <input type="button" id="delRecord" class="btn btn-default"  onclick="delRecord()" value="删除">
         <input type="button" id="viewRecord" class="btn btn-default" onclick="viewRecord()" value="查看"> 
         <input type="button" id="disableUser" class="btn btn-default" onclick="disableUser()" value="禁用"> <input type="button" id="enableUser" class="btn btn-default"  onclick="enableUser()" value="启用"> 
         <input type="button" id="initPassword" class="btn btn-default" onclick="initPassword()" value="初始化密码"> 
         </div>
         
         <p>
         <div class="search_field_class">
		         用户名：<input type="text" id="loginName"  class="form-control" placeholder="用户名"> 
		         手机号码：<input type="text" id="mobile" class="form-control" placeholder="手机号码">  
		         真实姓名：<input type="text" id="userName" class="form-control" placeholder="真实姓名"> 
		         禁用状态：<select id="isDisabled" style="width: 80px">
		         	<option value="-1">请选择</option>
		         	<option value="0">启用</option>
		         	<option value="1">禁用</option>
		         </select>
		         <input type="button" value="查询" class="btn btn-default" onclick="search()">
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
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });

    /*加载列表数据*/
    $(document).ready(function () {
    	
	    jQuery("#datagrid").jqGrid({
	       	url: '${pageContext.request.contextPath}/cus/bcustomeruser_list_json.do',
	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
      		       	{ label: 'id', name: 'id', key: true,hidden:true},
      		        { label: '用户名', name: 'loginName', width: 150},
      		        { label: '角色名', name: 'role.roleName', width: 150},
      		        { label: '角色代码', name: 'role.roleCode', width: 150,hidden:true},
      		        { label: '真实姓名', name: 'userName', width: 150},
      	     		{ label: '手机号码', name: 'mobile', width: 150},
      	     		{ label: '电子邮箱', name: 'email', width: 150},
      	     		{ label: '禁用状态', name: 'isDisabled', width: 100, align:'center', formatter:'checkbox', editoptions:{value:'1:0'}, formatoptions:{disabled:true}}
      	    ],
      	    viewrecords : true,
      	  	sortname : 'loginName',
      	    sortable:true,
      	  	sortorder:'asc',
	    	height:'100%',
	    	pager : "#pagegrid",
	    	page:"${page}",			//初始化查询页数
	    	jsonReader : {
	    		root: "formList",    // json中代表实际模型数据的入口
	    		records: "records",   // json中代表数据行总数的数据
	    		page: "page",    // json中代表当前页码的数据
	    		total: "total",   // json中代表页码总数的数据
	             repeatitems : false
	           },
	    	multiselect: true,
	    	onSelectRow: function (rowid, status) {
				selId = rowid;
			}
	    });
	    
    });
    
    //查询方法
    function search(){
    	
    	var isDisabled=$('#isDisabled').val();
    	var loginName =$('#loginName').val();
    	var mobile=$('#mobile').val();
    	var userName=$('#userName').val();
    	jQuery("#datagrid").jqGrid('setGridParam',{
	       	url: '${pageContext.request.contextPath}/cus/bcustomeruser_list_json.do',
	       	postData:{'isDisabled':isDisabled,'loginName':loginName,'mobile':mobile,'userName':userName},
	       	page:1
	    }).trigger("reloadGrid");
    }
    
        
    /*编辑记录*/
    function updateRecord(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==null || id==""){
    		bootbox.alert("请选择需要编辑记录！");
    		return false;
    	}else if(id.length>1){
    		bootbox.alert("选择了多行，请选择需要编辑一条记录！")
    		return false;
    	}else{
    		
    		$.get(
           			"${pageContext.request.contextPath}/cus/bcustomeruser_checkById.do?id="+id,
           			function(msg){
           				if(msg.result==true){
           						location.href='${pageContext.request.contextPath}/cus/bcustomeruser_toUpdate.do?id='+id+'&pageNo='+pageNo;
       		            }else{
       		            	bootbox.alert(msg.warnMsg); 
       		            	return false;
       		            }
    	            	
           			},"json"
        		); 
    		
    	}
    	
    }
    
    function viewRecord(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==null || id==""){
    		bootbox.alert("请选择需要查看的记录！");
    		return false;
    	}else if(id.length>1){
    		bootbox.alert("选择了多行，请选择需要查看一条记录！")
    		return false;
    	}else{
    		
    		$.get(
           			"${pageContext.request.contextPath}/cus/bcustomeruser_checkById.do?id="+id,
           			function(msg){
           				if(msg.result==true){
           						location.href='${pageContext.request.contextPath}/cus/bcustomeruser_toView.do?id='+id+'&pageNo='+pageNo;
       		            }else{
       		            	bootbox.alert(msg.warnMsg); 
       		            	return false;
       		            }
    	            	
           			},"json"
        		); 
    		
    	}
    }
    
    
    
    
    /*删除记录*/
    function delRecord(){
    	
    	var ids = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(ids==null || ids==""){
    		bootbox.alert("请选择需要修改记录！");
    	}else{
    		
    		//验证不能禁用管理员
    		for(var i=0;i<ids.length;i++){
    			var roleCode = $("#datagrid").jqGrid("getCell",ids[i],'role.roleCode');
    			if(roleCode=='CUS_001'){
    				bootbox.alert("运营管理员不能删除！");
    				return false;
    			}
    		}
    		
    		bootbox.confirm('确定需要删除吗？',
    		    function(result){
	    			if(result==true){  //执行删除操作
	    		    	$.post(
	    	           			"${pageContext.request.contextPath}/cus/bcustomeruser_delete.do",
	    	           			{"ids":ids.join(","),"${_csrf.parameterName}":"${_csrf.token}"},
	    	           			function(msg){
	    	           				if(msg.result==true){
	    	           					bootbox.alert(msg.warnMsg, function(){
	    	    						    	window.location.href='${pageContext.request.contextPath}/cus/bcustomeruser_list.do?pageNo='+pageNo;
	    	    						    }); 
	    	       		            }else{
	    	       		            	bootbox.alert(msg.warnMsg); 
	    	       		            	return false;
	    	       		            }
	    	    	            	
	    	           			}
	    	        		); 
	    			}
    		    }
    		);
    		
    	}
    }
    
    
    /*启用*/
    function enableUser(){
    	
    	var ids = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(ids==null || ids==""){
    		bootbox.alert("请选择需要启用用户记录！");
    	}else{
    		
    		bootbox.confirm('确定需要启用用户吗？',
    		    function(result){
	    			if(result==true){  //执行删除操作
	    		    	$.post(
	    	           			"${pageContext.request.contextPath}/cus/bcustomeruser_enable.do",
	    	           			{"ids":ids.join(","),"${_csrf.parameterName}":"${_csrf.token}"},
	    	           			function(msg){
	    	           				if(msg.result==true){
	    	           					bootbox.alert(msg.warnMsg, function(){
	    	    						    	window.location.href='${pageContext.request.contextPath}/cus/bcustomeruser_list.do?pageNo='+pageNo;
	    	    						    }); 
	    	       		            }else{
	    	       		            	bootbox.alert(msg.warnMsg); 
	    	       		            	return false;
	    	       		            }
	    	    	            	
	    	           			}
	    	        		); 
	    			}
    		    }
    		);
    		
    	}
    }
    
    
    
    /*禁用*/
    function disableUser(){
    	
    	var ids = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(ids==null || ids==""){
    		bootbox.alert("请选择需要禁用用户记录！");
    	}else{
    		
    		//验证不能禁用管理员
    		for(var i=0;i<ids.length;i++){
    			var roleCode = $("#datagrid").jqGrid("getCell",ids[i],'role.roleCode');
    			if(roleCode=='CUS_001'){
    				bootbox.alert("运营管理员不能禁用！");
    				return false;
    			}
    		}
    		
    		bootbox.confirm('确定需要禁用用户吗？',
    		    function(result){
	    			if(result==true){  //执行删除操作
	    		    	$.post(
	    	           			"${pageContext.request.contextPath}/cus/bcustomeruser_disable.do",
	    	           			{"ids":ids.join(","),"${_csrf.parameterName}":"${_csrf.token}"},
	    	           			function(msg){
	    	           				if(msg.result==true){
	    	           					bootbox.alert(msg.warnMsg, function(){
	    	    						    	window.location.href='${pageContext.request.contextPath}/cus/bcustomeruser_list.do?pageNo='+pageNo;
	    	    						    }); 
	    	       		            }else{
	    	       		            	bootbox.alert(msg.warnMsg); 
	    	       		            	return false;
	    	       		            }
	    	    	            	
	    	           			}
	    	        		); 
	    			}
    		    }
    		);
    		
    	}
    }
    
    /*初始化密码*/
    function initPassword(){
    	
    	var ids = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(ids==null || ids==""){
    		bootbox.alert("请选择需要初始化密码的记录！");
    	}else{
    		
    		bootbox.confirm('确定需要初始化密码吗？',
    		    function(result){
	    			if(result==true){  //执行删除操作
	    		    	$.post(
	    	           			"${pageContext.request.contextPath}/cus/bcustomeruser_initPassword.do",
	    	           			{"ids":ids.join(","),"${_csrf.parameterName}":"${_csrf.token}"},
	    	           			function(msg){
	    	           				if(msg.result==true){
	    	           					bootbox.alert(msg.warnMsg, function(){
	    	    						    	window.location.href='${pageContext.request.contextPath}/cus/bcustomeruser_list.do?pageNo='+pageNo;
	    	    						    }); 
	    	       		            }else{
	    	       		            	bootbox.alert(msg.warnMsg); 
	    	       		            	return false;
	    	       		            }
	    	    	            	
	    	           			}
	    	        		); 
	    			}
    		    }
    		);
    		
    	}
    }
    
    
    
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>