

package kr.ac.ssu.yoobh17.Omok.framework.impl;


import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import kr.ac.ssu.yoobh17.Omok.framework.Input;


public class AndroidInput implements Input {
	
	TouchHandler touchHandler;
	
	public AndroidInput( Context context, View view, float scaleX, float scaleY ){
		
		if( 5 > Integer.parseInt( VERSION.SDK ) ){
			
			touchHandler = new SingleTouchHandler( view, scaleX, scaleY );
			
		}
		else{
			
			touchHandler = new MultiTouchHandler( view, scaleX, scaleY );
			
		}
		
	}

	@Override
	public boolean isTouchDown( int pointer ) {

		return touchHandler.isTouchDown( pointer );
	}

	@Override
	public int getTouchX( int pointer ) {

		return touchHandler.getTouchX( pointer );
	}

	@Override
	public int getTouchY( int pointer ) {

		return touchHandler.getTouchY( pointer );
	}

	@Override
	public List<TouchEvent> getTouchEvents() {

		return touchHandler.getTouchEvents();
	}
	
	

}
