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
    <meta charset="utf-8" />
    <title>jqGrid Loading Data - Million Rows from a REST service</title>
    
    
    <!-- 本页调用样式 结束 -->
    <style>
        .wraper #main{ margin-top: 40px; }
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
        <h2 class="title_class">群发列表</h2>
		<div class="list_btn_div_class">
        <input type="button" id="addRecordArticle" class="btn btn-default"  onclick="location='${pageContext.request.contextPath}/cus/cMassSend_toAdd.do?materialType=mpnews'" value="新增图文群发">
        <input type="button" id="addRecordText" class="btn btn-default"  onclick="location='${pageContext.request.contextPath}/cus/cMassSend_toAdd.do?materialType=text'" value="新增文本群发">
        <input type="button" id="addRecordPic" class="btn btn-default"  onclick="location='${pageContext.request.contextPath}/cus/cMassSend_toAdd.do?materialType=image'" value="新增图片群发">
        <input type="button" id="previewRecord" class="btn btn-default" onclick="previewRecord()" value="预览"> 
        <input type="button" id="sendRecord" class="btn btn-default" onclick="sendRecord()" value="发送"> 
        <input type="button" id="delRecord" class="btn btn-default" onclick="delRecord()" value="删除">
        </div>
	
         <div  class="search_field_class">
	         <input type="text" id="title" class="form-control" placeholder="请输入标题进行搜索">  
		     <input type="button" value="查询" onclick="search()">
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

<jsp:include page="../cPreview.jsp"/>

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
	       	url: '${pageContext.request.contextPath}/cus/cMassSend_list_json.do',
	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
      		       	{ label: '选择', name: 'id', key: true,hidden:true},
//XXX tmh       	     		{ label: '序号', name: 'xxx', width: 150},
      	     		{ label: '标题', name: 'title', width: 250},
      	     		{ label: '发送时间', name: 'sendDateTime', width: 400},
      	     		{ label: '群发状态', name: 'sendStatus.dicName', width: 400}//TODO tmh test
      	    ],
      	    viewrecords : true,
      	  	sortname : 'createdDatetime',
      	    sortable:true,
      	  	sortorder:'desc',
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
    	
    	var title =escape($('#title').val());
    	jQuery("#datagrid").jqGrid('setGridParam',{
	       	url: '${pageContext.request.contextPath}/cus/cMassSend_list_json.do',
	       	postData:{'title':title},
	       	page:1
	    }).trigger("reloadGrid");
    }
        
    /*预览记录*/
    function previewRecord(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");   //当前选择记录ID值 
    	var pageNo = $('#datagrid').getGridParam('page');		//取当前面页码
    	if(id==null || id==""){
    		bootbox.alert("请选择需要预览记录！");
    		return false;
    	}else if(id.length>1){
    		bootbox.alert("选择了多行，请选择需要预览一条记录！")
    		return false;
    	}else{
    		$('#previewModal').modal('show');
    	} 
    	
    }
    
    /*发送预览记录*/
    function sentPreViewMethod(){
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");
		$.get(
       			"${pageContext.request.contextPath}/cus/cMassSend_preview.do?id="+id+"&OpenID="+$("#OpenIDInPreViewModal").val(),
       			function(msg){
       				if(msg.result==true){
       					alert(msg.warnMsg); 
   		            }else{
   		            	alert(msg.warnMsg); 
   		            	return false;
   		            }
	            	
       			}
    		); 
    }
    
   /*发送记录*/
   function sendRecord(){
    	
    	var id = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(id==null || id==""){
    		bootbox.alert("请选择需要发送记录！");
    	}else{
    		
    		bootbox.confirm('确定需要发送吗？',
    		    function(result){
	    			if(result==true){  //执行发送操作
	    		    	$.get(
	    	           			"${pageContext.request.contextPath}/cus/cMassSend_send.do?id="+id,
	    	           			function(msg){
	    	           				if(msg.result==true){
	    	           					bootbox.alert(msg.warnMsg, function(){
	    	    						    	window.location.href='${pageContext.request.contextPath}/cus/cMassSend_list.do?pageNo='+pageNo;
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
   
    /*删除记录*/
    function delRecord(){
    	
    	var ids = $("#datagrid").jqGrid("getGridParam", "selarrrow");
    	var pageNo = $('#datagrid').getGridParam('page');
    	if(ids==null || ids==""){
    		bootbox.alert("请选择需要删除记录！");
    	}else{
    		
    		bootbox.confirm('确定需要删除吗？',
    		    function(result){
	    			if(result==true){  //执行删除操作
	    		    	$.post(
	    	           			"${pageContext.request.contextPath}/cus/cMassSend_delete.do",
	    	           			{"ids":ids.join(","),"${_csrf.parameterName}":"${_csrf.token}"},
	    	           			function(msg){
	    	           				if(msg.result==true){
	    	           					bootbox.alert(msg.warnMsg, function(){
	    	    						    	window.location.href='${pageContext.request.contextPath}/cus/cMassSend_list.do?pageNo='+pageNo;
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