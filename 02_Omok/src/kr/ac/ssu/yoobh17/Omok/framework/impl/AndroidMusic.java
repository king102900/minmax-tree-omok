

package kr.ac.ssu.yoobh17.Omok.framework.impl;


import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import kr.ac.ssu.yoobh17.Omok.framework.Music;


public class AndroidMusic implements Music, OnCompletionListener {

	MediaPlayer	mediaPlayer;
	boolean		isPrepared	= false;

	public AndroidMusic( AssetFileDescriptor assetFileDescriptor ){
		
		mediaPlayer = new MediaPlayer();
		
		try {
			
			mediaPlayer.setDataSource( assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength() );
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener( this );
			
		}
		catch ( Exception e ) {

			throw new RuntimeException( "Couldn't load music" );
			
		}
	}

	@Override
	public void onCompletion( MediaPlayer player ) {

		synchronized ( this ) {
		
			isPrepared = false;
			
		}
	}

	@Override
	public void play() {

		if( mediaPlayer.isPlaying() ){
			
			return;
		}
		
		try{
			
			synchronized ( this ) {
				
				if( !isPrepared ){
					
					mediaPlayer.prepare();
					
				}
				
				mediaPlayer.start();
				
			}
		}
		catch( IllegalStateException e ){
			
			e.printStackTrace();
			
		}
		catch( IOException e ){
			
			e.printStackTrace();
			
		}
	}

	@Override
	public void stop() {

		mediaPlayer.stop();
		
		synchronized ( this ) {
		
			isPrepared = false;
			
		}
	}

	@Override
	public void pause() {

		
	}

	@Override
	public void setLooping( boolean looping ) {

		mediaPlayer.setLooping( looping );
		
	}

	@Override
	public void setVolume( float volume ) {

		mediaPlayer.setVolume( volume, volume );
		
	}

	@Override
	public boolean isPlaying() {

		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {

		return !isPrepared;
	}

	@Override
	public boolean isLooping() {

		return mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {

		if( mediaPlayer.isPlaying() ){
			
			mediaPlayer.stop();
			
		}
		
		mediaPlayer.release();
		
	}

}
