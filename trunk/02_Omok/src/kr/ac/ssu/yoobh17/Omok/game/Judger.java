

package kr.ac.ssu.yoobh17.Omok.game;

import kr.ac.ssu.yoobh17.Omok.game.GameBoard.BoardInfor;
import kr.ac.ssu.yoobh17.Omok.game.GameBoard.BoardState;


public class Judger {
	
	public static final int JUDGE_RULE_OK 		= 0;	// 놓아도 좋아~^^
	public static final int JUDGE_RULE_NO_AE 	= 1;	// 아 샹...이게 머드랑...ㅜㅜ
	public static final int JUDGE_RULE_NO_SS 	= 2;	// 삼삼이얌ㅗ
	
	public static final int JUDGE_WIN			= 3;	// 방금 놓은 사람이 이겼오!! 추카추~
	public static final int JUDGE_PASS			= 4;	// 계속 게임이나 하셔~
	
	
	public Judger(){
		
	}
	
	public int judgeRule( int[][] boardData, Point newPoint, int gameTurn ){
		
		if( BoardState.EMPTY == boardData[newPoint.getPosX()][newPoint.getPosY()] ){

			if( judgeTripleThree( boardData, newPoint, gameTurn ) ){
				
				return JUDGE_RULE_NO_SS;
			}
			
			return JUDGE_RULE_OK;
		}
		else{
		
			return JUDGE_RULE_NO_AE;
		}
	}
	
	private boolean judgeTripleThree( int[][] boardData, Point newPoint, int gameTurn ){

		int posX 	= newPoint.getPosX();
		int posY 	= newPoint.getPosY();
		int caseSS 	= 0;

		for( int i = 0; i < 4; i++ ){
		
			int resultLeftSearch 	= searchConnStone( boardData, gameTurn, posX, posY, i );
			int resultRightSearch 	= searchConnStone( boardData, gameTurn, posX, posY, 7 - i );

			if( 2 == resultLeftSearch + resultRightSearch ){

				caseSS++;

			}
		}
		
		if( 2 <= caseSS ){
			
			return true;
		}
		else{
			
			return false;
		}
	}
	
	public int jedgeWinner( int[][] boardData, Point newPoint, int gameTurn ){
		
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
	
	private int searchConnStone( final int[][] boardData, final int gameTurn, int posX, int posY, int direction ){

		int deltaX		= getDeltaX( direction );
		int deltaY 		= getDeltaY( direction );
		int searchPosX 	= posX + deltaX;
		int searchPosY 	= posY + deltaY;
		boolean isBouce = false;
		
		isBouce |= ( 0 > searchPosX ) || ( 0 > searchPosY );
		isBouce |= ( BoardInfor.BOARD_SIZE <= searchPosX ) || ( BoardInfor.BOARD_SIZE <= searchPosY );
		isBouce |= ( isBouce )?( true ):( gameTurn != boardData[searchPosX][searchPosY] );
		
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
// ① 대국판은 가로·세로 15줄의 오목판을 사용하며, 돌은 흑과 백을 사용한다. (두돌이 서로 동일하지만 않으면 된다.)

// ② 흑을 선수로 하고 오목판의 중앙(천원)으로부터 교대로 둔다. 흑·백 중 어느 쪽이 가로나 세로 또는 대각선으로
//	  자기 돌을 5개 연달아 놓으면 이긴다. 이것을 오목이라고 한다.

// ③ 흑은 오목이 되기까지 삼삼(三三) , 사사(四四) , 육목 이상을 두지 못하는데, 이를 금수(禁手)라고 한다.
//	  이를 범했을 때는 패하게 된다. 다만 백은 흑이 금수를 둘 경우, 이를 흑에게 알려줘야 하며, 이를 알아채지
//	  못하고 다음의 착수(着手)를 했을 때는 흑의 금수는 해소되고 경기는 속행된다.

// ④ 백에게는 금수가 전혀 없으며, 백의 육목이상은 백이 이긴 것으로 한다.

// ⑤ 백 6 이후의 착수는 포기할 수가 있다.

// ⑥ 무승부의 성립은 다음과 같다.
// 		첫째 : 어느 한쪽이 비기기를 제안하고 상대가 이를 받아들였을 경우.
// 		둘째 : 흑과 백이 연속적으로 착수를 패스하였을 경우.