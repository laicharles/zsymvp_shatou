package com.wisewater.wechatpublic.qrcode;

import com.wisewater.wechatpublic.qrcode.Qrcode.Action_info;
import com.wisewater.wechatpublic.qrcode.Qrcode.Action_info.Scene;

public class Main {

	public static void main(String[] args) {
		Qrcode qrcode = new Qrcode();
		Scene scene = new Scene();
		scene.setScene_id(101);
		Action_info action_info= new Action_info();
		action_info.setScene(scene);
		qrcode.setAction_info(action_info);
		String access_token = "2_5A6rvTJ5YrzcLeYqIdlzljogIUYMrH2Y_oA-HQpq3L7NQZCSzeVfVjuo17kMN-aUJd26wad4_Gv_OOxOzh22zr7ZLhzQKNdEwnVxuxxpI52S2iTxf4BJgjn52vMQaWRVQfCGAJNI";
		System.out.println(QrcodeUtil.createQrcode(qrcode, access_token));
		
//		QrcodeTemp qrcodeTemp = new QrcodeTemp();
//		Scene sceneTemp = new Scene();
//		sceneTemp.setScene_id(123);
//		Action_info action_info_temp= new Action_info();
//		action_info_temp.setScene(sceneTemp);
//		qrcodeTemp.setAction_info(action_info_temp);
//		System.out.println(QrcodeUtil.createQrcodeTemp(qrcodeTemp, access_token));
	}
	
	
}
