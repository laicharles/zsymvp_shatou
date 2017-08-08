<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

	<div class="modal fade" id="previewModal" tabindex="-1" role="dialog"
				aria-labelledby="previewModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="previewModalLabel">预览：</h4>
						</div>
						<div class="modal-body">
							<input type="text" class="form-control" id="OpenIDInPreViewModal"
								placeholder="请输入OpenID"
								style='border-left: 0px; border-top: 0px; border-right: 0px; border-bottom: 0px; width:300px;' />
							<select id="commonPerson" style='width:200px'>
							<option>选择常用人员</option>
							</select>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">返回</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal" id="btn-preView">确认</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>

	<script>
	
	$(document).ready(function(){
    	 
	     $("#btn-preView").click(function() {
	    	    if($("#OpenIDInPreViewModal").val()==null || $("#OpenIDInPreViewModal").val()==""){
	    	    	alert("请填写OPENID！");
	    	    	return false;
	    	    }else{
	    	    	sentPreViewMethod();
	    	    }
	    	    	
				
			});
	     
	     $("#commonPerson").change(function() {
	    	 $("#OpenIDInPreViewModal").val($(this).val());
         });
	     
	     
	     fullCommonPerson();
	     
	     
	     
	     
	     
     }); 
     
	function fullCommonPerson(){
 		$.ajax({
 			url:'${pageContext.request.contextPath}/cus/findcCommonPersonList.do',
 			dataType:"json",
 			async: false,
 			success:function(data){
			for(var CCommonPersonForm in data)
			{  
				$("#commonPerson").append("<option value='"+data[CCommonPersonForm].openID+"'>"+data[CCommonPersonForm].name+"</option>");
			
			} 
 				 
 			}
 		});
 	}
	
	
   </script>
