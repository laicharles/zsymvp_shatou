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
    <div class="user"><img class="avatar" width="25" height="25" src="<c:url value="/resources/js/bootstrap/img/avatar.png"/>" alt="Julio Marquez"><span>${user.loginName }</span><i class="icon-user trigger-user-settings"></i></div>
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
        <h2 class="title_class">自动回复设置</h2>
        <div class="list_btn_div_class">
	        <button type="button" class="btn btn-default" id="addBtn">新增</button>
	        <button type="button" class="btn btn-default" id="editBtn">编辑</button>
	        <button type="button" class="btn btn-default" id="delBtn">删除</button>
        </div>
	     <div class="search_field_class">
			         问题：<input type="text" id="question" class="form-control" placeholder="问题"> 
			         关键词：<input type="text" id="keywordStr" class="form-control" placeholder="关键词">  
			         标签：<select id="tagStr">
			         <option value="">请选择</option>
			         <c:forEach items="${cSelfTagForm}" var="tag" varStatus="status">
			         <option value="${tag.id}">${tag.name}</option>
			         </c:forEach>
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
        	window.location.href="${pageContext.request.contextPath}/cus/cauto_toAdd.do";	
        });
        //编辑
        $("#editBtn").click(function(){
        	editRecord();
        });
        //删除
        $("#delBtn").click(function(){
        	delRecord();
        });
    });
	//表格jqgrid
    $(document).ready(function () {
	    jQuery("#datagrid").jqGrid({
	       	url: '${pageContext.request.contextPath}/cus/cautoGrid.do',
	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
      		       	{ label: '选择', name: 'id', key: true,hidden:true},
      	     		{ label: '问题', name: 'question', width: 330},
      	     		{ label: '关键词', name: 'cautoKeywordStr', width: 180,sortable: false},
      	     		{ label: '标签', name: 'cselfTagStr', width: 180,sortable: false},
      	     		{ label: '回复类型', name: 'sdictionary.dicName',index:"SDictionary.dicName", width: 60, align:"center"},
      	     		{ label: '创建时间', name: 'createTime',formatter:"date",formatoptions: {srcformat:'u',newformat:'Y-m-d H:i:s'}, width: 150}
      	    ],
      	  	rownumbers:true,
      	    viewrecords : true,
	    	height:'100%',
	    	pager : "#pagegrid",
	    	page:"${page}",			//初始化查询页数
	    	rowNum : "${pageSize}",
	    	sortname : 'createTime',
      	    sortable:true,
      	  	sortorder:'desc',
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
    	
    	var question=$('#question').val();
    	var keywordStr = $('#keywordStr').val();
    	var tagStr = $('#tagStr').val();
    	jQuery("#datagrid").jqGrid('setGridParam',{
	       	url: '${pageContext.request.contextPath}/cus/cautoGrid.do',
	       	postData:{'question':question,'keywordStr':keywordStr,'tagStr':tagStr},
	       	page:1
	    }).trigger("reloadGrid");
    }
	
    //编辑记录    
    function editRecord(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==""||id==null){
    		bootbox.alert("请至少选择一条记录！",function(){});
    	}else{
    		location.href='${pageContext.request.contextPath}/cus/cauto_toUpdate.do?id='+id+'&pageNo='+pageNo;
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
     	           url:"${pageContext.request.contextPath}/cus/cauto_delete.do",
     	           dataType:"json",
     	           async:false,
     	           data:{"IDStr":idStr,"pageNo":pageNo,"${_csrf.parameterName}":"${_csrf.token}"},
     	           success:function(msg){
     	        	   if(msg.result==true){
     	        		  bootbox.alert(msg.warnMsg,function(){
     	        			 window.location.href='${pageContext.request.contextPath}/cus/cauto_list.do?pageNo='+pageNo;
     	        		  });
 			           }else{
 			        	  bootbox.alert(msg.warnMsg,function(){
 			        		 window.location.href='${pageContext.request.contextPath}/cus/cauto_list.do?pageNo='+pageNo;
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