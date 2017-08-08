<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>营业网点</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="format-detection" content="telephone=no" />  
<meta name="format-detection" content="email=no" />  
<link href="<c:url value="/resources/js/m/m_style.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap/plugins/jquery/jquery.js"/>"></script>
<style>
body{
	background-color:#f0f0f0;
}
</style>
</head>
<body onload="refresh()">
 
	<div id="wx-wrap-cms-list">
		<input type="hidden" value="${pageNo }" id="pageNoId">
    	<input type="hidden" value="${token }" id="tokenId">
		<div class="subimt-div">
		</div>
		<div class="btn-div">
			<input type="button" id="btna" value="点击加载更多"/>
		</div>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	<script type="text/javascript">
		function refresh(){
			$("#pageNoId").val("0");
			$("#btna").click();
		}
    	$("#btna").click(function(){
    		var pageNo = parseInt($("#pageNoId").val())+1;
    		var token = $("#tokenId").val();
    		$("#btna").val("正在加载...");
    		$.ajax({
    			url:'${pageContext.request.contextPath}/m/yywdGrid_wx.do?page='+pageNo+'&token='+token,
    			dataType:"json",
    			success:function(jqgridListForm){
    				
    				if (jqgridListForm.formList.length == 0) {
    			    	$("#btna").val("无更多数据");
    			    	$("#btna").unbind("click");
    			    }else{
    			    	for(var i=0; i<jqgridListForm.formList.length; i++){
    			    		var str=jqgridListForm.formList[i].name;
    			    		var name = str;//要展示的字符串
    			    		if(str.length>10){
    			    			name=str.substring(0,10)+"...";
    			    		}
    			    		$('<div class="div-rows" onclick="viewDetail(\''+jqgridListForm.formList[i].id+'\')"><img class="div-rows-img" src="<c:url value="/resources/images/m/icon_water.png"/>"><div class="div-col-label"><h2>'+name+'</h2><span>电话：'+jqgridListForm.formList[i].tel+'</span></div><img class="div-rows-icon" src="<c:url value="/resources/images/m/icon_go.png"/>"></div>').appendTo('.subimt-div');
    	                }
	    				if(jqgridListForm.page<jqgridListForm.total){
							$("#btna").val("点击加载更多");
	    				}else{
	    					$("#btna").val("无更多数据");
	    			    	$("#btna").unbind("click");
	    				}
	    			}
    				$("#pageNoId").val(pageNo);
    			}
    		});
    	});
    	//查看详细页面
    	function viewDetail(id){
    		window.location.href="${pageContext.request.contextPath}/m/ccmsYywd_wx_view.do?id="+id;
    	}
    </script>
</body>
</html>