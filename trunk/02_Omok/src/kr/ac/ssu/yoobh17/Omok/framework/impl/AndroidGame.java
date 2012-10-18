

package kr.ac.ssu.yoobh17.Omok.framework.impl;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import kr.ac.ssu.yoobh17.Omok.framework.Audio;
import kr.ac.ssu.yoobh17.Omok.framework.FileIO;
import kr.ac.ssu.yoobh17.Omok.framework.Game;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input;
import kr.ac.ssu.yoobh17.Omok.framework.Screen;


public abstract class AndroidGame extends Activity implements Game {

	AndroidFastRenderView	renderView;
	
	Graphics				graphics;
	Audio					audio;
	Input					input;
	FileIO					fileIO;
	Screen					screen;
	
	WakeLock				wakeLock;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		
		boolean isLandscape 		= Configuration.ORIENTATION_LANDSCAPE == getResources().getConfiguration().orientation;
		
		int		frameBufferWidth	= ( isLandscape )?( 800 ):( 480 );
		int		frameBufferHeight	= ( isLandscape )?( 480 ):( 800 );
		
		Bitmap	frameBuffer 		= Bitmap.createBitmap( frameBufferWidth, frameBufferHeight, Config.RGB_565 );
		
		float	scaleX				= (float) frameBufferWidth	/ getWindowManager().getDefaultDisplay().getWidth();
		float	scaleY				= (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		
		renderView 					= new AndroidFastRenderView( this, frameBuffer );
		graphics 					= new AndroidGraphics( getAssets(), frameBuffer );
		fileIO 						= new AndroidFileIO( getAssets() );
		audio 						= new AndroidAudio( this );
		input 						= new AndroidInput( this, renderView, scaleX, scaleY );
		screen 						= getStartScreen();
		
		setContentView( renderView );
		
		PowerManager powerManager 	= (PowerManager) getSystemService( Context.POWER_SERVICE );
		wakeLock 					= powerManager.newWakeLock( PowerManager.FULL_WAKE_LOCK, "GLGame" );
		
	}
	
	@Override
	protected void onResume() {

		super.onResume();
		
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
		
	}
	
	@Override
	protected void onPause() {

		super.onPause();
	
		wakeLock.release();
		screen.pause();
		renderView.pause();
		
		if( isFinishing() ){
			
			screen.dispose();
			
		}
	}

	@Override
	public Input getInput() {

		return input;
	}

	@Override
	public FileIO getFileIO() {

		return fileIO;
	}

	@Override
	public Graphics getGraphics() {

		return graphics;
	}

	@Override
	public Audio getAudio() {

		return audio;
	}

	@Override
	public void setScreen( Screen screen ) {

		if( null == screen ){
			
			throw new IllegalArgumentException( "Screen must not be null" );
			
		}
		
		this.screen.pause();
		this.screen.dispose();
		
		screen.resume();
		screen.update( 0 );
		
		this.screen = screen;
		
	}

	@Override
	public Screen getCurrentScreen() {

		return screen;
	}

//	@Override
//	public Screen getStartScreen() {
//
//		return null;
//	}

}
