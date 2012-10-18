

package kr.ac.ssu.yoobh17.Omok.view;


import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;


public class Button extends OButton {

	private Pixmap	buttonImage;
	private Pixmap	buttonImage_pressed;

	public Button( Pixmap buttonImage, Pixmap buttonImage_pressed, int x, int y ) {
		
		super( x, y, buttonImage.getWidth(), buttonImage.getHeight() );

		this.buttonImage 			= buttonImage;
		this.buttonImage_pressed 	= buttonImage_pressed;

	}

	@Override
	public boolean isButtonClicked( TouchEvent event ) {

		if ( inBounds( event, x, y, width, height ) ) {

			if ( TouchEvent.TOUCH_DOWN == event.type ) {

				isTouched = true;

			}
			else if ( TouchEvent.TOUCH_UP == event.type ) {

				if( isTouched ){
					
					isTouched = false;

					return true;
				}
			}
		}
		else{
			
			isTouched = false;
			
		}
		
		return false;
	}

	@Override
	public void draw( Graphics graphics ) {

		if( isTouched ){
			
//			graphics.drawPixmap( buttonImage_pressed, x, y );
			graphics.drawRect( x, y, width, height, 0xFF000000 );
			
		}
		else{
			
			graphics.drawPixmap( buttonImage, x, y );
	
		}
	}

}
