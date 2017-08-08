//非空校验
function isNotNull(obj){
	var val =  $.trim(obj.val());
	if(val==""||val==null){
		return false;
	}
	return true;
}

//数字校验
function isNotNumber(obj){
	var number = $.trim(obj.val());
	var regExp = /^\d*$/;
	if(regExp.test(number)){
		return false;
	}
	return true;
}

//长度校验
function chklength(obj,num){
	var str = $(obj).val();
	var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
	if(realLength > num){
		return false;
	}
	return true;
}

//长度校验Cms编辑器
function chklengthCms(checkObj,warnAfterObj,warnID){
	var num = 60000;
	var str = $(checkObj).val();
	var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
	if(str!=""&&realLength > num){
		warnAfterObj.after("<font id="+warnID+" color='red'>*正文: 超出最大字符数，最大只允许输入"+num+"字符（一个中文占两个字节）！</font>");
		return false;
	}
	return true;
}

//function chklengthCms(obj){
//	var num = 6;
//	var str = $(obj).val();
//	var realLength = 0, len = str.length, charCode = -1;
//    for (var i = 0; i < len; i++) {
//        charCode = str.charCodeAt(i);
//        if (charCode >= 0 && charCode <= 128) realLength += 1;
//        else realLength += 2;
//    }
//	if(realLength > num){
//		return false;
//	}
//	return true;
//}

//网址校验
function IsURL(urlString){
	regExp =  /^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/
 	if (urlString.match(regExp)){
	 	return true;
 	}else{
		return false;       
 	}
}

$(document).on("show.bs.modal", ".modal", function(){
	 $("#"+$(this).attr("id")).css("z-index","10000");
	});
$(document).on("hide.bs.modal", ".modal", function(){
	 $("#"+$(this).attr("id")).css("z-index","1");
	});

