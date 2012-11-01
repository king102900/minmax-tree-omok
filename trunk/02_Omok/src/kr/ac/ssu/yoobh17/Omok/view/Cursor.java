

package kr.ac.ssu.yoobh17.Omok.view;


import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;
import kr.ac.ssu.yoobh17.Omok.game.Board.BoardInfor;


public class Cursor {
	
	private Pixmap 	pixmap;
	
	private int		halfWidth;
	private int		halfHeight;
	
	public Cursor( Pixmap pixmap ){
		
		this.pixmap = pixmap;
		
		halfWidth	= this.pixmap.getWidth() / 2;
		halfHeight	= this.pixmap.getHeight() / 2;
		
	}
	
	public void draw( Graphics graphics, int x, int y ){
		
		float posX = ( x * BoardInfor.ONE_BLOCK_LENGTH ) + BoardInfor.STARTING_POINT_X - halfWidth;
		float posY = ( y * BoardInfor.ONE_BLOCK_LENGTH ) + BoardInfor.STARTING_POINT_Y - halfHeight;
		
		graphics.drawPixmap( pixmap, posX, posY );
		
	}

}
