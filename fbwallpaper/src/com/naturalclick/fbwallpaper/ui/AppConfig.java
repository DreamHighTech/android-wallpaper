package com.naturalclick.fbwallpaper.ui;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

/**
 * 
 * @author ken
 *
 */
public class AppConfig {
	public static boolean DEBUG_MODE = false;

	private static final String CONFIG_NAME = "FBWallPaper";	
	private SharedPreferences mPreferences;		
	public final static String SD_PATH = Environment.getExternalStorageDirectory().getPath();	
	
	public AppConfig(Context context) {
		mPreferences = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
	}
	
	public boolean getBoolean(String key, boolean defaultValue) {
		return mPreferences.getBoolean(key, defaultValue);
	}
	public void setBoolean(String key, boolean value) {
		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putBoolean(key, value);		
		editor.commit();
	}
	
	public String getString(String key, String defaultValue) {
		return mPreferences.getString(key, defaultValue);
	}
	public void setString(String key, String value) {
		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putString(key, value);		
		editor.commit();
	}
	
	public int getInt(String key, int defaultValue) {
		return mPreferences.getInt(key, defaultValue);
	}
	
	
	public void setInt(String key, int value) {
		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public float getFloat(String key, float defaultValue) {
		return mPreferences.getFloat(key, defaultValue);
	}
	
	
	public void setFloat(String key, float value) {
		SharedPreferences.Editor editor = mPreferences.edit();
		editor.putFloat(key, value);		
		editor.commit();
	}	
}
