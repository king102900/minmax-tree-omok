

package kr.ac.ssu.yoobh17.Omok.framework.impl;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class AndroidFastRenderView extends SurfaceView implements Runnable {

	AndroidGame			game;
	Bitmap				frameBuffer;
	Thread				rederThread	= null;
	SurfaceHolder		holder;
	volatile boolean	running		= false;

	private final float	THRESHOLD	= 0.0333333333f;

	public AndroidFastRenderView( AndroidGame game, Bitmap frameBuffer ){
		
		super( game );
		
		this.game 			= game;
		this.frameBuffer 	= frameBuffer;
		this.holder 		= getHolder();
		
	}
	
	public void resume(){
		
		running = true;
		rederThread = new Thread( this );
		rederThread.start();
		
	}
	
	public void run(){
		
		Rect 		dstRect 	= new Rect();
		long 		startTime 	= System.nanoTime();
		
		float		timeStack	= 0.0f;
		
		while( running ){
		
			if( !holder.getSurface().isValid() ){
				
				continue;
				
			}
			
			float deltaTime = ( System.nanoTime() - startTime ) / 1000000000.0f;
			startTime 		= System.nanoTime();
			
			timeStack += deltaTime;
			
			if( THRESHOLD < timeStack ){
				
				game.getCurrentScreen().update	( timeStack );
				game.getCurrentScreen().present	( timeStack );
			
				Canvas canvas = holder.lockCanvas();
			
				canvas.getClipBounds( dstRect );
				canvas.drawBitmap	( frameBuffer, null, dstRect, null );
			
				holder.unlockCanvasAndPost( canvas );

				timeStack -= THRESHOLD;
			
			}
		}
	}
	
	public void pause(){
		
		running = false;
		while( true ){
			
			try {
				
				rederThread.join();
				break;
				
			}
			catch ( InterruptedException e ) {
				// Àç½Ãµµ
			}
		}
	}

}
