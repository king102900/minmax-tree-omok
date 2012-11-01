

package kr.ac.ssu.yoobh17.Omok.game;


import kr.ac.ssu.yoobh17.Omok.game.Board.BoardInfor;


public class Point {	
	
	private int	posX;
	private int	posY;

	public Point(){
		
	}
	
	public Point( int posX, int posY ){
		
		this.posX = posX;
		this.posY = posY;
		
	}
	
	public void setPosXY( int posX, int posY ){

		this.posX = posX;
		this.posY = posY;
		
	}
	
	public int getPosX(){
		
		return posX;
	}

	public int getPosY(){
		
		return posY;
	}
	
	public void posXPlus(){
		
		if( BoardInfor.BOARD_SIZE > posX + 1 ){
			
			posX++;
			
		}
	}
	
	public void posXMinus(){
		
		if( -1 < posX - 1 ){
			
			posX--;
			
		}
	}
	
	public void posYPlus(){
		
		if( BoardInfor.BOARD_SIZE > posY + 1 ){
			
			posY++;
			
		}
	}
	
	public void posYMinus(){
		
		if( -1 < posY - 1 ){
			
			posY--;
			
		}
	}

}
