

package kr.ac.ssu.yoobh17.Omok;


import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics.PixmapFormat;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;


public class Assets {
	
	public static class GameScreen {
		
		public static Pixmap mainScreen;
		
		public static Pixmap omokBoard;
		public static Pixmap stoneBlack;
		public static Pixmap stoneWhite;
		public static Pixmap cursor;
		
		public static Pixmap buttonUp;
		public static Pixmap buttonDown;
		public static Pixmap buttonRight;
		public static Pixmap buttonLeft;
		public static Pixmap buttonPut;
		
		public static void load( final Graphics graphics ){
			
			mainScreen 	= graphics.newPixmap( "Omok_MainScreen.png"	, PixmapFormat.RGB565 );
			
			omokBoard 	= graphics.newPixmap( "Omok_Board.png"		, PixmapFormat.ARGB4444 );
			stoneBlack 	= graphics.newPixmap( "Omok_Black.png"		, PixmapFormat.ARGB4444 );
			stoneWhite 	= graphics.newPixmap( "Omok_White.png"		, PixmapFormat.ARGB4444 );
			cursor 		= graphics.newPixmap( "Cursor.png"			, PixmapFormat.ARGB4444 );

			buttonUp 	= graphics.newPixmap( "Button_Up.png"		, PixmapFormat.ARGB4444 );
			buttonDown 	= graphics.newPixmap( "Button_Down.png"		, PixmapFormat.ARGB4444 );
			buttonRight = graphics.newPixmap( "Button_Right.png"	, PixmapFormat.ARGB4444 );
			buttonLeft 	= graphics.newPixmap( "Button_Left.png"		, PixmapFormat.ARGB4444 );
			buttonPut 	= graphics.newPixmap( "Button_Put.png"		, PixmapFormat.ARGB4444 );
			
		}
	}

}
