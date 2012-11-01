

package kr.ac.ssu.yoobh17.Omok.game;


import java.util.ArrayList;


public class Board {

	public static class BoardState{

		public static final int OUT_OF_BOARD 	= -1;
		public static final int	EMPTY			= 0;
		public static final int	USER			= 1;
		public static final int	COMPUTER		= 2;

	}
	
	public static class BoardInfor{

		public final static int		BOARD_SIZE			= 19;

		public static final int		STARTING_POINT_X	= 196;
		public static final int		STARTING_POINT_Y	= 32;

		public static final float	ONE_BLOCK_LENGTH	= 22.5F;	// 23 ¶Ç´Â
																	// 22.5F

	}

	private int[][]				boardData;
	private ArrayList<Stone>	stoneData;

	public Board(){
		
		boardData 	= new int[BoardInfor.BOARD_SIZE][BoardInfor.BOARD_SIZE];
		stoneData	= new ArrayList<Stone>();
		
	}
	
	public void init(){

		for( int i = 0; i < BoardInfor.BOARD_SIZE; i++ ){
			
			for( int j = 0; j < BoardInfor.BOARD_SIZE; j++ ){
				
				boardData[i][j] = BoardState.EMPTY;
				
			}
		}
		
		if( !stoneData.isEmpty() ){
			
			stoneData.clear();
			
		}
	}
	
	public void newStone( Point point, int newState ){
		
		int posX = point.getPosX();
		int posY = point.getPosY();
		
		boardData[posX][posY] = newState;
		stoneData.add( new Stone( newState, new Point( posX, posY ) ) );
		
	}
	
	public int getBoardState( Point point ){
		
		return getBoardState( point.getPosX(), point.getPosY() );
	}

	public int getBoardState( int posX, int posY ){
		
		if( ( 0 > posX ) || ( BoardInfor.BOARD_SIZE <= posX ) ||
			( 0 > posY ) || ( BoardInfor.BOARD_SIZE <= posY ) ){
			
			return BoardState.OUT_OF_BOARD;
		}
		
		return boardData[posX][posY];
	}
	
	public int getStoneSize(){
		
		return stoneData.size();
	}
	
	public Stone getStoneData( int index ){
		
		return stoneData.get( index );
	}
	
}
