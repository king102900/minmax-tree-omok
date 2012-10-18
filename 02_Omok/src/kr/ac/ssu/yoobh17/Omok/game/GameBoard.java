

package kr.ac.ssu.yoobh17.Omok.game;


import java.util.List;

import android.util.Log;

import kr.ac.ssu.yoobh17.Omok.Assets;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;


public class GameBoard implements OnStonePuttedListener {
	
	public static class GameTurn{
		
		public static final int USER 		= 1;
		public static final int COMPUTER 	= 2;
		
		public static final int FINISH		= 0;
		
		public static int nextTurn( int nowTurn ){
			
			if( USER == nowTurn ){
			
				return COMPUTER;
			}
			else{

				return USER;
			}
		}
		
	}

	public class BoardState{
		
		public static final int EMPTY		= 0;
		public static final int USER 		= 1;
		public static final int COMPUTER 	= 2;
		
	}
	
	public class BoardInfor{

		public final static int		BOARD_SIZE 			= 19;
		
		public static final int 	STARTING_POINT_X 	= 196;
		public static final int		STARTING_POINT_Y 	= 32;

		public static final float 	ONE_BLOCK_LENGTH 	= 22.5F;	// 23 ¶Ç´Â 22.5F
		
	}

	
	private int[][]		boardData;
	private int			gameTurn;
	private int			winner;
	
	private User		user;
	private	Computer	computer;
	
	private Judger 		judger;
	
	private Pixmap		pixmapBlack;
	private Pixmap		pixmapWhite;
	
	public GameBoard(){
		
		boardData	= new int[BoardInfor.BOARD_SIZE][BoardInfor.BOARD_SIZE];
		user		= new User();
		computer	= new Computer();
		judger		= new Judger();
		
		user	.setOnStonePuttedListener( this );
		
		pixmapBlack	= Assets.GameScreen.stoneBlack;
		pixmapWhite	= Assets.GameScreen.stoneWhite;
		
		gameInit();
		
	}
	
	public void gameInit(){
		
		for( int i = 0; i < BoardInfor.BOARD_SIZE; i++ ){
			
			for( int j = 0; j < BoardInfor.BOARD_SIZE; j++ ){
				
				boardData[i][j] = BoardState.EMPTY;
				
			}
		}
		
		gameTurn 	= GameTurn.USER;
		winner		= GameTurn.FINISH;
		
		user.init();
		
	}
	
	public void update( List<TouchEvent> touchEvents ){
		
		Log.i( "GameBoard", "Game Turn: " + gameTurn );

		switch( gameTurn ){
		
		case GameTurn.USER:
			
			user.update( touchEvents );
			
			break;
			
		case GameTurn.COMPUTER:

			computer.update();
			
			break;
			
		}
	}
	
	public void draw( Graphics graphics ){

		graphics.drawPixmap( Assets.GameScreen.omokBoard, 173, 10 );
		
		for( int i = 0; i < BoardInfor.BOARD_SIZE; i++ ){
			
			for( int j = 0; j < BoardInfor.BOARD_SIZE; j++){
				
				float posX;
				float posY;
				
				switch ( boardData[i][j] ) {
				
				case BoardState.EMPTY:
					
					break;

				case BoardState.USER:

					posX = ( i * BoardInfor.ONE_BLOCK_LENGTH ) + BoardInfor.STARTING_POINT_X - ( pixmapBlack.getWidth() / 2 );
					posY = ( j * BoardInfor.ONE_BLOCK_LENGTH ) + BoardInfor.STARTING_POINT_Y - ( pixmapBlack.getWidth() / 2 );
					
					graphics.drawPixmap( pixmapBlack, posX, posY );
					
					break;

				case BoardState.COMPUTER:

					posX = ( i * BoardInfor.ONE_BLOCK_LENGTH ) + BoardInfor.STARTING_POINT_X - ( pixmapWhite.getWidth() / 2 );
					posY = ( j * BoardInfor.ONE_BLOCK_LENGTH ) + BoardInfor.STARTING_POINT_Y - ( pixmapWhite.getWidth() / 2 );
					
					graphics.drawPixmap( pixmapWhite, posX, posY );
					
					break;
					
				}
			}
		}

		switch( gameTurn ){
		
		case GameTurn.USER:
			
			user.draw( graphics );
			
			break;
			
		case GameTurn.COMPUTER:

			computer.draw( graphics );
			
			break;

		case GameTurn.FINISH:

			
			
			break;
			
		}
	}

	@Override
	public int onStonePutted( Point point ) {
		
		int judgeResultOfRule = judger.judgeRule( boardData, point );
		int judgeResultOfWinner;
		
		switch ( judgeResultOfRule ) {
		
		case Judger.JUDGE_RULE_OK:
			
			if( GameTurn.USER == gameTurn ){
				
				boardData[point.getPosX()][point.getPosY()] = GameTurn.USER;
				
			}
			else{

				boardData[point.getPosX()][point.getPosY()] = GameTurn.USER;
				
			}
			
			judgeResultOfWinner = judger.jedgeWinner( boardData );

			if( Judger.JUDGE_WIN == judgeResultOfWinner ){
				
				winner		= gameTurn;
				gameTurn 	= GameTurn.FINISH;
				
			}
			else if( Judger.JUDGE_PASS == judgeResultOfWinner ){

				gameTurn = GameTurn.nextTurn( gameTurn );
				
			}

			break;

		case Judger.JUDGE_RULE_NO_AE:
			
			break;

		case Judger.JUDGE_RULE_NO_SS:
			
			break;

		}
		
		return 0;
	}

}






