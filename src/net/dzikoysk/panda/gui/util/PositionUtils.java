package net.dzikoysk.panda.gui.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

public class PositionUtils {
	
	public static void center(Component c){
		Dimension windowSize = c.getSize();
		Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        c.setLocation(dx, dy);
	}
}
