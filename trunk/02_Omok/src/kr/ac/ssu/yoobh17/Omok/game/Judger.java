

package kr.ac.ssu.yoobh17.Omok.game;


import android.util.Log;
import kr.ac.ssu.yoobh17.Omok.game.Board.BoardInfor;
import kr.ac.ssu.yoobh17.Omok.game.Board.BoardState;


public class Judger {
	
	public static final int JUDGE_RULE_OK 		= 0;	// 놓아도 좋아~^^
	public static final int JUDGE_RULE_NO_AE 	= 1;	// 이미 다른 돌이 놓아져 있어 ㅄ아
	public static final int JUDGE_RULE_NO_SS 	= 2;	// 삼삼이얌ㅗ
	
	public static final int JUDGE_WIN			= 3;	// 방금 놓은 사람이 이겼오!! 추카추~
	public static final int JUDGE_PASS			= 4;	// 계속 게임이나 하셔~
	
	
	public Judger(){
		
	}
	
	public int judgeRule( Board boardData, Point newPoint, int gameTurn ){
		
		if( BoardState.EMPTY == boardData.getBoardState( newPoint ) ){

			if( judgeTripleThree( boardData, newPoint, gameTurn ) ){
				
				Log.i( "Judger's", "삼삼" );
				
				return JUDGE_RULE_NO_SS;
			}
			else{

				Log.i( "Judger's", "오키" );
				
				return JUDGE_RULE_OK;	
			}
		}
		else{

			Log.i( "Judger's", "이미놓여있음" );
			
			return JUDGE_RULE_NO_AE;
		}
	}
	
	private boolean judgeTripleThree( Board boardData, Point newPoint, int gameTurn ){

		int posX 	= newPoint.getPosX();
		int posY 	= newPoint.getPosY();
		int	p		= gameTurn;
		int caseSS 	= 0;

		//case: _**
		for(int i = -1; i <= 1; i += 2){
			if(p == boardData.getBoardState(posX+i, posY) 		&& p == boardData.getBoardState(posX+2*i,posY) &&
			   0 == boardData.getBoardState(posX+3*i,posY) 		&& 0 == boardData.getBoardState(posX-i,posY)) caseSS++; //가로
			if(p == boardData.getBoardState(posX,posY+i) 		&& p == boardData.getBoardState(posX,posY+2*i) &&
			   0 == boardData.getBoardState(posX,posY+3*i) 		&& 0 == boardData.getBoardState(posX,posY-i)) caseSS++; //세로
			if(p == boardData.getBoardState(posX+i,posY+i) 		&& p == boardData.getBoardState(posX+2*i,posY+2*i) &&
			   0 == boardData.getBoardState(posX+3*i,posY+3*i) 	&& 0 == boardData.getBoardState(posX-i,posY-i)) caseSS++; //대각선1
			if(p == boardData.getBoardState(posX-i,posY+i) 		&& p == boardData.getBoardState(posX-2*i,posY+2*i) &&
			   0 == boardData.getBoardState(posX-3*i,posY+3*i) 	&& 0 == boardData.getBoardState(posX+i,posY-i)) caseSS++; //대각선2
		}
		//case: *_*
			if(p == boardData.getBoardState(posX-1,posY) 		&& p == boardData.getBoardState(posX+1,posY) &&
			   0 == boardData.getBoardState(posX-2,posY) 		&& 0 == boardData.getBoardState(posX+2,posY)) caseSS++; //가로
			if(p == boardData.getBoardState(posX,posY+1) 		&& p == boardData.getBoardState(posX,posY-1) &&
			   0 == boardData.getBoardState(posX,posY-2) 		&& 0 == boardData.getBoardState(posX,posY+2)) caseSS++; //세로
			if(p == boardData.getBoardState(posX-1,posY-1) 		&& p == boardData.getBoardState(posX+1,posY+1) &&
			   0 == boardData.getBoardState(posX-2,posY-2) 		&& 0 == boardData.getBoardState(posX+2,posY+2)) caseSS++; //대각선1
			if(p == boardData.getBoardState(posX+1,posY-1) 		&& p == boardData.getBoardState(posX-1,posY+1) &&
			   0 == boardData.getBoardState(posX+2,posY-2) 		&& 0 == boardData.getBoardState(posX-2,posY+2)) caseSS++; //대각선2
		//case: *_ *
		for(int i = -1; i <= 1; i += 2){
			if(p == boardData.getBoardState(posX+i,posY) 		&& p == boardData.getBoardState(posX-2*i,posY) && 0 == boardData.getBoardState(posX-i,posY) &&
			   0 == boardData.getBoardState(posX+2*i,posY) 		&& 0 == boardData.getBoardState(posX-3*i,posY)) caseSS++; //가로
			if(p == boardData.getBoardState(posX,posY+i) 		&& p == boardData.getBoardState(posX,posY-2*i) && 0 == boardData.getBoardState(posX,posY-i) &&
			   0 == boardData.getBoardState(posX,posY+2*i) 		&& 0 == boardData.getBoardState(posX,posY-3*i)) caseSS++; //세로
			if(p == boardData.getBoardState(posX+i,posY+i) 		&& p == boardData.getBoardState(posX-2*i,posY-2*i) && 0 == boardData.getBoardState(posX-i,posY-i) &&
			   0 == boardData.getBoardState(posX+2*i,posY+2*i) 	&& 0 == boardData.getBoardState(posX-3*i,posY-3*i)) caseSS++; //대각선1
			if(p == boardData.getBoardState(posX-i,posY+i) 		&& p == boardData.getBoardState(posX+2*i,posY-2*i) && 0 == boardData.getBoardState(posX+i,posY-i) &&
			   0 == boardData.getBoardState(posX-2*i,posY+2*i) 	&& 0 == boardData.getBoardState(posX+3*i,posY-3*i)) caseSS++; //대각선2
		}
		//case: _* *
		for(int i = -1; i <= 1; i += 2){
			if(p == boardData.getBoardState(posX-i,posY) 		&& p == boardData.getBoardState(posX-3*i,posY) && 0 == boardData.getBoardState(posX-2*i,posY) &&
			   0 == boardData.getBoardState(posX+i,posY) 		&& 0 == boardData.getBoardState(posX-4*i,posY)) caseSS++; //가로
			if(p == boardData.getBoardState(posX,posY-i) 		&& p == boardData.getBoardState(posX,posY-3*i) && 0 == boardData.getBoardState(posX,posY-2*i) &&
			   0 == boardData.getBoardState(posX,posY+i) 		&& 0 == boardData.getBoardState(posX,posY-4*i)) caseSS++; //세로
			if(p == boardData.getBoardState(posX-i,posY-i) 		&& p == boardData.getBoardState(posX-3*i,posY-3*i) && 0 == boardData.getBoardState(posX-2*i,posY-2*i) &&
			   0 == boardData.getBoardState(posX+i,posY+i) 		&& 0 == boardData.getBoardState(posX-4*i,posY-4*i)) caseSS++; //대각선1
			if(p == boardData.getBoardState(posX+i,posY-i) 		&& p == boardData.getBoardState(posX+3*i,posY-3*i) && 0 == boardData.getBoardState(posX+i,posY-i) &&
			   0 == boardData.getBoardState(posX-i,posY+i) 		&& 0 == boardData.getBoardState(posX+4*i,posY-4*i)) caseSS++; //대각선2
		}
		//case: _ **
		for(int i = -1; i <= 1; i += 2){
			if(p == boardData.getBoardState(posX-2*i,posY) 		&& p == boardData.getBoardState(posX-3*i,posY) && 0 == boardData.getBoardState(posX-i,posY) &&
			   0 == boardData.getBoardState(posX+i,posY) 		&& 0 == boardData.getBoardState(posX-4*i,posY)) caseSS++; //가로
			if(p == boardData.getBoardState(posX,posY-2*i) 		&& p == boardData.getBoardState(posX,posY-3*i) && 0 == boardData.getBoardState(posX,posY-i) &&
			   0 == boardData.getBoardState(posX,posY+i) 		&& 0 == boardData.getBoardState(posX,posY-4*i)) caseSS++; //세로
			if(p == boardData.getBoardState(posX-2*i,posY-2*i)	&& p == boardData.getBoardState(posX-3*i,posY-3*i) && 0 == boardData.getBoardState(posX-i,posY-i) &&
			   0 == boardData.getBoardState(posX+i,posY+i) 		&& 0 == boardData.getBoardState(posX-4*i,posY-4*i)) caseSS++; //대각선1
			if(p == boardData.getBoardState(posX+2*i,posY-2*i) 	&& p == boardData.getBoardState(posX+3*i,posY-3*i) && 0 == boardData.getBoardState(posX+i,posY-i) &&
			   0 == boardData.getBoardState(posX-i,posY+i) 		&& 0 == boardData.getBoardState(posX+4*i,posY-4*i)) caseSS++; //대각선2
		}
		
		if( 2 <= caseSS ){
			
			return true;
		}
		else{
			
			return false;
		}
	}
	
