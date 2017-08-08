function checkForm(formId) {
			pass = true;

			 $("#"+formId).children().filter("div").children().filter("input,textarea").each(
				function() {
					if (!$(this).prop("disabled") && $(this).attr("type")!="file" && $(this).attr("type")!="button" && $(this).attr("type")!="hidden") {
						fontId = this.id.replace("\.","");
						text = $(this).parent().children().filter("label").text();
						if(text.indexOf("*")>=0)
						{
							if($.trim(this.value) == '')
							{   
								if($("#"+fontId+"color").length>0)
								{
									$("#"+fontId+"color").remove();
								}
								$(this).after("<font id="+fontId+"color color='red'>"+text+"是必填项！</font>");
								this.focus();
								pass = false;
							}
							else
							{
								if($("#"+fontId+"color").length>0)
								{
									$("#"+fontId+"color").remove();
								}
							}
						}
						else
						{
							if($(this).attr("class").indexOf("pageUrl_class")>=0)
							{ 
								if($.trim(this.value) != '')
								{
									 reg=/^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;   
									 
									 if(!this.value.match(reg))
									 {
										 if($("#"+fontId+"color").length>0)
											{
												$("#"+fontId+"color").remove();
											}
											$(this).after("<font id="+fontId+"color color='red'>"+text+"请输入正确的inernet地址！</font>");
											
											this.focus();
											pass = false;
									 }
									 else
										{
										 if($("#"+fontId+"color").length>0)
											{
												$("#"+fontId+"color").remove();
											}
										}
								}
								else
								{
									if($("#"+fontId+"color").length>0)
									{
										$("#"+fontId+"color").remove();
									}
								}
							}
						}
						
						//验证长度
						if(pass && $(this).attr("size")!=undefined && $.trim(this.value) != '')
						{
							
							if(this.value.length > parseInt($(this).attr("size")))
							{
								if($("#"+fontId+"color").length>0)
								{
									$("#"+fontId+"color").remove();
								}
								$(this).after("<font id="+fontId+"color color='red'>输入长度不能超过"+$(this).attr("size")+"个字符!</font>");
								this.focus();
								pass = false;
							}
							else
							{
								if($("#"+fontId+"color").length>0)
								{
									$("#"+fontId+"color").remove();
								}
							}
							
						}
						
					}
				}); 
			 //图文外链与正文二选一
			 if (!$("#pageUrl").prop("disabled") && !$("#KindEditorId").prop("disabled"))
			if($.trim($("#pageUrl").val())=="" && $.trim(zsy_editor.html())=="")
			{
				pageUrlId = "pageUrl";
				pageUrlText = $("#pageUrl").parent().children().filter("label").text().replace("：","");
				KindEditorIdText = $("#KindEditorId").parent().children().filter("label").text().replace("：","");
				if($("#"+pageUrlId+"color").length>0)
				{
					$("#"+pageUrlId+"color").remove();
				}
				$("#pageUrl").after("<font id="+pageUrlId+"color color='red'>"+pageUrlText+"和"+KindEditorIdText+"必填其中一项</font>");
				
				pass = false;
			}
			else
			{
				if($("#pageUrlcolor").length>0)
				{
					reg=/^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;   
					 if($("#pageUrl").val().match(reg))
						$("#pageUrlcolor").remove();
				}
				
			}
			 
			//验证图片
			if (!$("#picName").prop("disabled"))
			{
				if($('#picName').val()=="" && $('#picUrl').val()=="" && ($("#imgShow").attr("src")==undefined || $("#imgShow").attr("src")==""))
				{
					if($("#picUrlcolor").length>0)
					{
						$("#picUrlcolor").remove();
					}
					$("#picUrl").after("<font id='picUrlcolor' color='red'>必须上传或从素材库里选择一张图文图片</font>");
					
					pass = false;
				}
				else
				{
					if($("#picUrlcolor").length>0)
					{
						$("#picUrlcolor").remove();
					}
				}
			}
			
			//验证标签
			if($('div#tagContent span').length<=0)
			{
				if($("#tagContentcolor").length>0)
				{
					$("#tagContentcolor").remove();
				}
				$("#tagContent").after("<font id='tagContentcolor' color='red'>请添加标签</font>");
				
				pass = false;
			}
			else
			{
				if($("#tagContentcolor").length>0)
				{
					$("#tagContentcolor").remove();
				}
			}
			
			//验证关键字
			if($('div#kwContent span').length<=0)
			{
				if($("#kwContentcolor").length>0)
				{
					$("#kwContentcolor").remove();
				}
				$("#kwContent").after("<font id='kwContentcolor' color='red'>请添加关键词</font>");
				
				pass = false;
			}
			else
			{
				if($("#kwContentcolor").length>0)
				{
					$("#kwContentcolor").remove();
				}
			}
			 
			return pass;
		}