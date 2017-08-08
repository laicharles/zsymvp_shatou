<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

    <div class="modal fade" style="width:600px" id="picMtrlModal" tabindex="-1" role="dialog"
		aria-labelledby="picMtrlModalLabel" aria-hidden="true" >
		  <div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="picMtrlModalLabel">图片素材列表</h4>
				</div>
				<div class="modal-body">
			         <div class="search_field_class">
				        <input type="text" id="titleCriteriaPic" class="form-control" placeholder="请输入标题进行搜索">
					    <input type="button" value="查询" onclick="searchPic()">
			      	</div>
			      	
			        <table id="datagridPic" style="width: 500"></table>
			        <div id="pagegridPic"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						data-dismiss="modal" id="btn-chosePic">选中</button>
				</div>
			</div>
		</div> 
	</div>


	<script>
	
	var finalPicPath="${cusPicPathPickList}";
	
    $(document).ready(function(){
    	 jQuery("#datagridPic").jqGrid({//XXX tmh run this method when click button "素材库" 
 	       	url: '${pageContext.request.contextPath}/cus/cPicMtrl_picklist_json.do',
 	       	mtype: "GET",
            contentType: "application/json",
            datatype: "json",
            colModel:[
       		       	{ label: '选择', name: 'id', key: true,hidden:true},
       	     		{ label: '标题', name: 'title', width: 100},
       	     		{ label: '图片', name: 'picName', width: 200, align:"center",formatter:currencyFmatter}, 
       	     		{ label: '创建人', name: 'createdBy', width: 100},
       	     		{ label: '创建日期', name: 'createdDateTime', width: 100}
       	    ],
       	    viewrecords : true,//XXX tmh usage
       	  	sortname : 'createdDateTime',
       	    sortable:true,
       	  	sortorder:'desc',
 	    	height:'100%',
 	    	pager : "#pagegridPic",
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
    	 
    	 
    	 function currencyFmatter(cellvalue, options, rowdata){
   	        if (cellvalue != "0")
   	            return '<img class="alarmimg" src="${pageContext.request.contextPath}'+finalPicPath + cellvalue + '" style="width:100px;height:100px;"/>';
   	        else 
   	            return '';
   	    }
    	 
    	 
	     $("#btn-chosePic").click(function() {
				
				var id = $("#datagridPic").jqGrid("getGridParam", "selarrrow");
				if(id==null || id==""){
    	    		alert("请选择需要素材！");
    	    		return false;
    	    	}else if(id.length>1){
    	    		alert("选择了多行，请选择一条素材！")
    	    		return false;
    	    	}else{
    	    		$.ajax({
		    			url:'${pageContext.request.contextPath}/cus/cPicMtrl_getform.do?id='+id,
		    			dataType:"json",
		    			success:function(form){
		    				setValuePicMtrl(form);
		    			}
		    		});
    	    	}
			});
     }); 
     
   	 //查询方法
     function searchPic(){
     	
     	var title =escape($('#titleCriteriaPic').val());
     	var mtrlFrom=escape($('#mtrlFromPic').val());
     	jQuery("#datagridPic").jqGrid('setGridParam',{
 	       	url: '${pageContext.request.contextPath}/cus/cPicMtrl_picklist_json.do',
 	       	postData:{'tag':'','title':title, 'mtrlFrom':'cus'},
 	       	page:1
 	    }).trigger("reloadGrid");
     }
   
   </script>
