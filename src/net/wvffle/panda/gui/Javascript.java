package net.wvffle.panda.gui;

public class Javascript {
    public void report(String msg, String url, String lineNumber){
    	System.out.println("[ERROR] Error in "+url+" on line "+lineNumber);
    	System.out.println(msg);
    }
    public void exit() {
    	System.out.println("exit");
    }
}
