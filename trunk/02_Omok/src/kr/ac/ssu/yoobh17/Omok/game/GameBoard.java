

package kr.ac.ssu.yoobh17.Omok.game;


import java.util.List;

import kr.ac.ssu.yoobh17.Omok.Assets;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;


public class GameBoard {
	
	private class GameTurn{
		
		public static final int USER 		= 0;
		public static final int COMPUTER 	= 1;
		
	}

	private class BoardState{
		
		public static final int EMPTY		= 0;
		public static final int USER 		= 1;
		public static final int COMPUTER 	= 2;
		
	}

	private final int	BOARD_SIZE = 19;
	
	private int[][]		boardData;
	private int			gameTurn;

	
	public GameBoard(){
		
		boardData = new int[BOARD_SIZE][BOARD_SIZE];
		
	}
	
	public void init(){
		
		for( int i = 0; i < BOARD_SIZE; i++ ){
			
			for( int j = 0; j < BOARD_SIZE; j++ ){
				
				boardData[i][j] = BoardState.EMPTY;
				
			}
		}
		
		gameTurn = GameTurn.USER;
		
	}
	
	public void update( List<TouchEvent> touchEvents ){
		
		switch( gameTurn ){
		
		case GameTurn.USER:
			
			updateUserTurn( touchEvents );
			
			break;
			
		case GameTurn.COMPUTER:

			updateComputerTurn();
			
			break;
			
		}
	}
	
	public void updateUserTurn( List<TouchEvent> touchEvents ){

		int len = touchEvents.size();

		for ( int i = 0; i < len; i++ ) {

			TouchEvent event = touchEvents.get( i );

		}
	}

	public void updateComputerTurn(){
		
		
	}
	
	public void draw( Graphics graphics ){

		graphics.drawPixmap( Assets.GameScreen.omokBoard, 173, 10 );
		
		
	}

}
