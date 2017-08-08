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
    <!-- jqgrid  The link to the CSS that the grid needs -->
    <link rel="stylesheet" type="text/css" media="screen" href="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/css/ui.jqgrid.css"/>" />
    <link href="<c:url value="/resources/js/cms/cms.css"/>" rel="stylesheet">
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
    <div class="container-fluid">
        <!--主体内容-->
        <h2 class="title_class">营业网点</h2>
        <div class="list_btn_div_class">
	        <button type="button" class="btn btn-default" id="addBtn">新增</button>
	        <button type="button" class="btn btn-default" id="editBtn">编辑</button>
	        <button type="button" class="btn btn-default" id="delBtn">删除</button>
        </div>
<!--        	<div class="input-group"> -->
<%--        		<form:form action="${pageContext.request.contextPath}/cus/ccmsYywd_list.do?pageNo=1" id="searchForm"> --%>
<%--        			<input type="text" placeholder="请输入营业网点名称" name="searchField" value="${searchField }"> --%>
<!-- 	        	<button type="button" style="margin-top:-10px" class="btn btn-default" id="searchBtn">搜索</button> -->
<%--        		</form:form> --%>
<!--        	</div> -->
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

<!-- This is the Javascript file of jqGrid -->   
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/jquery.jqGrid.min.js"/>"></script>
<!-- This is the localization file of the grid controlling messages, labels, etc.
<!-- We support more than 40 localizations -->
<script type="text/ecmascript" src="<c:url value="/resources/js/bootstrap/plugins/jquery.jqgrid/js/i18n/grid.locale-cn.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/plugins/bootstrap.bootbox/4.4.0/bootbox.js"/>"></script>
<!-- 本页调用JS 开始 -->
<script>
    $(function() {
        SideBar.init({
            shortenOnClickOutside: false
        });
        //新增
        $("#addBtn").click(function(){
        	window.location.href="${pageContext.request.contextPath}/cus/ccmsYywd_toAdd.do";	
        });
        //编辑
        $("#editBtn").click(function(){
        	editRecord();
        });
        //删除
        $("#delBtn").click(function(){
        	delRecord();
        });
      	//搜索
        $("#searchBtn").click(function(){
        	$("#searchForm").submit();
        });
    });
	//表格jqgrid
    $(document).ready(function () {
	    jQuery("#datagrid").jqGrid({
	       	url: '${pageContext.request.contextPath}/cus/yywdGrid.do?searchField=${searchField}',
	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
      		       	{ label: '选择', name: 'id', key: true,hidden:true},
      	     		{ label: '名称', name: 'name', width: 200},
      	     		{ label: '电话', name: 'tel', width: 200},
      	     		{ label: '地址', name: 'addr', width: 400}
      	    ],
      	  	rownumbers:true,
      	    viewrecords : true,
	    	height:'100%',
	    	pager : "#pagegrid",
	    	page:"${page}",			//初始化查询页数
	    	rowNum : "${pageSize}",
	    	sortname : 'name',
      	    sortable:true,
      	  	sortorder:'asc',
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
    //编辑记录    
    function editRecord(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==""||id==null){
    		bootbox.alert("请至少选择一条记录！",function(){});
    	}else if(id.length>1){
    		bootbox.alert("只能选择一条记录进行编辑！")
    		return false;
    	}else{
    		location.href='${pageContext.request.contextPath}/cus/ccmsYywd_toUpdate.do?id='+id+'&pageNo='+pageNo;
    	}
    }
    //删除记录
    function delRecord(){
    	var idArr = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var idStr = "";
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(idArr==""||idArr==null){
    		bootbox.alert("请至少选择一条记录！",function(){});
			return;
		}
    	for(var i=0;i<idArr.length ;i++){
    		idStr = idStr + idArr[i];
    		if(i<idArr.length-1){
    			idStr = idStr + ";";
    		}
    	}
    	bootbox.confirm("确定删除吗？",function(result){
			if(result==true){
				$.ajax({
     	           type:"post",
     	           url:"${pageContext.request.contextPath}/cus/ccmsYywd_delete.do",
     	           dataType:"json",
     	           async:false,
     	           data:{"idStr":idStr,"pageNo":pageNo,"${_csrf.parameterName}":"${_csrf.token}"},
     	           success:function(resultList){
     	        	   if(resultList[0]=="success"){
     	        		  bootbox.alert("删除成功！",function(){
     	        			 window.location.href='${pageContext.request.contextPath}/cus/ccmsYywd_list.do?pageNo='+resultList[1];
     	        		  });
 			           }else{
 			        	  bootbox.alert("以下标题的营业网点删除失败！"+resultList[0],function(){
      	        			 window.location.href='${pageContext.request.contextPath}/cus/ccmsYywd_list.do?pageNo='+resultList[1];
      	        		  });
 			           }
     	           }
    	        });
			}
    	});
    }
   </script>
<!-- 本页调用JS 结束 -->
</body>
</html>