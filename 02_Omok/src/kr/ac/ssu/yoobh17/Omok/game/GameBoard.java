

package kr.ac.ssu.yoobh17.Omok.game;


import java.util.List;

import android.util.Log;

import kr.ac.ssu.yoobh17.Omok.Assets;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;
import kr.ac.ssu.yoobh17.Omok.framework.Pixmap;
import kr.ac.ssu.yoobh17.Omok.game.Board.BoardInfor;
import kr.ac.ssu.yoobh17.Omok.game.Board.BoardState;


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

	private Board 				boardData;
	
	private int					gameTurn;
	private int					winner;
	
	private User				user;
	private	Computer			computer;

	private User				user2;						//******************************
	
	private Judger 				judger;
	
	private Pixmap				pixmapBlack;
	private Pixmap				pixmapWhite;
	
	public GameBoard(){
		
		boardData	= new Board();
		
		user		= new User();
		computer	= new Computer();
		judger		= new Judger();
		
		user	.setOnStonePuttedListener( this );
		computer.setOnStonePuttedListener( this );

		user2		= new User();							//******************************
		user2	.setOnStonePuttedListener( this );			//******************************
		
		pixmapBlack	= Assets.GameScreen.stoneBlack;
		pixmapWhite	= Assets.GameScreen.stoneWhite;
		
		gameInit();
		
	}
	
	public void gameInit(){
		
		gameTurn 	= GameTurn.USER;
		winner		= GameTurn.FINISH;

		boardData	.init();
		
		user		.init();
		computer	.init();
		user2		.init();								//******************************
		
	}
	
	public void update( List<TouchEvent> touchEvents ){
		
//		Log.i( "GameBoard", "Game Turn: " + gameTurn );
		
		switch( gameTurn ){
		
		case GameTurn.USER:
			
			user.update( touchEvents );
			
			break;
			
		case GameTurn.COMPUTER:

			computer.update( boardData );					//******************************
			user2.update( touchEvents );					//******************************
			
			break;
			
		}
	}
	
	public void draw( Graphics graphics ){

		graphics.drawPixmap( Assets.GameScreen.omokBoard, 173, 10 );

		drawStone( graphics );

		switch( gameTurn ){
		
		case GameTurn.USER:
			
			user.draw( graphics );
			
			break;
			
		case GameTurn.COMPUTER:

			computer.draw( graphics );						//******************************
			user2	.draw( graphics );						//******************************
			
			break;

		case GameTurn.FINISH:

			break;
			
		}
	}
	
	private void drawStone( Graphics graphics ){
		
		int stoneSize = boardData.getStoneSize();
		
		for( int k = 0; k < stoneSize; k++ ){
			
			Stone 	stone 	= boardData.getStoneData( k );
			
			int		i 		= stone.getPoint().getPosX();
			int		j 		= stone.getPoint().getPosY();
			float 	posX 	= 0.0f;
			float 	posY 	= 0.0f;
			
			switch ( stone.getGameTurn() ) {
			
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

	@Override
	public int onStonePutted( Point point ) {
		
		int judgeResultOfRule = judger.judgeRule( boardData, point, gameTurn );
		int judgeResultOfWinner;
		
		switch ( judgeResultOfRule ) {
		
		case Judger.JUDGE_RULE_OK:

			boardData.newStone( point, gameTurn );
			
			judgeResultOfWinner = judger.jedgeWinner( boardData, point, gameTurn );

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
