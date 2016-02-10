package com.naturalclick.fbwallpaper;



import com.naturalclick.fbwallpaper.ui.AppConfig;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

	private static AppConfig config = null;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Context context = getApplicationContext();
		config = new AppConfig(context);
	}
	
	static public AppConfig config() {
		return config;
	}

}
