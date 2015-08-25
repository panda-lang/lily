package net.wvffle.panda.gui;

import net.dzikoysk.panda.Panda;

public class Version {
	public static boolean toVersion(String v){
		Integer Gver = Integer.valueOf(Panda.PANDA_VERSION.replace(".", ""));
		Integer ver = Integer.valueOf(v.replace(".", ""));
		
		if(Gver > ver) return false;
		
		return true;
	}
}
