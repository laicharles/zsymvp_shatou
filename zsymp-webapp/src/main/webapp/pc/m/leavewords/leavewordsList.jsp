<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include  file="../../../base/base.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<title>历史记录</title>
	<%@ include file="../base/cssbase.jsp"%>
	<link href="<c:url value="/resources/css/footer.css"/>" rel="stylesheet">
</head>
<body class="page-base-list">
	<div class="container">
		<ul id="list" class="base-list"></ul>
		<button id="loadMore" type="button">点击加载更多</button>
	</div>
	<%-- <%@ include file="../base/footer.jsp"%> --%>
	<!-- JavaScript -->
	<%@ include file="../base/jsbase.jsp"%>
	<script>
		var btn_status = 0; // 0: 没有数据, 1: 点击加载更多, 2: 加载中..., 3: 没有更多数据
		// page 代表页数，rows 代表每次加载数量
		var page = 1, rows = 10;
		var $list = $('#list');
		var $loadMore = $('#loadMore');
		// 总条数
		var totalSize = ${totalSize};

		$(document).ready(function () {
			loadData();
		});
		
		/**
		 * 1) 进入页面，请求获取数据
		 * 2) 如果没有数据，button 状态改为“没有数据”并且解绑事件
		 * 3) 如果有数据，模拟点击按钮，加载数据（存疑：这里相当于一进入页面就请求了两次，不科学）
		 */
		function loadData() {
			if (totalSize == 0 || totalSize == undefined) {
				setBtnStatus(0); // 没有数据
				$loadMore.unbind('click');
				return;
			} else {
				$loadMore.click();
			}
		}

		$(document).on('scroll', function () {
			var wScrollY = window.scrollY; // 当前滚动条位置
			var wInnerH = window.innerHeight; // 设备窗口的高度（不会变）
			var bScrollH = document.body.scrollHeight; // 滚动条总高度
			if (wScrollY + wInnerH >= bScrollH) {
				$loadMore.click();
			}
		});

		/**
		 * 1) 读不出数据说明数据已全部读出，button 状态改为“没有更多数据”，解绑事件
		 * 2) 如果可以读出数据，则拼接出 <li>，加入到 <ul> 中
		 * 3) nowrow 增加
		 * 4) button 状态变为“点击加载更多”
		 */
		$loadMore.on('click', function () {
			if (btn_status == 2) { // 避免网速慢的时候不小心多加载几次
				return;
			}
			var $this = $(this);
			setBtnStatus(2); // 加载中...
			$.ajax({
				url: '${pageContext.request.getContextPath()}/m/leavewordsListJson.do',
				dataType: 'json',
				async: true,
				data: {
					'token': '${token}',
					'openId': '${openID}',
					'page': page,
					'rows': rows
				},
				type: 'POST', // 请求方式
			    contentType: 'application/x-www-form-urlencoded; charset=utf-8',
			    beforeSend: function () {
			        // 请求前的处理
			    },
			    success: function (data) {
					if (data == null || data.length == 0 || data == undefined) {
						setBtnStatus(3); // 没有更多数据
						$this.unbind('click');
					} else {
						var html = '';
						var titleText = '';
						
						/* 数据结构不一样，这里的做法就会不一样 */
						for (var i = 0, len = data.length; i < len; i++) {
							if(data[i].leavewords ==='保修管理'){
								titleText = '保修';
							}else{
								titleText = '咨询';
							}
							
							
							if (data[i].isReply === '已回复') {
								html += '<li onclick="viewDetail(\'' + data[i].id + '\',\'${token}\')" class="answered">'
									+		'<p>' + data[i].feedbackOption + titleText + '<br>'
									+			'<span class="time">' + data[i].createTime + '</span>'
									+		'</p>'
									+		'<div class="arrow-wrapper">'
									+			'<img src="../resources/images/m/icon_go.png">'
									+		'</div>'
									+	'</li>';
							} else {
								html += '<li onclick="viewDetail(\'' + data[i].id + '\',\'${token}\')" id="viewBtn">'
									+		'<p>' + data[i].feedbackOption + titleText + '<br>'
									+			'<span class="time">' + data[i].createTime + '</span>'
									+		'</p>'
									+		'<div class="arrow-wrapper">'
									+			'<img src="../resources/images/m/icon_go.png">'
									+		'</div>'
									+	'</li>';
							}
						}
						
						$list.append(html);
						if ((page - 1) * rows + data.length == totalSize) {
							setBtnStatus(3); // 没有更多数据
							$loadMore.unbind('click');
						} else {
							page += 1;
							setBtnStatus(1); // 点击加载更多
						}
					}
			    }, 
			    complete: function () {
			        // 请求完成的处理
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

		/**
		 * loadMore 修改按钮状态和文字
		 *
		 * @param  {Number}  status  [0: 没有数据, 1: 点击加载更多, 2: 加载中..., 3: 没有更多数据]
		 * @return  无
		 */
		 function setBtnStatus(status) {
		 	switch (status) {
		 		case 0: 
			 		btn_status = 0;
			 		$loadMore.text('没有数据');
			 		break;
		 		case 1:
			 		btn_status = 1;
			 		$loadMore.text('点击加载更多');
			 		break;
		 		case 2:
			 		btn_status = 2;
			 		$loadMore.text('加载中...');
			 		break;
		 		case 3:
			 		btn_status = 3;
			 		$loadMore.text('没有更多数据');
		 	}
		 }
		 
		// 查看详细
		function viewDetail(id, token) {
			window.location.href = '${pageContext.request.contextPath}/m/leavewordsView.do?id=' + id+'&token='+token;	
		}
		
	</script>
</body>
</html>
