

package kr.ac.ssu.yoobh17.Omok.game;

import kr.ac.ssu.yoobh17.Omok.game.GameBoard.BoardInfor;
import kr.ac.ssu.yoobh17.Omok.game.GameBoard.BoardState;


public class Judger {
	
	public static final int JUDGE_RULE_OK 		= 0;	// ���Ƶ� ����~^^
	public static final int JUDGE_RULE_NO_AE 	= 1;	// �� ��...�̰� �ӵ��...�̤�
	public static final int JUDGE_RULE_NO_SS 	= 2;	// ����̾��
	
	public static final int JUDGE_WIN			= 3;	// ��� ���� ����� �̰��!! ��ī��~
	public static final int JUDGE_PASS			= 4;	// ��� �����̳� �ϼ�~
	
	
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
// �� �뱹���� ���Ρ����� 15���� �������� ����ϸ�, ���� ��� ���� ����Ѵ�. (�ε��� ���� ���������� ������ �ȴ�.)

// �� ���� ������ �ϰ� �������� �߾�(õ��)���κ��� ����� �д�. �桤�� �� ��� ���� ���γ� ���� �Ǵ� �밢������
//	  �ڱ� ���� 5�� ���޾� ������ �̱��. �̰��� �����̶�� �Ѵ�.

// �� ���� ������ �Ǳ���� ���(߲߲) , ���(����) , ���� �̻��� ���� ���ϴµ�, �̸� �ݼ�(���)��� �Ѵ�.
//	  �̸� ������ ���� ���ϰ� �ȴ�. �ٸ� ���� ���� �ݼ��� �� ���, �̸� �濡�� �˷���� �ϸ�, �̸� �˾�ä��
//	  ���ϰ� ������ ����(��)�� ���� ���� ���� �ݼ��� �ؼҵǰ� ���� ����ȴ�.

// �� �鿡�Դ� �ݼ��� ���� ������, ���� �����̻��� ���� �̱� ������ �Ѵ�.

// �� �� 6 ������ ������ ������ ���� �ִ�.

// �� ���º��� ������ ������ ����.
// 		ù° : ��� ������ ���⸦ �����ϰ� ��밡 �̸� �޾Ƶ鿴�� ���.
// 		��° : ��� ���� ���������� ������ �н��Ͽ��� ���.