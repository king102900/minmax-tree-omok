package kr.ac.ssu.yoobh17.Omok;

import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics.PixmapFormat;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;


public class Assets {
	
	public static class GameScreen {
		
		public static Pixmap mainScreen;
		
		public static void load( final Graphics graphics ){
			
			mainScreen = graphics.newPixmap( "Omok_MainScreen.png", PixmapFormat.RGB565 );
			
		}
	}

}
