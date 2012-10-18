

package kr.ac.ssu.yoobh17.Omok.game;

import kr.ac.ssu.yoobh17.Omok.game.GameBoard.BoardState;


public class Judger {
	
	public static final int JUDGE_RULE_OK 		= 0;
	public static final int JUDGE_RULE_NO_AE 	= 1;
	public static final int JUDGE_RULE_NO_SS 	= 2;
	
	public static final int JUDGE_WIN			= 3;
	public static final int JUDGE_PASS			= 4;
	
	
	public Judger(){
		
	}
	
	public int judgeRule( int[][] boardData, Point point ){
		
		if( BoardState.EMPTY == boardData[point.getPosX()][point.getPosY()] ){
			
			return JUDGE_RULE_OK;
		}
		else{
		
			return JUDGE_RULE_NO_AE;
		}
	}
	
	public int jedgeWinner( int[][] boardData ){
		
		return JUDGE_PASS;
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