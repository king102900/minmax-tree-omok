

package kr.ac.ssu.yoobh17.Omok.screen;


import java.util.List;

import kr.ac.ssu.yoobh17.Omok.Assets;
import kr.ac.ssu.yoobh17.Omok.framework.Game;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;
import kr.ac.ssu.yoobh17.Omok.framework.Screen;
import kr.ac.ssu.yoobh17.Omok.game.GameBoard;


public class MainScreen extends Screen {
	
	private GameBoard gameBoard;

	public MainScreen( Game game ) {

		super( game );
		
		Assets.GameScreen.load( game.getGraphics() );
		
		gameBoard = new GameBoard();

	}

	@Override
	public void update( float deltaTime ) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		gameBoard.update( touchEvents );
		
	}

	@Override
	public void present( float deltaTime ) {

		Graphics graphics = game.getGraphics();
		
		graphics.drawPixmap( Assets.GameScreen.mainScreen, 0, 0 );
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
