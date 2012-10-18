

package kr.ac.ssu.yoobh17.Omok.framework.impl;


import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import kr.ac.ssu.yoobh17.Omok.framework.Audio;
import kr.ac.ssu.yoobh17.Omok.framework.Music;
import kr.ac.ssu.yoobh17.Omok.framework.Sound;


public class AndroidAudio implements Audio {

	AssetManager assetManager;
	SoundPool soundPool;
	
	public AndroidAudio( Activity activity ){
		
		activity.setVolumeControlStream( AudioManager.STREAM_MUSIC );
		
		this.assetManager 	= activity.getAssets();
		this.soundPool		= new SoundPool( 20, AudioManager.STREAM_MUSIC, 0 );
		
	}
	
	@Override
	public Music newMusic( String fileName ) {

		try {
			
			AssetFileDescriptor assetFileDescriptor = assetManager.openFd( fileName );
			
			return new AndroidMusic( assetFileDescriptor );
		}
		catch ( IOException e ) {

			throw new RuntimeException( "Couldn't load music '" + fileName + "'" );
		}
	}

	@Override
	public Sound newSound( String fileName ) {

		try {
			
			AssetFileDescriptor assetFileDescriptor = assetManager.openFd( fileName );
			int soundId = soundPool.load( assetFileDescriptor, 0 );
			
			return new AndroidSound( soundPool, soundId );
		}
		catch ( IOException e ) {

			throw new RuntimeException( "Couldn't load sound '" + fileName + "'" );
		}
	}

}
