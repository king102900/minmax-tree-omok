

package kr.ac.ssu.yoobh17.Omok.screen;


import java.util.List;

import kr.ac.ssu.yoobh17.Omok.Assets;
import kr.ac.ssu.yoobh17.Omok.framework.Game;
import kr.ac.ssu.yoobh17.Omok.framework.Graphics;
import kr.ac.ssu.yoobh17.Omok.framework.Input.TouchEvent;
import kr.ac.ssu.yoobh17.Omok.framework.Screen;


public class MainScreen extends Screen {

	public MainScreen( Game game ) {

		super( game );
		
		Assets.GameScreen.load( game.getGraphics() );

	}

	@Override
	public void update( float deltaTime ) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for ( int i = 0; i < len; i++ ) {

			TouchEvent event = touchEvents.get( i );

		}
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
