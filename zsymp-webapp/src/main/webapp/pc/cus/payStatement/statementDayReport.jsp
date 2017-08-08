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
        <h2 class="title_class">微信日统计明细</h2>
       
        <div class="search_field_class">
		        日期：<input type="text" class="input-txt" id="date" name="date" readonly="readonly"  style="height: 25px;"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<select id="userT">
				   <option value="0">全部</option>
				   <option value="1">小户</option>
				   <option value="2">大户</option>
				</select>
		        <input type="button" value="查询" class="btn btn-default mar" onclick="search()">
      		    <input type="button" id="download" class="btn btn-default mar"  onclick="download()" value="下载">
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
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
    });

    /*加载列表数据*/
    $(document).ready(function () {
	    jQuery("#datagrid").jqGrid({
	       	url: '${pageContext.request.contextPath}/cus/statementDayReport.do',
	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
      		       	{ label: '用户号', name: 'userno', width: 100},
      		        { label: '抄表月份', name: 'cmonth', width: 100},
      		        { label: '水量', name: 'waternum', width: 100},
      		        { label: '水费', name: 'waterfee', width: 100},
      		        { label: '水费违约金', name: 'penalty', width: 100},
      	     		{ label: '污水费', name: 'sewagefee', width: 100},
      	     		{ label: '污水费滞纳金', name: 'latefee', width: 100},
      	     		{ label: '垃圾费', name: 'garbagefee', width: 100},
      	     		{ label: '总费用', name: 'totalfee', width: 100},
      	     		{ label: '微信订单号', name: 'orderNo', width: 250},
      	     		{ label: '订单状态', name: 'orderSatus', width: 100}
      	    ],
      	    viewrecords : true,
	    	height:'100%',
	    	pager : "#pagegrid",
	    	recordtext : ' 共 {2} 条',
	    	page:"1",			//初始化查询页数
	    	jsonReader : {
	    		root: "formList",    // json中代表实际模型数据的入口
	    		records: "records",   // json中代表数据行总数的数据
	    		page: "page",    // json中代表当前页码的数据
	    		total: "total",   // json中代表页码总数的数据
	            repeatitems : false
	           }
	    });
	    
	    
	    //disable sorting of grid
    	var grid = $("#datagrid");
    	//get all column names
    	var columnNames = grid.jqGrid('getGridParam','colModel');
   		//iterate through each and disable
    	for (i = 0; i < columnNames.length; i++) {
    	   grid.setColProp(columnNames[i].name, { sortable: false });
    	}
    });
    
    //查询方法
    function search(){
    	var date =$('#date').val();
    	var userType =$('#userT').val();
    	jQuery("#datagrid").jqGrid('setGridParam',{
	       	url: '${pageContext.request.contextPath}/cus/statementDayReport.do',
	       	postData:{'date':date,'userType':userType},
	       	page:1
	    }).trigger("reloadGrid");
    }
    
        
    /*下载*/
    function download(){
    	var date =$('#date').val();
    	var userType =$('#userT').val();
    	location.href='${pageContext.request.contextPath}/cus/statementDayReportDownload.do?date='+date+'&userType='+userType;
    }
    
   </script>
<!-- 本页调用JS 结束 -->
</body>

</html>