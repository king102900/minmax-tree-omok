

package kr.ac.ssu.yoobh17.Omok.framework.impl;


import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;


public class AndroidGraphics implements Graphics {

	AssetManager	assets;
	Bitmap			frameBuffer;
	Canvas			canvas;
	Paint			paint;
	Rect			srcRect		= new Rect();
	Rect			dstRect		= new Rect();

	public AndroidGraphics( AssetManager assets, Bitmap frameBuffer ){
		
		this.assets 		= assets;
		this.frameBuffer 	= frameBuffer;
		this.canvas 		= new Canvas( frameBuffer );
		this.paint 			= new Paint();
		
	}
	
	@Override
	public Pixmap newPixmap( String fileName, PixmapFormat format ) {
		
		Config config = null;
		
		if( PixmapFormat.RGB565 == format ){
			
			config = Config.RGB_565;
			
		}
		else if( PixmapFormat.ARGB4444 == format ){
			
			config = Config.ARGB_4444;
			
		}
		else{
			
			config = Config.ARGB_8888;
			
		}
		
		Options 	options 		= new Options();
		options.inPreferredConfig 	= config;
		
		InputStream inputStream 	= null;
		Bitmap		bitmap 			= null;
		
		try{
			
			inputStream = assets.open( fileName );
			bitmap = BitmapFactory.decodeStream( inputStream );
			
			if( null == bitmap ){

				throw new RuntimeException( "Couldn't load bitmap from asset '" + fileName + "'" );
				
			}
		}
		catch( IOException e ){

			throw new RuntimeException( "Couldn't load bitmap from asset '" + fileName + "'" );
				
		}
		finally{
			
			if( null != inputStream ){
				
				try{
					
					inputStream.close();
					
				}
				catch( IOException e ){
					
				}
			}
		}
		
		if( Config.RGB_565 == bitmap.getConfig() ){
			
			format = PixmapFormat.RGB565;
			
		}
		else if( PixmapFormat.ARGB4444 == format ){

			format = PixmapFormat.ARGB4444;
			
		}
		else{

			format = PixmapFormat.ARGB8888;
			
		}

		return new AndroidPixmap( bitmap, format );
	}

	@Override
	public void clear( int color ) {
		
		int r = ( color & 0xFF0000 	) >> 16;
		int g = ( color & 0xFF00 	) >> 8;
		int b = ( color & 0xFF 		);

		canvas.drawRGB( r, g, b );
		
	}

	@Override
	public void drawPixel( int x, int y, int color ) {

		paint	.setColor( color );
		canvas	.drawPoint( x, y, paint );
		
	}

	@Override
	public void drawLine( int x, int y, int x2, int y2, int color ) {

		paint	.setColor( color );
		canvas	.drawLine( x, y, x2, y2, paint );
		
	}

	@Override
	public void drawRect( int x, int y, int width, int height, int color ) {

		paint	.setColor( color );
		paint	.setStyle( Style.FILL );
		canvas	.drawRect( x, y, x + width - 1, y + height - 1, paint );
		
	}

	@Override
	public void drawPixmap( Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight ) {

		srcRect.left 	= srcX;
		srcRect.top 	= srcY;
		srcRect.right 	= srcX + srcWidth - 1;
		srcRect.bottom 	= srcY + srcHeight - 1;

		dstRect.left 	= x;
		dstRect.top 	= y;
		dstRect.right 	= x + srcWidth - 1;
		dstRect.bottom 	= y + srcHeight - 1;
		
		canvas.drawBitmap( ( (AndroidPixmap)pixmap ).bitmap, srcRect, dstRect, null );
		
	}

	@Override
	public void drawPixmap( Pixmap pixmap, int x, int y ) {

		canvas.drawBitmap( ( (AndroidPixmap)pixmap ).bitmap, x, y, null );
		
	}

	@Override
	public int getWidth() {

		return frameBuffer.getWidth();
	}

	@Override
	public int getHeight() {

		return frameBuffer.getHeight();
	}

}
