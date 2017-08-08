package com.wisewater.wechatpublic.pojo;

public class Location {
	public String name;
	public String address;
	/**
	 * 纬度
	 */
	public double LocationX; 
	/**
	 * 经度
	 */
	public double LocationY;
	/**
	 * 距离
	 */
	public double l;

	private static final double EARTH_RADIUS = 6378137;
	
	/**
	 * 
	 * @param lng1 经度
	 * @param lat1 纬度
	 * @return
	 */
	public double getDistance(String lng1, String lat1){
		l = GetDistance(Double.parseDouble(lng1),Double.parseDouble(lat1), LocationY,
				LocationX);
		return l;
	}
	
	private double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为公里
	 * 
	 * @param lng1 经度
	 * @param lat1 纬度
	 * @param lng2 经度
	 * @param lat2 纬度
	 * @return
	 */
	private double GetDistance(double lng1, double lat1, double lng2,
			double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s/1000;
	}

	public Location(String name, String address, double locationX,
			double locationY) {
		super();
		this.name = name;
		this.address = address;
		LocationX = locationX;
		LocationY = locationY;
	}
	
	public Location(){}

}