	public int jedgeWinner( Board boardData, Point newPoint, int gameTurn ){
		
		int newPosX = newPoint.getPosX();
		int newPosY = newPoint.getPosY();
		
		for( int i = 0; i < 4; i++ ){
		
			int resultLeftSearch 	= searchConnStone( boardData, gameTurn, newPosX, newPosY, i );
			int resultRightSearch 	= searchConnStone( boardData, gameTurn, newPosX, newPosY, 7 - i );

			if( 4 == resultLeftSearch + resultRightSearch ){
				
				return JUDGE_WIN;
			}
		}
		
		return JUDGE_PASS;
	}
	
	private int searchConnStone( final Board boardData, final int gameTurn, int posX, int posY, int direction ){

		int deltaX		= getDeltaX( direction );
		int deltaY 		= getDeltaY( direction );
		int searchPosX 	= posX + deltaX;
		int searchPosY 	= posY + deltaY;
		boolean isBouce = false;
		
		isBouce |= ( 0 > searchPosX ) || ( 0 > searchPosY );
		isBouce |= ( BoardInfor.BOARD_SIZE <= searchPosX ) || ( BoardInfor.BOARD_SIZE <= searchPosY );
		isBouce |= ( isBouce )?( true ):( gameTurn != boardData.getBoardState( searchPosX, searchPosY ) );
		
		if( isBouce ){
			
			return 0;
		}
		else{
		
			return searchConnStone( boardData, gameTurn, searchPosX, searchPosY, direction ) + 1;
		}
	}

	private int getDeltaX( int direction ){
		
		int deltaX = 0;

		switch( direction ){
		
		case 0:
		case 3:
		case 5:
			
			deltaX = -1;
			break;

		case 1:
		case 6:

			deltaX = 0;
			break;

		case 2:
		case 4:
		case 7:

			deltaX = 1;
			break;

		}
		
		return deltaX;
	}

	private int getDeltaY( int direction ){

		int deltaY = 0;
		
		switch( direction ){
		
		case 0:
		case 1:
		case 2:

			deltaY = -1;
			break;

		case 3:
		case 4:

			deltaY = 0;
			break;
			
		case 5:
		case 6:
		case 7:

			deltaY = 1;
			break;

		}

		return deltaY;
	}

}
