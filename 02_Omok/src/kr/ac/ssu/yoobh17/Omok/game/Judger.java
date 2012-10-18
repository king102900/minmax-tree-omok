

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