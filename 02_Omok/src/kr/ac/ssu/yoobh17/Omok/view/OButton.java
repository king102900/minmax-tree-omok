

package kr.ac.ssu.yoobh17.Omok.view;


import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;


public abstract class OButton {

	protected int		x;
	protected int		y;
	protected int		width;
	protected int		height;
	protected boolean	isTouched;

	public OButton( int x, int y, int width, int height ){
		
		this.x 		= x;
		this.y 		= y;
		
		this.width 	= width;
		this.height = height;
		
		isTouched	= false;
		
	}

	public abstract void 	draw			( Graphics graphics );
	public abstract boolean isButtonClicked	( TouchEvent event );

	protected boolean inBounds( TouchEvent event, int x, int y, int width, int height ){
		
		if( event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1 ){
			
			return true;
		}
		else{
		
			return false;
		}
	}

}
