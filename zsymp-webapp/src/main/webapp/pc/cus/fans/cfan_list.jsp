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
        <h2 class="title_class">粉丝列表</h2>
         
         <div class="list_btn_div_class">
         	<input type="button" value="查看" class="btn btn-default" onclick="viewRecord()"> 
         	<input type="button" value="同步微信用户列表" class="btn btn-default" onclick="synRecords()">
         	<input type="button" value="添加到常用人员" class="btn btn-default" onclick="addCommonRecord()">
         	<input type="button" value="添加到反馈人员" class="btn btn-default" onclick="addFeedBackRecord()">
         </div>
         
         <div class="search_field_class">
		         昵称：<input type="text" id="nickName" class="form-control" placeholder="昵称"> 
		         <input type="button" value="查询" onclick="search()" class="btn btn-default"> 
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
    	console.log("${_csrf}");
	    jQuery("#datagrid").jqGrid({
	       	url: '${pageContext.request.contextPath}/cus/cfan_list_json.do',//请求的连接
	       	mtype: "POST",//那种方式的请求
	       	postData:{"_csrf":"${_csrf.token}"},//传的参数值
            contentType: "application/json",
            datatype: "json",
            colModel:[//设定格列的参数值
      		       	{ label: 'id', name: 'id', key: true,hidden:true},
      		        { label: '昵称', name: 'nickName', width: 150},
      		        { label: 'openID', name: 'openID', width: 300},
      		        { label: '是否关注', name: 'isSubscribe', width: 150,align:'center', formatter:'checkbox', editoptions:{value:'1:0'}, formatoptions:{disabled:true}},
      		        { label: '城市', name: 'cityName', width: 150},
      	     		{ label: '关注时间', name: 'subscribeDate', width: 150},
      	     		{ label: '退订时间', name: 'unsubscribeDate', width: 150}
      	    ],
      	    viewrecords : true,//在页码控制条上是否显示所有记录的条数
      	  	sortname : 'subscribeDate',//设定默认的拍序列
      	    sortable:true,//默认为TRUE
      	  	sortorder:'desc',//排序的方式
	    	height:'100%',
	    	pager : "#pagegrid",//页码控制条
	    	page:"${page}",			//初始化查询页数
	    	jsonReader : {
	    		root: "formList",    // json中代表实际模型数据的入口
	    		records: "records",   // json中代表数据行总数的数据
	    		page: "page",    // json中代表当前页码的数据
	    		total: "total",   // json中代表页码总数的数据
	             repeatitems : false//
	           },
	    	multiselect: true,
	    	onSelectRow: function (rowid, status) {
				selId = rowid;
			}
	    });
	    
    });
    
    //查询方法
    function search(){
    	var nickName =$('#nickName').val();
    	var hasBind=$('#hasBind').val();
    	jQuery("#datagrid").jqGrid('setGridParam',{
	       	url: '${pageContext.request.contextPath}/cus/cfan_list_json.do',
	       	postData:{'nickName':nickName},
	       	page:1
	    }).trigger("reloadGrid");
    }
    
        
    //查看粉丝详细信息
    function viewRecord(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==null || id==""){
    		bootbox.alert("请选择需要查看记录！");
    		return false;
    	}else if(id.length>1){
    		bootbox.alert("选择了多行，请选择查看一条记录！")
    		return false;
    	}else{
    		
    		$.get(
           			"${pageContext.request.contextPath}/cus/cfan_checkById.do?id="+id,
           			function(msg){
           				if(msg.result==true){
           						location.href='${pageContext.request.contextPath}/cus/cfan_toView.do?id='+id+'&pageNo='+pageNo;
       		            }else{
       		            	bootbox.alert(msg.warnMsg); 
       		            	return false;
       		            }
    	            	
           			},"json"
        		); 
    		
    	}
    }
    
    //同步微信用户列表
    function synRecords(){
    	
    	bootbox.confirm('确定需要同步吗？\n同步操作需要一定时间来完成并重置粉丝列表，请谨慎使用！',
    		    function(result){
    	
	    	$.get(
	    			'${pageContext.request.contextPath}/cus/cfan_synFans.do',
	       			function(msg){
	       				if(msg.result==true){
	       					bootbox.alert(msg.warnMsg,function(){
	       						search();
	       					}); 
	   		            	return false;
	   		            }else{
	   		            	bootbox.alert(msg.warnMsg,function(){
	   		            		search();
	   		            	}); 
	   		            	return false;
	   		            }
		            	
	       			},"json"
	    		); 
    	});
    }
    
   //添加到常用人员方法
    function addCommonRecord () {
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==""||id==null){
    		bootbox.alert("请至少选择一个粉丝！",function(){});
    	}else if(id.length>1){
    		bootbox.alert("只能选择一个粉丝进行添加！")
    		return false;
    	}else{
    		location.href='${pageContext.request.contextPath}/cus/toAddCommonPerson.do?id='+id+'&pageNo='+pageNo;
    	}
	   
    }
    //添加到反馈人员方法
    function addFeedBackRecord() {
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==""||id==null){
    		bootbox.alert("请至少选择一个粉丝！",function(){});
    	}else if(id.length>1){
    		bootbox.alert("只能选择一个粉丝进行添加！")
    		return false;
    	}else{
    		location.href='${pageContext.request.contextPath}/cus/toAddCFeedBackPerson.do?id='+id+'&pageNo='+pageNo;
    	}
	   
    }
    
    
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>