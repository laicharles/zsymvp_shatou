<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>综合反馈</title>
	<%@ include file="../base/cssbase.jsp"%>
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
	<style>
		#selectPic {
			position: relative;
		}
	
		#chooseImage {
			position: absolute;
			top: -5px;
			right: 10px;
			width: 35px;
			height: 35px;
			padding: 0;
			border: none;
		}
		
		#chooseImage .glyphicon {
			font-size: 24px;
			line-height: 35px;
		}
		
		.bill-img {
			position: relative;
			display: inline-block;
			width: 30%;
			height: 125px;
			margin: 1%;
			background-color: #000;
			background-size: cover;
			background-position: center;
			border-radius: 3px;
			box-shadow: 0px 0px 8px rgba(0, 0, 0, .5);
		}
		
		.bill-img .delete {
			position: absolute;
			top: -8px;
			right: -9px;
			display: block;
			width: 20px;
			height: 20px;
			line-height: 18px;
			font-size: 1.8rem;
			color: #fff;
			text-align: center;
			text-decoration: none;
			border-radius: 10px;
			background: #f00;
			z-index: 10;
		}

	</style>
</head>
<body id="page-xzhdfk" class="page-color-card-form">
	<div class="container">
		<div class="mytooltip-container"></div>
		
		<form id="feedbackForm" class="color-card-form" data-parsley-trigger="focusout">
			<div class="form-row">
				<div class="input-row">
					<label class="nessesary">标题</label>
					<input type="text" placeholder="请填写标题" id="fTitle" required data-parsley-required-message="标题不能为空">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label class="nessesary">联系人</label>
					<input type="text" placeholder="请填写联系人" id="fPeopleName" required data-parsley-required-message="联系人不能为空">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label class="nessesary">联系电话</label>
					<input type="text" placeholder="请填写联系电话" id="fPeopleTel" data-parsley-tel="true" required data-parsley-required-message="联系电话不能为空">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label>地址</label>
					<input type="text" placeholder="请填写地址" id="fPeopleAddr">
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label class="nessesary">反馈类型</label>
					<select id="feedbackType">
						<c:forEach items="${feedbackMenuList}" var="feedbacklist">
							<option value="${feedbacklist.menuCode }" id="${feedbacklist.id }">${feedbacklist.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-row">
				<div class="input-row">
					<label class="nessesary">反馈内容</label>
					<textarea cols="4" placeholder="请填写内容" id="fContent" required data-parsley-required-message="反馈内容不能为空"></textarea>
				</div>
			</div>
			<div class="form-row">
				<div id="selectPic" class="input-row">
					<label>拍照上传</label>
					<button type="button" id="chooseImage" class="btn btn-lg btn-default">
						<span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
					</button>
				</div>
			</div>
			<div class="div-rows1" id="imgDiv" style="text-align: center;"></div>
			<input type="hidden" id="sMediaIdStr" required data-parsley-required-message="请上传水表照片">
		</form>
		
		<div class="handler-group">
			<div class="cell">
				<button id="saveBtn" class="btn btn-lg btn-base col-xs-10 f-cb">提交</button>
			</div>
			<div class="cell">
				<button id="historyBtn" class="btn btn-lg btn-base col-xs-10 f-cb">历史记录</button>
			</div>
		</div>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	
	<!-- JavaScript -->
	<%@ include file="../base/jsbase.jsp"%>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
	wx.config({
	    debug: false, // 开启调试模式，调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: '${wxJsBean.appId}', // 必填，公众号的唯一标识
	    timestamp: '${wxJsBean.timestamp}', // 必填，生成签名的时间戳
	    nonceStr: '${wxJsBean.nonceStr}', // 必填，生成签名的随机串
	    signature: '${wxJsBean.signature}',// 必填，签名，见附录1
	    jsApiList: [
					'checkJsApi',
					'chooseImage', 
					'previewImage',
					'uploadImage',
					'downloadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

	wx.ready(function () {
		// 图片
		var images = {
				localId: [],
				serverId: []
		};
		
		/* 拍照或从手机相册中选图接口 */
		document.querySelector('#chooseImage').onclick = function () {
			wx.chooseImage({
				count: 1, // 默认9
		    	sizeType: ['compressed'], // 可以指定是原图还是压缩图，默认二者都有
		    	sourceType: ['camera'], // 可以指定来源是相册还是相机，默认二者都有
				success: function (res) {
					var id = generateMixed(9);
					images.localId = res.localIds;
					$('#imgDiv').append('<div class="bill-img" style="background-image: url(' + res.localIds + ');"><a id="'+id+'" value="" href="'+res.localIds+'" class="delete">×</a></div>');
					funUpload(id);
			  	}
			});
		};
		
		// 删除上传图片并从数组中去掉
		$('#imgDiv').on('click', '.bill-img .delete', function () {
			var thisVlaue = $(this).val();
			deleteElement(images.serverId,thisVlaue);
			$('#sMediaIdStr').val(images.serverId);
			$(this).closest('.bill-img').remove();
		});
		
		// 删除数组的指定元素
		function deleteElement(array,element){
			for(var i=0;i<array.length;i++){
			    if(array[i]==element){
			    	array.splice(i,1);//从下标为i的元素开始，连续删除1个元素
			        i--;//因为删除下标为i的元素后，该位置又被新的元素所占据，所以要重新检测该位置
			    }
			}
		}

		/* 上传图片接口 */
		function funUpload(id) {
			if (images.localId.length == 0) {
				alert('无上传图片');
				return;
			}
			var i = 0, length = images.localId.length;
			function upload(id) {
			  wx.uploadImage({
				localId: images.localId[i],
				success: function (res) {
					$('#'+id).val(res.serverId);
					i++;
					images.serverId.push(res.serverId);
					$('#sMediaIdStr').val(images.serverId);
					if (i < length) {
						upload(id);
				  	}
				},
				fail: function (res) {
					alert(JSON.stringify(res));
				}
			  });
			}
			upload(id);
		}
	});
	
	var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];

	function generateMixed(n) {
	     var res = "";
	     for(var i = 0; i < n ; i ++) {
	         var id = Math.ceil(Math.random()*35);
	         res += chars[id];
	     }
	     return res;
	}
	
	// 历史记录
	$('#historyBtn').click(function () {
		window.location.href = '${pageContext.request.contextPath}/m/feedbackList.do?fToken=${wxJsBean.token}&fMenuId=${fMenuId}&fOpenId=${fOpenId}';	
	});
	
	// 保存 
	$('#saveBtn').click(function () {
		$('#feedbackForm').parsley().validate();
	});
	
	$('#feedbackForm').parsley().on('form:success', function () {
		/* var telReg = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
		var fPeopleTel = $('#fPeopleTel').val();
		if(fPeopleTel.length !== 0 && !telReg.test(fPeopleTel)){
			$.MyToolTip.popupMessage('电话格式不正确！', 'danger');
			return;
		} */
		$('#saveBtn').attr('disabled', true);
		$.ajax({
			url: '${pageContext.request.getContextPath()}/m/saveFeedback.do',
			dataType: 'json',
			async: true,
			data: {
				'fTitle' : $('#fTitle').val().trim(),
				'fContent' : $('#fContent').val().trim(),
				'fPeopleName' : $('#fPeopleName').val().trim(),
				'fPeopleTel' : $('#fPeopleTel').val().trim(),
				'fPeopleAddr' : $('#fPeopleAddr').val().trim(),
				'fToken' : '${wxJsBean.token}',
				'fOpenId' : '${fOpenId}',
				'fMenuId' : $('#feedbackType option:checked').attr('id'),    //被选中的反馈类型的menuId 
				'sAccessToken' : '${wxJsBean.accessToken }',
				'sMediaIdStr' : $('#sMediaIdStr').val().trim(),
				'fMenuCode' : $('#feedbackType').val()        //被选中的反馈类型的menuCode 
			},
			type: 'POST',   // 请求方式
		    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		    beforeSend: function () {
		        // 请求前的处理
		    },
		    success: function (msg) {
		        // 请求成功时处理
		        if (msg.msg == 'ok') {
		        	$.MyToolTip.popupMessage('提交成功，感谢反馈，届时会有专人给予回复，你可在历史记录中查询！', 'success');
		        	setTimeout(function () {
		        		window.location.reload();
		        	}, 1000);
		        } else if (msg.msg == 'wrong') {
		        	$.MyToolTip.popupMessage('提交失败!', 'danger');
		        }
		    }, 
		    complete: function () {
		        // 请求完成的处理
		    	$('#saveBtn').attr('disabled', false);
		    },
		    error: function (s) {
		    	var old = s.error;
		        var errHeader = s.errorHeader || 'jumppay';
		        var errMsg = s.getResponseHeader(errHeader);
		        window.location = errMsg;
		        // 请求出错处理
		    }
		});
	});
		
	</script>
</body>
</html>
