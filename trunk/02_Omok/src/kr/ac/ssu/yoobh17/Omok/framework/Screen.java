

package kr.ac.ssu.yoobh17.Omok.framework;


public abstract class Screen {
	
	protected final Game game;
	
	public Screen( Game game ){
		
		this.game = game;
		
	}
	
	public abstract void update	( float deltaTime );	// Data ������Ʈ
	public abstract void present( float deltaTime );	//
	public abstract void pause	();						//
	public abstract void resume	();						//
	public abstract void dispose();						//

}
