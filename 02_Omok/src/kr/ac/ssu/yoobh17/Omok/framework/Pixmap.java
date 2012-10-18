

package kr.ac.ssu.yoobh17.Omok.framework;


import kr.ac.ssu.yoobh17.Omok.framework.Graphics.PixmapFormat;


public interface Pixmap {

	public int 			getWidth();
	public int 			getHeight();

	public PixmapFormat getFormat();

	public void 		dispose();

}
