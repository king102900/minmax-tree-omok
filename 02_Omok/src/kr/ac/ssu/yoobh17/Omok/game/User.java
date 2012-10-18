

package kr.ac.ssu.yoobh17.Omok.game;


import java.util.List;

import android.util.Log;

import kr.ac.ssu.yoobh17.Omok.Assets;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;
import kr.ac.ssu.yoobh17.Omok.view.Button;
import kr.ac.ssu.yoobh17.Omok.view.Cursor;


public class User {

	private Button					buttonUp;
	private Button					buttonDown;
	private Button					buttonRight;
	private Button					buttonLeft;
	private Button					buttonPut;

	private Cursor					cursor;
	private Point					pointCursor;

	private OnStonePuttedListener	onStonePuttedListener;

	public User(){
		
		buttonUp 				= new Button( Assets.GameScreen.buttonUp	, Assets.GameScreen.buttonUp	, 58, 60 );
		buttonDown 				= new Button( Assets.GameScreen.buttonDown	, Assets.GameScreen.buttonDown	, 58, 174 );
		buttonLeft 				= new Button( Assets.GameScreen.buttonLeft	, Assets.GameScreen.buttonLeft	, 0, 116 );
		buttonRight 			= new Button( Assets.GameScreen.buttonRight	, Assets.GameScreen.buttonRight	, 116, 116 );
		buttonPut 				= new Button( Assets.GameScreen.buttonPut	, Assets.GameScreen.buttonPut	, 645, 80 );
		
		cursor					= new Cursor( Assets.GameScreen.cursor );
		
		pointCursor				= new Point();
		
		onStonePuttedListener	= null;
		
	}
	
	public void init(){
		
		pointCursor.setPosXY( 9, 9 );
		
	}
	
	public void setOnStonePuttedListener( OnStonePuttedListener onStonePuttedListener ){
		
		this.onStonePuttedListener = onStonePuttedListener;
		
	}

	public void update( List<TouchEvent> touchEvents ){

		int len = touchEvents.size();

		for ( int i = 0; i < len; i++ ) {
			
			TouchEvent event = touchEvents.get( i );

			if( buttonUp.isButtonClicked( event ) ){
				
//				Log.i( "User's", "BUTTON_UP" );
				pointCursor.posYMinus();
				
			}
			else if( buttonDown.isButtonClicked( event ) ){

//				Log.i( "User's", "BUTTON_DOWN" );
				pointCursor.posYPlus();
				
			}
			else if( buttonRight.isButtonClicked( event ) ){

//				Log.i( "User's", "BUTTON_RIGHT" );
				pointCursor.posXPlus();
				
			}
			else if( buttonLeft.isButtonClicked( event ) ){

//				Log.i( "User's", "BUTTON_LEFT" );
				pointCursor.posXMinus();
				
			}
			else if( buttonPut.isButtonClicked( event ) ){

//				Log.i( "User's", "´­·É~" );
				onStonePuttedListener.onStonePutted( pointCursor );
				
			}
		}
	}
	
	public void draw( Graphics graphics ){
		
		buttonUp	.draw( graphics );
		buttonDown	.draw( graphics );
		buttonRight	.draw( graphics );
		buttonLeft	.draw( graphics );
		buttonPut	.draw( graphics );
		
		cursor.draw( graphics, pointCursor.getPosX(), pointCursor.getPosY() );
		
	}

}










