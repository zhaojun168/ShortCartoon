package com.android.dzj.shortcartoon.utils;

import android.annotation.SuppressLint;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	/** 获取当前时间 */
	@SuppressLint("SimpleDateFormat")
	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前时间
		Date curDate = new Date(System.currentTimeMillis());
		String time = formatter.format(curDate);
		return time;
	}
	
	


}
