<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"  %>

    <div class="modal fade" style="width:600px" id="articleMtrlModal" tabindex="-1" role="dialog"
		aria-labelledby="articleMtrlModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="articleMtrlModalLabel">图文素材列表</h4>
				</div>
				<div class="modal-body">
			         <div class="search_field_class">
				        <input type="text" id="titleCriteriaArticle" class="form-control" placeholder="请输入标题进行搜索">
					    <input type="button" value="查询" onclick="searchArticle()">
			      	</div>
			      	
			        <table id="datagridArticle" style="width: 500"></table>
			        <div id="pagegridArticle"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						data-dismiss="modal" id="btn-choseArticle">选中</button>
				</div>
			</div>
		</div> 
	</div>


	<script>
	
	var finalArticlePicPath="${cusPicPathPickList}";
	var list=new Array();
	var string = "";
    $(document).ready(function(){
    	 jQuery("#datagridArticle").jqGrid({//XXX tmh run this method when click button "素材库" 
 	       	url: '${pageContext.request.contextPath}/cus/cArticleMtrl_picklist_json.do',
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
 	    	pager : "#pagegridArticle",
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
 				if(status==true){
 				    string += rowid+",";
 				}else if(status==false){
 					string=string.replace(rowid+",","");
 				} 
 			}
 	    });
      	 
    	 function currencyFmatter(cellvalue, options, rowdata){
   	        if (cellvalue != "0")
   	            return '<img class="alarmimg" src="${pageContext.request.contextPath}'+finalArticlePicPath + cellvalue + '" style="width:100px;height:100px;"/>';
   	        else 
   	            return '';
   	    }
    	     	 
	    $("#btn-choseArticle").click(function() {	
	    	
              	list=string.split(",");
	    	    for(var i=0;i<list.length-1;i++){
	    	    	var id;
	    	    	id=list[i];   	    		
    	    		$.ajax({
		    			url:'${pageContext.request.contextPath}/cus/cArticleMtrl_getform.do?id='+id,
		    			async: false,
		    			dataType:"json",
		    			success:function(form){
		    				setValueArticleMtrl(form);
		    			} 
		    		});
	             }
	    	    if(id==null || id==""){
		    		alert("请选择需要素材！");
		    		return false;
		    	}
	    	    string = "";
			});
        }); 
     
   	 //查询方法
     function searchArticle(){
     	
     	var title =escape($('#titleCriteriaArticle').val());
     	var mtrlFrom=escape($('#mtrlFromArticle').val());
     	jQuery("#datagridArticle").jqGrid('setGridParam',{
 	       	url: '${pageContext.request.contextPath}/cus/cArticleMtrl_picklist_json.do',
 	       	postData:{'tag':'','title':title, 'mtrlFrom':'cus'},
 	       	page:1
 	    }).trigger("reloadGrid");
     }
   
     function func(){
     	$("rowid").trigger("reloadGrid");
     }
   </script>
