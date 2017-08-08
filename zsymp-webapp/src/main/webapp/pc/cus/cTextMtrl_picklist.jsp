<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

    <div class="modal fade" style="width:850px" id="textMtrlModal" tabindex="-1" role="dialog"
		aria-labelledby="textMtrlModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="textMtrlModalLabel">文本素材列表</h4>
				</div>
				<div class="modal-body">
			         <div class="search_field_class">
				        <input type="text" id="titleCriteriaText" class="form-control" placeholder="请输入标题进行搜索">
					    <input type="button" value="查询" onclick="searchText()">
			      	</div>
			      	
			        <table id="datagridText" style="width: 500"></table>
			        <div id="pagegridText"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						data-dismiss="modal" id="btn-choseText">选中</button>
				</div>
			</div>
		</div> 
	</div>

	<script>
	
    $(document).ready(function(){
    	 jQuery("#datagridText").jqGrid({//XXX tmh run this method when click button "素材库" 
 	       	url: '${pageContext.request.contextPath}/cus/cTextMtrl_picklist_json.do',
 	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
       		       	{ label: '选择', name: 'id', key: true,hidden:true},
       	     		{ label: '标题', name: 'title', width: 350},
       	     		{ label: '创建人', name: 'createdBy', width: 160},
       	     		{ label: '创建日期', name: 'createdDateTime', width: 160}
       	    ],
       	    viewrecords : true,//XXX tmh usage
       	  	sortname : 'createdDateTime',
       	    sortable:true,
       	  	sortorder:'desc',
 	    	height:'100%',
 	    	pager : "#pagegridText",
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
    	 
    	 
	     $("#btn-choseText").click(function() {
				
				var id = $("#datagridText").jqGrid("getGridParam", "selarrrow");
				if(id==null || id==""){
    	    		alert("请选择需要素材！");
    	    		return false;
    	    	}else if(id.length>1){
    	    		alert("选择了多行，请选择一条素材！")
    	    		return false;
    	    	}else{
    	    		$.ajax({
		    			url:'${pageContext.request.contextPath}/cus/cTextMtrl_getform.do?id='+id,
		    			dataType:"json",
		    			success:function(form){
				    		setValueTextMtrl(form);
		    			}
		    		});
    	    	}
			});
     }); 
     
   	 //查询方法
     function searchText(){
     	var title =escape($('#titleCriteriaText').val());
     	var mtrlFrom=escape($('#mtrlFromText').val());
     	jQuery("#datagridText").jqGrid('setGridParam',{
 	       	url: '${pageContext.request.contextPath}/cus/cTextMtrl_picklist_json.do',
 	       	postData:{'tag':'','title':title, 'mtrlFrom':'cus'},
 	       	page:1
 	    }).trigger("reloadGrid");
     }
   
   </script>
