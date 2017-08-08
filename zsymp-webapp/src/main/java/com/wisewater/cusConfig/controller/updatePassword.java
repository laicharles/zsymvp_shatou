package com.wisewater.cusConfig.controller;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class updatePassword {
public static void main(String[] args) {
	String password="1"; String loginName="admin";
	Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	System.out.println("密碼-----》"+encoder.encodePassword(password,loginName));
}
}
