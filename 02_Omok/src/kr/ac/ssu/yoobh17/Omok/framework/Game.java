

package kr.ac.ssu.yoobh17.Omok.framework;


public interface Game {
	
	public Input 	getInput		();
	public FileIO 	getFileIO		();
	public Graphics getGraphics		();
	public Audio 	getAudio		();
	
	public void 	setScreen		( Screen screen );
	public Screen 	getCurrentScreen();
	public Screen 	getStartScreen	();

}
