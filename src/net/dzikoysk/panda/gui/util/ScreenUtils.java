package net.dzikoysk.panda.gui.util;

import java.awt.GraphicsEnvironment;

public class ScreenUtils {

	public static int getMaxWidth(){
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}
	
	public static int getMaxHeight(){
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
