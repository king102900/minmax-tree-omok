

package kr.ac.ssu.yoobh17.Omok.framework;


import java.util.List;


public interface Input {

//	public static class KeyEvent {
//
//		public static final int	KEY_DOWN	= 0;
//		public static final int	KEY_UP		= 1;
//
//		public int				type;
//		public int				keyCode;
//		public char				keyChar;
//
//	}

	public static class TouchEvent {

		public static final int	TOUCH_DOWN		= 0;
		public static final int	TOUCH_UP		= 1;
		public static final int	TOUCH_DRAGGED	= 2;

		public int				type;
		public int				x;
		public int				y;
		public int				pointer;

		public String toString() {

			StringBuilder builder = new StringBuilder();
			
			if ( type == TOUCH_DOWN ){

				builder.append( "touch down, " );
				
			}
			else if ( type == TOUCH_DRAGGED ){
				
				builder.append( "touch dragged, " );
				
			}
			else{
				
				builder.append( "touch up, " );
				
			}
			
			builder.append( pointer );
			builder.append( "," );
			builder.append( x );
			builder.append( "," );
			builder.append( y );
			
			return builder.toString();
		}
	}

	// 폴링 Method
	
//	public boolean 			isKeyPressed( int keyCode );
	
	public boolean 			isTouchDown	( int pointer );
	public int 				getTouchX	( int pointer );
	public int 				getTouchY	( int pointer );

//	public float 			getAccelX();
//	public float 			getAccelY();
//	public float 			getAccelZ();
	
	// 이벤트 기반 처리

//	public List<KeyEvent> 	getKeyEvents();
	public List<TouchEvent> getTouchEvents();

}
