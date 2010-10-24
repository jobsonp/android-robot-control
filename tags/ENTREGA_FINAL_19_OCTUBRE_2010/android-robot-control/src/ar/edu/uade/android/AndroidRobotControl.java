package ar.edu.uade.android;

import android.app.Application;
import android.content.Context;

public class AndroidRobotControl extends Application {
	
	private static Context app;
	
	@Override
	public void onCreate( ) {
		
		super.onCreate();
		
		AndroidRobotControl.app = this;
		
	}
	
	public static Context getApp( ) {
		
		return AndroidRobotControl.app;
		
	}

}
