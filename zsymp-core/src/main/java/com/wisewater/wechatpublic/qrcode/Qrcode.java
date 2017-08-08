package com.wisewater.wechatpublic.qrcode;

public class Qrcode {

	private String action_name = "QR_LIMIT_SCENE";

	private Action_info action_info;

	public String getAction_name() {
		return action_name;
	}

	public Action_info getAction_info() {
		return action_info;
	}

	public void setAction_info(Action_info action_info) {
		this.action_info = action_info;
	}

	public static class Action_info {
		private Scene scene;

		public void setScene(Scene scene) {
			this.scene = scene;
		}

		public Scene getScene() {
			return scene;
		}

		public static class Scene {

			private int scene_id;

			public int getScene_id() {
				return scene_id;
			}

			public void setScene_id(int scene_id) {
				this.scene_id = scene_id;
			}

		}
	}
}
